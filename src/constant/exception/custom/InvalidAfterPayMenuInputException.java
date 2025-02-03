package constant.exception.custom;

import constant.exception.ErrorMessage;

public class InvalidAfterPayMenuInputException extends RuntimeException{
    public InvalidAfterPayMenuInputException() {
        super(ErrorMessage.INVALID_AFTER_PAY_MENU_INPUT.getMessage());
    }
}
