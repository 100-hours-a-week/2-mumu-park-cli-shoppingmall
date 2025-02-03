package view;

import constant.exception.custom.*;
import dto.CartProductInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static validator.InputValidator.*;

public class InputView {
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static final String CART_PRODUCT_SEPARATOR = ",";
    private final OutputView outputView;

    public InputView(OutputView outputView) {
        this.outputView = outputView;
    }

    public String readMainInput() throws IOException {
        while (true) {
            try {
                printMainPage();
                String userInput = br.readLine();
                checkValidMainInput(userInput);

                return userInput;
            } catch (RuntimeException e) {
                outputView.printExceptionMessage(e);
            }
        }
    }

    public String readBrowseProductUserInput() throws IOException {
        while (true) {
            try {
                System.out.print(
                        """
                                                
                                아래의 메뉴를 선택해주세요
                                1. 상품 상세보기
                                2. 장바구니 담기
                                3. 홈으로 돌아가기
                                """
                );

                String userInput = br.readLine();
                checkValidMenuInput(userInput);
                return userInput;
            } catch (IllegalArgumentException e) {
                outputView.printExceptionMessage(e);
            }
        }
    }

    public String readProductName() throws IOException {
        while (true) {
            try {
                System.out.println("상품 상세를 보고싶은 상품명을 입력해주세요.");
                String productName = br.readLine();
                checkEmptyInput(productName);

                return productName;
            } catch (EmptyInputException e) {
                outputView.printExceptionMessage(e);
            }
        }
    }

    public CartProductInfo readCartProduct() throws IOException {
        while (true) {
            try {
                System.out.println("장바구니에 담을 상품명과 수량을 입력해주세요. (ex. 반팔,1)");
                String cartProductInput = br.readLine();
                checkEmptyInput(cartProductInput);

                return handleCartProductInput(cartProductInput);
            } catch (EmptyInputException e) {
                outputView.printExceptionMessage(e);
            }
        }
    }

    public String readCartMenuInput() throws IOException {
        while (true) {
            try {
                System.out.println("아래 메뉴를 선택해주세요.");
                System.out.println("1. 결제하기");
                System.out.println("2. 장바구니 상품 제거");
                System.out.println("3. 홈으로 돌아가기");

                String userInput = br.readLine();
                checkValidMenuInput(userInput);

                return userInput;
            } catch (InvalidMenuInputException e) {
                outputView.printExceptionMessage(e);
            }
        }
    }

    public CartProductInfo readDeleteProductFromCart() throws IOException {
        while (true) {
            try {
                System.out.println("제거하고 싶은 상품의 상품명과 수량을 입력해주세요. (ex. 연청바지,1)");
                String userInput = br.readLine();
                return handleCartProductInput(userInput);
            } catch (InvalidCartFormatException e) {
                outputView.printExceptionMessage(e);
            }
        }
    }

    public String readPaymentMenuInput() throws IOException {
        while (true) {
            try {
                System.out.println("아래 메뉴를 선택해주세요.");
                System.out.println("1. 쿠폰 사용하기");
                System.out.println("2. 포인트 사용하기");
                System.out.println("3. 결제하기");

                String userInput = br.readLine();
                checkValidMenuInput(userInput);

                return userInput;
            } catch (InvalidMenuInputException e) {
                outputView.printExceptionMessage(e);
            }
        }
    }

    public int readPayAmount() throws IOException, NumberFormatException {
        while (true) {
            try {
                System.out.println("돈을 지불해주세요.");
                String userInput = br.readLine();
                checkValidPay(userInput);

                return Integer.parseInt(userInput);
            } catch (InvalidPayFormatException e) {
                outputView.printExceptionMessage(e);
            }
        }
    }

    public String readAfterPayMenu() throws IOException {
        while (true) {
            try {
                System.out.println("\n구매 감사합니다. 계속 쇼핑하시겠어요? (y or n)");

                String userInput = br.readLine();
                checkAfterPayMenuInput(userInput);

                return userInput;
            } catch (InvalidAfterPayMenuInputException e) {
                outputView.printExceptionMessage(e);
            }
        }
    }

    private void printMainPage() {
        System.out.println("안녕하세요. 무무 쇼핑몰 입니다.");
        System.out.println();
        System.out.println("1. 상품 구경하기");
        System.out.println("2. 랜덤쿠폰 발급받기");
        System.out.println("3. 장바구니 보러가기");
        System.out.println("4. 포인트 확인하기");
        System.out.println("5. 주문내역 확인하기");
        System.out.println("6. 쇼핑 그만하기");

        System.out.println("메뉴중 하나를 선택해주세요. (ex. 1)");
    }

    private CartProductInfo handleCartProductInput(String userInput) {
        checkValidCartProductInput(userInput);

        String[] split = userInput.split(CART_PRODUCT_SEPARATOR);
        return new CartProductInfo(
                split[0],
                Integer.parseInt(split[1])
        );
    }
}
