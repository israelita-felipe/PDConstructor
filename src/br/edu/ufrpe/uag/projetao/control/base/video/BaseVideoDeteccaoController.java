package br.edu.ufrpe.uag.projetao.control.base.video;

import br.edu.ufrpe.uag.projetao.abstracts.AbstractDBController;
import br.edu.ufrpe.uag.projetao.model.BaseVideoDeteccao;

/**
 * 
 * @author bruno
 *
 */
public class BaseVideoDeteccaoController extends AbstractDBController<BaseVideoDeteccao> {

    public BaseVideoDeteccaoController() {
	super(BaseVideoDeteccao.class);
    }

    @Override
    public BaseVideoDeteccao getSelected() {

	if (getCurrent() == null) {
	    setCurrent(new BaseVideoDeteccao());

	    setSelectedItemIndex(-1);
	}
	return getCurrent();
    }

    @Override
    public BaseVideoDeteccao prepareCreate() {

	setCurrent(new BaseVideoDeteccao());

	setSelectedItemIndex(-1);
	return getCurrent();

    }

}
