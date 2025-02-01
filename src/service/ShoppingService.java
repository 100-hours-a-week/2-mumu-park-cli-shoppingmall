package service;

import constant.ErrorMessage;
import dto.*;
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

        user.receiveCoupon(CouponGenerator.issueRandomCoupon());
        return user.getCouponDiscountRate();
    }

    public int getUserPoint() {
        return user.getPoint();
    }

    public Map<String, List<ProductSimpleInfo>> getProducts() {
        return shoppingMall.getProductsSimpleInfo();
    }

    public ProductDetailInfo getProductDetailInfoByName(String productName) {
        return shoppingMall.findProductDetailByName(productName);
    }

    public void addProductToCart(CartProductInfo cartProductInfo) {
        // Todo : 여러개를 장바구니에 담는 기능도 추가해보기
        shoppingMall.addCart(cartProductInfo);
    }

    public List<ProductSimpleInfo> getCartProducts() {
        return shoppingMall.getCartProducts();
    }

    public void deleteCartProduct(CartProductInfo deleteInfo) {
        shoppingMall.deleteCartProduct(deleteInfo);
    }

    public DiscountInfo getUserDiscountInfo() {
        return user.generateDiscountInfo(LocalDateTime.now());
    }

    public ChangeAndPoint paymentProgress(PaymentInfo paymentInfo) {
        ChangeAndPoint changeAndPoint = shoppingMall.paymentProgress(LocalDateTime.now(), paymentInfo);
        user.payProcess(paymentInfo, changeAndPoint.rewardPoint());

        return changeAndPoint;
    }

    public List<OrderHistory> getUserOrderHistory() {
        return shoppingMall.getOrderHistory();
    }
}
