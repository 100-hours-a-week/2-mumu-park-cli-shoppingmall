package constant.exception.custom;

import constant.exception.ErrorMessage;

public class NoSuchPointException extends RuntimeException{
    public NoSuchPointException() {
        super(ErrorMessage.POINT_DOES_NOT_EXIST.getMessage());
    }
}
