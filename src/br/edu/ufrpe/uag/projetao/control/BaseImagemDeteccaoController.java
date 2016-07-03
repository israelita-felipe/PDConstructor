/**
 * 
 */
package br.edu.ufrpe.uag.projetao.control;

import br.edu.ufrpe.uag.projetao.abstracts.AbstractController;
import br.edu.ufrpe.uag.projetao.model.BaseImagemDeteccao;

/**
 * @author israel
 *
 */
public class BaseImagemDeteccaoController extends AbstractController<BaseImagemDeteccao> {

    public BaseImagemDeteccaoController() {
	super(BaseImagemDeteccao.class);
    }

    @Override
    public BaseImagemDeteccao getSelected() {
	if (getCurrent() == null) {
	    setCurrent(new BaseImagemDeteccao());

	    setSelectedItemIndex(-1);
	}
	return getCurrent();
    }

    @Override
    public BaseImagemDeteccao prepareCreate() {
	setCurrent(new BaseImagemDeteccao());

	setSelectedItemIndex(-1);
	return getCurrent();
    }

}
