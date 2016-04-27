/**
 * 
 */
package br.edu.ufrpe.uag.projetao.view.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 * @author israel
 *
 */
public class RemoveDeListActionListener implements ActionListener {

    private JList list;

    /**
     * 
     */
    public RemoveDeListActionListener(JList list) {
	// TODO Auto-generated constructor stub
	this.list = list;
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
	if (list.getSelectedIndex() != -1) {
	    DefaultListModel model = (DefaultListModel) list.getModel();
	    model.removeElementAt(list.getSelectedIndex());	    
	    model = null;
	    list.revalidate();	    
	}
    }

}
