package vn.edu.fit.iuh.lab1.models;

public enum Status {


    ACTIVE(1),
    DEACTIVE(0),
    DELETE(-1);

    private final int stage;

    public int getStage() {
        return stage;
    }

    private Status(int stage){
        this.stage = stage;
    }
    public String toString() {
        return super.toString();
    }
    public static Status fromStatus(int value) {
        for (Status status: vn.edu.fit.iuh.lab1.models.Status.values())
            if (status.stage == value) {
                return status;
            }
        throw new IllegalArgumentException("Invalid GrantStatus value: " + value);
    }
}
