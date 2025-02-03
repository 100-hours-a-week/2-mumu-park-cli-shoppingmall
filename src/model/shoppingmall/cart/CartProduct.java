package model.shoppingmall.cart;

import dto.ProductSimpleInfo;
import model.shoppingmall.product.Clothes;
import model.shoppingmall.product.Product;
import validator.CartValidator;

public class CartProduct {
    private final Product product;
    private int purchaseQuantity;

    public CartProduct(Product product, int purchaseQuantity) {
        this.product = product;
        this.purchaseQuantity = purchaseQuantity;
    }

    public void addCartQuantity(int addQuantity) {
        this.purchaseQuantity += addQuantity;
    }

    public ProductSimpleInfo toSimpleInfoDto() {
        Clothes clothes = (Clothes) product;

        return new ProductSimpleInfo(
                product.getName(),
                clothes.getSize(),
                purchaseQuantity,
                product.getPrice() * purchaseQuantity
        );
    }

    public void delete(int deleteQuantity) {
        purchaseQuantity -= deleteQuantity;
    }

    public boolean purchaseQuantityIsZero() {
        return purchaseQuantity == 0;
    }

    public boolean isEqualName(String productName) {
        return product.getName().equals(productName);
    }

    public void checkValidDeleteQuantity(int deleteQuantity) {
        CartValidator.checkDeleteQuantity(purchaseQuantity, deleteQuantity);
    }
}
