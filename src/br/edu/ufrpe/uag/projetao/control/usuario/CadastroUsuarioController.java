package br.edu.ufrpe.uag.projetao.control.usuario;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.hibernate.exception.ConstraintViolationException;
import org.kairos.core.Activity;

import br.edu.ufrpe.uag.projetao.control.ControllerFactory;
import br.edu.ufrpe.uag.projetao.control.hibernate.TransactionManager;
import br.edu.ufrpe.uag.projetao.interfaces.InterfaceController;
import br.edu.ufrpe.uag.projetao.model.Perfil;
import br.edu.ufrpe.uag.projetao.model.Usuario;
import br.edu.ufrpe.uag.projetao.view.util.SHA256;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;

/**
 * @author israel
 *
 */
public class CadastroUsuarioController extends Activity {
    private final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
	    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    @FXML
    private TextField nome;
    @FXML
    private TextField email;
    @FXML
    private PasswordField senha;
    @FXML
    private PasswordField confirmaSenha;
    @FXML
    private ComboBox<Perfil> perfil;

    @FXML
    public void login() {
	startActivity(LoginController.class);
    }

    @Override
    public void onCreate() {
	super.onCreate();
	setContentView(getClass().getResource("/br/edu/ufrpe/uag/projetao/view/usuario/CadastroUsuarioView.fxml"));
	perfil.setItems(FXCollections.observableList(ControllerFactory.getPerfilController().prepareList()));
    }

    @FXML
    private void cadastrar() {
	InterfaceController<Usuario> controller = ControllerFactory.getUsuarioController();

	try {
	    validar();

	    TransactionManager.begin();

	    controller.getSelected().setNome(this.nome.getText());
	    controller.getSelected().setEmail(this.email.getText());
	    controller.getSelected().setSenha(SHA256.encode(this.senha.getText()));
	    controller.getSelected().setPerfil((Perfil) perfil.getSelectionModel().getSelectedItem());
	    controller.create();

	    TransactionManager.end();

	    Alert dialogoErro = new Alert(Alert.AlertType.INFORMATION);
	    dialogoErro.setTitle("Cadastro");
	    dialogoErro.setHeaderText("Cadastro efetuado com sucesso");
	    dialogoErro.setContentText("Você será redirecionado para a página de login\n");
	    dialogoErro.showAndWait();

	    startActivity(LoginController.class);

	} catch (IllegalArgumentException ex) {
	    Alert dialogoErro = new Alert(Alert.AlertType.INFORMATION);
	    dialogoErro.setTitle("Cadastro");
	    dialogoErro.setHeaderText("Dados inválidos");
	    dialogoErro.setContentText(ex.getMessage());
	    dialogoErro.showAndWait();

	} catch (ClassCastException ex) {
	    Alert dialogoErro = new Alert(Alert.AlertType.WARNING);
	    dialogoErro.setTitle("Dados inválidos");
	    dialogoErro.setHeaderText("Perfil inválido");
	    dialogoErro.setContentText("Selecione um perfil");
	    dialogoErro.showAndWait();

	} catch (ConstraintViolationException ex) {
	    Alert dialogoErro = new Alert(Alert.AlertType.INFORMATION);
	    dialogoErro.setTitle("Cadastro");
	    dialogoErro.setHeaderText("Usuário já cadastrado");
	    dialogoErro.setContentText(
		    "E-mail com perfil já foi cadastrado no sistema\nTodos os e-mails são associados até dois perfil\n");
	    dialogoErro.showAndWait();

	} catch (Exception ex) {
	    Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
	    dialogoErro.setTitle("Ops");
	    dialogoErro.setHeaderText("Algo saiu errado");
	    dialogoErro.setContentText("Não foi possível prosseguir, por favor tente novamente");

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

    /**
     * Valida os campos
     * 
     * @throws IllegalArgumentException
     */
    private void validar() throws IllegalArgumentException {
	if (this.perfil.getSelectionModel().getSelectedItem() == null) {
	    throw new IllegalArgumentException("Selecione um perfil");
	}
	if (this.nome.getText() == null || this.nome.getText().trim().equals("")) {
	    throw new IllegalArgumentException("O nome de usuário deve ser preenchido");
	}
	if (this.nome.getText() == null || this.nome.getText().trim().equals("")) {
	    throw new IllegalArgumentException("O E-mail de usuário deve ser preenchido");
	}
	if (!this.email.getText().matches(EMAIL_PATTERN)) {
	    throw new IllegalArgumentException("Endereço de E-mail inválido");
	}
	if (!this.senha.getText().equals(this.confirmaSenha.getText())) {
	    throw new IllegalArgumentException("Senhas não conferem");
	}
    }

}
