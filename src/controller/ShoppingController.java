package controller;

import dto.*;
import service.ShoppingService;
import validator.PaymentValidator;
import view.InputView;
import view.OutputView;

import java.io.IOException;
import java.util.List;

public class ShoppingController {
    private final InputView inputView;
    private final OutputView outputView;
    private final ShoppingService shoppingService;

    public ShoppingController(InputView inputView, OutputView outputView, ShoppingService shoppingService) {
        this.inputView = inputView;
        this.outputView = outputView;
        this.shoppingService = shoppingService;
    }

    public void run() throws IOException {
        String userMainInput = readUserMainInput();

        switch (userMainInput) {
            case "1" -> browseProductProcess();
            case "2" -> issueRandomCoupon();
            case "3" -> cartProcess();
            case "4" -> showUserPointProcess();
            case "5" -> showUserOrderHistoryProcess();
            default -> printExitMessage();
        }
    }

    private String readUserMainInput() throws IOException {
        while (true) {
            try {
                return inputView.readMainInput();
            } catch (IllegalArgumentException e) {
                outputView.printExceptionMessage(e);
            }
        }
    }

    private void browseProductProcess() throws IOException {
        outputView.printProductSimpleInfo(shoppingService.getProducts());
        handleBrowseProductInput();
    }

    private void issueRandomCoupon() throws IOException {
        try {
            int couponDiscountRate = shoppingService.issueRandomCoupon();
            outputView.printIssuedCoupon(couponDiscountRate);
        } catch (IllegalArgumentException e) {
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

    private void handleBrowseProductInput() throws IOException {
        executeBrowseMenuByUserInput(readBrowseProductUserInput());
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

    private void executeBrowseMenuByUserInput(String userInput) throws IOException {
        while (true) {
            try {
                switch (userInput) {
                    case "1" -> showProductDetailProcess();
                    case "2" -> addToCartProcess();
                    default -> run();
                }
                break;
            } catch (IllegalArgumentException e) {
                outputView.printExceptionMessage(e);
            }
        }
    }

    private void showProductDetailProcess() throws IOException {
        String productName = inputView.readProductName();
        ProductDetailInfo productDetail = shoppingService.getProductDetailInfoByName(productName);
        outputView.printProductDetailInfo(productDetail);
        handleBrowseProductInput();
    }

    private String readCartMenuInput() throws IOException {
        while (true) {
            try {
                return inputView.readCartMenuInput();
            } catch (IllegalArgumentException e) {
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
        switch (menuInput) {
            case "1" -> handlePaymentUserInput(cartProducts, shoppingService.getUserDiscountInfo());
            case "2" -> deleteCartProductProcess();
            default -> run();
        }
    }

    private void handlePaymentUserInput(List<ProductSimpleInfo> cartProducts, DiscountInfo userDiscountInfo) throws IOException {
        outputView.printPaymentInfos(cartProducts, userDiscountInfo);

        while(true) {
            try {
                String menuInput = inputView.readPaymentMenuInput();
                switch (menuInput) {
                    case "1" -> {
                        userDiscountInfo.applyCoupon();
                        handlePaymentUserInput(cartProducts, userDiscountInfo);
                        return;
                    }
                    case "2" -> {
                        userDiscountInfo.usePoint();
                        handlePaymentUserInput(cartProducts, userDiscountInfo);
                        return;
                    }
                    default -> {
                        paymentProcess(cartProducts, userDiscountInfo);
                        return;
                    }
                }
            } catch (IllegalArgumentException e) {
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
            } catch (IllegalArgumentException e) {
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
        if (userChoice.equals("y")) {
            run();
        } else {
            printExitMessage();
        }
    }

    private String readAfterPayMenu() throws IOException {
        while(true) {
            try {
                return inputView.readAfterPayMenu();
            } catch (IllegalArgumentException e) {
                outputView.printExceptionMessage(e);
            }
        }
    }

    private boolean deleteCartProductProcess() throws IOException {
        try {
            return deleteCartProduct(readDeleteProductFromCart());
        } catch (IllegalArgumentException e) {
            outputView.printExceptionMessage(e);
            return deleteCartProductProcess();
        }
    }

    private CartProductInfo readDeleteProductFromCart() throws IOException {
        while(true) {
            try {
                return inputView.readDeleteProductFromCart();
            } catch (IllegalArgumentException e) {
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
