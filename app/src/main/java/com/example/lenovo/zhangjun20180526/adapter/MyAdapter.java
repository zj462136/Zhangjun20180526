package com.example.lenovo.zhangjun20180526.adapter;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.example.lenovo.zhangjun20180526.R;
import com.example.lenovo.zhangjun20180526.bean.BaseBean;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    Context context;
    List<BaseBean.DataBean> data;
    private int HOLDRE1=1;
    private int HOLDRE2=2;
    private int HOLDRE0=0;

    public MyAdapter(Context context, List<BaseBean.DataBean> data) {
        this.context=context;
        this.data=data;
    }
    @Override
    public int getItemViewType(int position) {
        if (position%3==0){
            return HOLDRE0;
        }else if(position%3==1){
            return HOLDRE1;
        } else {
            return HOLDRE2;
        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType==HOLDRE0){
            View view=View.inflate(context, R.layout.item0_layout,null);
            Holder0 holder0=new Holder0(view);
            return holder0;
        }else if (viewType==HOLDRE1){
            View view=View.inflate(context, R.layout.item1_layout,null);
            Holder1 holder1=new Holder1(view);
            return holder1;
        }else if (viewType==HOLDRE2){
            View view=View.inflate(context, R.layout.item2_layout,null);
            Holder2 holder2=new Holder2(view);
            return holder2;
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof Holder0){
            ((Holder0) holder).drawee_view.setImageURI(data.get(position).getProfile_image());
            ((Holder0) holder).text_name.setText(data.get(position).getName());
            ((Holder0) holder).text_time.setText(data.get(position).getPasstime());
        }
        if (data.get(position).getType().equals("41")) {
            if (holder instanceof Holder2) {
                ((Holder2) holder).drawee_view.setImageURI(data.get(position).getProfile_image());
                ((Holder2) holder).text_name.setText(data.get(position).getName());
                ((Holder2) holder).text_time.setText(data.get(position).getPasstime());
                //网络视频
                Uri uri = Uri.parse(data.get(position).getVideouri());
                //设置视频控制器
                ((Holder2) holder).video_View.setMediaController(new MediaController(context));
                //播放完成回调
                ((Holder2) holder).video_View.setOnCompletionListener(new MyPlayerOnCompletionListener());
                //设置视频路径
                ((Holder2) holder).video_View.setVideoURI(uri);
                //开始播放视频
                ((Holder2) holder).video_View.start();
            }
        }
        if (holder instanceof Holder1) {
            ((Holder1) holder).drawee_view.setImageURI(data.get(position).getProfile_image());
            ((Holder1) holder).text_name.setText(data.get(position).getName());
            ((Holder1) holder).text_time.setText(data.get(position).getPasstime());
            ((Holder1) holder).drawee_view1.setImageURI(data.get(position).getImage0());
        }

    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    class Holder0 extends RecyclerView.ViewHolder{
        public SimpleDraweeView drawee_view;
        public TextView text_name;
        public TextView text_time;
        public Holder0(View itemView) {
            super(itemView);
            drawee_view = itemView.findViewById(R.id.drawee_view);
            text_name = itemView.findViewById(R.id.text_name);
            text_time = itemView.findViewById(R.id.text_time);
        }
    }
    class Holder1 extends RecyclerView.ViewHolder{
        public SimpleDraweeView drawee_view;
        public TextView text_name;
        public TextView text_time;
        public SimpleDraweeView drawee_view1;
        public Holder1(View itemView) {
            super(itemView);
            drawee_view = itemView.findViewById(R.id.drawee_view);
            text_name = itemView.findViewById(R.id.text_name);
            text_time = itemView.findViewById(R.id.text_time);
            drawee_view1 = itemView.findViewById(R.id.drawee_view1);
        }
    }
    class Holder2 extends RecyclerView.ViewHolder{
        public SimpleDraweeView drawee_view;
        public TextView text_name;
        public TextView text_time;
        public VideoView video_View;
        public Holder2(View itemView) {
            super(itemView);
            drawee_view = itemView.findViewById(R.id.drawee_view);
            text_name = itemView.findViewById(R.id.text_name);
            text_time = itemView.findViewById(R.id.text_time);
            video_View = itemView.findViewById(R.id.video_View);
        }
    }

    class MyPlayerOnCompletionListener implements MediaPlayer.OnCompletionListener {

        @Override
        public void onCompletion(MediaPlayer mp) {
            Toast.makeText( context, "播放完成了", Toast.LENGTH_SHORT).show();
        }
    }
}
