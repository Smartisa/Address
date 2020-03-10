package com.bean;

public class Bean
{
    private String ID;
    private String Name;
    private String Address;
    private String Code;

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }
    public Bean()
    {}
    public Bean(String id, String name, String address, String code) {
        ID = id;
        Name = name;
        Address = address;
        Code = code;
    }
}
