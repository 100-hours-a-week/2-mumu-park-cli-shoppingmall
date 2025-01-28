package service;

import constant.ErrorMessage;
import dto.ProductSimpleInfo;
import model.shoppingMall.ShoppingMall;
import model.user.User;
import util.CouponGenerator;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public class ShoppingService {

    private final ShoppingMall shoppingMall;
    private final User user;

    public ShoppingService(ShoppingMall shoppingMall, User user) {
        this.shoppingMall = shoppingMall;
        this.user = user;
    }

    public int issueRandomCoupon() {
        if (user.hasCoupon(LocalDateTime.now())) {
            throw new IllegalArgumentException(ErrorMessage.ALREADY_EXIST_COUPON.getMessage());
        }

        user.setCoupon(CouponGenerator.issueRandomCoupon());
        return user.getCouponDiscountRate();
    }

    public int getUserPoint() {
        return user.getPoint();
    }

    public Map<String, List<ProductSimpleInfo>> getProducts() {
        return shoppingMall.getProductsSimpleInfo();
    }
}
