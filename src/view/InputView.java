package view;

import constant.ErrorMessage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputView {
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
}
