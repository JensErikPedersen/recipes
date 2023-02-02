package dk.serik.recipes.exceptions;

public enum ClientError {

    UNHANDLED_EXCEPTION(0, "Unhandled Exception"),
    // category entity specific codes
    CATEGORY_NOT_FOUND(200, "Category could not be found");


    private final int code;
    private final String description;

    private ClientError(int errorCode, String description) {
        this.code = errorCode;
        this.description = description;
    }

    public int getCode() {
        return this.code;
    }

    public String getDescription() {
        return this.description;
    }
}
