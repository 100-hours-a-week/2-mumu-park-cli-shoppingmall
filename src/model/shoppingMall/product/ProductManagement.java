package model.shoppingMall.product;

import dto.ProductSimpleInfo;

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
}
