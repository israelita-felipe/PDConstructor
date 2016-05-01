/**
 * 
 */
package br.edu.ufrpe.uag.projetao.view.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import br.edu.ufrpe.uag.projetao.control.ControllerFactory;
import br.edu.ufrpe.uag.projetao.control.hibernate.FacesContextUtil;
import br.edu.ufrpe.uag.projetao.view.jdialog.CriarUsuarioJDialog;

/**
 * @author israel
 *
 */
public class NovoUsuarioActionListener implements ActionListener {

    /**
     * 
     */
    public NovoUsuarioActionListener() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub		
	new CriarUsuarioJDialog().setVisible(true);
    }

}
