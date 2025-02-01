package constant;

public enum ErrorMessage {
    VALIDATOR_CLASS("Validator Class"),
    COMMON_EXCEPTION("[ERROR] "),
    EMPTY_INPUT("비정상적인 입력입니다."),
    INVALID_MAIN_INPUT("1 ~ 5 사이의 숫자를 입력해주세요.\n"),
    INVALID_MENU_INPUT("1 ~ 3 사이의 숫자를 입력해주세요."),
    NOT_EXIST_PRODUCT("존재하지 않는 상품입니다."),
    NOT_EXIST_PRODUCT_IN_CART("장바구니에 존재하지 않는 상품입니다."),
    INVALID_CART_FORMAT("장바구니 형식에 맞게 입력해주세요. ex)반팔,1"),
    INVALID_PAY_FORMAT("1원 이상의 숫자 금액을 입력해주세요. ex)10000"),
    OVER_PURCHASE_QUANTITY("수량이 부족합니다."),
    OVER_DELETE_QUANTITY("장바구니 수량이하의 삭제수량을 입력해주세요."),
    ZERO_DELETE_QUANTITY("1개 이상의 재고를 삭제해주세요."),
    NOT_EXIST_COUPON("적용할 쿠폰이 없습니다."),
    NOT_EXIST_POINT("적용할 포인트가 없습니다."),
    LACK_PAYMENT_AMOUNT("결제 금액이 부족합니다."),
    ALREADY_EXIST_COUPON("이미 쿠폰이 존재합니다.\n");

    private final String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return COMMON_EXCEPTION.message + message;
    }
}
