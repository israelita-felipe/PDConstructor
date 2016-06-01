
import org.kairos.core.ActivityFactory;

import br.edu.ufrpe.uag.projetao.control.usuario.LoginController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class MainApp extends Application {
    public static void main(String[] args) {
	launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

	StackPane root = new StackPane();
	primaryStage.setScene(new Scene(root));
	ActivityFactory factory = new ActivityFactory(primaryStage);

	factory.startActivity(LoginController.class);
	primaryStage.show();
    }
}
