package model.shoppingMall.cart;

import constant.ErrorMessage;
import dto.CartProductInfo;
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

    public void deleteProduct(CartProductInfo deleteInfo) {
        CartProduct cartProduct = getCartProductByName(deleteInfo.name());

        cartProduct.checkValidDeleteQuantity(deleteInfo.purchaseOrDeleteQuantity());
        cartProduct.delete(deleteInfo.purchaseOrDeleteQuantity());

        if (cartProduct.purchaseQuantityIsZero()) {
            carts.remove(cartProduct);
        }
    }

    private CartProduct getCartProductByName(String productName) {
        for (CartProduct cart : carts) {
            if (cart.isEqualName(productName)) {
                return cart;
            }
        }

        throw new IllegalArgumentException(ErrorMessage.NOT_EXIST_PRODUCT_IN_CART.getMessage());
    }
}
