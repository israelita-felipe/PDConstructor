/**
 * 
 */
package testes;

import br.edu.ufrpe.uag.projetao.control.base.imagem.BaseImagemClasseHistogramaFactory;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author israel
 *
 */
public class BaseImagemClasseHistogramaControllerTest extends Application{
    public static void main(String[] args) {
	launch(args);
    }

    @Override
    public void start(Stage dialogStage) {

	dialogStage.setTitle("Test Histograma Imagem Classe");

	Scene scene = new Scene(BaseImagemClasseHistogramaFactory.getBaseImagemClasseHistograma());
	dialogStage.setScene(scene);

	dialogStage.show();
    }

}
