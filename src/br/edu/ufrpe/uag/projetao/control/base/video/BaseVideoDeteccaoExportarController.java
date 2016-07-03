/**
 * 
 */
package br.edu.ufrpe.uag.projetao.control.base.video;

import java.io.FileWriter;

import br.edu.ufrpe.uag.projetao.abstracts.AbstractBaseExportarController;
import br.edu.ufrpe.uag.projetao.control.util.video.VideoDigital;
import br.edu.ufrpe.uag.projetao.model.AlocacaoVideoDeteccao;
import br.edu.ufrpe.uag.projetao.model.BaseVideoDeteccao;
import javafx.concurrent.Task;
import javafx.scene.control.Alert;

/**
 * @author israel
 *
 */
public class BaseVideoDeteccaoExportarController extends AbstractBaseExportarController<BaseVideoDeteccao> {

    public BaseVideoDeteccaoExportarController(BaseVideoDeteccao base) {
	super(base);
    }

    @Override
    public void salvar() {
	Task<FileWriter> tarefa = new Task<FileWriter>() {

	    @Override
	    protected FileWriter call() throws Exception {
		int total = getBase().getAlocacaoVideoDeteccaos().size();
		int contador = 0;

		FileWriter writer = new FileWriter(getCaminhoPasta().getText() + "/result.csv");
		for (AlocacaoVideoDeteccao at : getBase().getAlocacaoVideoDeteccaos()) {

		    contador++;

		    // capturando o progresso atual
		    final double progress = contador * 100 / total;

		    // criando uma thread para mudar o valor
		    Task<Double> calcuraProgresso = new Task<Double>() {

			@Override
			protected Double call() throws Exception {
			    getProgresso().setProgress(progress / 100);
			    return progress;
			}
		    };
		    new Thread(calcuraProgresso).start();

		    // escrevendo no arquivo
		    VideoDigital.writeFileTo(at.getVideoDeteccao().getObjeto(),
			    getCaminhoPasta().getText() + "/" + String.valueOf(at.getVideoDeteccao().getId()) + ".mp4");
		    writer.write(at.getVideoDeteccao().getId() + " | " + at.getDeteccaoVideos() + "\n");
		}
		writer.close();

		// arquivo de informação de base
		writer = new FileWriter(getCaminhoPasta().getText() + "/info.txt");
		writer.write(getTituloBase().getText() + "\n");
		writer.write(getDescricaoBase().getText());
		writer.close();

		return writer;
	    }

	    @Override
	    protected void failed() {
		Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
		dialogoErro.setTitle("Erro desconhecido");
		dialogoErro.setHeaderText("Erro ao gravar arquivo de resultado");
		dialogoErro.setContentText(
			"Não foi possível gravar o arquivo\n" + "caso o problema persista reinicie a aplicação");
		dialogoErro.showAndWait();

	    }

	    @Override
	    protected void succeeded() {
		Alert dialogoErro = new Alert(Alert.AlertType.INFORMATION);
		dialogoErro.setTitle("Exportação");
		dialogoErro.setHeaderText("Exportação da base");
		dialogoErro.setContentText("Base exportada com sucesso");
		dialogoErro.showAndWait();
	    }
	};

	// cria thread para não travar a aplicação
	new Thread(tarefa).start();

    }

}
