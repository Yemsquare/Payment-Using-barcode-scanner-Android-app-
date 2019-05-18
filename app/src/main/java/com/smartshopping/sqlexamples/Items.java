package com.smartshopping.sqlexamples;

/**
 * Created by User on 03/11/2018.
 */

public class Items {

    private String product_name;
    private int i_amount;
    public Items(String product_name,int i_amount) {
        this.product_name = product_name;
        this.i_amount = i_amount;
    }
    public String getProduct_name() {
        return product_name;
    }

    public int getItem_amount() {
        return i_amount;
    }


}
