/**
 * 
 */
package br.edu.ufrpe.uag.projetao.abstracts;

import java.io.IOException;

import org.kairos.components.MaterialButton;

import br.edu.ufrpe.uag.projetao.interfaces.InterfaceBase;
import br.edu.ufrpe.uag.projetao.interfaces.InterfaceBaseExportarController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

/**
 * @author israel
 *
 */
public abstract class AbstractBaseExportarController<T extends InterfaceBase> implements InterfaceBaseExportarController {

    @FXML
    private TextField caminhoPasta;

    @FXML
    private MaterialButton buscaPastaButton;

    @FXML
    private MaterialButton salvarButton;

    @FXML
    private MaterialButton cancelarButton;

    @FXML
    private TextField tituloBase;

    @FXML
    private TextArea descricaoBase;

    @FXML
    private ProgressBar progresso;

    private final String view;

    private Stage dialogStage;
    
    private T base;

    /**
     * Cria um tela de exportação de base
     * 
     * @param base
     *            a ser exportada
     */
    public AbstractBaseExportarController(T base) {
	this.base = base;
	this.view = getView(base);

	this.init();

	this.tituloBase.setText(base.getTitulo());
	this.descricaoBase.setText(base.getDescricao());
    }

    private void init() {
	try {
	    this.dialogStage = new Stage();
	    this.dialogStage.setTitle("Exportar base");

	    FXMLLoader loader = new FXMLLoader();
	    loader.setLocation(getClass().getResource(getView()));
	    loader.setController(this);

	    BorderPane node = loader.load();
	    Scene scene = new Scene(node);
	    this.dialogStage.setScene(scene);

	} catch (IOException e) {
	    e.printStackTrace();
	    Alert dialogoErro = new Alert(Alert.AlertType.ERROR);
	    dialogoErro.setTitle("Erro desconhecido");
	    dialogoErro.setHeaderText("Erro ao exibir tela de exportação de base");
	    dialogoErro.setContentText("Não foi possível exibir a janela de exportação, por favor tente novamente\n"
		    + "caso o problema persista reinicie a aplicação");
	    dialogoErro.showAndWait();
	}
    }

    private static String getView(InterfaceBase base) {
	return "/br/edu/ufrpe/uag/projetao/view/base/BaseExportar.fxml";
    }

    /**
     * @return the caminhoPasta
     */
    public TextField getCaminhoPasta() {
	return caminhoPasta;
    }

    /**
     * @param caminhoPasta
     *            the caminhoPasta to set
     */
    public void setCaminhoPasta(TextField caminhoPasta) {
	this.caminhoPasta = caminhoPasta;
    }

    /**
     * @return the buscaPastaButton
     */
    public MaterialButton getBuscaPastaButton() {
	return buscaPastaButton;
    }

    /**
     * @param buscaPastaButton
     *            the buscaPastaButton to set
     */
    public void setBuscaPastaButton(MaterialButton buscaPastaButton) {
	this.buscaPastaButton = buscaPastaButton;
    }

    /**
     * @return the salvarButton
     */
    public MaterialButton getSalvarButton() {
	return salvarButton;
    }

    /**
     * @param salvarButton
     *            the salvarButton to set
     */
    public void setSalvarButton(MaterialButton salvarButton) {
	this.salvarButton = salvarButton;
    }

    /**
     * @return the cancelarButton
     */
    public MaterialButton getCancelarButton() {
	return cancelarButton;
    }

    /**
     * @param cancelarButton
     *            the cancelarButton to set
     */
    public void setCancelarButton(MaterialButton cancelarButton) {
	this.cancelarButton = cancelarButton;
    }

    /**
     * @return the tituloBase
     */
    public TextField getTituloBase() {
	return tituloBase;
    }

    /**
     * @param tituloBase
     *            the tituloBase to set
     */
    public void setTituloBase(TextField tituloBase) {
	this.tituloBase = tituloBase;
    }

    /**
     * @return the descricaoBase
     */
    public TextArea getDescricaoBase() {
	return descricaoBase;
    }

    /**
     * @param descricaoBase
     *            the descricaoBase to set
     */
    public void setDescricaoBase(TextArea descricaoBase) {
	this.descricaoBase = descricaoBase;
    }

    /**
     * @return the view
     */
    public String getView() {
	return view;
    }

    /**
     * @return the progresso
     */
    public ProgressBar getProgresso() {
	return progresso;
    }

    /**
     * @param progresso
     *            the progresso to set
     */
    public void setProgresso(ProgressBar progresso) {
	this.progresso = progresso;
    }

    @FXML
    @Override
    public void show() {
	this.dialogStage.show();
    }

    @FXML
    @Override
    public void cancelar() {
	this.dialogStage.close();
    }

    @FXML
    @Override
    public void buscarPasta() {
	DirectoryChooser dc = new DirectoryChooser();
	this.caminhoPasta.setText(dc.showDialog(null).getAbsolutePath());
    }

    /**
     * @return the base
     */
    public T getBase() {
        return base;
    }

    /**
     * @param base the base to set
     */
    public void setBase(T base) {
        this.base = base;
    }

}
