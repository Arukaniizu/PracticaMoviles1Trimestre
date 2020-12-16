package com.example.EjercicioMoviles1TRIM.Interfaces.common;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.EjercicioMoviles1TRIM.Interfaces.domain.Lugar;
import com.example.EjercicioMoviles1TRIM.R;

import java.util.List;

public class PoliAdapter extends BaseAdapter {
    private Context mContext;
    private List<Lugar> mLugarList;

    public PoliAdapter(Context mContext, List<Lugar> mLugarList) {
        this.mContext = mContext;
        this.mLugarList = mLugarList;
    }
    @Override
    public int getCount() {
        return mLugarList.size();
    }

    @Override
    public Object getItem(int i) {
        return mLugarList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            //si es null referencia el view al list_pokemon.xml
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.list_poli, null);
        }
        TextView mTextView = view.findViewById(R.id.txtPoli);
        //mTextView.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_baseline_pool_24, 0, 0, 0);
        // + "\n" + mPiscinaList.get(i).getLocation().getLatitude() + "\n" + mPiscinaList.get(i).getLocation().getLongitude()
        mTextView.setText(mLugarList.get(i).getTitle());
        return view;
    }
}
