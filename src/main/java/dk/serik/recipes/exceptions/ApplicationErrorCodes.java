package dk.serik.recipes.exceptions;

public enum ApplicationErrorCodes {

    UNHANDLED_EXCEPTION(0),
    VALIDATION_EXCEPTION(20),
    // Category
    CATEGORY_NOT_FOUND(100),

    INGREDIENT_DTO_IS_NULL(200),

    INGREDIENT_ID_IS_NULL(201),

    INGREDIENT_NOT_FOUND(210);
    private int code;

    private ApplicationErrorCodes(int code) {
        this.code=code;
    }

    public int getCode() {
        return this.code;
    }
}
