package util;

import model.shoppingMall.history.HistoryManagement;
import model.shoppingMall.payment.PaymentManagement;
import model.shoppingMall.product.Bottom;
import model.shoppingMall.ShoppingMall;
import model.shoppingMall.product.ProductManagement;
import model.shoppingMall.product.Top;

import java.util.List;

public class ProductInitializer {
    public static ShoppingMall init() {
        return new ShoppingMall(
                new ProductManagement(List.of(
                        new Top("후드티", 60000, 5, 'S', 70),
                        new Top("반팔티", 30000, 16, 'L', 68),
                        new Top("니트", 70000, 2, 'M', 65),
                        new Bottom("청바지", 90000, 3, 'M', 101, 30),
                        new Bottom("반바지", 45000, 7, 'S', 60, 30),
                        new Bottom("카고바지", 55000, 2, 'L', 120, 32)
                )),
                new HistoryManagement(),
                new PaymentManagement()
        );
    }
}
