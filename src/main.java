import controller.ShoppingController;
import model.user.User;
import service.ShoppingService;
import util.ProductInitializer;
import view.InputView;
import view.OutputView;

import java.io.IOException;

public class main {
    public static void main(String[] args) throws IOException {
        ShoppingController controller = new ShoppingController(
                new InputView(),
                new OutputView(),
                new ShoppingService(ProductInitializer.init(), new User())
        );

        controller.run();
    }
}
