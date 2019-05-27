package com.example.pobierzwaluty;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;

import android.widget.TextView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

import java.util.ArrayList;

//główny widok aplikacji
public class MainActivity extends AppCompatActivity implements MyAdapter.OnNoteListener{

    //String będzie przechowywał 3-literowy kod waluty, który zostanie zastosowany w widoku Detalis
    public String detalis = new String();
    // Task niezbędny do połączenie z internetem w celu pobrania danych
    MyTask myAsyncTask;
    //budowa widoku głównego
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //wykonanie procedury AsyncTask
       try {
           myAsyncTask = new MyTask();
           myAsyncTask.execute(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //podanie niestandardowego ActionBara
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    //interfejs pełniący 2 funkcje:
    //przywołuje kod elementu z kalsy MyAdapter;
    //wywołuje nowe okno widoku Detalis wraz z przekazaniem mu parametru 3-literowego kodu waluty
    public void onNoteClick(String string) {
        //kod waluty
        detalis = string;
        Intent mintent = new Intent(MainActivity.this, Detalis.class);
        //przesłanie parametru do wykonania w następnym widoku
        mintent.putExtra("detalis", detalis);
        startActivity(mintent);
    }
    //klasa obsługująca połączenie z internetem
    private static class MyTask extends AsyncTask<Object, Void, String> {

        MainActivity activity;
        @Override
        protected String doInBackground(Object... params) {
            activity = (MainActivity)params[0];

            try {
                //tworzenie stringbuildera w celu obsługi bufferreadera
                StringBuilder sb = new StringBuilder();
                URL url = new URL("https://api.nbp.pl/api/exchangerates/tables/A/?format=json/");

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
            Root[] waluty = gson.fromJson(str, Root[].class);
            int i;
            //tworzenie listy elementów z parametrami do wyświetlania
            ArrayList<currency> currencies = new ArrayList<>();
             for (i = 0; i < waluty[0].getRates().length; i++) {
                 String kod = waluty[0].getRates()[i].getCode();
                 if (kod.equals("EUR") || kod.equals("USD") || kod.equals("CHF") || kod.equals("GBP")) {
                     int iconId = activity.getResources().getIdentifier(kod.toLowerCase(), "drawable", activity.getPackageName());
                     currency cur = new currency(waluty[0].getRates()[i].getCode(), waluty[0].getRates()[i].getMid(), waluty[0].getRates()[i].getCurrency(),iconId);
                     currencies.add(cur);
                    }
                }
                //podanie aktualnej daty w widoku
                TextView data = (TextView) activity.findViewById(R.id.textView2);
                data.setText(waluty[0].getEffectiveDate());
                //deklaracja modułu recycler view
                RecyclerView recyclerView = (RecyclerView) activity.findViewById(R.id.currencies);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(activity));
                recyclerView.setItemAnimator(new DefaultItemAnimator());
                //przekazanie listy elementów do adaptera
            recyclerView.setAdapter(new MyAdapter(currencies, recyclerView, activity));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}


