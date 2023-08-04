package com.Model;

public class Customer{
    private  int id;
    private String phoneNumber;
    private String name;
    private String surname;
    private String tcNum;

    public Customer() {
    }

    public Customer(int id, String phoneNumber, String name, String surname, String tcNum) {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.surname = surname;
        this.tcNum = tcNum;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTcNum() {
        return tcNum;
    }

    public void setTcNum(String tcNum) {
        this.tcNum = tcNum;
    }
}
