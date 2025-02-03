package model.shoppingmall;

import constant.exception.ErrorMessage;
import constant.exception.custom.OverPurchaseQuantityException;
import dto.*;
import model.shoppingmall.cart.CartManagement;
import model.shoppingmall.history.HistoryManagement;
import model.shoppingmall.payment.PaymentManagement;
import model.shoppingmall.product.Product;
import model.shoppingmall.product.ProductManagement;
import validator.CartValidator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class ShoppingMall {
    private final ProductManagement productManagement;
    private final HistoryManagement historyManagement;
    private final PaymentManagement paymentManagement;
    private final CartManagement cartManagement;

    public ShoppingMall(ProductManagement productManagement, HistoryManagement historyManagement, PaymentManagement paymentManagement, CartManagement cartManagement) {
        this.productManagement = productManagement;
        this.historyManagement = historyManagement;
        this.paymentManagement = paymentManagement;
        this.cartManagement = cartManagement;
    }

    public Map<String, List<ProductSimpleInfo>> getProductsSimpleInfo() {
        return productManagement.getProductsSimpleInfo();
    }

    public ProductDetailInfo findProductDetailByName(String productName) {
        return productManagement.findProductDetailByName(productName);
    }

    public void addCart(CartProductInfo cartProductInfo) {
        Product product = productManagement.findProductByName(cartProductInfo.name());
        CartValidator.checkPurchaseQuantity(product, cartProductInfo.purchaseOrDeleteQuantity());

        product.minusQuantity(cartProductInfo.purchaseOrDeleteQuantity());
        cartManagement.addCart(product, cartProductInfo);
    }

    public List<ProductSimpleInfo> getCartProducts() {
        return cartManagement.getCartProductsInfo();
    }

    public void deleteCartProduct(CartProductInfo deleteInfo) {
        cartManagement.deleteProduct(deleteInfo);
        productManagement.addProductQuantity(deleteInfo.name(), deleteInfo.purchaseOrDeleteQuantity());
    }

    public ChangeAndPoint paymentProgress(LocalDateTime now, PaymentInfo paymentInfo) {
        ChangeAndPoint changeAndPoint = paymentManagement.calculateChangeAndRewardPoint(paymentInfo);
        historyManagement.saveOrder(now, paymentInfo, changeAndPoint);
        cartManagement.deleteAllCartProduct();

        return changeAndPoint;
    }

    public List<OrderHistory> getOrderHistory() {
        return historyManagement.getOrderHistory();
    }
}
