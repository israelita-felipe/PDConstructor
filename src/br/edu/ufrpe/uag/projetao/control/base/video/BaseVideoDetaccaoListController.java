package br.edu.ufrpe.uag.projetao.control.base.video;

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
import br.edu.ufrpe.uag.projetao.control.util.video.VideoDigital;
import br.edu.ufrpe.uag.projetao.interfaces.InterfaceController;
import br.edu.ufrpe.uag.projetao.model.AlocacaoVideoDeteccao;
import br.edu.ufrpe.uag.projetao.model.BaseVideoDeteccao;
import br.edu.ufrpe.uag.projetao.model.VideoDeteccao;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

public class BaseVideoDetaccaoListController extends Fragment {

	private FXMLLoader loader;
	@FXML
	private TextField titulo;
	@FXML
	private TextArea descricao;
	@FXML
	private ListView<File> videos;
	@FXML
	private TableView<BaseVideoDeteccao> baseVideoDeteccao;

	@Override
	public void onCreateView(FXMLLoader fxmlLoader) {
		this.loader = fxmlLoader;
		list();

	}

	@FXML
	private void salvar() {

	}


	@FXML
	private void excluir() {
		if (baseVideoDeteccao.getSelectionModel().getSelectedItem() != null) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Exclusão");
			alert.setHeaderText("Excluir base: " + baseVideoDeteccao.getSelectionModel().getSelectedItem().getTitulo());
			alert.setContentText("Deseja realmente excluir esta base?");

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				TransactionManager.begin();
				InterfaceController<BaseVideoDeteccao> base = ControllerFactory.getBaseVideoDeteccaoController();
				base.destroy(baseVideoDeteccao.getSelectionModel().getSelectedItem());
				TransactionManager.end();
				list();
			}
		}
	}

	@FXML
	private void removeArquivo() {
		remove(videos, videos.getSelectionModel().getSelectedIndex());
	}

	private void remove(ListView list, int index) {
		if (index != -1 && index < list.getItems().size()) {
			list.getItems().remove(index);
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

			    InterfaceController<BaseVideoDeteccao> base = ControllerFactory.getBaseVideoDeteccaoController();

			    InterfaceController<AlocacaoVideoDeteccao> alocacaoVideoDeteccao = ControllerFactory
				    .getAlocacaoVideoDeteccaoController();
			    InterfaceController<VideoDeteccao> videoDeteccaoController = ControllerFactory
				    .getVideoDeteccaoController();

			    TransactionManager.begin();
			    // criação da base
			    base.prepareCreate();
			    base.getSelected().setTitulo(titulo.getText());
			    base.getSelected().setDescricao(descricao.getText());
			    base.getSelected().setUsuario(UsuarioController.currrentSupervisor);
			    base.create();
			    TransactionManager.end();

			    // criação de vídeos para detecção
			    for (int i = 0; i < videos.getItems().size(); i++) {

				TransactionManager.begin();
				videoDeteccaoController.prepareCreate();

				videoDeteccaoController.getSelected().setObjeto(VideoDigital
					.toByte(videos.getItems().get(i)));

				videoDeteccaoController.getSelected().setUsuario(UsuarioController.currrentSupervisor);
				videoDeteccaoController.create();

				// aloca um vídoe para uma base

				alocacaoVideoDeteccao.prepareCreate();
				alocacaoVideoDeteccao.getSelected().setBaseVideoDeteccao(base.getSelected());
				alocacaoVideoDeteccao.getSelected().setUsuario(UsuarioController.currrentSupervisor);
				alocacaoVideoDeteccao.getSelected().setVideoDeteccao(videoDeteccaoController.getSelected());
				alocacaoVideoDeteccao.create();
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
			    dialogoErro.setHeaderText("Alguns campos não podem ficar em branco");
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
	 
	 @FXML
	 private void gravarLiberacao() {
		 
	 }
	 

	@FXML
	private void selecionaArquivos() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Todos os arquivos", "*.*"),
				new FileChooser.ExtensionFilter("mp4", "*.mp4"), new FileChooser.ExtensionFilter("avi", "*.avi"));
		List<File> arquivos = fileChooser.showOpenMultipleDialog(null);
		for (File arquivo : arquivos) {
			if (!videos.getItems().contains(arquivo)) {
				videos.getItems().add(arquivo);
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
	if (videos.getItems().size() == 0) {
	    throw new IllegalArgumentException("Lista de arquivos de vídeos não pode estar vazia");
	}
    }

	@FXML
	private void novo() {
		getChildren().clear();
		this.loader.setLocation(
				getClass().getResource("/br/edu/ufrpe/uag/projetao/view/base/video/BaseVideoDeteccaoCriarView.fxml"));
		try {
			this.loader.load();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@FXML
	private void liberar() {

	}

	private void list() {
		getChildren().clear();
		this.loader.setLocation(
				getClass().getResource("/br/edu/ufrpe/uag/projetao/view/base/video/BaseVideoDeteccaoListView.fxml"));
		try {
			this.loader.load();
			this.baseVideoDeteccao.setItems(FXCollections.observableList(
					ControllerFactory.getBaseVideoDeteccaoController().getItemsFromCriteria(DetachedCriteriaFactory
							.getBasesVideoDeteccaoDoUsuario(UsuarioController.currrentSupervisor))));

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
