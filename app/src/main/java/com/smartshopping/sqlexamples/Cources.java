package com.smartshopping.sqlexamples;

/**
 * Created by User on 20/10/2018.
 */

public class Cources {
     String bankname;
    int amunt;

    public Cources(String bankname, int amunt) {
        this.bankname = bankname;
        this.amunt = amunt;
    }
    public String getBankname() {
        return bankname;
    }

    public int getAmunt() {
        return amunt;
    }
}
