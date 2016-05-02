/**
 * 
 */
package br.edu.ufrpe.uag.projetao.interfaces;

/**
 * @author israel
 *
 */
public interface InterfaceWindow {

    /**
     * Fecha a janela
     */
    void dispose();

    /**
     * Exibe a janela
     * 
     * @param visible
     */
    void setVisible(boolean visible);

    /**
     * Inicializa os componentes graficos
     */
    void init();

    /**
     * Adiciona Listeners aos componentes
     */
    void addListeners();

    /**
     * Preenche os campos da janela
     */
    void preencheCampos();
}
