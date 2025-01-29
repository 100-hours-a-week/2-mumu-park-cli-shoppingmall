package controller;

import dto.CartProductInfo;
import dto.ProductSimpleInfo;
import service.ShoppingService;
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
            case "1" -> browseProductProcess();
            case "2" -> {
                issueRandomCoupon();
                run();
            }
            case "3" -> {
                List<ProductSimpleInfo> cartProducts = getCartProducts();
                outputView.printCartProducts(cartProducts);
                if (cartProducts.isEmpty())  {
                    run();
                    return;
                }

                String menuInput = readCartMenuInput();
                handleCartMenuInput(menuInput);
            }
            case "4" -> {
                showUserPoint();
                run();
            }
            case "5" -> System.out.println("2");
            case "6" -> printExitMessage();
            default -> System.out.println("default");
        }
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

    private void handleCartMenuInput(String menuInput) throws IOException {
        if (menuInput.equals("1")) {
            // 결제하기
        } else if (menuInput.equals("2")) {
            // 장바구니 상품 제거
        } else {
            // 홈으로
            run();
        }
    }

    private void printExitMessage() {
        outputView.printExitMessage();
    }
}
