/**
 * 
 */
package br.edu.ufrpe.uag.projetao.control.base.texto;

import br.edu.ufrpe.uag.projetao.abstracts.AbstractDBController;
import br.edu.ufrpe.uag.projetao.model.EscolhaClasseTexto;

/**
 * @author israel
 *
 */
public class EscolhaClasseTextoController extends AbstractDBController<EscolhaClasseTexto> {

    public EscolhaClasseTextoController() {
	super(EscolhaClasseTexto.class);
	// TODO Auto-generated constructor stub
    }

    @Override
    public EscolhaClasseTexto getSelected() {
	if (getCurrent() == null) {
	    setCurrent(new EscolhaClasseTexto());

	    setSelectedItemIndex(-1);
	}
	return getCurrent();
    }

    @Override
    public EscolhaClasseTexto prepareCreate() {
	setCurrent(new EscolhaClasseTexto());

	setSelectedItemIndex(-1);
	return getCurrent();
    }

}
