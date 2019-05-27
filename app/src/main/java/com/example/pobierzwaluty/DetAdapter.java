package com.example.pobierzwaluty;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class DetAdapter extends RecyclerView.Adapter {

    //dekalracja listy elementów do wyświetlania
    private ArrayList<curdet> mCurdet = new ArrayList<>();
    //deklaracja modułu recycler view;
    //obecna klasa nie jest pochodna od widoku, więc niezbędne jest pobranie parametrów z klasy Detalis
    private RecyclerView mRecyclerView;

    //klasa będąca pojedynczym modułem widoku recycler view
    private class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView mDay;
        public TextView mMid;
        public TextView mDeff;
        //przypisanie modułów interfejsu z widoku
        public MyViewHolder(View pItem) {
            super(pItem);
            mDay = (TextView) pItem.findViewById(R.id.day);
            mMid = (TextView) pItem.findViewById(R.id.mid);
            mDeff = (TextView) pItem.findViewById(R.id.deff);
        }
    }
    //konstruktor klasy
    public DetAdapter(ArrayList<curdet> pCurrencies, RecyclerView pRecyclerView){
        mCurdet = pCurrencies;
        mRecyclerView = pRecyclerView;
    }
    //utworzenie głównego widoku recycler wiew
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.szczegoly_layout, viewGroup, false);

        return new DetAdapter.MyViewHolder(view);
    }
    //utworzenie widoku-listy recycler view i przekazanie mu parametrów;
    //przekazywanie parametrów wykonywane jest cyklicznie
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {
        //odkodowanie listy i przypisanie ich do elementu widoku;
        //czynność wykonywana jest każdorazowo na nowym elemencie recycler view
        curdet Curdet = mCurdet.get(i);
        ((DetAdapter.MyViewHolder) viewHolder).mDay.setText(Curdet.getDay());
        ((DetAdapter.MyViewHolder) viewHolder).mMid.setText(Curdet.getMid());
        ((DetAdapter.MyViewHolder) viewHolder).mDeff.setText(Curdet.getDeff());
    }
    //funkcja zwracająca ilość elementów
    @Override
    public int getItemCount() {
        return mCurdet.size();
    }
}
