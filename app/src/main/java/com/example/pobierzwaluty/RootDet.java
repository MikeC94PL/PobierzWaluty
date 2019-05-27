package com.example.pobierzwaluty;

//jedne z 2 modułów pobieranych w formacie json przez internet
//służy do obsługi klasy Detalis
//moduł po pobraniu nigdzie nie jest użyty
public class RootDet
{
    //parametr A; nigdzie nie użyty
    private String table;
    //pełna nazwa waluty
    private String currency;
    //3-literowy kod waluty
    private String code;
    //tablica kursów
    private RatesDet[] rates;

    public RatesDet[] getRates() {
        return rates;
    }
}
