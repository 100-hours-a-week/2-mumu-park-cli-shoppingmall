package model.shoppingMall.cart;

import java.util.ArrayList;
import java.util.List;

public class CartManagement {
    private final List<CartProduct> carts;

    public CartManagement() {
        this.carts = new ArrayList<>();
    }

    public void addCart(CartProduct cartProduct) {
        carts.add(cartProduct);
    }
}
