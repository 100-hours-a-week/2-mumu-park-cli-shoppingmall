package model.shoppingMall.cart;

import model.shoppingMall.product.Product;

public class CartProduct {
    private final Product product;
    private final int purchaseQuantity;

    public CartProduct(Product product, int purchaseQuantity) {
        this.product = product;
        this.purchaseQuantity = purchaseQuantity;
    }
}
