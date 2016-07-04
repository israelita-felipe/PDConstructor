/**
 * 
 */
package br.edu.ufrpe.uag.projetao.control.base.texto;

import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import org.kairos.components.TextInputLayout;
import org.kairos.core.Fragment;

import br.edu.ufrpe.uag.projetao.control.hibernate.TransactionManager;
import br.edu.ufrpe.uag.projetao.control.usuario.UsuarioController;
import br.edu.ufrpe.uag.projetao.control.util.ControllerFactory;
import br.edu.ufrpe.uag.projetao.control.util.DetachedCriteriaFactory;
import br.edu.ufrpe.uag.projetao.interfaces.InterfaceController;
import br.edu.ufrpe.uag.projetao.model.AlocacaoTexto;
import br.edu.ufrpe.uag.projetao.model.ClassificacaoTexto;
import br.edu.ufrpe.uag.projetao.model.EscolhaClasseTexto;
import br.edu.ufrpe.uag.projetao.model.LiberacaoBaseTexto;
import br.edu.ufrpe.uag.projetao.model.Usuario;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.Pagination;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.util.Callback;

/**
 * @author israel
 * @author Bruno Barros
 *
 */
public class BaseTextoLiberacaoController extends Fragment {

    private FXMLLoader loader;

    @FXML
    private TableView<LiberacaoBaseTexto> tabelaLiberacoesTexto;
    @FXML
    private ObservableList<Node> opcoesDeClasses;
    @FXML
    private Pagination pagination;

    private List<ClassificacaoTexto> classificacoes;

    @Override
    public void onCreateView(FXMLLoader fxmlLoader) {
	this.loader = fxmlLoader;
	list();
    }

