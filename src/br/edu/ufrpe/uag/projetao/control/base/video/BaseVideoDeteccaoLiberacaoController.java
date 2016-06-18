package br.edu.ufrpe.uag.projetao.control.base.video;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import org.kairos.components.TextInputLayout;
import org.kairos.core.Fragment;

import br.edu.ufrpe.uag.projetao.control.ControllerFactory;
import br.edu.ufrpe.uag.projetao.control.DetachedCriteriaFactory;
import br.edu.ufrpe.uag.projetao.control.UsuarioController;
import br.edu.ufrpe.uag.projetao.control.base.imagem.util.CustomRectangle;
import br.edu.ufrpe.uag.projetao.control.hibernate.TransactionManager;
import br.edu.ufrpe.uag.projetao.control.util.imagem.ImagemDigital;
import br.edu.ufrpe.uag.projetao.interfaces.InterfaceController;
import br.edu.ufrpe.uag.projetao.model.AlocacaoImagemDeteccao;
import br.edu.ufrpe.uag.projetao.model.AlocacaoVideoDeteccao;
import br.edu.ufrpe.uag.projetao.model.DeteccaoImagem;
import br.edu.ufrpe.uag.projetao.model.DeteccaoVideo;
import br.edu.ufrpe.uag.projetao.model.LiberacaoBaseImagemDeteccao;
import br.edu.ufrpe.uag.projetao.model.LiberacaoBaseVideoDeteccao;
import javafx.collections.FXCollections;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Pagination;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.shape.StrokeLineCap;
import javafx.util.Callback;

public class BaseVideoDeteccaoLiberacaoController extends Fragment{
	private FXMLLoader loader;

    @FXML
    private TableView<LiberacaoBaseVideoDeteccao> tabelaLiberacoesVideoDeteccao;
    @FXML
    private Pagination pagination;
    @FXML
    private List<AlocacaoVideoDeteccao> alocacoes;

    private ListView<DeteccaoVideo> deteccoes;

    private AlocacaoVideoDeteccao alocacaoAtual;

    private InterfaceController<DeteccaoVideo> deteccaoController = ControllerFactory.getDeteccaoVideoController();

    private Group group = new Group();

    @Override
    public void onCreateView(FXMLLoader fxmlLoader) {
	this.loader = fxmlLoader;
	list();
    }

    @FXML
    private void list() {
	getChildren().clear();
	this.loader.setLocation(getClass()
		.getResource("/br/edu/ufrpe/uag/projetao/view/base/video/BaseVideoDeteccaoLiberacaoView.fxml"));
	try {
	    this.loader.load();
	    this.tabelaLiberacoesVideoDeteccao.setItems(FXCollections.observableList(ControllerFactory
		    .getLiberacaoBaseVideoDeteccaoController().getItemsFromCriteria(DetachedCriteriaFactory
			    .getLiberacoesBaseVideoDeteccaoDoEscravo(UsuarioController.currentEscravo))));
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    /**
     * Cria uma página para detecção dado o índice da alocação
     * 
     * @param PageIndex
     *            número da página e da alocação ao qual será criando uma tela
     *            de detecção
     * @return Página para ser adicionada no paginador
     * @throws IOException
     */
    private Node createPage(int pageIndex) throws IOException {
    	return null;
    }

   
    
    @FXML
    private void paginator() {
	
    }

    /**
     * Ordena as alocações de forma que as com menos detecções fiquem primeiro
     */
    public void sortAlocacoes() {
	Collections.sort(alocacoes, new Comparator<AlocacaoVideoDeteccao>() {
	    @Override
	    public int compare(AlocacaoVideoDeteccao o1, AlocacaoVideoDeteccao o2) {
		return o1.getDeteccaoVideos().size() - o2.getDeteccaoVideos().size();
	    }
	});
    }

    public int buscaPrimeiraAlocacaoSemDeteccao() {
	sortAlocacoes();
	/*
	 * int i = 0; while (i < alocacoes.size() &&
	 * !alocacoes.get(i).getDeteccaoImagems().isEmpty()) { i++; } return i
	 * == 0 ? 0 : i - 1;
	 */
	return 0;
    }

    public DeteccaoVideo selecionaDeteccao() {

	return null;
    }
}
