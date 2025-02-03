package validator;

import constant.exception.custom.AlreadyExistCouponException;
import constant.exception.custom.NoSuchCouponException;
import constant.exception.custom.ValidatorClassException;
import model.user.User;

import java.time.LocalDateTime;

public class CouponValidator {

    private CouponValidator() throws ValidatorClassException {
        throw new ValidatorClassException();
    }

    public static void checkExistCouponToApply(int couponRate, boolean isCouponUsed) {
        if (couponRate == 0 || isCouponUsed) {
            throw new NoSuchCouponException();
        }
    }

    public static void checkUserHasCoupon(User user) {
        if (user.hasCoupon(LocalDateTime.now())) {
            throw new AlreadyExistCouponException();
        }
    }
}
