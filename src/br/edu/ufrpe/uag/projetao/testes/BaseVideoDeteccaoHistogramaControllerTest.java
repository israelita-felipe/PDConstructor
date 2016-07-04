package br.edu.ufrpe.uag.projetao.testes;

import java.util.List;

import br.edu.ufrpe.uag.projetao.control.base.graficos.BaseGraficosFactory;
import br.edu.ufrpe.uag.projetao.control.util.ControllerFactory;
import br.edu.ufrpe.uag.projetao.model.BaseVideoDeteccao;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * 
 * @author Juan Augusto
 *
 */

public class BaseVideoDeteccaoHistogramaControllerTest extends Application {
    public static void main(String[] args) {
	launch(args);
    }

    @Override
    public void start(Stage dialogStage) {

	dialogStage.setTitle("Test Histograma Video Deteccao");
	List<BaseVideoDeteccao> list = ControllerFactory.getBaseVideoDeteccaoController().getItems();
	if (!list.isEmpty()) {
	    Scene scene = new Scene(BaseGraficosFactory.getBaseVideoDeteccaoHistograma(list.get(0)));
	    dialogStage.setScene(scene);

	    dialogStage.show();
	}
    }
}
