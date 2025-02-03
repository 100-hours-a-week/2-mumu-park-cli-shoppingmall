package model.shoppingmall.cart;

import constant.exception.custom.NoSuchProductInCartException;
import dto.CartProductInfo;
import dto.ProductSimpleInfo;
import model.shoppingmall.product.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CartManagement {
    private final List<CartProduct> carts;

    public CartManagement() {
        this.carts = new ArrayList<>();
    }

    public void addCart(Product product, CartProductInfo cartProductInfo) {
        int purchaseQuantity = cartProductInfo.purchaseOrDeleteQuantity();

        getCartProductByName(cartProductInfo.name())
                .ifPresentOrElse(
                        cartProduct -> cartProduct.addCartQuantity(purchaseQuantity),
                        () -> carts.add(new CartProduct(product, purchaseQuantity))
                );
    }

    public List<ProductSimpleInfo> getCartProductsInfo() {
        return carts.stream().map(CartProduct::toSimpleInfoDto).toList();
    }

    public void deleteProduct(CartProductInfo deleteInfo) {
        CartProduct cartProduct = getCartProductByName(deleteInfo.name())
                .orElseThrow(NoSuchProductInCartException::new);

        cartProduct.checkValidDeleteQuantity(deleteInfo.purchaseOrDeleteQuantity());
        cartProduct.delete(deleteInfo.purchaseOrDeleteQuantity());

        if (cartProduct.purchaseQuantityIsZero()) carts.remove(cartProduct);
    }

    public void deleteAllCartProduct() {
        carts.clear();
    }

    private Optional<CartProduct> getCartProductByName(String productName) {
        return carts.stream()
                .filter((cartProduct -> cartProduct.isEqualName(productName)))
                .findFirst();
    }
}
