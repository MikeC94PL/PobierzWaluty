package com.example.pobierzwaluty;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;

public class Detalis extends AppCompatActivity {

    Intent intent = getIntent();
    //tutaj pobiera się parametr z poprzedniego okna; jest nią kod waluty
    public String det = intent.getStringExtra("detalis");
    //element pokazujący wybraną walutę
    TextView tytul;
    //wywołanie obiektu AsyncTask
    DetTask detAsyncTask;
    //budowa widoku szczegółów
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalis);

        tytul = findViewById(R.id.tytul);
        tytul.setText(det);

        try {
            detAsyncTask = new DetTask();
            detAsyncTask.execute(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class DetTask extends AsyncTask<Object, Void, String> {
        //http://api.nbp.pl/api/exchangerates/rates/a/gbp/last/14/?format=json
        Detalis detalis;

        @Override
        protected String doInBackground(Object... params) {
            detalis = (Detalis)params[0];

            try {
                //tworzenie stringbuildera w celu obsługi bufferreadera
                StringBuilder sb = new StringBuilder();
                URL url = new URL("http://api.nbp.pl/api/exchangerates/rates/a/"+det+"/last/31/?format=json");

                //tworzenie bufferreadera w celu obsługi strumienia danych wejściowych
                BufferedReader in;
                in = new BufferedReader(
                        new InputStreamReader(
                                url.openStream()));
                //wczytanie modułów do StringBuildera
                //pętla warunkowa jest niezbędna do zatrzymania pobierania kiedy ciąg jest pusty
                String inputLine;
                while ((inputLine = in.readLine()) != null)
                    sb.append(inputLine);
                in.close();
                //zwrócenie StringBuildera do metody doInBackground
                return sb.toString();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
        //metoda wykonywana po zakończeniu poprzedniej metody;
        //jej celem jest wykonanie listy elemenyów do wyświetlania;
        //finalnie wywołuje adapter recycler view przekazując mu wygenerowaną listę,
        //bazującą na owej przefiltrowanej tablicy
        @Override
        protected void onPostExecute(String str) {

            try {
                //deklaracja modułu gson będącego tablicą elementów do wyświetlania
                Gson gson = new GsonBuilder().create();
                //tworzenie tablicy elementów i przekazanie jej stringa pozyskanego w metodzie doInBackground
                RootDet szczegoly = gson.fromJson(str, RootDet.class);
                int i;
                //tworzenie listy elementów z parametrami do wyświetlania
                ArrayList<curdet> curdets = new ArrayList<>();
                    for(i=30; i > 0; i--)
                        {
                            int ii = i-1;
                            curdet cur = new curdet(szczegoly.getRates()[i].getEffectiveDate(), szczegoly.getRates()[i].getMid(), szczegoly.getRates()[ii].getMid());
                            curdets.add(cur);
                        }
                    //deklaracja modułu recycler view
                    RecyclerView recyclerView = detalis.findViewById(R.id.detalis);
                    recyclerView.setLayoutManager(new LinearLayoutManager(detalis));
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                //przekazanie listy elementów do adaptera
                recyclerView.setAdapter(new DetAdapter(curdets, recyclerView));
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
