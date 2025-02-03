package constant.exception.custom;

import constant.exception.ErrorMessage;

public class EmptyInputException extends RuntimeException{
    public EmptyInputException() {
        super(ErrorMessage.EMPTY_INPUT.getMessage());
    }
}
