package com.example.navigationdrawerpractica.Interfaces.common;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.navigationdrawerpractica.Interfaces.domain.PokemonList;
import com.example.navigationdrawerpractica.R;

import java.util.List;

public class PokemonAdapter extends BaseAdapter {

    private Context mContext;
    private List<PokemonList> mPokemon;

    public PokemonAdapter(Context mContext, List<PokemonList> mPokemon) {
        this.mContext = mContext;
        this.mPokemon = mPokemon;
    }

    @Override
    public int getCount() {
        return mPokemon.size();
    }

    @Override
    public Object getItem(int i) {
        return mPokemon.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View v, ViewGroup parent) {

        if (v == null){
            LayoutInflater layoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = layoutInflater.inflate(R.layout.list_pokemon, null);
        }
        TextView mTextView = v.findViewById(R.id.txtPokemon);
        mTextView.setText(mPokemon.get(i).getName());
        return  v;
    }
}
