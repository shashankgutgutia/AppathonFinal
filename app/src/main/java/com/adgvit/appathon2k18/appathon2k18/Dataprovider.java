package com.adgvit.appathon2k18.appathon2k18;



public class Dataprovider {



    public Dataprovider(int img_res,String companyname)
    {
        this.setImg_res(img_res);
        this.setCompanyname(companyname);
    }

    private int img_res;
    private String companyname;

    public int getImg_res() {
        return img_res;
    }

    public void setImg_res(int img_res) {
        this.img_res = img_res;
    }

    public String getCompanyname() {
        return companyname;
    }

    public void setCompanyname(String companyname) {
        this.companyname = companyname;
    }
}
