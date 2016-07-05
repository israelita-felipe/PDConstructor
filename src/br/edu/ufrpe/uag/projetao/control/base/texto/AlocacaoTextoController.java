/**
 * 
 */
package br.edu.ufrpe.uag.projetao.control.base.texto;

import br.edu.ufrpe.uag.projetao.abstracts.AbstractDBController;
import br.edu.ufrpe.uag.projetao.model.AlocacaoTexto;

/**
 * @author israel
 *
 */
public class AlocacaoTextoController extends AbstractDBController<AlocacaoTexto> {

    public AlocacaoTextoController() {
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
