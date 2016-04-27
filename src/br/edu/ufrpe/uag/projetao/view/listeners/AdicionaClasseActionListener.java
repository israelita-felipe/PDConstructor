/**
 * 
 */
package br.edu.ufrpe.uag.projetao.view.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JTextField;

/**
 * @author israel
 *
 */
public class AdicionaClasseActionListener implements ActionListener {

    private JTextField classeTextField;
    private JList<String> classesList;

    /**
     * 
     */
    public AdicionaClasseActionListener(JTextField classeTextField, JList<String> classesList) {
	// TODO Auto-generated constructor stub
	this.classeTextField = classeTextField;
	this.classesList = classesList;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	DefaultListModel<String> model = (DefaultListModel<String>) this.classesList.getModel();
	if (!model.contains(classeTextField.getText())) {
	    model.addElement(classeTextField.getText());
	}
	classeTextField.setText(null);
    }

}
