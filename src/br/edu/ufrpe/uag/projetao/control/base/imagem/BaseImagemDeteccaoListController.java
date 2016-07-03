/**
 * 
 */
package br.edu.ufrpe.uag.projetao.control.base.imagem;

import java.io.File;
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
import br.edu.ufrpe.uag.projetao.control.util.imagem.ImagemDigital;
import br.edu.ufrpe.uag.projetao.interfaces.InterfaceController;
import br.edu.ufrpe.uag.projetao.model.AlocacaoImagemDeteccao;
import br.edu.ufrpe.uag.projetao.model.BaseImagemDeteccao;
import br.edu.ufrpe.uag.projetao.model.ImagemDeteccao;
import br.edu.ufrpe.uag.projetao.model.LiberacaoBaseImagemDeteccao;
import br.edu.ufrpe.uag.projetao.model.Usuario;
import br.edu.ufrpe.uag.projetao.model.enumerate.StatusDeLiberacao;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * @author israel
 *
 */
public class BaseImagemDeteccaoListController extends Fragment {

    private FXMLLoader loader;

    @FXML
    private TableView<BaseImagemDeteccao> basesImagemDeteccao;
    @FXML
    private TextField titulo;
    @FXML
    private TextArea descricao;
    @FXML
    private ListView<File> listaDeImagens;
    @FXML
    private TableView<Usuario> tabelaEscravos;
    @FXML
    private TextField baseTitulo;
    @FXML
    private TableView<LiberacaoBaseImagemDeteccao> tabelaLiberacoes;

    @Override
    public void onCreateView(FXMLLoader fxmlLoader) {
	this.loader = fxmlLoader;
	list();
    }

    @FXML
    private void novo() {
	getChildren().clear();
	this.loader.setLocation(
		getClass().getResource("/br/edu/ufrpe/uag/projetao/view/base/imagem/BaseImagemDeteccaoCriarView.fxml"));
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

	    InterfaceController<BaseImagemDeteccao> base = ControllerFactory.getBaseImagemDeteccaoController();

	    InterfaceController<AlocacaoImagemDeteccao> alocacaoImagemDeteccao = ControllerFactory
		    .getAlocacaoImagemDeteccaoController();
	    InterfaceController<ImagemDeteccao> imagemDeteccaoController = ControllerFactory
		    .getImagemDeteccaoController();

	    TransactionManager.begin();
	    // criação da base
	    base.prepareCreate();
	    base.getSelected().setTitulo(titulo.getText());
	    base.getSelected().setDescricao(descricao.getText());
	    base.getSelected().setUsuario(UsuarioController.currrentSupervisor);
	    base.create();
	    TransactionManager.end();

	    // criação de imagens para detecção
	    for (int i = 0; i < listaDeImagens.getItems().size(); i++) {

		TransactionManager.begin();
		imagemDeteccaoController.prepareCreate();

		imagemDeteccaoController.getSelected().setObjeto(ImagemDigital
			.toByte(ImagemDigital.carregarImagemCor(listaDeImagens.getItems().get(i).getAbsolutePath())));

		imagemDeteccaoController.getSelected().setUsuario(UsuarioController.currrentSupervisor);
		imagemDeteccaoController.create();

		// aloca uma imagem para uma base

		alocacaoImagemDeteccao.prepareCreate();
		alocacaoImagemDeteccao.getSelected().setBaseImagemDeteccao(base.getSelected());
		alocacaoImagemDeteccao.getSelected().setUsuario(UsuarioController.currrentSupervisor);
		alocacaoImagemDeteccao.getSelected().setImagemDeteccao(imagemDeteccaoController.getSelected());
		alocacaoImagemDeteccao.create();
		TransactionManager.end();

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
		getClass().getResource("/br/edu/ufrpe/uag/projetao/view/base/imagem/BaseImagemDeteccaoListView.fxml"));
	try {
	    this.loader.load();
	    this.basesImagemDeteccao.setItems(FXCollections.observableList(
		    ControllerFactory.getBaseImagemDeteccaoController().getItemsFromCriteria(DetachedCriteriaFactory
			    .getBasesImagemDeteccaoDoUsuario(UsuarioController.currrentSupervisor))));

	} catch (IOException e) {
	    e.printStackTrace();
	}
    }

