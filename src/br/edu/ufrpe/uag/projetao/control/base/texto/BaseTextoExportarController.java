/**
 * 
 */
package br.edu.ufrpe.uag.projetao.control.base.texto;

import java.io.FileWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.edu.ufrpe.uag.projetao.abstracts.AbstractBaseExportarController;
import br.edu.ufrpe.uag.projetao.model.AlocacaoTexto;
import br.edu.ufrpe.uag.projetao.model.BaseTexto;
import br.edu.ufrpe.uag.projetao.model.ClassificacaoTexto;
import br.edu.ufrpe.uag.projetao.model.EscolhaClasseTexto;
import javafx.concurrent.Task;
import javafx.scene.control.Alert;

/**
 * @author israel
 *
 */
public class BaseTextoExportarController extends AbstractBaseExportarController<BaseTexto> {

    /**
     * @param base
     */
    public BaseTextoExportarController(BaseTexto base) {
	super(base);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * br.edu.ufrpe.uag.projetao.interfaces.InterfaceBaseExportarController#
     * salvar()
     */
    @Override
    public void salvar() {

	Task<FileWriter> tarefa = new Task<FileWriter>() {

	    @Override
	    protected FileWriter call() throws Exception {
		int total = getBase().getAlocacaoTextos().size();
		int contador = 0;

		FileWriter writer = new FileWriter(getCaminhoPasta().getText() + "/result.csv");
		for (AlocacaoTexto at : getBase().getAlocacaoTextos()) {

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
		    String classe = getClasse(at.getEscolhaClasseTextos(), at.getClassificacaoTextos());
		    writer.write(at.getTexto() + " | " + classe + "\n");
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

    /**
     * Retorna a classe com maior ocorrencia nas classificações
     * 
     * @param classes
     *            lista de classes que poderiam ser escolhidas
     * @param classificacoes
     *            lista de classificações efetivadas
     * @return
     */
    private String getClasse(Set<EscolhaClasseTexto> classes, Set<ClassificacaoTexto> classificacoes) {
	Map<String, List<ClassificacaoTexto>> map = new HashMap<>();
	String classe = "";
	int maior = 0;
	// inicializa o map para contagem
	for (EscolhaClasseTexto escolha : classes) {
	    map.put(escolha.getDescricao(), new LinkedList<>());
	}
	for (ClassificacaoTexto ct : classificacoes) {
	    map.get(ct.getEscolhaClasseTexto().getDescricao()).add(ct);
	}
	for (String key : map.keySet()) {
	    int size = map.get(key).size();
	    if (size > maior) {
		classe = key;
		maior = size;
	    }
	}
	return classe;
    }

}
