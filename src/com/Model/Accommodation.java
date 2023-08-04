package com.Model;

public class Accommodation {
    private int id;
    private String type;
    private int coefficient;

    public Accommodation(int id, String type, int coefficient) {
        this.id=id;
        this.type = type;
        this.coefficient = coefficient;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCoefficient() {
        return coefficient;
    }

    public void setCoefficient(int coefficient) {
        this.coefficient = coefficient;
    }
}
