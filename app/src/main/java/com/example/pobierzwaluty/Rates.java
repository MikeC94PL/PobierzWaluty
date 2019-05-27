package com.example.pobierzwaluty;

//jedne z 2 modułów pobieranych w formacie json przez internet
//służy do obsługi klasy MainActivity
public class Rates
{
    //3-literowy kod waluty
    private String code;
    //kurs
    private String mid;
    //nazwa waluty
    private String currency;

    public String getCode ()
    {
        return code;
    }

    public void setCode (String code)
    {
        this.code = code;
    }

    public String getMid ()
    {
        return mid;
    }

    public void setMid (String mid)
    {
        this.mid = mid;
    }

    public String getCurrency ()
    {
        return currency;
    }

    public void setCurrency (String currency)
    {
        this.currency = currency;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [code = "+code+", mid = "+mid+", currency = "+currency+"]";
    }
}

