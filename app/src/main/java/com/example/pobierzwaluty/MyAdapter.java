package com.example.pobierzwaluty;

import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;


public class MyAdapter extends RecyclerView.Adapter {

    //dekalracja listy elementów do wyświetlania
    private ArrayList<currency> mCurrencies = new ArrayList<>();
    //deklaracja interfejsu do obsługi przesyłania danych między obecną klasą a MainActivity
    private OnNoteListener mOnNoteListener;
    //deklaracja modułu recycler view;
    //obecna klasa nie jest pochodna od widoku, więc niezbędne jest pobranie parametrów z klasy MainActivity
    private RecyclerView mRecyclerView;
    //klasa będąca pojedynczym modułem widoku recycler view
    private class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        OnNoteListener mOnNoteListener;

        public TextView mCode;
        public TextView mName;
        public ImageView mIcon;
        public String str;
        //przypisanie modułów interfejsu z widoku
        public MyViewHolder(View pItem, OnNoteListener onNoteListener) {
            super(pItem);

            mCode = (TextView) pItem.findViewById(R.id.currency_code);
            mName = (TextView) pItem.findViewById(R.id.currency_name);
            mIcon = (ImageView) pItem.findViewById(R.id.currency_icon);
            this.mOnNoteListener = onNoteListener;

            itemView.setOnClickListener(this);
        }
        //metoda interfejsu, przez którą przekazuje się kod waluty na którą nacisnął użytkownik
        @Override
        public void onClick(View v) {
            int i = this.getAdapterPosition();
            currency Currency = mCurrencies.get(i);
            str = Currency.getCode();
            mOnNoteListener.onNoteClick(str);
        }
    }
    //konstruktor adaptera
    public MyAdapter(ArrayList<currency> pCurrencies, RecyclerView pRecyclerView, OnNoteListener onNoteListener) {
        mCurrencies = pCurrencies;
        mRecyclerView = pRecyclerView;
        mOnNoteListener = onNoteListener;
    }
    //utworzenie głównego widoku recycler wiew
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, final int i) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.waluty_layout, viewGroup, false);

        return new MyViewHolder(view, mOnNoteListener);
    }
    //utworzenie widoku-listy recycler view i przekazanie mu parametrów;
    //przekazywanie parametrów wykonywane jest cyklicznie
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int i) {
        //odkodowanie listy i przypisanie ich do elementu widoku;
        //czynność wykonywana jest każdorazowo na nowym elemencie recycler view
        final currency Currency = mCurrencies.get(i);
        //String fullcode służy jako składnia kodu i kursu wyświetlająca się na jednym z elementów
        String fullCode = Currency.getCode() + " " + Currency.getMid();
        ((MyViewHolder) viewHolder).mCode.setText(fullCode);
        ((MyViewHolder) viewHolder).mName.setText(Currency.getName());
        ((MyViewHolder) viewHolder).mIcon.setImageResource(Currency.getIcon());
    }
    //funkcja zwracająca ilość elementów
    @Override
    public int getItemCount() {
        return mCurrencies.size();
    }

    //defiicja intrfejsu, używanego do obsługi komunikacji z MainActivity
    public interface OnNoteListener{
        void onNoteClick(String string);
    }

}