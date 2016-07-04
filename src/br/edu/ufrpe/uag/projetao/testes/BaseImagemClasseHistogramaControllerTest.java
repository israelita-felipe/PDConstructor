/**
 * 
 */
package br.edu.ufrpe.uag.projetao.testes;

import java.util.List;

import br.edu.ufrpe.uag.projetao.control.base.graficos.BaseGraficosFactory;
import br.edu.ufrpe.uag.projetao.control.util.ControllerFactory;
import br.edu.ufrpe.uag.projetao.model.BaseImagemClasse;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author israel
 *
 */
public class BaseImagemClasseHistogramaControllerTest extends Application {

    public static void main(String[] args) {
	launch(args);
    }

    @Override
    public void start(Stage dialogStage) {

	dialogStage.setTitle("Test Histograma Imagem Classe");
	List<BaseImagemClasse> list = ControllerFactory.getBaseImagemClasseController().getItems();
	if (!list.isEmpty()) {
	    Scene scene = new Scene(BaseGraficosFactory.getBaseImagemClasseHistograma(list.get(0)));
	    dialogStage.setScene(scene);

	    dialogStage.show();
	}
    }

}
