package model.shoppingMall;

import model.shoppingMall.history.HistoryManagement;
import model.shoppingMall.payment.PaymentManagement;
import model.shoppingMall.product.ProductManagement;

public class ShoppingMall {
    private final ProductManagement productManagement;
    private final HistoryManagement historyManagement;
    private final PaymentManagement paymentManagement;

    public ShoppingMall(ProductManagement productManagement, HistoryManagement historyManagement, PaymentManagement paymentManagement) {
        this.productManagement = productManagement;
        this.historyManagement = historyManagement;
        this.paymentManagement = paymentManagement;
    }
}
