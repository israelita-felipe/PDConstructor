/**
 * 
 */
package br.edu.ufrpe.uag.projetao.view.jdialog;

import java.awt.BorderLayout;

import br.edu.ufrpe.uag.projetao.abstracts.AbstractClassificarBaseJDialog;
import br.edu.ufrpe.uag.projetao.model.EscolhaClasseTexto;
import br.edu.ufrpe.uag.projetao.view.scrollPanel.ClassificarTextoJScrollPane;

/**
 * @author israel
 *
 */
public class ClassificarBaseTextoJDialog
	extends AbstractClassificarBaseJDialog<ClassificarTextoJScrollPane, EscolhaClasseTexto> {

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
	setMediaComponent(classificaTextoScrollPane);
    }

    @Override
    public ClassificarTextoJScrollPane getMediaComponet() {
	// TODO Auto-generated method stub
	return this.classificaTextoScrollPane;
    }

}
