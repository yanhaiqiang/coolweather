package com.coolweather.android.db;

import org.litepal.crud.LitePalSupport;
import org.litepal.exceptions.DataSupportException;

public class Province extends LitePalSupport {



    private int id;
    private String privinceName;
    private int provinceCode;





    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(int provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getPrivinceName() {
        return privinceName;
    }

    public void setPrivinceName(String privinceName) {
        this.privinceName = privinceName;
    }
}
