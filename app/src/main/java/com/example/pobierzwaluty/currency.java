package com.example.pobierzwaluty;

//klasa służy jako moduł listy MyAdapter widoku MainActivity
public class currency {
    private String mCode;
    private String mMid;
    private String mName;
    private int mIcon;

    //konstruktor pobiera kod, kurs, nazwę oraz ikonę
    public currency(String code, String mid,String name,int icon) {

        mCode = code;
        mMid = mid;
        mName = name;
        mIcon = icon;
    }

    public String getCode() {
        return mCode;
    }

    public String getMid() {return mMid;}

    public String getName() {
        return mName;
    }

    public int getIcon() {
        return mIcon;
    }
}
