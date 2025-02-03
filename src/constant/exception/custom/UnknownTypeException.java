package constant.exception.custom;

import constant.exception.ErrorMessage;

public class UnknownTypeException extends RuntimeException{

    public UnknownTypeException() {
        super(ErrorMessage.UNKNOWN_TYPE.getMessage());
    }
}
