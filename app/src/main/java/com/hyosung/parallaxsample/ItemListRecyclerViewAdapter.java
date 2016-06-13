package com.hyosung.parallaxsample;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sbkim on 2016. 6. 13..
 */
public class ItemListRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private OnItemClickListener onItemClickListener;

    private ArrayList<ListViewItem> datas;

//    Context context;

    public void setSelectItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        public void onSelectItem(View v, int position);
    }

    public ItemListRecyclerViewAdapter(Context context, ArrayList<ListViewItem> list) {
        this.datas = list;
//        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.sample_item, parent, false);
        return new SampleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        SampleViewHolder sampleViewHolder = (SampleViewHolder) viewHolder;
        ImageView itemImage = sampleViewHolder.itemImage;
        TextView itemName = sampleViewHolder.itemName;
        itemImage.setImageDrawable(datas.get(position).getImage()); // datas.get(position).
        itemName.setText(datas.get(position).getTitle()); // datas.get(position).
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    private class ItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ItemViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(onItemClickListener != null) {
                onItemClickListener.onSelectItem(v, getPosition());
            }
        }
    }

//    public void refreshAccountData() {
//        ArrayList<AccountData> accountDatas = new ArrayList<AccountData>();
//        AccountSQLiteHandler accountSQLiteHandler = new AccountSQLiteHandler(mContext);
//        Cursor cursor = accountSQLiteHandler.selectAccount();
//        Constants.CURRENT_ACCOUNT_COUNT = cursor.getCount();
//        if (Constants.CURRENT_ACCOUNT_COUNT != 0) {
//            while (cursor.moveToNext()) {
//                String num = cursor.getString(cursor.getColumnIndex("accountnum"));
//                String name = cursor.getString(cursor.getColumnIndex("accountname"));
//
//                AccountData account = new AccountData();
//                account.setAccountNum(num);
//                account.setAccountName(name);
//                accountDatas.add(account);
//            }
//        }
//
//        cursor.close();
//        accountSQLiteHandler.closeAccount();
//
//        this.accountDatas.clear();
//        this.accountDatas = accountDatas;
//        notifyDataSetChanged();
//    }

    public class SampleViewHolder extends ItemViewHolder {
        ImageView itemImage;
        TextView itemName;

        public SampleViewHolder(View itemView) {
            super(itemView);
            itemImage = (ImageView) itemView.findViewById(R.id.iv_sample);
            itemName = (TextView) itemView.findViewById(R.id.txt_item_name);
        }
    }
}
