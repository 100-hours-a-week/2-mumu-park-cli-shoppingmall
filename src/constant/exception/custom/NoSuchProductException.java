package constant.exception.custom;

import constant.exception.ErrorMessage;

public class NoSuchProductException extends RuntimeException{
    public NoSuchProductException(String productName) {
        super(ErrorMessage.PRODUCT_DOES_NOT_EXIST.formatMessage(productName));
    }
}
