package model.shoppingmall.cart;

import constant.ErrorMessage;
import dto.ProductSimpleInfo;
import model.shoppingmall.product.Clothes;
import model.shoppingmall.product.Product;

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
        checkDeleteQuantityIsZero(deleteQuantity);
        checkDeleteQuantityOverCartQuantity(deleteQuantity);
    }

    private void checkDeleteQuantityIsZero(int deleteQuantity) {
        if (deleteQuantity == 0) {
            throw new IllegalArgumentException(ErrorMessage.ZERO_DELETE_QUANTITY.getMessage());
        }
    }

    private void checkDeleteQuantityOverCartQuantity(int deleteQuantity) {
        if (deleteQuantity > purchaseQuantity) {
            throw new IllegalArgumentException(ErrorMessage.OVER_DELETE_QUANTITY.getMessage());
        }
    }
}
