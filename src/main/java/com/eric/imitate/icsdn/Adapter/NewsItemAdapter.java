package com.eric.imitate.icsdn.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.eric.imitate.icsdn.Bean.NewsItem;
import com.eric.imitate.icsdn.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.util.List;

/**
 * Created by Administrator on 2015/11/17.
 */
public class NewsItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int TYPE_ITEM = 0;
    private static final int TYPE_FOOTER = 1;
    private List<NewsItem> mDatas;
    private LayoutInflater inflater;
    private ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options;
    private OnItemClickListener mListener;
    public NewsItemAdapter(Context context , List<NewsItem> datas) {
        this.mDatas = datas;
        inflater = LayoutInflater.from(context);
        imageLoader.init(ImageLoaderConfiguration.createDefault(context));
        options = new DisplayImageOptions.Builder().showStubImage(R.drawable.images).showImageForEmptyUri(R.drawable.images).showImageOnFail(R.drawable.images)
                .cacheInMemory().cacheOnDisc().displayer(new RoundedBitmapDisplayer(20)).displayer(new FadeInBitmapDisplayer(3000)).build();
    }



    public interface OnItemClickListener{
        void OnItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.mListener = onItemClickListener;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == TYPE_ITEM) {
            View itemView = inflater.from(parent.getContext()).inflate(R.layout.show_news_list_item, parent, false);
            MyViewHolder viewHolder = new MyViewHolder(itemView);
            return viewHolder;
        }else if(viewType == TYPE_FOOTER){
            View footerView = inflater.from(parent.getContext()).inflate(R.layout.footer,parent,false);
            FooterViewHolder footerViewHolder = new FooterViewHolder(footerView);
            return footerViewHolder;
        }
        return null;
    }

    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
            if (holder instanceof MyViewHolder) {
                NewsItem newsItem = mDatas.get(position);
                ((MyViewHolder) holder).news_title.setText(newsItem.getTitle());
                ((MyViewHolder) holder).news_content.setText(newsItem.getContent());
                if (newsItem.getImgLink() != null) {
                    ((MyViewHolder) holder).news_img.setVisibility(View.VISIBLE);
                    imageLoader.displayImage(newsItem.getImgLink(), ((MyViewHolder) holder).news_img, options);
                } else {
                    ((MyViewHolder) holder).news_img.setVisibility(View.GONE);
                }
                ((MyViewHolder) holder).news_date.setText(newsItem.getDate());
                if(mListener != null){
                        ((MyViewHolder) holder).item.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mListener.OnItemClick(v, position);
                        }
                    });
                }
            }

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        if(position + 1== getItemCount()){
            return TYPE_FOOTER;
        }else{
            return TYPE_ITEM;
        }
    }

    @Override
    public int getItemCount() {
        int d = 0;
        d = mDatas.size();
        Log.d("TAG", "data.size = " + d);
        return mDatas.size() + 1/*FooterView*/;
    }

//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        ViewHolder viewHolder = null;
//        if(convertView == null){
//            convertView = inflater.inflate(R.layout.show_news_list_item, null);
//            viewHolder = new ViewHolder();
//            viewHolder.news_title = (TextView) convertView.findViewById(R.id.news_title);
//            viewHolder.news_img = (ImageView) convertView.findViewById(R.id.news_img);
//            viewHolder.news_content = (TextView) convertView.findViewById(R.id.news_content);
//            viewHolder.news_date = (TextView) convertView.findViewById(R.id.news_date);
//
//            convertView.setTag(viewHolder);
//        }else{
//            viewHolder = (ViewHolder) convertView.getTag();
//        }
//
//        NewsItem newsItem = mDatas.get(position);
//
//        viewHolder.news_title.setText(newsItem.getTitle());
//        viewHolder.news_content.setText(newsItem.getContent());
//        viewHolder.news_date.setText(newsItem.getDate());
//        if(newsItem.getImgLink() != null){
//            viewHolder.news_img.setVisibility(View.VISIBLE);
//            imageLoader.displayImage(newsItem.getImgLink(), viewHolder.news_img, options);
//        }else{
//            viewHolder.news_img.setVisibility(View.GONE);
//        }
//        return convertView;
//    }

//    private final class ViewHolder{
//        TextView news_title;
//        ImageView news_img;
//        TextView news_content;
//        TextView news_date;
//    }
    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView news_title;
        ImageView news_img;
        TextView news_content;
        TextView news_date;
        LinearLayout item;

        public MyViewHolder(View itemView) {
            super(itemView);
            news_title = (TextView) itemView.findViewById(R.id.news_title);
            news_date = (TextView) itemView.findViewById(R.id.news_date);
            news_img = (ImageView) itemView.findViewById(R.id.news_img);
            news_content = (TextView) itemView.findViewById(R.id.news_content);
            item = (LinearLayout) itemView.findViewById(R.id.item);
        }

    @Override
    public void onClick(View v) {
        mListener.OnItemClick(v,getPosition());
    }
}
    public class FooterViewHolder extends RecyclerView.ViewHolder{

        public FooterViewHolder(View itemView) {
            super(itemView);
        }
    }
    public void reAdd(List<NewsItem> mDatas) {
        this.mDatas.clear();
//        Log.d("TAG", "size = " + mDatas.size());
        this.mDatas.addAll(mDatas);
        this.notifyDataSetChanged();
    }
    public void addAll(List<NewsItem> mDatas) {
        this.mDatas.clear();
        this.mDatas.addAll(mDatas);
        this.notifyDataSetChanged();
    }
}
