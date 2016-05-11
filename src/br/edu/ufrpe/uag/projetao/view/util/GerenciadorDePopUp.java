/**
 * 
 */
package br.edu.ufrpe.uag.projetao.view.util;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPopupMenu;

/**
 * @author israel
 *
 */
public class GerenciadorDePopUp {

    public static void addPopup(Component component, final JPopupMenu popup) {
	component.addMouseListener(new MouseAdapter() {
	    public void mousePressed(MouseEvent e) {
		if (e.isPopupTrigger()) {
		    showMenu(e);
		}
	    }

	    public void mouseReleased(MouseEvent e) {
		if (e.isPopupTrigger()) {
		    showMenu(e);
		}
	    }

	    private void showMenu(MouseEvent e) {
		popup.show(e.getComponent(), e.getX(), e.getY());
	    }
	});
    }
}
