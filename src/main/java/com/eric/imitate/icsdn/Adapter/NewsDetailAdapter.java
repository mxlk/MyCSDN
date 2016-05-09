//package com.eric.imitate.icsdn.Adapter;
//
//import android.content.Context;
//import android.graphics.Bitmap;
//import android.support.v7.widget.RecyclerView;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import com.eric.imitate.icsdn.Bean.News;
//import com.eric.imitate.icsdn.R;
//import com.nostra13.universalimageloader.core.DisplayImageOptions;
//import com.nostra13.universalimageloader.core.ImageLoader;
//import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
//import com.nostra13.universalimageloader.core.assist.ImageScaleType;
//import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by Administrator on 2015/12/17.
// */
//public class NewsDetailAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
//
//    private List<News> news  = new ArrayList<News>();
//    private ImageLoader imageLoader = ImageLoader.getInstance();
//    private LayoutInflater inflater;
//    private DisplayImageOptions options;
//
//    public NewsDetailAdapter(Context context,  List<News> details){
//        this.news = details;
//        inflater = LayoutInflater.from(context);
//        imageLoader.init(ImageLoaderConfiguration.createDefault(context));
//        options = new DisplayImageOptions.Builder().showStubImage(R.drawable.images)
//                .showImageForEmptyUri(R.drawable.images).showImageOnFail(R.drawable.images).cacheInMemory()
//                .cacheOnDisc().imageScaleType(ImageScaleType.EXACTLY).bitmapConfig(Bitmap.Config.RGB_565)
//                .displayer(new FadeInBitmapDisplayer(300)).build();
//    }
//
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        View view = inflater.from(parent.getContext()).inflate(R.layout.news_detail, parent, false);
//        NewsViewHolder viewHolder = new NewsViewHolder(view);
//        return viewHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        News data = news.get(position);
//        switch (data.getType()){
//            case News.NewsType.TITLE:
//                ((NewsViewHolder)holder).content.setText(data.getTitle());
//                break;
//            case News.NewsType.SUMMARY:
//                ((NewsViewHolder)holder).content.setText(data.getSummary());
//                break;
//            case News.NewsType.CONTENT:
//                ((NewsViewHolder)holder).content.setText(data.getContent());
//                break;
//            case News.NewsType.IMG:
//                ((NewsViewHolder)holder).image.setVisibility(View.VISIBLE);
//                imageLoader.displayImage(data.getImgLink(),((NewsViewHolder)holder).image, options);
//                break;
//            case News.NewsType.BOLD_TITLE:
//                ((NewsViewHolder)holder).content.setText(data.getBoldTitle());
//
//        }
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return news.size();
//    }
//    public void addNewses(List<News> news) {
//        news.addAll(news);
//        this.notifyDataSetChanged();
//    }
//
//    public class NewsViewHolder extends RecyclerView.ViewHolder{
//
//        private TextView content;
//        private ImageView image;
//        public NewsViewHolder(View itemView) {
//            super(itemView);
//            content = (TextView) itemView.findViewById(R.id.content);
//            image = (ImageView) itemView.findViewById(R.id.image);
//        }
//    }
//}
