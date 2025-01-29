package model.shoppingMall.cart;

import dto.ProductSimpleInfo;
import model.shoppingMall.product.Clothes;
import model.shoppingMall.product.Product;

public class CartProduct {
    private final Product product;
    private final int purchaseQuantity;

    public CartProduct(Product product, int purchaseQuantity) {
        this.product = product;
        this.purchaseQuantity = purchaseQuantity;
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
}
