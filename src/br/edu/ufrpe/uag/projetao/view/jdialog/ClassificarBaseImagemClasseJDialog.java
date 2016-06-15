/**
 * 
 */
package br.edu.ufrpe.uag.projetao.view.jdialog;

import java.awt.BorderLayout;

import javax.swing.SwingConstants;

import br.edu.ufrpe.uag.projetao.abstracts.AbstractClassificarBaseJDialog;
import br.edu.ufrpe.uag.projetao.model.EscolhaImagemClasse;
import br.edu.ufrpe.uag.projetao.view.scrollPanel.ClassificarImagemClasseJScrollPane;

/**
 * @author Juan Augusto
 *
 */
public class ClassificarBaseImagemClasseJDialog
	extends AbstractClassificarBaseJDialog<ClassificarImagemClasseJScrollPane, EscolhaImagemClasse> {
    private ClassificarImagemClasseJScrollPane classificaImagemClasseScrollPane;

    /**
     * 
     */
    public ClassificarBaseImagemClasseJDialog() {
	super(null, "Classificar Base de imagem", true);
    }

    @Override
    public void init() {
	super.init();
	classificaImagemClasseScrollPane = new ClassificarImagemClasseJScrollPane(null);
	classificaImagemClasseScrollPane.getImagemLabel().setHorizontalTextPosition(SwingConstants.CENTER);
	classificaImagemClasseScrollPane.getImagemLabel().setHorizontalAlignment(SwingConstants.CENTER);
	getMainPanel().add(classificaImagemClasseScrollPane, BorderLayout.CENTER);
	setMediaComponent(classificaImagemClasseScrollPane);
    }

    @Override
    public ClassificarImagemClasseJScrollPane getMediaComponet() {
	return this.classificaImagemClasseScrollPane;
    }
}
