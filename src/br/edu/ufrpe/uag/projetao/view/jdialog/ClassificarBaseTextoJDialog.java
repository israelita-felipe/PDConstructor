/**
 * 
 */
package br.edu.ufrpe.uag.projetao.view.jdialog;

import java.awt.BorderLayout;

import br.edu.ufrpe.uag.projetao.view.listeners.ClassificarBaseTextoProximoActionListener;
import br.edu.ufrpe.uag.projetao.view.scrollPanel.ClassificarTextoJScrollPane;

/**
 * @author israel
 *
 */
public class ClassificarBaseTextoJDialog extends ClassificarBaseJDialog {

    private ClassificarTextoJScrollPane classificaTextoScrollPane;

    /**
     * 
     */
    public ClassificarBaseTextoJDialog() {
	// TODO Auto-generated constructor stub
	super(null, "Classificar Base de Texto", true);
    }

    @Override
    public void init() {
	// TODO Auto-generated method stub
	super.init();
	classificaTextoScrollPane = new ClassificarTextoJScrollPane(null);
	getMainPanel().add(classificaTextoScrollPane, BorderLayout.CENTER);

    }

    @Override
    public void addListeners() {
	// TODO Auto-generated method stub
	super.addListeners();
	getProximoButton().addActionListener(new ClassificarBaseTextoProximoActionListener(this));
    }

    public ClassificarTextoJScrollPane getClassificaTextoScrollPane() {
	return classificaTextoScrollPane;
    }

    public void setClassificaTextoScrollPane(ClassificarTextoJScrollPane classificaTextoScrollPane) {
	this.classificaTextoScrollPane = classificaTextoScrollPane;
    }

}
