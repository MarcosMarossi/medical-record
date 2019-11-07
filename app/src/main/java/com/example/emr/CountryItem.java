package com.example.emr;

public class CountryItem {

    private String countryName;
    private int countryFlag;

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public int getCountrFlag() {
        return countryFlag;
    }

    public void setCountrFlag(int countrFlag) {
        this.countryFlag = countrFlag;
    }

    public CountryItem(String countryName, int countryFlag) {
        this.countryName = countryName;
        this.countryFlag = countryFlag;
    }
}
