package br.edu.ufrpe.uag.projetao.interfaces;

import org.kairos.components.MaterialButton;

import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public interface InterfaceBaseExportarController {
    /**
     * @return the caminhoPasta
     */
    public TextField getCaminhoPasta();

    /**
     * @param caminhoPasta
     *            the caminhoPasta to set
     */
    public void setCaminhoPasta(TextField caminhoPasta);

    /**
     * @return the buscaPastaButton
     */
    public MaterialButton getBuscaPastaButton();

    /**
     * @param buscaPastaButton
     *            the buscaPastaButton to set
     */
    public void setBuscaPastaButton(MaterialButton buscaPastaButton);

    /**
     * @return the salvarButton
     */
    public MaterialButton getSalvarButton();

    /**
     * @param salvarButton
     *            the salvarButton to set
     */
    public void setSalvarButton(MaterialButton salvarButton);

    /**
     * @return the cancelarButton
     */
    public MaterialButton getCancelarButton();

    /**
     * @param cancelarButton
     *            the cancelarButton to set
     */
    public void setCancelarButton(MaterialButton cancelarButton);

    /**
     * @return the tituloBase
     */
    public TextField getTituloBase();

    /**
     * @param tituloBase
     *            the tituloBase to set
     */
    public void setTituloBase(TextField tituloBase);

    /**
     * @return the descricaoBase
     */
    public TextArea getDescricaoBase();

    /**
     * @param descricaoBase
     *            the descricaoBase to set
     */
    public void setDescricaoBase(TextArea descricaoBase);

    /**
     * @return the view
     */
    public String getView();

    /**
     * @return the progresso
     */
    public ProgressBar getProgresso();

    /**
     * @param progresso
     *            the progresso to set
     */
    public void setProgresso(ProgressBar progresso);

    /**
     * Exibe a janela atual
     */
    public void show();

    /**
     * Exporta a base atual
     */
    public void salvar();

    /**
     * Busca uma pasta para exportar a base
     */
    public void buscarPasta();

    /**
     * Cancela a exportação da base
     */
    public void cancelar();
}
