/**
 * 
 */
package br.edu.ufrpe.uag.projetao.control.configuracao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.kairos.core.Activity;

import br.edu.ufrpe.uag.projetao.control.usuario.LoginController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

/**
 * @author israel
 *
 */
public class ConfiguracaoController extends Activity {

    @FXML
    private TextField hostTextField;
    @FXML
    private TextField portaTextField;
    @FXML
    private Button cancelarButton;
    @FXML
    private Button salvarButton;

    @Override
    public void onCreate() {
	super.onCreate();
	setContentView(getClass().getResource("ConfiguracaoView.fxml"));
	carregar();
    }

    @FXML
    public void salvar() {
	try {

	    validar();

	    Properties resources = new Properties();

	    resources.setProperty("hibernate.connection.url.ip", hostTextField.getText());
	    resources.setProperty("hibernate.connection.url.port", portaTextField.getText());

	    resources.store(new FileOutputStream("config_pt_BR.properties"), null);

	    startActivity(LoginController.class);

	} catch (FileNotFoundException e) {
	    Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
	    dialogoErro.setTitle("Configurações");
	    dialogoErro.setHeaderText("Arquivo de configurações");
	    dialogoErro.setContentText("O arquivo de configurações não pode ser encontrado");
	    dialogoErro.showAndWait();
	} catch (IOException e) {
	    Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
	    dialogoErro.setTitle("Configurações");
	    dialogoErro.setHeaderText("Arquivo de configurações");
	    dialogoErro.setContentText("O arquivo de configurações não pode ser escrito");
	    dialogoErro.showAndWait();
	}
    }

    private void carregar() {
	try {
	    Properties resources = new Properties();

	    resources.load(new FileInputStream("config_pt_BR.properties"));

	    hostTextField.setText(resources.getProperty("hibernate.connection.url.ip"));
	    portaTextField.setText(resources.getProperty("hibernate.connection.url.port"));

	} catch (FileNotFoundException e) {
	    Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
	    dialogoErro.setTitle("Configurações");
	    dialogoErro.setHeaderText("Arquivo de configurações");
	    dialogoErro.setContentText("O arquivo de configurações não pode ser encontrado");
	    dialogoErro.showAndWait();
	} catch (IOException e) {
	    Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
	    dialogoErro.setTitle("Configurações");
	    dialogoErro.setHeaderText("Arquivo de configurações");
	    dialogoErro.setContentText("O arquivo de configurações não pode ser lido");
	    dialogoErro.showAndWait();
	}

    }

    @FXML
    public void cancelar() {
	startActivity(LoginController.class);
    }

    private void validar() {
	if (hostTextField.getText() == null || hostTextField.getText().equals("")) {
	    throw new IllegalArgumentException("O campo 'host (IP)' não pode estar vazio");
	}
	if (portaTextField.getText() == null || portaTextField.getText().equals("")) {
	    throw new IllegalArgumentException("O campo 'porta' não pode estar vazio");
	}
    }

}
