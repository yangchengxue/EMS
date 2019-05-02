package com.example.ycx36.ems.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.example.ycx36.ems.R;

import java.util.ArrayList;

public class adapter_CurrentInfo extends RecyclerView.Adapter<adapter_CurrentInfo.ViewHolder>{
    private ArrayList<muc_data> mData;   //数据
    /**定义两个接口*/
    public interface OnItemClickListener {
        void setOnItemClickListener(View view, int position);
    }
    public interface OnLongClickListener {
        void setOnLongClickListener(View view, int position);
    }

    /**声明接口*/
    private OnItemClickListener mOnItemClickListener;
    private OnLongClickListener mOnLongClickListener;

    //创建条目点击的方法，用变量接收一下接口对象
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.mOnItemClickListener=onItemClickListener;

    }
    //创建条目长按的方法，用变量接收一下接口对象
    public void setOnLongClickListener(OnLongClickListener onLongClickListener){
        this.mOnLongClickListener=onLongClickListener;
    }

    /**适配器的构造函数，传入一组数据数组，数据类型为自定义的Food类*/
    public adapter_CurrentInfo(ArrayList<muc_data> data) {
        this.mData = data;
    }

    public void updateData(ArrayList<muc_data> data) {
        this.mData = data;
        notifyDataSetChanged();

    }

    /**这个方法用于创建ViewHolder实例，在这里将item布局加载进来，然后创建一个ViewHolder实例，
     * 并把加载进来的布局传到ViewHolder构造函数当中，最后将ViewHolder实例返回。*/
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_currentinfo, parent, false);
        // 实例化viewholder
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    /**这个方法是用于对RecyclerView子项的数据进行赋值的，会在每个子项被滚动屏幕内的时候执行，
     * 这里我们通过postion参数得到当前项的实例，然后将数据设置到ViewHolder的ImageView和TextView当中。*/
    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        // 绑定数据
        muc_data muc_data = mData.get(position); //通过数组分别创建每个数组元素的实例。
        holder.tv_name.setText(muc_data.getInfoName());
        holder.tv_value.setText(muc_data.getInfoValue());
        holder.standard.setText(muc_data.getInfoStandard());
        /**子项的监听事件*/
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mOnItemClickListener.setOnItemClickListener(holder.itemView,position);//控件和条目下标
            }
        });
        //holder的条目视图长按监听
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mOnLongClickListener.setOnLongClickListener(holder.itemView,position);
                return false;
            }
        });
    }
    /**这个方法用于告诉RecyclerView一共有多少个项，然后直接返回长度。*/
    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }
    /**自己定义的一个内部类，继承自RecyclerView.ViewHolder（适配器名）*/
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_name;
        TextView tv_value;
        TextView standard;
        //内部类构造函数，需要传入一个View参数，这个参数就是RecyclerView子项的最外层布局
        public ViewHolder(View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_value = itemView.findViewById(R.id.tv_value);
            standard = itemView.findViewById(R.id.tv_standard);
        }
    }
    /**删除item的方法*/
    public void removeData(int position) {
        //保证列表有数据，并且最少有一条
        if(mData.size()<2&&mData.size()!=0){
            mData.remove(0);
            notifyDataSetChanged();
        }else if(mData.size()==0){//当列表没有数据提示用户，免得造成系统崩溃
//            Toast.makeText(context,"没数据了",Toast.LENGTH_SHORT).show();
        }else{//更新列表
            mData.remove(position);
            notifyDataSetChanged();
            notifyItemRemoved(position);
            notifyItemRangeChanged(position,mData.size());
        }
    }
}