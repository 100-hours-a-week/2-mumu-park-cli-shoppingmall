package validator;

import constant.exception.custom.NoSuchPointException;
import constant.exception.custom.ValidatorClassException;

public class PointValidator {
    private PointValidator() throws ValidatorClassException {
        throw new ValidatorClassException();
    }

    public static void checkExistPointToUse(int totalPoint) {
        if (totalPoint == 0) {
            throw new NoSuchPointException();
        }
    }
}
