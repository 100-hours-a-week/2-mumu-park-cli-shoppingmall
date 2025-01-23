package model.shoppingMall;

import java.util.ArrayList;
import java.util.List;

public class ShoppingMall {
    private List<Product> products = new ArrayList<>();

    public ShoppingMall(List<Product> products) {
        this.products = products;
    }
}
