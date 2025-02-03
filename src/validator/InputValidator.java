package validator;

import constant.exception.custom.*;

import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputValidator {
    private static final Pattern CART_INPUT_PATTERN = Pattern.compile("^(.+),(\\d+)$");
    private static final Pattern VALID_PAY_PATTERN = Pattern.compile(("([1-9]\\d*)$"));
    private static final Set<String> VALID_MENU_INPUT_VALUES = Set.of("1", "2", "3");
    private static final Set<String> VALID_MAIN_MENU_INPUT_VALUES = Set.of("1", "2", "3", "4", "5", "6");
    private static final String YES = "y";
    private static final String NO = "n";

    private InputValidator() throws ValidatorClassException {
        throw new ValidatorClassException();
    }

    public static void checkEmptyInput(String userInput) {
        if (userInput == null || userInput.isEmpty()) {
            throw new EmptyInputException();
        }
    }

    public static void checkValidMainInput(String userInput) {
        if (!VALID_MAIN_MENU_INPUT_VALUES.contains(userInput)) {
            throw new InvalidMainMenuInputException();
        }
    }

    public static void checkValidMenuInput(String userInput) {
        if (!VALID_MENU_INPUT_VALUES.contains(userInput)) {
            throw new InvalidMenuInputException();
        }
    }

    public static void checkValidPay(String pay) {
        Matcher matcher = VALID_PAY_PATTERN.matcher(pay);

        if (!matcher.matches()) {
            throw new InvalidPayFormatException();
        }
    }

    public static void checkValidCartProductInput(String userInput) {
        Matcher matcher = CART_INPUT_PATTERN.matcher(userInput);

        if (!matcher.matches()) {
            throw new InvalidCartFormatException();
        }
    }

    public static void checkAfterPayMenuInput(String userInput) {
        if (!(userInput.equals(YES) || userInput.equals(NO))) {
            throw new InvalidAfterPayMenuInputException();
        }
    }
}
