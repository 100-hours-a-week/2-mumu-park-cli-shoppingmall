package constant.exception.custom;

import constant.exception.ErrorMessage;

public class InvalidMenuInputException extends RuntimeException{
    public InvalidMenuInputException() {
        super(ErrorMessage.INVALID_MENU_INPUT.getMessage());
    }
}
