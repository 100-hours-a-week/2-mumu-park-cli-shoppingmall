package constant.exception.custom;

import constant.exception.ErrorMessage;

public class ValidatorClassException extends Exception{

    public ValidatorClassException() {
        super(ErrorMessage.VALIDATOR_CLASS.getMessage());
    }
}
