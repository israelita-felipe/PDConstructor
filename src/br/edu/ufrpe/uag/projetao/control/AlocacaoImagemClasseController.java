package br.edu.ufrpe.uag.projetao.control;

import br.edu.ufrpe.uag.projetao.abstracts.AbstractController;
import br.edu.ufrpe.uag.projetao.model.AlocacaoImagemClasse;

/**
 * 
 * @author Juan Augusto
 *
 */
public class AlocacaoImagemClasseController extends AbstractController<AlocacaoImagemClasse> {

    public AlocacaoImagemClasseController() {
	super(AlocacaoImagemClasse.class);
    }

    @Override
    public AlocacaoImagemClasse getSelected() {
	if (getCurrent() == null) {
	    setCurrent(new AlocacaoImagemClasse());

	    setSelectedItemIndex(-1);
	}
	return getCurrent();
    }

    @Override
    public AlocacaoImagemClasse prepareCreate() {
	setCurrent(new AlocacaoImagemClasse());

	setSelectedItemIndex(-1);
	return getCurrent();
    }

}
