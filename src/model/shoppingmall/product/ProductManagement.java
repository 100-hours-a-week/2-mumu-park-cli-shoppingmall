package model.shoppingmall.product;

import constant.ErrorMessage;
import dto.ProductDetailInfo;
import dto.ProductSimpleInfo;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductManagement {
    private final List<Product> products;

    public ProductManagement(List<Product> products) {
        this.products = products;
    }

    public Map<String, List<ProductSimpleInfo>> getProductsSimpleInfo() {
        return products.stream()
                .filter(product -> product instanceof Clothes)
                .map(product -> (Clothes) product)
                .collect(Collectors.groupingBy(
                        clothes -> clothes instanceof Top ? "TOP" : "BOTTOM",
                        Collectors.mapping(Clothes::generateSimpleInfo, Collectors.toList())
                ));
    }

    public ProductDetailInfo findProductDetailByName(String productName) {
        Product product = findProductByName(productName);

        if (product instanceof Top top) {
            return top.generateDetailInfo();
        } else if (product instanceof Bottom bottom) {
            return bottom.generateDetailInfo();
        }

        throw new IllegalArgumentException(ErrorMessage.UNKNOWN_TYPE.getMessage());
    }

    public Product findProductByName(String productName) {
        return products.stream()
                .filter((p -> p.getName().equals(productName)))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(ErrorMessage.PRODUCT_DOES_NOT_EXIST.getMessage()));
    }

    public void addProductQuantity(String productName, int addQuantity) {
        findProductByName(productName).addQuantity(addQuantity);
    }
}
