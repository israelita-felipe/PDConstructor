/**
 * 
 */
package br.edu.ufrpe.uag.projetao.interfaces;

import java.awt.Component;

import javax.swing.JButton;

/**
 * 
 * @author israel
 *
 * @param <C>
 *            componente de visual de mídia
 */
public interface InterfaceCriaEAtualizaBase<C extends Component> extends InterfaceWindow {

    C getMediaComponent();
    
    void setMediaComponent(C component);

    JButton getSalvarButton();

    JButton getCancelarButton();
}
