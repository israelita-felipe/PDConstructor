/**
 * 
 */
package br.edu.ufrpe.uag.projetao.interfaces;

import javax.swing.JList;

/**
 * 
 * @author israel
 *
 * @param <T>
 *            Tipo de variável de armazenamento da coordenada
 */
public interface InterfaceComponenteListavel<T> {

    JList<T> getCoordenadas();
}
