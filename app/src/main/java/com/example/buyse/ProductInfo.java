package com.example.buyse;

public class ProductInfo {
    private String Item_name;
    private String Price;
    private String Description;
    private String Meet_place;
    private String Phone_number;
    private String Key;
    private String img;

    public ProductInfo(String item,String price,String desc,String Meetplace, String phoneno,String key){
        Item_name = item;
        Price = price;
        Description = desc;
        Meet_place = Meetplace;
        Phone_number = phoneno;
        Key = key;
    }

    public String getItem_name(){
        return Item_name;
    }

    public void setItem_name(){
        this.Item_name = Item_name;
    }

    public String getPrice(){
        return Price;
    }

    public void setPrice(){
        this.Price = Price;
    }

    public String getDescription(){
        return Description;
    }

    public void setDescription(){
        this.Description = Description;
    }

    public String getMeet_place(){
        return Meet_place;
    }

    public void setMeet_place(){
        this.Meet_place = Meet_place;
    }

    public String getPhone_number(){
        return Phone_number;
    }

    public void setPhone_number(){
        this.Phone_number = Phone_number;
    }

    public String getKey() {
        return Key;
    }

    public void setKey(String key) {
        Key = key;
    }
}
