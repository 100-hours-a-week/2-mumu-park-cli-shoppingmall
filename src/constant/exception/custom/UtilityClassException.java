package constant.exception.custom;

import constant.exception.ErrorMessage;

public class UtilityClassException extends Exception{

    public UtilityClassException() {
        super(ErrorMessage.UTILITY_CLASS.getMessage());
    }
}
