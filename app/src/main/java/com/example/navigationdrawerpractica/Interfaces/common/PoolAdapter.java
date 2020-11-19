package com.example.navigationdrawerpractica.Interfaces.common;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.navigationdrawerpractica.Interfaces.domain.PoolList;
import com.example.navigationdrawerpractica.R;

import java.util.List;

public class PoolAdapter extends BaseAdapter {

    private Context mContext;
    private List<PoolList> mPoolLists;

    public PoolAdapter(Context mContext, List<PoolList> mPoolLists) {
        this.mContext = mContext;
        this.mPoolLists = mPoolLists;
    }

    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View v, ViewGroup viewGroup) {
        if (v == null){
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = layoutInflater.inflate(R.layout.list_pool, null);
        }
        TextView mTextView = v.findViewById(R.id.txtPool);
        mTextView.setText(mPoolLists.get(i).getTitle());
        return  v;
    }
}
