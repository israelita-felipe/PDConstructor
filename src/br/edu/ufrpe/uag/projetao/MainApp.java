package br.edu.ufrpe.uag.projetao;

import org.kairos.core.ActivityFactory;

import br.edu.ufrpe.uag.projetao.control.usuario.LoginController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

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
	primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
	    
	    @Override
	    public void handle(WindowEvent event) {
		Platform.exit();
		System.exit(0);
	    }
	});
	primaryStage.show();
    }
}
