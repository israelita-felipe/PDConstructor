/**
 * 
 */
package br.edu.ufrpe.uag.projetao.control;

import br.edu.ufrpe.uag.projetao.abstracts.AbstractController;
import br.edu.ufrpe.uag.projetao.model.AlocacaoTexto;

/**
 * @author israel
 *
 */
public class AlocacaoTextoController extends AbstractController<AlocacaoTexto> {

    protected AlocacaoTextoController() {
	super(AlocacaoTexto.class);
	// TODO Auto-generated constructor stub
    }

    @Override
    public AlocacaoTexto getSelected() {
	if (getCurrent() == null) {
	    setCurrent(new AlocacaoTexto());

	    setSelectedItemIndex(-1);
	}
	return getCurrent();
    }

    @Override
    public AlocacaoTexto prepareCreate() {
	setCurrent(new AlocacaoTexto());

	setSelectedItemIndex(-1);
	return getCurrent();
    }

}
