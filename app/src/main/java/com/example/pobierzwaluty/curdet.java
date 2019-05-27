package com.example.pobierzwaluty;

import java.text.DecimalFormat;

//klasa służy jako moduł listy DetAdapter widoku Detalis
public class curdet {
    //dzień
    String mDay;
    //kurs
    double mMid;
    //różnica w kursie międzu poprzednim dniem
    String mDeff;
    //konstruktor pobiera dzień, kurs, oraz kurs poprzedniego elementu listy
    public curdet(String day,double mid, double deff) {
        //wyliczenie proporcjonalniej różnicy w kursie i wyświetlenie jej w procentach
        double d = (mid / deff - 1.0) * 100;
        DecimalFormat dec = new DecimalFormat("#0.00");
        String dd = dec.format(d);
        //przypisania parametrów
        mDeff = dd + '%';
        mDay = day;
        mMid = mid;
    }
    //funkcje pobierania paramerrów
    public String getDay() {
        return mDay;
    }
    public String getMid() {
        String a;
        a = Double.toString(mMid);
        return a;
    }
    public String getDeff() {
        return mDeff;
    }

}
