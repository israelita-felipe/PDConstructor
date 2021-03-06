package br.edu.ufrpe.uag.projetao.control.base.video;

import br.edu.ufrpe.uag.projetao.abstracts.AbstractDBController;
import br.edu.ufrpe.uag.projetao.model.AlocacaoVideoDeteccao;

/**
 * 
 * @author bruno
 *
 */
public class AlocacaoVideoDeteccaoController extends AbstractDBController<AlocacaoVideoDeteccao> {

    public AlocacaoVideoDeteccaoController() {
	super(AlocacaoVideoDeteccao.class);
    }

    @Override
    public AlocacaoVideoDeteccao getSelected() {
	if (getCurrent() == null) {
	    setCurrent(new AlocacaoVideoDeteccao());

	    setSelectedItemIndex(-1);
	}
	return getCurrent();

    }

    @Override
    public AlocacaoVideoDeteccao prepareCreate() {

	setCurrent(new AlocacaoVideoDeteccao());

	setSelectedItemIndex(-1);
	return getCurrent();
    }

}
