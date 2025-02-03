package controller;

import constant.InputValue;
import constant.exception.custom.NoSuchProductInCartException;
import dto.*;

import service.ShoppingService;
import validator.PaymentValidator;
import view.InputView;
import view.OutputView;

import java.io.IOException;
import java.util.List;

public class ShoppingController {
    private static final String YES = "y";
    private static final String MAIN_MENU = "MAIN_MENU";
    private static final String PRODUCT_MENU = "PRODUCT_MENU";
    private static final String CART_MENU = "CART_MENU";
    private static final String PAY_MENU = "PAY_MENU";
    private final InputView inputView;
    private final OutputView outputView;
    private final ShoppingService shoppingService;

    public ShoppingController(InputView inputView, OutputView outputView, ShoppingService shoppingService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.shoppingService = shoppingService;
    }

    public void run() throws IOException {
        InputValue inputValue = InputValue.fromValue(readUserMainInput(), MAIN_MENU);

        switch (inputValue) {
            case InputValue.BROWSE_PRODUCT -> browseProductProcess();
            case InputValue.ISSUE_COUPON -> issueRandomCoupon();
            case InputValue.CART -> cartProcess();
            case InputValue.SHOW_POINT -> showUserPointProcess();
            case InputValue.SHOW_ORDER_HISTORY -> showUserOrderHistoryProcess();
            default -> printExitMessage();
        }
    }

    private String readUserMainInput() throws IOException {
        while (true) {
            try {
                return inputView.readMainInput();
            } catch (RuntimeException e) {
                outputView.printExceptionMessage(e);
            }
        }
    }

    private void browseProductProcess() throws IOException {
        outputView.printProductSimpleInfo(shoppingService.getProducts());
        executeBrowseMenuByUserInput();
    }

    private void issueRandomCoupon() throws IOException {
        try {
            int couponDiscountRate = shoppingService.issueRandomCoupon();
            outputView.printIssuedCoupon(couponDiscountRate);
        } catch (RuntimeException e) {
            outputView.printExceptionMessage(e);
        } finally {
            run();
        }
    }

    private void cartProcess() throws IOException {
        List<ProductSimpleInfo> cartProducts = getCartProducts();
        outputView.printCartProducts(cartProducts);

        if (cartProducts.isEmpty())  {
            run();
        } else {
            handleCartMenuInput(readCartMenuInput(), cartProducts);
        }
    }

    private void showUserPointProcess() throws IOException {
        outputView.printUserPoint(shoppingService.getUserPoint());
        run();
    }

    private void showUserOrderHistoryProcess() throws IOException {
        outputView.printUserOrderHistory(shoppingService.getUserOrderHistory());
        run();
    }

    private void executeBrowseMenuByUserInput() throws IOException {
        while (true) {
            try {
                InputValue inputValue = InputValue.fromValue(readBrowseProductUserInput(), PRODUCT_MENU);

                switch (inputValue) {
                    case SHOW_DETAIL -> showProductDetailProcess();
                    case ADD_CART -> addToCartProcess();
                    default -> run();
                }
                break;
            } catch (RuntimeException e) {
                outputView.printExceptionMessage(e);
            }
        }
    }

    private String readBrowseProductUserInput() throws IOException {
        while (true) {
            try {
                return inputView.readBrowseProductUserInput();
            } catch (IllegalArgumentException e) {
                outputView.printExceptionMessage(e);
            }
        }
    }

    private void showProductDetailProcess() throws IOException {
        String productName = inputView.readProductName();
        ProductDetailInfo productDetail = shoppingService.getProductDetailInfoByName(productName);
        outputView.printProductDetailInfo(productDetail);
        executeBrowseMenuByUserInput();
    }

    private String readCartMenuInput() throws IOException {
        while (true) {
            try {
                return inputView.readCartMenuInput();
            } catch (RuntimeException e) {
                outputView.printExceptionMessage(e);
            }
        }
    }

    private List<ProductSimpleInfo> getCartProducts() {
        return shoppingService.getCartProducts();
    }

