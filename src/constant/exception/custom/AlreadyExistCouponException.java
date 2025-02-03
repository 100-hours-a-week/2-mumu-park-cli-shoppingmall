package constant.exception.custom;

import constant.exception.ErrorMessage;

public class AlreadyExistCouponException extends RuntimeException{
    public AlreadyExistCouponException() {
        super(ErrorMessage.ALREADY_EXIST_COUPON.getMessage());
    }
}
