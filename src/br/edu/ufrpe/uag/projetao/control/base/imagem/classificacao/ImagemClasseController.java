/**
 * 
 */
package br.edu.ufrpe.uag.projetao.control.base.imagem.classificacao;

import br.edu.ufrpe.uag.projetao.abstracts.AbstractDBController;
import br.edu.ufrpe.uag.projetao.model.ImagemClasse;

/**
 * @author Juan Augusto
 *
 */
public class ImagemClasseController extends AbstractDBController<ImagemClasse> {

    public ImagemClasseController() {
	super(ImagemClasse.class);
    }

    @Override
    public ImagemClasse getSelected() {
	if (getCurrent() == null) {
	    setCurrent(new ImagemClasse());

	    setSelectedItemIndex(-1);
	}
	return getCurrent();
    }

    @Override
    public ImagemClasse prepareCreate() {
	setCurrent(new ImagemClasse());

	setSelectedItemIndex(-1);
	return getCurrent();
    }

}
