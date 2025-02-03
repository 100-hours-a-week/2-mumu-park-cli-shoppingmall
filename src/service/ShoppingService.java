package service;

import dto.*;
import model.shoppingmall.ShoppingMall;
import model.user.User;
import util.CouponGenerator;
import validator.CouponValidator;

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
        CouponValidator.checkUserHasCoupon(user);

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

    public ChangeAndPoint pay(PaymentInfo paymentInfo) {
        ChangeAndPoint changeAndPoint = shoppingMall.paymentProgress(LocalDateTime.now(), paymentInfo);
        user.payProcess(paymentInfo, changeAndPoint.rewardPoint());

        return changeAndPoint;
    }

    public List<OrderHistory> getUserOrderHistory() {
        return shoppingMall.getOrderHistory();
    }
}
