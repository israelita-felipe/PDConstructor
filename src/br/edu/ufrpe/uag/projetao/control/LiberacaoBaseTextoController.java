/**
 * 
 */
package br.edu.ufrpe.uag.projetao.control;

import br.edu.ufrpe.uag.projetao.abstracts.AbstractController;
import br.edu.ufrpe.uag.projetao.model.LiberacaoBaseTexto;
import br.edu.ufrpe.uag.projetao.model.LiberacaoBaseTextoId;

/**
 * @author israel
 *
 */
public class LiberacaoBaseTextoController extends AbstractController<LiberacaoBaseTexto> {

    protected LiberacaoBaseTextoController() {
	super(LiberacaoBaseTexto.class);
	// TODO Auto-generated constructor stub
    }

    @Override
    public void setEmbeddableKeys() {
	// TODO Auto-generated method stub
	getSelected().setId(new LiberacaoBaseTextoId());
    }

    @Override
    public void initializeEmbeddableKey() {
	// TODO Auto-generated method stub
	setEmbeddableKeys();
    }

    @Override
    public LiberacaoBaseTexto getSelected() {
	if (getCurrent() == null) {
	    setCurrent(new LiberacaoBaseTexto());
	    initializeEmbeddableKey();
	    setSelectedItemIndex(-1);
	}
	return getCurrent();
    }

    @Override
    public LiberacaoBaseTexto prepareCreate() {
	setCurrent(new LiberacaoBaseTexto());
	initializeEmbeddableKey();
	setSelectedItemIndex(-1);
	return getCurrent();
    }

}
