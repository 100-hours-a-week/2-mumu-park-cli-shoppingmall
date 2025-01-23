package controller;

import service.ShoppingService;
import view.InputView;
import view.OutputView;

import java.io.IOException;

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
            case "1" -> System.out.println("hi");
            case "2" -> {
                issueRandomCoupon();
                run();
            }
            case "3" -> System.out.println("2");
            case "4" -> System.out.println("2");
            case "5" -> System.out.println("2");
            default -> System.out.println("default");
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
}
