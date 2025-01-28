package model.shoppingMall;

import dto.ProductDetailInfo;
import dto.ProductSimpleInfo;
import model.shoppingMall.history.HistoryManagement;
import model.shoppingMall.payment.PaymentManagement;
import model.shoppingMall.product.ProductManagement;

import java.util.List;
import java.util.Map;

public class ShoppingMall {
    private final ProductManagement productManagement;
    private final HistoryManagement historyManagement;
    private final PaymentManagement paymentManagement;

    public ShoppingMall(ProductManagement productManagement, HistoryManagement historyManagement, PaymentManagement paymentManagement) {
        this.productManagement = productManagement;
        this.historyManagement = historyManagement;
        this.paymentManagement = paymentManagement;
    }

    public Map<String, List<ProductSimpleInfo>> getProductsSimpleInfo() {
        return productManagement.getProductsSimpleInfo();
    }

    public ProductDetailInfo findProductDetailByName(String productName) {
        return productManagement.findProductDetailByName(productName);
    }

}
