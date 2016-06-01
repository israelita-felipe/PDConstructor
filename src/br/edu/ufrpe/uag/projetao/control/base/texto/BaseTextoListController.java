/**
 * 
 */
package br.edu.ufrpe.uag.projetao.control.base.texto;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import java.util.Optional;

import org.kairos.core.Fragment;

import br.edu.ufrpe.uag.projetao.control.ControllerFactory;
import br.edu.ufrpe.uag.projetao.control.DetachedCriteriaFactory;
import br.edu.ufrpe.uag.projetao.control.UsuarioController;
import br.edu.ufrpe.uag.projetao.control.hibernate.TransactionManager;
import br.edu.ufrpe.uag.projetao.control.util.FileManager;
import br.edu.ufrpe.uag.projetao.interfaces.InterfaceController;
import br.edu.ufrpe.uag.projetao.model.AlocacaoTexto;
import br.edu.ufrpe.uag.projetao.model.BaseTexto;
import br.edu.ufrpe.uag.projetao.model.EscolhaClasseTexto;
import br.edu.ufrpe.uag.projetao.model.LiberacaoBaseTexto;
import br.edu.ufrpe.uag.projetao.model.Usuario;
import br.edu.ufrpe.uag.projetao.model.enumerate.StatusDeLiberacao;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

/**
 * @author israel
 *
 */
public class BaseTextoListController extends Fragment {

    private FXMLLoader loader;

    @FXML
    private TableView<BaseTexto> basesTexto;
    @FXML
    private TextField titulo;
    @FXML
    private TextArea descricao;
    @FXML
    private TextField classe;
    @FXML
    private ListView<String> listaDeClasses;
    @FXML
    private ListView<File> listaDeTexto;
    @FXML
    private TableView<Usuario> tabelaEscravos;
    @FXML
    private TextField baseTitulo;
    @FXML
    private TableView<LiberacaoBaseTexto> tabelaLiberacoes;

    @Override
    public void onCreateView(FXMLLoader fxmlLoader) {
	this.loader = fxmlLoader;
	list();
    }

