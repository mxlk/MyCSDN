package com.eric.imitate.icsdn.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.eric.imitate.icsdn.Adapter.NewsItemAdapter;
import com.eric.imitate.icsdn.Bean.CommonException;
import com.eric.imitate.icsdn.Bean.Constants;
import com.eric.imitate.icsdn.Bean.NewsItem;
import com.eric.imitate.icsdn.Biz.NewsItemBiz;
import com.eric.imitate.icsdn.NewsDetailActivity;
import com.eric.imitate.icsdn.R;
import com.eric.imitate.icsdn.Utils.NetUtils;
import com.eric.imitate.icsdn.assistant.DividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/11/15.
 */
@SuppressLint("ValidFragment")
public class HotBlogFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener{
//    默认的newsType
    private int newsType = Constants.NEWS_TYPE_YEJIE;
//    当前页面
    private int currentPage = 1;
//    处理新闻的业务
    private NewsItemBiz newsItemBiz;

//    数据适配器
    private NewsItemAdapter newsItemAdapter;

    private List<NewsItem> mDatas = new ArrayList<NewsItem>();

    @SuppressLint("ValidFragment")
    public HotBlogFragment(int newsType){
        this.newsType = newsType;
        newsItemBiz = new NewsItemBiz();
    }
    private SwipeRefreshLayout swipeRefreshLayout;

    private RecyclerView recyclerView;

    private LinearLayoutManager layoutManager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.show_news_fragment, container,false);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swiperefresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorBlack, R.color.colorAccent, R.color.colorPrimary, R.color.colorPrimaryDark);
//        swipeRefreshLayout.setProgressViewOffset(false, 0, (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24, getResources().getDisplayMetrics()));
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview);
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        new LoadDatasTask().execute(2);

        newsItemAdapter = new NewsItemAdapter(getActivity(),mDatas);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(newsItemAdapter);
        newsItemAdapter.setOnItemClickListener(new NewsItemAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(View view, int position) {
                Intent intent = new Intent();
                intent.setClass(getActivity(), NewsDetailActivity.class);
                intent.putExtra(NewsDetailActivity.URLSTR, mDatas.get(position).getLink());
                intent.putExtra(NewsDetailActivity.TITLE, mDatas.get(position).getTitle());
                startActivity(intent);
                Toast.makeText(getActivity(), "datas =" + position + "    " + mDatas.size(), Toast.LENGTH_SHORT).show();
//                mDatas.get(position).getTitle()
            }
        });

        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener(){
            int lastVisibleItem;
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = layoutManager.findLastVisibleItemPosition();
            }

            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if(newState == RecyclerView.SCROLL_STATE_IDLE && lastVisibleItem + 1 == newsItemAdapter.getItemCount()){
                    Log.d("LAST", "lastVisible" + lastVisibleItem);
                    Log.d("LAST", "count = " + newsItemAdapter.getItemCount());
                    new LoadDatasTask().execute(1);
                }
            }
        });
        return view;
    }

    @Override
    public void onRefresh() {
        if(NetUtils.checkNet(getActivity())) {
            new LoadDatasTask().execute(0);
        }else{
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    class LoadDatasTask extends AsyncTask<Integer, Void, Integer> {
        @Override
        protected Integer doInBackground(Integer... params) {
            switch (params[0]) {
                case 0:// new
                    if(NetUtils.checkNet(getActivity())) {
                        try {
                            List<NewsItem> newsItems = newsItemBiz.getNewsItem(newsType, 1);
                            Log.d("TAG", "看看你长什么样" + newsItems);
                            mDatas = newsItems;
                        } catch (CommonException e) {
                            e.printStackTrace();
                        }
                        return 0;
                    }else{
                        return -1;
                    }
                case 1://more
                    if(NetUtils.checkNet(getActivity())){
                    try {
                        currentPage = currentPage + 1;

                        List<NewsItem> newsItems = newsItemBiz.getNewsItem(newsType, currentPage);
//                        newsItemAdapter.addAll(newsItems);
                        mDatas.addAll(newsItems);
                    } catch (CommonException e) {
                        e.printStackTrace();
                        currentPage -= 1;
                    }
                    return 1;
                    }else{
                        return -1;
                    }
                case 2:
                    if(NetUtils.checkNet(getActivity())) {
                        try {
                            List<NewsItem> newsItems = newsItemBiz.getNewsItem(newsType, 1);
                            Log.d("TAG", "看看你长什么样" + newsItems);
                            mDatas = newsItems;
                        } catch (CommonException e) {
                            e.printStackTrace();
                        }
                        return 2;
                    }else{
                        return -1;
                    }
            }
            return -1;
        }
        @Override
        protected void onPostExecute(Integer result) {
            switch (result) {
                case 0:
                    swipeRefreshLayout.setRefreshing(false);
                    newsItemAdapter.reAdd(mDatas);
                    break;
                case 1:
                    newsItemAdapter.addAll(mDatas);
                    break;
                case 2:
                    newsItemAdapter.addAll(mDatas);
                    break;
            }
        }
    }
}
