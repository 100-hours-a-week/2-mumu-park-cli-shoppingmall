package constant.exception.custom;

import constant.exception.ErrorMessage;

public class InvalidMainMenuInputException extends RuntimeException{
    public InvalidMainMenuInputException() {
        super(ErrorMessage.INVALID_MAIN_INPUT.getMessage());
    }
}
