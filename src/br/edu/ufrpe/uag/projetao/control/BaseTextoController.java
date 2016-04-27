/**
 * 
 */
package br.edu.ufrpe.uag.projetao.control;

import br.edu.ufrpe.uag.projetao.abstracts.AbstractController;
import br.edu.ufrpe.uag.projetao.model.BaseTexto;

/**
 * @author israel
 *
 */
public class BaseTextoController extends AbstractController<BaseTexto> {
    

    protected BaseTextoController() {
	super(BaseTexto.class);
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
    public BaseTexto getSelected() {
	if (getCurrent() == null) {
	    setCurrent(new BaseTexto());
	    initializeEmbeddableKey();
	    setSelectedItemIndex(-1);
	}
	return getCurrent();
    }

    @Override
    public BaseTexto prepareCreate() {
	setCurrent(new BaseTexto());
	initializeEmbeddableKey();
	setSelectedItemIndex(-1);
	return getCurrent();
    }    

}
