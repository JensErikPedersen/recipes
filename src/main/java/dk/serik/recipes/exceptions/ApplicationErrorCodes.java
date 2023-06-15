package dk.serik.recipes.exceptions;

public enum ApplicationErrorCodes {

    UNHANDLED_EXCEPTION(0),
    VALIDATION_EXCEPTION(20),
    // Category
    CATEGORY_NOT_FOUND(100);
    private int code;

    private ApplicationErrorCodes(int code) {
        this.code=code;
    }

    public int getCode() {
        return this.code;
    }
}
