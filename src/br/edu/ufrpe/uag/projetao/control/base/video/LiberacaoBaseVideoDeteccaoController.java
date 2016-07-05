package br.edu.ufrpe.uag.projetao.control.base.video;

import br.edu.ufrpe.uag.projetao.abstracts.AbstractDBController;
import br.edu.ufrpe.uag.projetao.model.LiberacaoBaseVideoDeteccao;

/**
 * 
 * @author bruno
 *
 */
public class LiberacaoBaseVideoDeteccaoController extends AbstractDBController<LiberacaoBaseVideoDeteccao> {

    public LiberacaoBaseVideoDeteccaoController() {
	super(LiberacaoBaseVideoDeteccao.class);
    }

    @Override
    public LiberacaoBaseVideoDeteccao getSelected() {
	if (getCurrent() == null) {
	    setCurrent(new LiberacaoBaseVideoDeteccao());

	    setSelectedItemIndex(-1);
	}
	return getCurrent();
    }

    @Override
    public LiberacaoBaseVideoDeteccao prepareCreate() {
	setCurrent(new LiberacaoBaseVideoDeteccao());

	setSelectedItemIndex(-1);
	return getCurrent();

    }

}
