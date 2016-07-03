/**
 * 
 */
package testes;

import java.util.List;

import br.edu.ufrpe.uag.projetao.control.ControllerFactory;
import br.edu.ufrpe.uag.projetao.control.base.imagem.BaseGraficosFactory;
import br.edu.ufrpe.uag.projetao.model.BaseImagemDeteccao;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Juan Augusto
 *
 */
public class BaseImagemDeteccaoHistogramaControllerTest extends Application{
	
	public static void main(String[] args) {
		launch(args);
	    }

	    @Override
	    public void start(Stage dialogStage) {

		dialogStage.setTitle("Test Histograma Imagem Deteccao");
		List<BaseImagemDeteccao> list = ControllerFactory.getBaseImagemDeteccaoController().getItems();
		if (!list.isEmpty()) {
		    Scene scene = new Scene(BaseGraficosFactory.getBaseImagemDeteccaoHistograma(list.get(0)));
		    dialogStage.setScene(scene);

		    dialogStage.show();
		}
	    }

}
