package com.hyosung.parallaxsample;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SBKim on 2016-06-13.
 */
public class ItemListAdapter extends BaseAdapter implements ItemListRecyclerViewAdapter.OnItemClickListener{
    private ArrayList<ListViewItem> listViewItemList = new ArrayList<ListViewItem>() ;
    private OnRecyclerViewItemClickListener onRecyclerViewItemClickListener;
    TextView title;
    RecyclerView itemRecyclerView;
    LinearLayoutManager mLayoutManager;
    ItemListRecyclerViewAdapter itemListRecyclerViewAdapter;
    int resource;

    public void setSelectItemClickListener(OnRecyclerViewItemClickListener onRecyclerViewItemClickListener) {
        this.onRecyclerViewItemClickListener = onRecyclerViewItemClickListener;
    }

    public interface OnRecyclerViewItemClickListener {
        public void onRecyclerViewItemSelect(View v, int position);
    }

    public ItemListAdapter(int resource) {
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Context context = parent.getContext();
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resource, parent, false);
        }

        title = (TextView) convertView.findViewById(R.id.txt_title);
        title.setText(listViewItemList.get(position).getTitle());
        itemRecyclerView = (RecyclerView) convertView.findViewById(R.id.recycler_item);
        itemRecyclerView.setHasFixedSize(true);
        itemRecyclerView.setHorizontalFadingEdgeEnabled(true);
        itemRecyclerView.setFadingEdgeLength(10);
        mLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, true);
        mLayoutManager.setStackFromEnd(true);
        itemRecyclerView.setLayoutManager(mLayoutManager);

        ArrayList<ListViewItem> sampleList = new ArrayList<>();
        for (int i = 0; i < 20; i++) {
            ListViewItem item = new ListViewItem();
            item.setImage(listViewItemList.get(position).getImage());
            item.setTitle("Item " + i);
            sampleList.add(item);
        }

        itemListRecyclerViewAdapter = new ItemListRecyclerViewAdapter(context, sampleList);
        itemListRecyclerViewAdapter.setSelectItemClickListener(this);
        itemRecyclerView.setAdapter(itemListRecyclerViewAdapter);
        return convertView;
    }

    @Override
    public void onSelectItem(View v, int position) {
        onRecyclerViewItemClickListener.onRecyclerViewItemSelect(v, position);
    }

    @Override
    public Object getItem(int position) {
        return listViewItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getCount() {
        return listViewItemList.size() ;
    }

    public void addItem(Drawable image, String title) {
        ListViewItem item = new ListViewItem();

        item.setImage(image);
        item.setTitle(title);

        listViewItemList.add(item);
    }
}
