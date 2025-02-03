package util;

import constant.exception.ErrorMessage;
import constant.exception.custom.UtilityClassException;
import model.shoppingmall.cart.CartManagement;
import model.shoppingmall.history.HistoryManagement;
import model.shoppingmall.payment.PaymentManagement;
import model.shoppingmall.product.Bottom;
import model.shoppingmall.ShoppingMall;
import model.shoppingmall.product.ProductManagement;
import model.shoppingmall.product.Top;

import java.util.List;

public class ProductInitializer {
    private ProductInitializer() throws UtilityClassException {
        throw new UtilityClassException();
    }

    public static ShoppingMall init() {
        return new ShoppingMall(
                new ProductManagement(List.of(
                        new Top("Printing Panel T-Shirt", 60000, 5, 'S', 66, 48.5, 57, 24.5),
                        new Top("Oversize Hooded Sweatshirt", 150000, 3, 'M', 67, 60, 66, 56),
                        new Top("Curved Angora Knit", 130000, 2, 'L', 71, 57, 62, 65),
                        new Bottom("Wide Denim Pants", 180000, 3, 'S', 104, 39, 33.5, 33.5, 24),
                        new Bottom("Straight Denim Pants", 90000, 10, 'M', 104, 41, 31, 32, 22),
                        new Bottom("Cut-off Tapered Sweatpants", 150000, 6, 'L', 109, 39, 35, 37.5, 28.5)
                )),
                new HistoryManagement(),
                new PaymentManagement(),
                new CartManagement()
        );
    }
}
