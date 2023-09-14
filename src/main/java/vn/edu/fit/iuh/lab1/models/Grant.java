package vn.edu.fit.iuh.lab1.models;

public enum Grant {
    DISABLED(0),
    ENABLED(1);

    private final int stage;

    private Grant(int stage) {
        this.stage = stage;
    }

    public int getStage() {
        return stage;
    }


    public String toString() {
        return super.toString();
    }
    public static Grant fromGrant(int value) {
        for (Grant grant : Grant.values()) {
            if (grant.stage == value) {
                return grant;
            }
        }
        throw new IllegalArgumentException("Invalid GrantStatus value: " + value);
    }
}
