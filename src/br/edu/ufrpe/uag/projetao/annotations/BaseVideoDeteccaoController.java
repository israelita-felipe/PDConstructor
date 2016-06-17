package br.edu.ufrpe.uag.projetao.annotations;

import br.edu.ufrpe.uag.projetao.abstracts.AbstractController;
import br.edu.ufrpe.uag.projetao.model.BaseVideoDeteccao;
/**
 * 
 * @author bruno
 *
 */
public class BaseVideoDeteccaoController extends AbstractController<BaseVideoDeteccao>{

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
