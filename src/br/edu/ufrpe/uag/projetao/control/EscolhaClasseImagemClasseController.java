/**
 * 
 */
package br.edu.ufrpe.uag.projetao.control;

import br.edu.ufrpe.uag.projetao.abstracts.AbstractController;
import br.edu.ufrpe.uag.projetao.model.EscolhaImagemClasse;

/**
 * @author Juan Augusto
 *
 */
public class EscolhaClasseImagemClasseController extends AbstractController<EscolhaImagemClasse> {

    public EscolhaClasseImagemClasseController() {
	super(EscolhaImagemClasse.class);
    }

    @Override
    public EscolhaImagemClasse getSelected() {
	if (getCurrent() == null) {
	    setCurrent(new EscolhaImagemClasse());

	    setSelectedItemIndex(-1);
	}
	return getCurrent();
    }

    @Override
    public EscolhaImagemClasse prepareCreate() {
	setCurrent(new EscolhaImagemClasse());

	setSelectedItemIndex(-1);
	return getCurrent();
    }

}
