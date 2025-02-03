package constant.exception.custom;

import constant.exception.ErrorMessage;

public class OverDeleteQuantityException extends RuntimeException{

    public OverDeleteQuantityException() {
        super(ErrorMessage.OVER_DELETE_QUANTITY.getMessage());
    }
}
