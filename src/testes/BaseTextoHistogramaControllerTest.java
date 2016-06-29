/**
 * 
 */
package testes;

import java.util.List;

import br.edu.ufrpe.uag.projetao.control.ControllerFactory;
import br.edu.ufrpe.uag.projetao.control.base.imagem.BaseGraficosFactory;
import br.edu.ufrpe.uag.projetao.model.BaseTexto;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author israel
 *
 */
public class BaseTextoHistogramaControllerTest extends Application {
   

    public static void main(String[] args) {
	launch(args);
    }

    @Override
    public void start(Stage dialogStage) {

	dialogStage.setTitle("Test Histograma Texto");
	List<BaseTexto> list = ControllerFactory.getBaseTextoController().getItems();
	if (!list.isEmpty()) {
	    Scene scene = new Scene(BaseGraficosFactory.getBaseTextoHistograma(list.get(0)));
	    dialogStage.setScene(scene);

	    dialogStage.show();
	}
    }

}
