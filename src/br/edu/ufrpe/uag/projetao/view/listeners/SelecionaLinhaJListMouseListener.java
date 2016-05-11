/**
 * 
 */
package br.edu.ufrpe.uag.projetao.view.listeners;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JList;
import javax.swing.ListSelectionModel;

/**
 * @author israel
 *
 */
public class SelecionaLinhaJListMouseListener extends MouseAdapter {

    private JList list;

    /**
     * 
     */
    public SelecionaLinhaJListMouseListener(JList list) {
	this.list = list;
	this.list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    @Override
    public void mousePressed(MouseEvent e) {
	if (e.getButton() == MouseEvent.BUTTON3) {
	    int index = list.locationToIndex(e.getPoint());
	    if (index != -1) {
		list.setSelectedIndex(index);
	    }
	}
    }
}
