package constant.exception.custom;

import constant.exception.ErrorMessage;

public class InvalidCartFormatException extends RuntimeException{
    public InvalidCartFormatException() {
        super(ErrorMessage.INVALID_CART_FORMAT.getMessage());
    }
}
