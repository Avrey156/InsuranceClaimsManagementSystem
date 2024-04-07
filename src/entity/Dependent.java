package entity;

/**
 * @author <Nguyen Ngoc Minh Thu - S3941327>
 */

public class Dependent extends Customer{
    public Dependent(String id, String fullName) {
        super(id, fullName);
    }

    @Override
    public String toString() {
        return id + "," + fullName;
    }
}
