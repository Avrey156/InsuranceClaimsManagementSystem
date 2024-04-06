package entity;

public class Dependent extends Customer{
    public Dependent(String id, String fullName) {
        super(id, fullName);
    }

    @Override
    public String toString() {
        return id + "," + fullName;
    }
}
