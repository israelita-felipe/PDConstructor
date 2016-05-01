/**
 * 
 */
package br.edu.ufrpe.uag.projetao.control;

import br.edu.ufrpe.uag.projetao.abstracts.AbstractController;
import br.edu.ufrpe.uag.projetao.model.LiberacaoBaseTexto;

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
    public LiberacaoBaseTexto getSelected() {
	if (getCurrent() == null) {
	    setCurrent(new LiberacaoBaseTexto());

	    setSelectedItemIndex(-1);
	}
	return getCurrent();
    }

    @Override
    public LiberacaoBaseTexto prepareCreate() {
	setCurrent(new LiberacaoBaseTexto());

	setSelectedItemIndex(-1);
	return getCurrent();
    }

}
