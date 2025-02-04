package validator;

import constant.exception.custom.NoSuchPointException;
import constant.exception.custom.ValidatorClassException;

public class PointValidator {
    private static final int ZERO_POINT = 0;
    private PointValidator() throws ValidatorClassException {
        throw new ValidatorClassException();
    }

    public static void checkExistPointToUse(int totalPoint) {
        if (totalPoint == ZERO_POINT) {
            throw new NoSuchPointException();
        }
    }
}
