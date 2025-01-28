package controller;

import dto.ProductDetailInfo;
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
            case "3" -> System.out.println("2");
            case "4" -> {
                showUserPoint();
                run();
            }
            case "5" -> System.out.println("2");
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
                    String productName = inputView.readProductName();
                    ProductDetailInfo detailInfo = shoppingService.getProductDetailInfoByName(productName);
                    outputView.printProductDetailInfo(detailInfo);
                    handleBrowseProductInput();
                } else {
                    System.out.println("Todo : 장바구니 담기 구현");
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
}
