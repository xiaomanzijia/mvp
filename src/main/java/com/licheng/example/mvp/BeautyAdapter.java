package com.licheng.example.mvp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import java.util.List;


/**
 * Created by licheng on 4/2/16.
 */
public class BeautyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Beauty> beautyList;

    private Context mContext;

    private final int TYPE_FOOTER = 1;
    private final int TYPE_NORMAL = 0;

    private Boolean isShowFooter = true;

    public Boolean getShowFooter() {
        return isShowFooter;
    }

    public void setShowFooter(Boolean showFooter) {
        isShowFooter = showFooter;
    }

    public BeautyAdapter(List<Beauty> beautyList, Context mContext) {
        this.beautyList = beautyList;
        this.mContext = mContext;
    }

    private onItenClickListener listener;

    public void setListener(onItenClickListener listener) {
        this.listener = listener;
    }

    //实现点击
    public interface onItenClickListener{
        void onItemClick(View view, int position);
        void onItemLongClick(View view, int position);
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(viewType == TYPE_NORMAL) {
            ItemViewHolder holder = new ItemViewHolder(LayoutInflater.from(mContext)
                    .inflate(R.layout.shop_item, parent, false));
            return holder;
        }
        else if(viewType == TYPE_FOOTER){
            View footerview = LayoutInflater.from(mContext).inflate(R.layout.footerview,parent,false);
            return new FooterViewHolder(footerview);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof ItemViewHolder){
            ((ItemViewHolder) holder).reportshopname.setText(beautyList.get(position).getDescription());
            ((ItemViewHolder) holder).reportnums.setText(beautyList.get(position).getDescription());
            ((ItemViewHolder) holder).address.setText(beautyList.get(position).getDescription());
            ((ItemViewHolder) holder).distance.setText(beautyList.get(position).getDescription());

        }
        else if(holder instanceof FooterViewHolder){
            ((FooterViewHolder) holder).textFooter.setText("加载中...");
        }

        //点击事件回调
        if(listener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getAdapterPosition();
                    listener.onItemClick(holder.itemView,pos);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int pos = holder.getAdapterPosition();
                    listener.onItemLongClick(holder.itemView,pos);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        int begin = isShowFooter?1:0;
        if(beautyList == null) {
            return begin;
        }
        return beautyList.size() + begin;
    }

    @Override
    public int getItemViewType(int position) {

        if(!isShowFooter){
            return TYPE_NORMAL;
        }

        if(position + 1 == getItemCount()){
            return TYPE_FOOTER;
        }
        else {
            return TYPE_NORMAL;
        }
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        TextView reportshopname;
        TextView reportnums;
        TextView address;
        TextView distance;

        public ItemViewHolder(View itemView) {
            super(itemView);
            reportshopname = (TextView) itemView.findViewById(R.id.textReportShopName);
            reportnums = (TextView) itemView.findViewById(R.id.textReportNums);
            address = (TextView) itemView.findViewById(R.id.textReportShopAddress);
            distance = (TextView) itemView.findViewById(R.id.textDistance);
        }
    }

    public class FooterViewHolder extends RecyclerView.ViewHolder{
        TextView textFooter;
        public FooterViewHolder(View itemView) {
            super(itemView);
            textFooter = (TextView) itemView.findViewById(R.id.textFooter);
        }
    }


}
