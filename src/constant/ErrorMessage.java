package constant;

public enum ErrorMessage {
    COMMON_EXCEPTION("[ERROR] "),
    INVALID_MAIN_INPUT("1~5 사이의 숫자를 입력해주세요.\n"),
    INVALID_BROWSE_PRODUCT_INPUT("1 또는 2를 입력해주세요."),
    ALREADY_EXIST_COUPON("이미 쿠폰이 존재합니다.\n");

    private String message;

    ErrorMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return COMMON_EXCEPTION.message + message;
    }
}
