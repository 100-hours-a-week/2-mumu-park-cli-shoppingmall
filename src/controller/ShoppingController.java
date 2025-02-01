package controller;

import dto.*;
import service.ShoppingService;
import validator.PaymentValidator;
import view.InputView;
import view.OutputView;

import java.io.IOException;
import java.util.List;
import java.util.Map;

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
            case "1" -> {
                browseProductProcess();
            }
            case "2" -> {
                issueRandomCoupon();
                run();
            }
            case "3" -> {
                cartProcess();
            }
            case "4" -> {
                showUserPoint();
                run();
            }
            case "5" -> System.out.println("2");
            default -> {
                printExitMessage();
            }
        }
    }

    private void cartProcess() throws IOException {
        List<ProductSimpleInfo> cartProducts = getCartProducts();
        outputView.printCartProducts(cartProducts);
        if (cartProducts.isEmpty())  {
            run();
            return;
        }

        handleCartMenuInput(readCartMenuInput(), cartProducts);
    }

    private void browseProductProcess() throws IOException {
        Map<String, List<ProductSimpleInfo>> productSimpleInfos = shoppingService.getProducts();
        outputView.printProductSimpleInfo(productSimpleInfos);

        handleBrowseProductInput();
    }

    private void handleBrowseProductInput() throws IOException {
        processBrowseProductUserInput(readBrowseProductUserInput());
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

    // Todo : 메서드 네이밍 다시하기
    private void processBrowseProductUserInput(String userInput) throws IOException {
        while (true) {
            try {
                if (userInput.equals("1")) {
                    outputView.printProductDetailInfo(
                            shoppingService.getProductDetailInfoByName(
                                    inputView.readProductName()
                            )
                    );
                    handleBrowseProductInput();
                } else if (userInput.equals("2")){
                    CartProductInfo cartProductInfo = inputView.readCartProduct();
                    shoppingService.addProductToCart(cartProductInfo);
                    outputView.printSuccessAddCartMessage(cartProductInfo);
                    browseProductProcess();
                } else {
                    run();
                }
                break;
            } catch (IllegalArgumentException e) {
                outputView.printExceptionMessage(e);
            }
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

    private boolean issueRandomCoupon() throws IOException {
        while (true) {
            try {
                int couponDiscountRate = shoppingService.issueRandomCoupon();
                outputView.printIssuedCoupon(couponDiscountRate);
                return true;
            } catch (IllegalArgumentException e) {
                outputView.printExceptionMessage(e);
                run();
            }
        }
    }

    private void showUserPoint() {
        outputView.printUserPoint(shoppingService.getUserPoint());
    }

    private List<ProductSimpleInfo> getCartProducts() {
        return shoppingService.getCartProducts();
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

    private void handleCartMenuInput(String menuInput, List<ProductSimpleInfo> cartProducts) throws IOException {
        if (menuInput.equals("1")) {
            // 결제하기
            DiscountInfo userDiscountInfo = shoppingService.getUserDiscountInfo();
            paymentProcess(cartProducts, userDiscountInfo);
        } else if (menuInput.equals("2")) {
            // 장바구니 상품 제거
            deleteCartProductProcess();
        } else {
            // 홈으로
            run();
        }
    }

    private void paymentProcess(List<ProductSimpleInfo> cartProducts, DiscountInfo userDiscountInfo) throws IOException {
        // Todo : 메서드 분리 필요
        outputView.printPaymentInfos(cartProducts, userDiscountInfo);

        while(true) {
            try {
                String menuInput = inputView.readPaymentMenuInput();

                switch (menuInput) {
                    case "1" -> {
                        userDiscountInfo.applyCoupon();
                        paymentProcess(cartProducts, userDiscountInfo);
                        return;
                    }
                    case "2" -> {
                        userDiscountInfo.usePoint();
                        paymentProcess(cartProducts, userDiscountInfo);
                        return;
                    }
                    default -> {
                        int finalPrice = userDiscountInfo.getFinalPrice(cartProducts);
                        outputView.printFinalPrice(finalPrice);
                        int payAmount = readPayAmount(finalPrice);

                        ChangeAndPoint changeAndPoint = shoppingService.paymentProgress(
                                new PaymentInfo(
                                        cartProducts,
                                        userDiscountInfo.isCouponUsed() ? userDiscountInfo.getCouponRate() : 0,
                                        userDiscountInfo.getAppliedPoint(),
                                        finalPrice,
                                        payAmount
                                )
                        );

                        outputView.printPaymentResult(payAmount, changeAndPoint);

                        String userChoice = readAfterPayMenu();
                        if (userChoice.equals("y")) {
                            run();
                            return;
                        }

                        printExitMessage();
                        return;
                    }
                }
            } catch (IllegalArgumentException e) {
                outputView.printExceptionMessage(e);
            }
        }
    }

    private boolean deleteCartProductProcess() throws IOException {
        while(true) {
            try {
                CartProductInfo deleteInfo = readDeleteProductFromCart();
                shoppingService.deleteCartProduct(deleteInfo);
                outputView.printSuccessDeleteCartProduct(deleteInfo);
                cartProcess();
                return true;
            } catch (IllegalArgumentException e) {
                outputView.printExceptionMessage(e);
            }
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

    private String readAfterPayMenu() throws IOException {
        while(true) {
            try {
                return inputView.readAfterPayMenu();
            } catch (IllegalArgumentException e) {
                outputView.printExceptionMessage(e);
            }
        }
    }

    private void printExitMessage() {
        outputView.printExitMessage();
    }
}
