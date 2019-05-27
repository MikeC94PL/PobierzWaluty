package com.example.pobierzwaluty;

//jedne z 2 modułów pobieranych w formacie json przez internet
//służy do obsługi klasy Detalis
public class RatesDet {

    //identyfikator; nie jest nigdzie użyty
    private String no;
    //data
    private String effectiveDate;
    //kurs
    private double mid;

    public String getNo()
    {
        return no;
    }

    public String getEffectiveDate()
    {
        return effectiveDate;
    }

    public double getMid()
    {
        return mid;
    }

}
