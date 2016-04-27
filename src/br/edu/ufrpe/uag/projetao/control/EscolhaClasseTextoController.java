/**
 * 
 */
package br.edu.ufrpe.uag.projetao.control;

import br.edu.ufrpe.uag.projetao.abstracts.AbstractController;
import br.edu.ufrpe.uag.projetao.model.EscolhaClasseTexto;

/**
 * @author israel
 *
 */
public class EscolhaClasseTextoController extends AbstractController<EscolhaClasseTexto> {

    protected EscolhaClasseTextoController() {
	super(EscolhaClasseTexto.class);
	// TODO Auto-generated constructor stub
    }

    @Override
    public void setEmbeddableKeys() {
	// TODO Auto-generated method stub

    }

    @Override
    public void initializeEmbeddableKey() {
	// TODO Auto-generated method stub

    }

    @Override
    public EscolhaClasseTexto getSelected() {
	if (getCurrent() == null) {
	    setCurrent(new EscolhaClasseTexto());
	    initializeEmbeddableKey();
	    setSelectedItemIndex(-1);
	}
	return getCurrent();
    }

    @Override
    public EscolhaClasseTexto prepareCreate() {
	setCurrent(new EscolhaClasseTexto());
	initializeEmbeddableKey();
	setSelectedItemIndex(-1);
	return getCurrent();
    }

}
