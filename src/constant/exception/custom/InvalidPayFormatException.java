package constant.exception.custom;

import constant.exception.ErrorMessage;

public class InvalidPayFormatException extends RuntimeException{
    public InvalidPayFormatException() {
        super(ErrorMessage.INVALID_PAY_FORMAT.getMessage());
    }
}
