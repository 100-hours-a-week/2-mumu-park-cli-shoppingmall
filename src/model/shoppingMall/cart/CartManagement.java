package model.shoppingMall.cart;

import dto.ProductSimpleInfo;

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

    public List<ProductSimpleInfo> getCartProductsInfo() {
        return carts.stream().map(CartProduct::toSimpleInfoDto).toList();
    }
}
