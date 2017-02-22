package com.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.recyclerviewdeno.R;

import java.util.ArrayList;
import java.util.List;

/**
 * @className: RecyclerViewAdapter
 * @classDescription: RecyclerView数据适配器
 * @author: miao
 * @createTime: 2017年2月21日
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    //数据源
    private List<String> mData;
    //随机改变一个mHeight值 用于演示StaggeredGridLayout(瀑布流)布局管理器
    private List<Integer> mHeight;
    //条目点击监听
    private onItemClickListener mOnItemClickListener;
    //条目长按监听
    private onItemLongClickListener mItemLongClickListener;
    //计数
    private int count =0;

    //构造
    public RecyclerViewAdapter(List<String> mData){
        this.mData = mData;
        mHeight = new ArrayList<Integer>();
        //随机改变一个mHeight值 用于演示StaggeredGridLayout(瀑布流)布局管理器
        for (int i = 0; i < mData.size(); i++)
        {
            mHeight.add((int) (100 + Math.random() * 300));
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.list_item.setText(mData.get(position));

        //绑定数据的同时，修改每个ItemView的高度 用于演示StaggeredGridLayout(瀑布流)布局管理器
        //ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        //lp.height = mHeight.get(position);
        //holder.itemView.setLayoutParams(lp);

        //Item点击事件监听
        if(mOnItemClickListener != null){
            holder.list_item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mOnItemClickListener.onItemClick(holder.list_item,holder.getAdapterPosition());
                }
            });
        }
        //Item长按事件监听
        if(mItemLongClickListener != null){
            holder.list_item.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    mItemLongClickListener.onItemLongClick(holder.list_item,holder.getAdapterPosition());
                    //表示消费了此事件，不会传递下去
                    return true;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private TextView list_item;
        public ViewHolder(View itemView) {
            super(itemView);
            list_item = (TextView) itemView.findViewById(R.id.list_item);
        }
    }

    /**
     * Item点击事件监听interface
     * @author miao
     * @createTime 2017年2月22日
     * @lastModify 2017年2月22日
     * @param
     * @return
     */
    public interface onItemClickListener{
        void onItemClick(View view,int postion);
    }

    /**
     * Item长按事件监听interface
     * @author miao
     * @createTime 2017年2月22日
     * @lastModify 2017年2月22日
     * @param
     * @return
     */
    public interface onItemLongClickListener{
        void onItemLongClick(View view,int postion);
    }

    /**
     * 设置点击事件监听
     * @author miao
     * @createTime 2017年2月22日
     * @lastModify 2017年2月22日
     * @param
     * @return
     */
    public void setOnItemClickListener(onItemClickListener itemClickListener){
        this.mOnItemClickListener = itemClickListener;
    }

    /**
     * 设置长按事件监听
     * @author miao
     * @createTime 2017年2月22日
     * @lastModify 2017年2月22日
     * @param
     * @return
     */
    public void setOnItemLongClickListener(onItemLongClickListener onItemLongClickListener){
        this.mItemLongClickListener = onItemLongClickListener;
    }

    /**
     * 移除某项数据
     * @author miao
     * @createTime 2017年2月22日
     * @lastModify 2017年2月22日
     * @param
     * @return
     */
    public void removeData(int position) {
        mData.remove(position);
        notifyItemRemoved(position);
    }

    /**
     * 在某位置新增一项
     * @author miao
     * @createTime 2017年2月22日
     * @lastModify 2017年2月22日
     * @param
     * @return
     */
    public void addData(int position){
        mData.add(position,"我是新增的");
        notifyItemInserted(position);
    }

    /**
     * 更改某个位置的数据
     * @author miao
     * @createTime 2017年2月22日
     * @lastModify 2017年2月22日
     * @param
     * @return
     */
    public void changeData(int position){
        mData.set(position,"Item改变了"+ count++);
        notifyItemChanged(position);
    }
}
