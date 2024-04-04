package entity;

import jdk.jfr.Description;

public class Customer {
    @Description("c - 7 numbers")
    protected String id;
    protected String fullName;
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public Customer(String id, String fullName) {
        this.id = id;
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
