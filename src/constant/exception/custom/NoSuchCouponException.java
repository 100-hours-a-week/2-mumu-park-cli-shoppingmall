package constant.exception.custom;

import constant.exception.ErrorMessage;

public class NoSuchCouponException extends RuntimeException{
    public NoSuchCouponException() {
        super(ErrorMessage.COUPON_DOES_NOT_EXIST.getMessage());
    }
}
