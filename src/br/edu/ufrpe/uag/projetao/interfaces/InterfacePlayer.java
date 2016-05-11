/**
 * 
 */
package br.edu.ufrpe.uag.projetao.interfaces;

/**
 * @author israel
 *
 */
public interface InterfacePlayer {

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
}