    @FXML
    private void novo() {
	getChildren().clear();
	this.loader.setLocation(
		getClass().getResource("/br/edu/ufrpe/uag/projetao/view/base/texto/BaseTextoCriarView.fxml"));
	try {
	    this.loader.load();
	} catch (IOException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
    }

    @FXML
    private void cancela() {
	list();
    }

    @FXML
    private void gravar() {
	try {

	    validar();

	    TransactionManager.begin();

	    InterfaceController<BaseTexto> base = ControllerFactory.getBaseTextoController();
	    InterfaceController<EscolhaClasseTexto> escolhaClasseTexto = ControllerFactory
		    .getEscolhaClasseTextoController();
	    InterfaceController<AlocacaoTexto> alocacaoTexto = ControllerFactory.getAlocacaoTextoController();

	    // criação da base
	    base.prepareCreate();
	    base.getSelected().setTitulo(titulo.getText());
	    base.getSelected().setDescricao(descricao.getText());
	    base.getSelected().setUsuario(UsuarioController.currrentSupervisor);
	    base.create();

	    // criação dos textos
	    for (int i = 0; i < listaDeTexto.getItems().size(); i++) {
		try {

		    // aloca um texto para uma base
		    alocacaoTexto.prepareCreate();
		    alocacaoTexto.getSelected().setBaseTexto(base.getSelected());
		    alocacaoTexto.getSelected().setUsuario(UsuarioController.currrentSupervisor);
		    alocacaoTexto.getSelected()
			    .setTexto(FileManager.lerArquivo(listaDeTexto.getItems().get(i).toString()));
		    alocacaoTexto.create();

		    // criação das classes
		    for (int j = 0; j < listaDeClasses.getItems().size(); j++) {

			// aloca uma classe para uma alocacao de texto
			escolhaClasseTexto.prepareCreate();
			escolhaClasseTexto.getSelected().setAlocacaoTexto(alocacaoTexto.getSelected());
			escolhaClasseTexto.getSelected().setDescricao(listaDeClasses.getItems().get(j).toString());
			escolhaClasseTexto.create();
		    }

		} catch (FileNotFoundException e1) {
		    Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
		    dialogoErro.setTitle("Arquivo");
		    dialogoErro.setHeaderText("Não foi possível ler arquivo de texto");
		    dialogoErro.setContentText("Erro ao ler arquivo:\n" + listaDeTexto.getItems().get(i).toString());
		    dialogoErro.showAndWait();
		}
	    }

	    Alert dialogoErro = new Alert(Alert.AlertType.INFORMATION);
	    dialogoErro.setTitle("Informação");
	    dialogoErro.setHeaderText("Gravação da base");
	    dialogoErro.setContentText("Base " + base.getSelected().getTitulo() + " criada com sucesso");
	    dialogoErro.showAndWait();
	    list();

	} catch (IllegalArgumentException ex) {
	    Alert dialogoErro = new Alert(Alert.AlertType.WARNING);
	    dialogoErro.setTitle("Preencha os campos");
	    dialogoErro.setHeaderText("Alguns campos não podem figar em branco");
	    dialogoErro.setContentText(ex.getMessage());
	    dialogoErro.showAndWait();

	} catch (Exception ex) {
	    Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
	    dialogoErro.setTitle("Não foi possível fazer...");
	    dialogoErro.setHeaderText("Algo saiu errado");
	    dialogoErro.setContentText("Não foi possível gravar, tente novamente");

	    // Create expandable Exception.
	    StringWriter sw = new StringWriter();
	    PrintWriter pw = new PrintWriter(sw);
	    ex.printStackTrace(pw);
	    String exceptionText = sw.toString();

	    Label label = new Label("The exception stacktrace was:");

	    TextArea textArea = new TextArea(exceptionText);
	    textArea.setEditable(false);
	    textArea.setWrapText(true);

	    textArea.setMaxWidth(Double.MAX_VALUE);
	    textArea.setMaxHeight(Double.MAX_VALUE);
	    GridPane.setVgrow(textArea, Priority.ALWAYS);
	    GridPane.setHgrow(textArea, Priority.ALWAYS);

	    GridPane expContent = new GridPane();
	    expContent.setMaxWidth(Double.MAX_VALUE);
	    expContent.add(label, 0, 0);
	    expContent.add(textArea, 0, 1);

	    // Set expandable Exception into the dialog pane.
	    dialogoErro.getDialogPane().setExpandableContent(expContent);

	    dialogoErro.showAndWait();

	} finally {
	    TransactionManager.end();
	}
    }

    private void list() {
	getChildren().clear();
	this.loader.setLocation(
		getClass().getResource("/br/edu/ufrpe/uag/projetao/view/base/texto/BaseTextoListView.fxml"));
	try {
	    this.loader.load();
	    this.basesTexto.setItems(
		    FXCollections.observableList(ControllerFactory.getBaseTextoController().getItemsFromCriteria(
			    DetachedCriteriaFactory.getBasesTextoDoUsuario(UsuarioController.currrentSupervisor))));

	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    @FXML
    private void excluir() {
	if (basesTexto.getSelectionModel().getSelectedItem() != null) {
	    Alert alert = new Alert(AlertType.CONFIRMATION);
	    alert.setTitle("Exclusão");
	    alert.setHeaderText("Excluir base: " + basesTexto.getSelectionModel().getSelectedItem().getTitulo());
	    alert.setContentText("Deseja realmente excluir esta base?");

	    Optional<ButtonType> result = alert.showAndWait();
	    if (result.get() == ButtonType.OK) {
		TransactionManager.begin();
		InterfaceController<BaseTexto> base = ControllerFactory.getBaseTextoController();
		base.destroy(base.prepareList().indexOf(basesTexto.getSelectionModel().getSelectedItem()));
		TransactionManager.end();
		list();
	    }
	}
    }

    @FXML
    private void addClasse(Event event) {
	if (this.classe.getText() != null && !this.classe.getText().trim().toUpperCase().equals("")
		&& !this.listaDeClasses.getItems().contains(this.classe.getText())) {
	    this.listaDeClasses.getItems().add(this.classe.getText());
	    this.classe.setText(null);
	}
	this.classe.requestFocus();
    }

    @FXML
    private void removeClasse() {
	remove(listaDeClasses, listaDeClasses.getSelectionModel().getSelectedIndex());
    }

    @FXML
    private void removeArquivo() {
	remove(listaDeTexto, listaDeTexto.getSelectionModel().getSelectedIndex());
    }

    private void remove(ListView list, int index) {
	if (index != -1 && index < list.getItems().size()) {
	    list.getItems().remove(index);
	}
    }

    @FXML
    private void selecionaArquivos() {
	FileChooser fileChooser = new FileChooser();
	fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Todos os arquivos", "*.*"),
		new FileChooser.ExtensionFilter("TXT", "*.txt"));
	List<File> arquivos = fileChooser.showOpenMultipleDialog(null);
	for (File arquivo : arquivos) {
	    if (!listaDeTexto.getItems().contains(arquivo)) {
		listaDeTexto.getItems().add(arquivo);
	    }
	}
    }

    /**
     * Executa a validação dos campos
     * 
     * @throws IllegalArgumentException
     */
    private void validar() throws IllegalArgumentException {
	if (titulo.getText() == null || titulo.getText().trim().equals("")) {
	    throw new IllegalArgumentException("Campo de título não pode estar vazio");
	}
	if (descricao.getText() == null || descricao.getText().equals("")) {
	    throw new IllegalArgumentException("Campo de descrição não pode estar vazio");
	}
	if (listaDeClasses.getItems().size() == 0) {
	    throw new IllegalArgumentException("Lista de classes não pode estar vazia");
	}
	if (listaDeTexto.getItems().size() == 0) {
	    throw new IllegalArgumentException("Lista de arquivos não pode estar vazia");
	}
    }

    @FXML
    private void gravarLiberacao() {
	if (this.tabelaEscravos.getSelectionModel().getSelectedItem() != null) {
	    InterfaceController<LiberacaoBaseTexto> liberacoesController = ControllerFactory
		    .getLiberacaoBaseTextoController();

	    TransactionManager.begin();
	    liberacoesController.prepareCreate();
	    liberacoesController.getSelected()
		    .setUsuarioByEscravo(this.tabelaEscravos.getSelectionModel().getSelectedItem());
	    liberacoesController.getSelected().setBaseTexto(ControllerFactory.getBaseTextoController().getSelected());
	    liberacoesController.getSelected().setUsuarioBySupervisor(UsuarioController.currrentSupervisor);
	    liberacoesController.getSelected().setStatus(StatusDeLiberacao.LIBERADO);
	    liberacoesController.create();
	    TransactionManager.end();

	    Alert dialogoErro = new Alert(Alert.AlertType.INFORMATION);
	    dialogoErro.setTitle("Informação");
	    dialogoErro.setHeaderText("Liberação de base");
	    dialogoErro.setContentText(
		    "Liberado para: " + this.tabelaEscravos.getSelectionModel().getSelectedItem().getNome());
	    dialogoErro.showAndWait();
	}
	list();
    }

    @FXML
    private void liberar() {
	if (basesTexto.getSelectionModel().getSelectedItem() != null) {

	    BaseTexto base = basesTexto.getSelectionModel().getSelectedItem();
	    ControllerFactory.getBaseTextoController()
		    .prepareView(ControllerFactory.getBaseTextoController().prepareList().indexOf(base));

	    getChildren().clear();
	    this.loader.setLocation(getClass().getResource(
		    "/br/edu/ufrpe/uag/projetao/view/base/texto/liberacao/BaseTextoLiberaUsuarioView.fxml"));
	    try {
		this.loader.load();

		this.baseTitulo.setText(base.getTitulo());

		this.tabelaEscravos.setItems(FXCollections.observableList(ControllerFactory.getUsuarioController()
			.getItemsFromCriteria(DetachedCriteriaFactory.getTodosEscravos())));

		this.tabelaLiberacoes
			.setItems(FXCollections.observableList(ControllerFactory.getLiberacaoBaseTextoController()
				.getItemsFromCriteria(DetachedCriteriaFactory.getLiberacoesPorBaseDeTexto(base))));

		// removendo os usuários já liberados
		for (LiberacaoBaseTexto liberacao : this.tabelaLiberacoes.getItems()) {
		    this.tabelaEscravos.getItems().remove(liberacao.getUsuarioByEscravo());
		}

	    } catch (IOException e) {
		e.printStackTrace();
	    }
	} else {
	    Alert dialogoErro = new Alert(Alert.AlertType.INFORMATION);
	    dialogoErro.setTitle("Informação");
	    dialogoErro.setHeaderText("Liberação de base");
	    dialogoErro.setContentText("Selecione primeiro uma base");
	    dialogoErro.showAndWait();
	}
    }

}
