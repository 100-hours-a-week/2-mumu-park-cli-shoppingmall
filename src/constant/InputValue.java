package constant;

import constant.exception.custom.UnknownTypeException;

import java.util.Arrays;

public enum InputValue {
    BROWSE_PRODUCT("1", "MAIN_MENU"),
    ISSUE_COUPON("2", "MAIN_MENU"),
    CART("3", "MAIN_MENU"),
    SHOW_POINT("4", "MAIN_MENU"),
    SHOW_ORDER_HISTORY("5", "MAIN_MENU"),
    OUT("6", "MAIN_MENU"),

    SHOW_DETAIL("1", "PRODUCT_MENU"),
    ADD_CART("2", "PRODUCT_MENU"),
    GO_HOME("3", "PRODUCT_MENU"),

    PAY("1", "CART_MENU"),
    DELETE_CART_PRODUCT("2", "CART_MENU"),
    TO_HOME("3", "CART_MENU"),

    USE_COUPON("1", "PAY_MENU"),
    USE_POINT("2", "PAY_MENU"),
    PAYMENT("3", "PAY_MENU");

    final String value;
    final String category;

    InputValue(String value, String category) {
        this.value = value;
        this.category = category;
    }

    public static InputValue fromValue(String value, String category) {
        return Arrays.stream(values())
                .filter(input -> input.value.equals(value) && input.category.equals(category))
                .findFirst()
                .orElseThrow(UnknownTypeException::new);
    }
}
