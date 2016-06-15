/**
 * 
 */
package br.edu.ufrpe.uag.projetao.control.base.imagem;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.kairos.components.TextInputLayout;
import org.kairos.core.Fragment;

import br.edu.ufrpe.uag.projetao.control.ControllerFactory;
import br.edu.ufrpe.uag.projetao.control.DetachedCriteriaFactory;
import br.edu.ufrpe.uag.projetao.control.UsuarioController;
import br.edu.ufrpe.uag.projetao.control.base.imagem.util.ZoomnableImageView;
import br.edu.ufrpe.uag.projetao.control.hibernate.TransactionManager;
import br.edu.ufrpe.uag.projetao.control.util.imagem.ImagemDigital;
import br.edu.ufrpe.uag.projetao.interfaces.InterfaceController;
import br.edu.ufrpe.uag.projetao.model.AlocacaoImagemClasse;
import br.edu.ufrpe.uag.projetao.model.ClasssificacaoImagemClasse;
import br.edu.ufrpe.uag.projetao.model.EscolhaImagemClasse;
import br.edu.ufrpe.uag.projetao.model.LiberacaoBaseImagemClasse;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

/**
 * @author israel
 *
 */
public class BaseImagemClasseLiberacaoController extends Fragment {

    private FXMLLoader loader;

    @FXML
    private TableView<LiberacaoBaseImagemClasse> tabelaLiberacoesImagemClasse;
    @FXML
    private ObservableList<Node> opcoesDeClasses;
    @FXML
    private Pagination pagination;

    private List<AlocacaoImagemClasse> alocacoes;

    @Override
    public void onCreateView(FXMLLoader fxmlLoader) {
	this.loader = fxmlLoader;
	list();
    }

    @FXML
    private void list() {
	getChildren().clear();
	this.loader.setLocation(getClass()
		.getResource("/br/edu/ufrpe/uag/projetao/view/base/imagem/BaseImagemClasseLiberacaoView.fxml"));
	try {
	    this.loader.load();
	    this.tabelaLiberacoesImagemClasse.setItems(FXCollections.observableList(ControllerFactory
		    .getLiberacaoBaseImagemClasseController().getItemsFromCriteria(DetachedCriteriaFactory
			    .getLiberacoesBaseImagemClasseDoEscravo(UsuarioController.currentEscravo))));
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    private Node createPage(int pageIndex) {

	// cria as caixas de armazenamento dos componentes visuais
	BorderPane borderPane = new BorderPane();
	VBox escolha = new VBox();
	escolha.setPadding(new Insets(8));
	escolha.setSpacing(8);
	escolha.getStyleClass().add("card");
	Label label = new Label("Escolha uma classe");

	escolha.getChildren().add(label);

	// cria caixa de descrição da base
	TextInputLayout descricaoLayout = new TextInputLayout();
	descricaoLayout.setPadding(new Insets(16));
	TextArea descricaoTextArea = new TextArea();
	descricaoTextArea.setEditable(false);
	descricaoTextArea.setWrapText(true);
	descricaoTextArea.setText(this.alocacoes.get(pageIndex).getBaseImagemClasse().getDescricao());
	descricaoTextArea.setPromptText("Descrição da base");
	descricaoLayout.getChildren().add(descricaoTextArea);
	descricaoLayout.setMaxHeight(80);
	descricaoLayout.setMinHeight(80);
	descricaoLayout.setPrefHeight(80);
	borderPane.setTop(descricaoLayout);

	// cria caixa de texto a classificar
	Image image = SwingFXUtils
		.toFXImage(ImagemDigital.toImage(this.alocacoes.get(pageIndex).getImagemClasse().getObjeto()), null);
	ImageView imageView = new ImageView(image);
	imageView.preserveRatioProperty().set(true);

	ScrollPane scroll = new ScrollPane();

	ZoomnableImageView zoom = new ZoomnableImageView(imageView, scroll, 100.0);

	BorderPane center = new BorderPane();
	center.setCenter(scroll);
	center.setRight(zoom.getControles());
	center.setPadding(new Insets(8));
	center.getStyleClass().add("card");

	borderPane.setCenter(center);

	// grupo de seleção do radiobutton
	ToggleGroup group = new ToggleGroup();
	for (EscolhaImagemClasse classe : ControllerFactory.getEscolhaClasseImagemClasseController()
		.getItemsFromCriteria(DetachedCriteriaFactory.getEscolhaImagemClassePorLiberacaoEAlocacao(
			tabelaLiberacoesImagemClasse.getSelectionModel().getSelectedItem(),
			alocacoes.get(pageIndex)))) {

	    // cria um radiobutton para cada opção de classe
	    RadioButton opcao = new RadioButton(classe.getDescricao());
	    opcao.setUserData(classe);

	    for (ClasssificacaoImagemClasse classificacaoEfetivada : ControllerFactory
		    .getClassificacaoImagemClasseController()
		    .getItemsFromCriteria(DetachedCriteriaFactory.getClassificacaoImagemClassePorEscravoEAlocacao(
			    UsuarioController.currentEscravo, this.alocacoes.get(pageIndex)))) {
		if (classificacaoEfetivada.getEscolhaImagemClasse().getDescricao().equals(classe.getDescricao())) {
		    opcao.setSelected(true);
		    break;
		}
	    }

	    opcao.setToggleGroup(group);
	    escolha.getChildren().add(opcao);
	}

	// adiciona escutador para poder gravar ou alterar
	group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

	    public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {

		InterfaceController<ClasssificacaoImagemClasse> controller = ControllerFactory
			.getClassificacaoImagemClasseController();

		ClasssificacaoImagemClasse classificacaoAtual = null;
		for (ClasssificacaoImagemClasse classificacaoEfetivada : ControllerFactory
			.getClassificacaoImagemClasseController()
			.getItemsFromCriteria(DetachedCriteriaFactory.getClassificacaoImagemClassePorEscravoEAlocacao(
				UsuarioController.currentEscravo, alocacoes.get(pageIndex)))) {
		    if (classificacaoEfetivada.getEscolhaImagemClasse() != null
			    && classificacaoEfetivada.getEscolhaImagemClasse().getDescricao()
				    .equals(((EscolhaImagemClasse) old_toggle.getUserData()).getDescricao())) {
			classificacaoAtual = classificacaoEfetivada;
			break;
		    }
		}

		// se já tem classificação atualiza
		if (classificacaoAtual != null) {
		    try {
			TransactionManager.begin();
			controller.prepareEdit(classificacaoAtual);
			controller.getSelected().setEscolhaImagemClasse((EscolhaImagemClasse) new_toggle.getUserData());
			controller.update();
		    } catch (Throwable t) {
			t.printStackTrace();
		    } finally {
			TransactionManager.end();
		    }
		} else// se não tem classificação cria uma nova
		if (new_toggle.getUserData() != null) {
		    try {
			TransactionManager.begin();
			controller.prepareCreate();
			controller.getSelected().setAlocacaoImagemClasse(alocacoes.get(pageIndex));
			controller.getSelected().setUsuario(UsuarioController.currentEscravo);
			controller.getSelected().setEscolhaImagemClasse((EscolhaImagemClasse) new_toggle.getUserData());
			controller.create();
		    } catch (Throwable t) {
			t.printStackTrace();
		    } finally {
			TransactionManager.end();
		    }
		}
		// atualizando as alocações com a votação atual.
		// fazendo uma nova busca no banco
		atualizaAlocacoes();
	    }
	});

	HBox.setHgrow(escolha, Priority.SOMETIMES);
	BorderPane bp = new BorderPane();
	bp.setPadding(new Insets(0, 0, 0, 8));
	bp.setCenter(escolha);
	borderPane.setRight(bp);

	return borderPane;
    }

