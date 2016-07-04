/**
 * 
 */
package br.edu.ufrpe.uag.projetao.control.base.imagem.classificacao;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import br.edu.ufrpe.uag.projetao.abstracts.AbstractBaseExportarController;
import br.edu.ufrpe.uag.projetao.control.util.ImagemDigital;
import br.edu.ufrpe.uag.projetao.model.AlocacaoImagemClasse;
import br.edu.ufrpe.uag.projetao.model.BaseImagemClasse;
import br.edu.ufrpe.uag.projetao.model.ClasssificacaoImagemClasse;
import br.edu.ufrpe.uag.projetao.model.EscolhaImagemClasse;
import javafx.concurrent.Task;
import javafx.scene.control.Alert;

/**
 * @author israel
 *
 */
public class BaseImagemClasseExportarController extends AbstractBaseExportarController<BaseImagemClasse> {

    /**
     * @param base
     */
    public BaseImagemClasseExportarController(BaseImagemClasse base) {
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

	Task<File> tarefa = new Task<File>() {

	    @Override
	    protected File call() throws Exception {
		int total = getBase().getAlocacaoImagemClasses().size();
		int contador = 0;

		for (AlocacaoImagemClasse at : getBase().getAlocacaoImagemClasses()) {

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
		    String classe = getClasse(at.getEscolhaImagemClasses(), at.getClasssificacaoImagemClasses());
		    ImagemDigital.salvarImagemCorPNG(ImagemDigital.toImage(at.getImagemClasse().getObjeto()),
			    getCaminhoPasta().getText() + "/" + at.getImagemClasse().getId() + "_" + classe + ".png");
		}

		// arquivo de informação de base
		FileWriter writer = new FileWriter(getCaminhoPasta().getText() + "/info.txt");
		writer.write(getTituloBase().getText() + "\n");
		writer.write(getDescricaoBase().getText());
		writer.close();

		return new File(getCaminhoPasta().getText());
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
     * Verifica qual a maior classe
     * 
     * @param classes
     *            lista de possíveis classes
     * @param classificacoes
     *            lista de classificações efetivadas no banco
     * @return
     */
    private String getClasse(Set<EscolhaImagemClasse> classes, Set<ClasssificacaoImagemClasse> classificacoes) {
	Map<String, List<ClasssificacaoImagemClasse>> map = new HashMap<>();
	String classe = "";
	int maior = 0;
	// inicializa o map para contagem
	for (EscolhaImagemClasse escolha : classes) {
	    map.put(escolha.getDescricao(), new LinkedList<>());
	}
	for (ClasssificacaoImagemClasse ct : classificacoes) {
	    map.get(ct.getEscolhaImagemClasse().getDescricao()).add(ct);
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
