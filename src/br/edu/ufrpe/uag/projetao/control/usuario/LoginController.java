/**
 * 
 */
package br.edu.ufrpe.uag.projetao.control.usuario;

import java.util.List;

import org.kairos.core.Activity;

import br.edu.ufrpe.uag.projetao.control.ControllerFactory;
import br.edu.ufrpe.uag.projetao.control.DetachedCriteriaFactory;
import br.edu.ufrpe.uag.projetao.control.UsuarioController;
import br.edu.ufrpe.uag.projetao.control.configuracao.ConfiguracaoController;
import br.edu.ufrpe.uag.projetao.control.principal.PrincipalEscravoController;
import br.edu.ufrpe.uag.projetao.control.principal.PrincipalSupervisorController;
import br.edu.ufrpe.uag.projetao.model.Perfil;
import br.edu.ufrpe.uag.projetao.model.Usuario;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

/**
 * @author israel
 * @author Bruno Barros
 *
 */
public class LoginController extends Activity {

    @FXML
    private PasswordField senha;
    @FXML
    private TextField email;
    @FXML
    private ComboBox<Perfil> perfil;

    @FXML
    public void cadastrar() {
	startActivity(CadastroUsuarioController.class);
    }
    
    @FXML
    public void configuracoes(){
	startActivity(ConfiguracaoController.class);
    }

    @Override
    public void onCreate() {
	super.onCreate();
	setContentView(getClass().getResource("/br/edu/ufrpe/uag/projetao/view/usuario/LoginView.fxml"));
	perfil.setItems(FXCollections.observableList(ControllerFactory.getPerfilController().prepareList()));
    }

    @FXML
    public void principal() {

	try {

	    validar();

	    List<Usuario> usuarios = ControllerFactory.getUsuarioController()
		    .getItemsFromCriteria(DetachedCriteriaFactory.getUsuario(email.getText(), senha.getText(),
			    perfil.getSelectionModel().getSelectedItem()));

	    if (usuarios.size() == 1) {
		if (usuarios.get(0).getPerfil().getNome().equals("SUPERVISOR")) {
		    UsuarioController.currrentSupervisor = usuarios.get(0);
		    startActivity(PrincipalSupervisorController.class);
		} else {
		    UsuarioController.currentEscravo = usuarios.get(0);
		    startActivity(PrincipalEscravoController.class);
		}
	    } else {
		Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
		dialogoErro.setTitle("Login");
		dialogoErro.setHeaderText("Dados inválidos");
		dialogoErro.setContentText("Usuário e/ou senha(s) errado(s), tente novamente\n");
		dialogoErro.showAndWait();
	    }

	} catch (IllegalArgumentException ex) {
	    Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
	    dialogoErro.setTitle("Login");
	    dialogoErro.setHeaderText("Dados inválidos");
	    dialogoErro.setContentText(ex.getMessage());
	    dialogoErro.showAndWait();
	} catch (Throwable ex) {
	    Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
	    dialogoErro.setTitle("Login");
	    dialogoErro.setHeaderText("Erro desconhecido");
	    dialogoErro.setContentText("Um erro ocorreu, reinicie o sistema e tente novamente");
	    dialogoErro.showAndWait();
	}
    }

    private void validar() {
	if (email.getText() == null || email.getText().trim().toUpperCase().equals("")) {
	    throw new IllegalArgumentException("Informe seu e-mail");
	}
	if (perfil.getSelectionModel().getSelectedItem() == null) {
	    throw new IllegalArgumentException("Informe o perfil");
	}
	if (senha.getText() == null || senha.getText().trim().toUpperCase().equals("")) {
	    throw new IllegalArgumentException("Informe sua senha");
	}
    }
}