    @FXML
    private void list() {
	getChildren().clear();
	this.loader.setLocation(getClass().getResource("BaseTextoLiberacaoView.fxml"));
	try {
	    this.loader.load();
	    this.tabelaLiberacoesTexto.setItems(FXCollections.observableList(
		    ControllerFactory.getLiberacaoBaseTextoController().getItemsFromCriteria(DetachedCriteriaFactory
			    .getLiberacoesBaseTextoDoEscravo(UsuarioController.currentEscravo))));
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    private Node createPage(int pageIndex) {

	// cria as caixas de armazenamento dos componentes visuais
	BorderPane borderPane = new BorderPane();
	borderPane.setPadding(new Insets(8));
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
	descricaoTextArea.setText(this.classificacoes.get(pageIndex).getAlocacaoTexto().getBaseTexto().getDescricao());
	descricaoTextArea.setPromptText("Descrição da base");
	descricaoLayout.getChildren().add(descricaoTextArea);
	descricaoLayout.setPrefHeight(100);
	descricaoLayout.setMinHeight(100);
	descricaoLayout.setMaxHeight(100);
	borderPane.setTop(descricaoLayout);

	// cria caixa de texto a classificar
	TextInputLayout textoLayout = new TextInputLayout();
	TextArea textoTextArea = new TextArea();
	textoTextArea.setEditable(false);
	textoTextArea.setWrapText(true);
	textoTextArea.setText(this.classificacoes.get(pageIndex).getAlocacaoTexto().getTexto());
	textoTextArea.setPromptText("Classifique o texto");
	textoLayout.getChildren().add(textoTextArea);
	borderPane.setCenter(textoLayout);

	BorderPane center = new BorderPane();
	center.setCenter(textoLayout);
	center.setPadding(new Insets(8));
	center.getStyleClass().add("card");

	borderPane.setCenter(center);

	// grupo de seleção do radiobutton
	ToggleGroup group = new ToggleGroup();
	for (EscolhaClasseTexto classe : this.classificacoes.get(pageIndex).getAlocacaoTexto()
		.getEscolhaClasseTextos()) {

	    // cria um radiobutton para cada opção de classe
	    RadioButton opcao = new RadioButton(classe.getDescricao());
	    opcao.setUserData(classe);

	    if (this.classificacoes.get(pageIndex).getEscolhaClasseTexto() != null
		    && this.classificacoes.get(pageIndex).getEscolhaClasseTexto().equals(classe)) {
		// seleciona o item já classificado
		opcao.setSelected(true);

	    }
	    opcao.setToggleGroup(group);
	    escolha.getChildren().add(opcao);
	}

	// adiciona escutador para poder gravar ou alterar
	group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {

	    public void changed(ObservableValue<? extends Toggle> ov, Toggle old_toggle, Toggle new_toggle) {

		InterfaceController<ClassificacaoTexto> controller = ControllerFactory
			.getClassificacaoTextoController();

		// se já tem classificação atualiza
		if (classificacoes.get(pageIndex).getEscolhaClasseTexto() != null) {
		    try {
			TransactionManager.begin();
			controller.prepareEdit(controller.prepareList().indexOf(classificacoes.get(pageIndex)));
			controller.getSelected().setEscolhaClasseTexto((EscolhaClasseTexto) new_toggle.getUserData());
			classificacoes.set(pageIndex, controller.update());

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
			controller.getSelected().setAlocacaoTexto(classificacoes.get(pageIndex).getAlocacaoTexto());
			controller.getSelected().setUsuario(UsuarioController.currentEscravo);
			controller.getSelected().setEscolhaClasseTexto((EscolhaClasseTexto) new_toggle.getUserData());
			classificacoes.set(pageIndex, controller.create());
		    } catch (Throwable t) {
			t.printStackTrace();
		    } finally {
			TransactionManager.end();
		    }
		}
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
	LiberacaoBaseTexto liberacao = tabelaLiberacoesTexto.getSelectionModel().getSelectedItem();

	if (liberacao != null) {
	    getChildren().clear();

	    this.loader.setLocation(getClass().getResource("BaseTextoClassificacaoView.fxml"));

	    try {
		this.loader.load();

		pagination.setPageFactory(new Callback<Integer, Node>() {

		    @Override
		    public Node call(Integer pageIndex) {
			return createPage(pageIndex);
		    }
		});
		prepareClassificacoes(liberacao);

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

    private void prepareClassificacoes(LiberacaoBaseTexto liberacao) {
	// criando controladores
	InterfaceController<ClassificacaoTexto> classificacaoTextoController = ControllerFactory
		.getClassificacaoTextoController();
	InterfaceController<AlocacaoTexto> alocacaoTextoController = ControllerFactory.getAlocacaoTextoController();

	// criação de lista de classificação
	classificacoes = new LinkedList<>();
	Usuario escravo = UsuarioController.currentEscravo;

	List<AlocacaoTexto> alocacoesTexto = alocacaoTextoController
		.getItemsFromCriteria(DetachedCriteriaFactory.getAlocacoesTextoPorLiberacao(liberacao));

	// capturando a alocação de texto dado a liberação selecionada
	for (AlocacaoTexto alocacao : alocacoesTexto) {

	    // classificação já existente
	    List<ClassificacaoTexto> clas = classificacaoTextoController.getItemsFromCriteria(
		    DetachedCriteriaFactory.getClassificacaoTextoPorEscravoEAlocacao(escravo, alocacao));
	    classificacoes.addAll(clas);
	}

	int possivelIndiceInicial = classificacaoTextoController.getItems().size() - 1;

	int indiceInicial = possivelIndiceInicial < 0 ? 0 : possivelIndiceInicial;

	for (int i = indiceInicial; classificacoes.size() < alocacoesTexto.size(); i++) {

	    ClassificacaoTexto classificacao = new ClassificacaoTexto();
	    classificacao.setUsuario(UsuarioController.currentEscravo);
	    classificacao.setAlocacaoTexto(alocacaoTextoController.getItems().get(i));

	    classificacoes.add(classificacao);
	}
	pagination.setPageCount(classificacoes.size());
	pagination.setCurrentPageIndex(buscaPrimeiraClassificacaoEfetivada(classificacoes));
    }

    /**
     * Ordena as alocações de forma que as com menos detecções fiquem primeiro
     */
    public void sortAlocacoes(List<ClassificacaoTexto> alocacoes) {
	Collections.sort(alocacoes, new Comparator<ClassificacaoTexto>() {
	    @Override
	    public int compare(ClassificacaoTexto o1, ClassificacaoTexto o2) {
		return o1.getAlocacaoTexto().getClassificacaoTextos().size()
			- o2.getAlocacaoTexto().getClassificacaoTextos().size();
	    }
	});
    }

    public int buscaPrimeiraClassificacaoEfetivada(List<ClassificacaoTexto> lista) {
	sortAlocacoes(lista);
	return 0;
    }
}
