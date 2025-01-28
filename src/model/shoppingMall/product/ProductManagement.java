package model.shoppingMall.product;

import constant.ErrorMessage;
import dto.ProductDetailInfo;
import dto.ProductSimpleInfo;
import dto.TopDetailInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ProductManagement {
    private final List<Product> products;

    public ProductManagement(List<Product> products) {
        this.products = products;
    }

    public Map<String, List<ProductSimpleInfo>> getProductsSimpleInfo() {
        Map<String, List<ProductSimpleInfo>> productSimpleInfos = new HashMap<>();
        List<ProductSimpleInfo> tops = new ArrayList<>();
        List<ProductSimpleInfo> bottoms = new ArrayList<>();

        for (Product product : products) {
            Clothes clothes = (Clothes) product;
            if (product instanceof Top) {
                tops.add(clothes.generateSimpleInfo());
                continue;
            }

            bottoms.add(clothes.generateSimpleInfo());
        }
        productSimpleInfos.put("TOP", tops);
        productSimpleInfos.put("BOTTOM", bottoms);

        return productSimpleInfos;
    }

    public ProductDetailInfo findProductDetailByName(String productName) {
        Product product = products.stream()
                .filter((p -> p.getName().equals(productName)))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.NOT_EXIST_PRODUCT.getMessage()));

        if (product instanceof Top) {
            Top top = (Top) product;
            return top.generateDetailInfo();
        }

        Bottom bottom = (Bottom) product;
        return bottom.generateDetailInfo();
    }
}
