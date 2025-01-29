package model.shoppingMall;

import constant.ErrorMessage;
import dto.CartProductInfo;
import dto.ProductDetailInfo;
import dto.ProductSimpleInfo;
import model.shoppingMall.cart.CartManagement;
import model.shoppingMall.cart.CartProduct;
import model.shoppingMall.history.HistoryManagement;
import model.shoppingMall.payment.PaymentManagement;
import model.shoppingMall.product.Product;
import model.shoppingMall.product.ProductManagement;

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

        if (!product.isValidPurchaseQuantity(cartProductInfo.purchaseOrDeleteQuantity())) {
            throw new IllegalArgumentException(ErrorMessage.OVER_PURCHASE_QUANTITY.getMessage());
        }

        // MinusQuantity 가 productManagement에서 행해져야 하는걸까?
        product.minusQuantity(cartProductInfo.purchaseOrDeleteQuantity());
        cartManagement.addCart(new CartProduct(product, cartProductInfo.purchaseOrDeleteQuantity()));
    }

    public List<ProductSimpleInfo> getCartProducts() {
        return cartManagement.getCartProductsInfo();
    }

    public void deleteCartProduct(CartProductInfo deleteInfo) {
        cartManagement.deleteProduct(deleteInfo);
        productManagement.addProductQuantity(deleteInfo.name(), deleteInfo.purchaseOrDeleteQuantity());
    }
}