    private void addToCartProcess() throws IOException {
        CartProductInfo cartProductInfo = inputView.readCartProduct();
        shoppingService.addProductToCart(cartProductInfo);
        outputView.printSuccessAddCartMessage(cartProductInfo);
        browseProductProcess();
    }

    private void handleCartMenuInput(String menuInput, List<ProductSimpleInfo> cartProducts) throws IOException {
        InputValue inputValue = InputValue.fromValue(menuInput, CART_MENU);

        switch (inputValue) {
            case PAY -> handlePaymentUserInput(cartProducts, shoppingService.getUserDiscountInfo());
            case DELETE_CART_PRODUCT -> deleteCartProductProcess();
            default -> run();
        }
    }

    private void handlePaymentUserInput(List<ProductSimpleInfo> cartProducts, DiscountInfo userDiscountInfo) throws IOException {
        outputView.printPaymentInfos(cartProducts, userDiscountInfo);

        while(true) {
            try {
                InputValue inputValue = InputValue.fromValue(inputView.readPaymentMenuInput(), PAY_MENU);

                switch (inputValue) {
                    case USE_COUPON -> {
                        userDiscountInfo.applyCoupon();
                        handlePaymentUserInput(cartProducts, userDiscountInfo);
                        return;
                    }
                    case USE_POINT -> {
                        userDiscountInfo.usePoint(cartProducts);
                        handlePaymentUserInput(cartProducts, userDiscountInfo);
                        return;
                    }
                    default -> {
                        paymentProcess(cartProducts, userDiscountInfo);
                        return;
                    }
                }
            } catch (RuntimeException e) {
                outputView.printExceptionMessage(e);
            }
        }
    }

    private void paymentProcess(List<ProductSimpleInfo> cartProducts, DiscountInfo userDiscountInfo) throws IOException {
        int finalPrice = userDiscountInfo.getFinalPrice(cartProducts);
        outputView.printFinalPrice(finalPrice);

        int payAmount = readPayAmount(finalPrice);
        outputView.printPaymentResult(
                payAmount,
                shoppingService.pay(createPaymentInfo(cartProducts, userDiscountInfo, finalPrice, payAmount))
        );

        handlePostPayment();
    }

    private int readPayAmount(int finalPrice) throws IOException {
        while(true) {
            try {
                int payAmount = inputView.readPayAmount();
                PaymentValidator.checkValidPayAmount(finalPrice, payAmount);
                return payAmount;
            } catch (RuntimeException e) {
                outputView.printExceptionMessage(e);
            }
        }
    }

    private PaymentInfo createPaymentInfo(List<ProductSimpleInfo> cartProducts, DiscountInfo userDiscountInfo, int finalPrice, int payAmount) {
        return new PaymentInfo(
                cartProducts,
                userDiscountInfo.isCouponUsed() ? userDiscountInfo.getCouponRate() : 0,
                userDiscountInfo.getAppliedPoint(),
                finalPrice,
                payAmount
        );
    }

    private void handlePostPayment() throws IOException {
        String userChoice = readAfterPayMenu();
        if (userChoice.equals(YES)) {
            run();
        } else {
            printExitMessage();
        }
    }

    private String readAfterPayMenu() throws IOException {
        while(true) {
            try {
                return inputView.readAfterPayMenu();
            } catch (RuntimeException e) {
                outputView.printExceptionMessage(e);
            }
        }
    }

    private boolean deleteCartProductProcess() throws IOException {
        try {
            return deleteCartProduct(readDeleteProductFromCart());
        } catch (NoSuchProductInCartException e) {
            outputView.printExceptionMessage(e);
            return deleteCartProductProcess();
        }
    }

    private CartProductInfo readDeleteProductFromCart() throws IOException {
        while(true) {
            try {
                return inputView.readDeleteProductFromCart();
            } catch (RuntimeException e) {
                outputView.printExceptionMessage(e);
            }
        }
    }

    private boolean deleteCartProduct(CartProductInfo deleteInfo) throws IOException {
        shoppingService.deleteCartProduct(deleteInfo);
        outputView.printSuccessDeleteCartProduct(deleteInfo);
        cartProcess();
        return true;
    }

    private void printExitMessage() {
        outputView.printExitMessage();
    }
}
