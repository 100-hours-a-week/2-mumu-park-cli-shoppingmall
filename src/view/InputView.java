package view;

import constant.ErrorMessage;
import dto.CartProductInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputView {
    private static final Pattern CART_INPUT_PATTERN = Pattern.compile("^(.+),(\\d+)$");
    private BufferedReader br;
    public InputView() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    public String readMainInput() throws IOException {
        printMainPage();
        String userInput = br.readLine();
        checkValidMainInput(userInput);

        return userInput;
    }

    public String readBrowseProductUserInput() throws IOException {
        System.out.print(
                """
                
                아래의 메뉴를 선택해주세요
                1. 상품 상세보기
                2. 장바구니 담기
                """
        );

        String userInput = br.readLine();
        checkValidBrowseProductInput(userInput);
        return userInput;
    }

    public String readProductName() throws IOException {
        System.out.println("상품 상세를 보고싶은 상품명을 입력해주세요.");
        String productName = br.readLine();
        checkEmptyInput(productName);

        return productName;
    }

    public CartProductInfo readCartProduct() throws IOException {
        System.out.println("장바구니에 담을 상품명과 수량을 입력해주세요. (ex. 반팔,1)");
        String cartProductInput = br.readLine();
        checkEmptyInput(cartProductInput);

        return handleCartProductInput(cartProductInput);
    }

    private void printMainPage() {
        System.out.println("안녕하세요. 무무 쇼핑몰 입니다.");
        System.out.println();
        System.out.println("1. 상품 구경하기");
        System.out.println("2. 랜덤쿠폰 발급받기");
        System.out.println("3. 장바구니 보러가기");
        System.out.println("4. 포인트 확인하기");
        System.out.println("5. 쇼핑 그만하기");

        System.out.println("메뉴중 하나를 선택해주세요. (ex. 1)");
    }

    private void checkEmptyInput(String userInput) {
        if (userInput == null || userInput.isEmpty()) {
            throw new IllegalArgumentException(ErrorMessage.EMPTY_INPUT.getMessage());
        }
    }

    private void checkValidMainInput(String userInput) {
        String[] validMainInputValues = {"1", "2", "3", "4", "5"};

        boolean flag = true;
        for (String validMainInputValue : validMainInputValues) {
            if (userInput.equals(validMainInputValue)) {
                flag = false;
                break;
            }
        }

        if (flag) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_MAIN_INPUT.getMessage());
        }
    }

    private void checkValidBrowseProductInput(String userInput) {
        String[] validMainInputValues = {"1", "2"};

        boolean flag = true;
        for (String validMainInputValue : validMainInputValues) {
            if (userInput.equals(validMainInputValue)) {
                flag = false;
                break;
            }
        }

        if (flag) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_BROWSE_PRODUCT_INPUT.getMessage());
        }
    }

    private CartProductInfo handleCartProductInput(String userInput) {
        Matcher matcher = CART_INPUT_PATTERN.matcher(userInput);

        if (!matcher.matches()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_CART_FORMAT.getMessage());
        }

        return new CartProductInfo(
                matcher.group(1),
                Integer.parseInt(matcher.group(2))
        );
    }
}
