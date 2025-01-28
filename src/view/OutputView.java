package view;

import dto.CartProductInfo;
import dto.ProductDetailInfo;
import dto.ProductSimpleInfo;

import java.util.List;
import java.util.Map;

public class OutputView {
    private static final String TOP = "TOP";
    private static final String BOTTOM = "BOTTOM";

    public void printExceptionMessage(IllegalArgumentException exception) {
        System.out.println(exception.getMessage());
    }

    public void printIssuedCoupon(int couponDiscountRate) {
        System.out.println("축하드립니다. " + couponDiscountRate + "% 쿠폰을 발급해드렸습니다.");
        System.out.println("유효기간은 5분 입니다.");
    }

    public void printUserPoint(int userPoint) {
        System.out.println("고객님의 포인트는 " + userPoint + "원 존재합니다.\n");
    }

    public void printProductSimpleInfo(Map<String, List<ProductSimpleInfo>> productSimpleInfos) {
        System.out.println("상품을 구경하고 맘에드는 상품을 장바구니에 넣어보세요.\n");
        System.out.println("[Top]");
        System.out.println("-------------------------------------------------------");
        List<ProductSimpleInfo> tops = productSimpleInfos.get(TOP);
        for (ProductSimpleInfo top : tops) {
            System.out.println(formatProductSimpleInfo(top));
        }

        System.out.println("\n[Bottom]");
        System.out.println("-------------------------------------------------------");
        List<ProductSimpleInfo> bottoms = productSimpleInfos.get(BOTTOM);
        for (ProductSimpleInfo bottom : bottoms) {
            System.out.println(formatProductSimpleInfo(bottom));
        }
    }

    public void printProductDetailInfo(ProductDetailInfo detailInfo) {
        System.out.println("-------------------------------------------------------");
        System.out.println("[%s]의 상세정보 입니다.".formatted(detailInfo.name()));
        System.out.println("-------------------------------------------------------");
        System.out.print(detailInfo.getDetail());
        System.out.println("가격 - " + formatPrice(detailInfo.price()));
    }

    private String formatProductSimpleInfo(ProductSimpleInfo product) {
        return "- [%s] %s사이즈, %s, %d개 남음 ".formatted(product.name(), product.size(), formatPrice(product.price()), product.quantity());
    }

    private String formatPrice(int price) {
        return String.format("%,d원", price);
    }

    public void printSuccessAddCartMessage(CartProductInfo cartProductInfo) {
        System.out.println("[" + cartProductInfo.name() + "] 상품 "+ cartProductInfo.purchaseQuantity() + "개가 장바구니에 추가되었습니다.");
    }
}