    @FXML
    private void paginator() {
	LiberacaoBaseImagemClasse liberacao = tabelaLiberacoesImagemClasse.getSelectionModel().getSelectedItem();

	if (liberacao != null) {
	    getChildren().clear();

	    this.loader.setLocation(getClass().getResource(
		    "/br/edu/ufrpe/uag/projetao/view/base/imagem/classificacao/BaseImagemClasseClassificacaoView.fxml"));

	    try {
		this.loader.load();

		atualizaAlocacoes();

		pagination.setPageFactory(new Callback<Integer, Node>() {

		    @Override
		    public Node call(Integer pageIndex) {
			atualizaAlocacoes();
			return createPage(pageIndex);
		    }
		});

		pagination.setPageCount(alocacoes.size());
		pagination.setCurrentPageIndex(buscaPrimeiraAlocacaoSemDeteccao());

	    } catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	} else {
	    Alert dialogoErro = new Alert(Alert.AlertType.INFORMATION);
	    dialogoErro.setTitle("Informação");
	    dialogoErro.setHeaderText("Seleção da base");
	    dialogoErro.setContentText("Selecione uma base para poder classificar");
	    dialogoErro.showAndWait();
	    list();
	}
    }

    private void atualizaAlocacoes() {
	this.alocacoes = ControllerFactory.getAlocacaoImagemClasseController()
		.getItemsFromCriteria(DetachedCriteriaFactory.getAlocacoesImagemPorLiberacao(
			this.tabelaLiberacoesImagemClasse.getSelectionModel().getSelectedItem()));
    }

    /**
     * Ordena as alocações de forma que as com menos detecções fiquem primeiro
     */
    public void sortAlocacoes() {
	Collections.sort(alocacoes, new Comparator<AlocacaoImagemClasse>() {
	    @Override
	    public int compare(AlocacaoImagemClasse o1, AlocacaoImagemClasse o2) {
		return o1.getClasssificacaoImagemClasses().size() - o2.getClasssificacaoImagemClasses().size();
	    }
	});
    }

    public int buscaPrimeiraAlocacaoSemDeteccao() {
	sortAlocacoes();
	return 0;
    }

}
