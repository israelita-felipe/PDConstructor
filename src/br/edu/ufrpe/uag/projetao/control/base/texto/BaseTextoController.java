/**
 * 
 */
package br.edu.ufrpe.uag.projetao.control.base.texto;

import br.edu.ufrpe.uag.projetao.abstracts.AbstractController;
import br.edu.ufrpe.uag.projetao.model.BaseTexto;

/**
 * @author israel
 *
 */
public class BaseTextoController extends AbstractController<BaseTexto> {

    public BaseTextoController() {
	super(BaseTexto.class);
	// TODO Auto-generated constructor stub
    }

    @Override
    public BaseTexto getSelected() {
	if (getCurrent() == null) {
	    setCurrent(new BaseTexto());

	    setSelectedItemIndex(-1);
	}
	return getCurrent();
    }

    @Override
    public BaseTexto prepareCreate() {
	setCurrent(new BaseTexto());

	setSelectedItemIndex(-1);
	return getCurrent();
    }

}