    @FXML
    private void excluir() {
	if (basesImagemDeteccao.getSelectionModel().getSelectedItem() != null) {
	    Alert alert = new Alert(AlertType.CONFIRMATION);
	    alert.setTitle("Exclusão");
	    alert.setHeaderText(
		    "Excluir base: " + basesImagemDeteccao.getSelectionModel().getSelectedItem().getTitulo());
	    alert.setContentText("Deseja realmente excluir esta base?");

	    Optional<ButtonType> result = alert.showAndWait();
	    if (result.get() == ButtonType.OK) {
		TransactionManager.begin();
		InterfaceController<BaseImagemDeteccao> base = ControllerFactory.getBaseImagemDeteccaoController();
		base.destroy(base.prepareList().indexOf(basesImagemDeteccao.getSelectionModel().getSelectedItem()));
		TransactionManager.end();
		list();
	    }
	}
    }

    @FXML
    private void removeArquivo() {
	remove(listaDeImagens, listaDeImagens.getSelectionModel().getSelectedIndex());
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
		new FileChooser.ExtensionFilter("png", "*.png"), new FileChooser.ExtensionFilter("jpg", "*.jpg"),
		new FileChooser.ExtensionFilter("jpeg", "*.jpeg"));
	List<File> arquivos = fileChooser.showOpenMultipleDialog(null);
	for (File arquivo : arquivos) {
	    if (!listaDeImagens.getItems().contains(arquivo)) {
		listaDeImagens.getItems().add(arquivo);
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
	if (listaDeImagens.getItems().size() == 0) {
	    throw new IllegalArgumentException("Lista de arquivos de imagem não pode estar vazia");
	}
    }

    @FXML
    private void gravarLiberacao() {
	if (this.tabelaEscravos.getSelectionModel().getSelectedItem() != null) {
	    InterfaceController<LiberacaoBaseImagemDeteccao> liberacoesController = ControllerFactory
		    .getLiberacaoBaseImagemDeteccaoController();

	    TransactionManager.begin();
	    liberacoesController.prepareCreate();
	    liberacoesController.getSelected()
		    .setUsuarioByEscravo(this.tabelaEscravos.getSelectionModel().getSelectedItem());
	    liberacoesController.getSelected()
		    .setBaseImagemDeteccao(ControllerFactory.getBaseImagemDeteccaoController().getSelected());
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
	if (basesImagemDeteccao.getSelectionModel().getSelectedItem() != null) {

	    BaseImagemDeteccao base = basesImagemDeteccao.getSelectionModel().getSelectedItem();
	    ControllerFactory.getBaseImagemDeteccaoController()
		    .prepareView(ControllerFactory.getBaseImagemDeteccaoController().prepareList().indexOf(base));

	    getChildren().clear();
	    this.loader.setLocation(getClass().getResource(
		    "/br/edu/ufrpe/uag/projetao/view/base/imagem/liberacao/BaseImagemDeteccaoLiberaUsuarioView.fxml"));
	    try {
		this.loader.load();

		this.baseTitulo.setText(base.getTitulo());

		this.tabelaEscravos.setItems(FXCollections.observableList(ControllerFactory.getUsuarioController()
			.getItemsFromCriteria(DetachedCriteriaFactory.getTodosEscravos())));

		this.tabelaLiberacoes.setItems(FXCollections.observableList(
			ControllerFactory.getLiberacaoBaseImagemDeteccaoController().getItemsFromCriteria(
				DetachedCriteriaFactory.getLiberacoesPorBaseDeImagemDeteccao(base))));

		// removendo os usuários já liberados
		for (LiberacaoBaseImagemDeteccao liberacao : this.tabelaLiberacoes.getItems()) {
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

    @FXML
    private void relatorio() {
	if (basesImagemDeteccao.getSelectionModel().getSelectedItem() != null) {
	    Stage dialogStage = new Stage();
	    dialogStage.setTitle("Bases de detecção em imagens");

	    Scene scene = new Scene(BaseGraficosFactory
		    .getBaseImagemDeteccaoHistograma(basesImagemDeteccao.getSelectionModel().getSelectedItem()));
	    dialogStage.setScene(scene);

	    dialogStage.show();
	}
    }

    @FXML
    private void exportar() {
	if (basesImagemDeteccao.getSelectionModel().getSelectedItem() != null) {
	    new BaseImagemDeteccaoExportarController(basesImagemDeteccao.getSelectionModel().getSelectedItem()).show();
	}
    }
}
