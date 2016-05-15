/**
 * 
 */
package br.edu.ufrpe.uag.projetao.interfaces;

import javax.swing.JPanel;

import br.edu.ufrpe.uag.projetao.control.util.video.CoordenadaTempoVideo;

/**
 * 
 * @author israel
 *
 */
public interface InterfacePlayer extends InterfaceComponenteListavel<CoordenadaTempoVideo> {

    /**
     * Inicia o player
     * 
     * @return verdadeiro caso inicie a reprodução
     */
    boolean iniciar();

    /**
     * Para o player
     * 
     * @return verdadeiro caso para a reprodução
     */
    boolean parar();

    /**
     * Pega o tempo restanta a ser reproduzido
     * 
     * @return o tempo restante em segundos
     */
    double getTempoRestante();

    /**
     * Tepo total de reprodução da mídia
     * 
     * @return o tempo total de reprodução em segundos
     */
    double getTempoTotal();

    /**
     * Pega o tempo atual da mídia que está sendo reproduzida
     * 
     * @return o tempo atual em segundos
     */
    double getTempoAtual();

    /**
     * 
     * @return Painel de video
     */
    JPanel getVideoPanel();

    /**
     * Insere um arquivo de mídia para ser reproduzido
     * 
     * @param arquivoDeMidia
     *            caminho do arquivo
     */
    void setMidia(String arquivoDeMidia);

    /**
     * Libera o player avisando ao GargabeCollector
     */
    void encerrar();
}
