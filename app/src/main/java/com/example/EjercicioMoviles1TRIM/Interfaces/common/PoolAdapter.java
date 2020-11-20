package com.example.EjercicioMoviles1TRIM.Interfaces.common;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.EjercicioMoviles1TRIM.Interfaces.domain.PoolList;
import com.example.EjercicioMoviles1TRIM.R;

import java.util.List;

public class PoolAdapter extends BaseAdapter {

    private Context mContext;
    private List<PoolList> mPoolLists;
    private int layaout;

    public PoolAdapter(Context mContext, List<PoolList> mPoolLists, int layaout) {
        this.mContext = mContext;
        this.mPoolLists = mPoolLists;
        this.layaout = layaout;
    }

    @Override
    //devuelve el tamaño
    public int getCount() {
        return mPoolLists.size();
    }
//devuelve la posicion del item
    @Override
    public Object getItem(int i) {
        return mPoolLists.get(i);
    }
// devuelve el item de la posicion i
    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View v, ViewGroup viewGroup) {
        if (v == null){
            //layout inflater mete los datos en el listview
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = layoutInflater.inflate(R.layout.list_pool, null);
        }
        TextView mTextView = v.findViewById(R.id.txtPool);
        mTextView.setText(mPoolLists.get(i).getTitle());
        return  v;
    }
}
