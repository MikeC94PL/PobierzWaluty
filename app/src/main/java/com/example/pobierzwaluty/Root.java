package com.example.pobierzwaluty;

//jedne z 2 modułów pobieranych w formacie json przez internet
//służy do obsługi klasy MainActivity
//moduł po pobraniu użyty jest tylko częściowo
public class Root
{
    //identyfikator; nigrdzie nie użyty
    private String no;
    //tablica kursów
    private Rates[] rates;
    //parametr A; nigdzie nie użyty
    private String table;
    //data
    private String effectiveDate;



    //metody
    public String getNo ()
    {
        return no;
    }

    public void setNo (String no)
    {
        this.no = no;
    }

    public Rates[] getRates ()
    {
        return rates;
    }

    public void setRates (Rates[] rates)
    {
        this.rates = rates;
    }

    public String getTable ()
    {
        return table;
    }

    public void setTable (String table)
    {
        this.table = table;
    }

    public String getEffectiveDate ()
    {
        return effectiveDate;
    }

    public void setEffectiveDate (String effectiveDate)
    {
        this.effectiveDate = effectiveDate;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [no = "+no+", rates = "+rates+", table = "+table+", effectiveDate = "+effectiveDate+"]";
    }
}
			