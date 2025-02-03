package validator;

import constant.exception.custom.OverDeleteQuantityException;
import constant.exception.custom.OverPurchaseQuantityException;
import constant.exception.custom.ValidatorClassException;
import constant.exception.custom.ZeroDeleteQuantityException;
import model.shoppingmall.product.Product;

public class CartValidator {
    private CartValidator() throws ValidatorClassException {
        throw new ValidatorClassException();
    }

    public static void checkDeleteQuantity(int purchaseQuantity, int deleteQuantity) {
        checkDeleteQuantityIsZero(deleteQuantity);
        checkDeleteQuantityOverCartQuantity(purchaseQuantity, deleteQuantity);
    }

    public static void checkPurchaseQuantity(Product product, int purchaseQuantity) {
        if (!product.isValidPurchaseQuantity(purchaseQuantity)) {
            throw new OverPurchaseQuantityException();
        }
    }

    private static void checkDeleteQuantityIsZero(int deleteQuantity) {
        if (deleteQuantity == 0) {
            throw new ZeroDeleteQuantityException();
        }
    }

    private static void checkDeleteQuantityOverCartQuantity(int purchaseQuantity, int deleteQuantity) {
        if (deleteQuantity > purchaseQuantity) {
            throw new OverDeleteQuantityException();
        }
    }

}
