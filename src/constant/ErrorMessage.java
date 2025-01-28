package constant;

public enum ErrorMessage {
    COMMON_EXCEPTION("[ERROR] "),
    EMPTY_INPUT("비정상적인 입력입니다."),
    INVALID_MAIN_INPUT("1~5 사이의 숫자를 입력해주세요.\n"),
    INVALID_BROWSE_PRODUCT_INPUT("1 또는 2를 입력해주세요."),
    NOT_EXIST_PRODUCT("존재하지 않는 상품입니다."),
    INVALID_CART_FORMAT("장바구니 형식에 맞게 입력해주세요. ex)반팔,1"),
    OVER_PURCHASE_QUANTITY("수량이 부족합니다."),
    ALREADY_EXIST_COUPON("이미 쿠폰이 존재합니다.\n");

    private String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return COMMON_EXCEPTION.message + message;
    }
}
