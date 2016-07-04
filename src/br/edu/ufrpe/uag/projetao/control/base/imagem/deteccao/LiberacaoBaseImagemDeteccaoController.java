/**
 * 
 */
package br.edu.ufrpe.uag.projetao.control.base.imagem.deteccao;

import br.edu.ufrpe.uag.projetao.abstracts.AbstractController;
import br.edu.ufrpe.uag.projetao.model.LiberacaoBaseImagemDeteccao;

/**
 * @author israel
 *
 */
public class LiberacaoBaseImagemDeteccaoController extends AbstractController<LiberacaoBaseImagemDeteccao> {

    public LiberacaoBaseImagemDeteccaoController() {
	super(LiberacaoBaseImagemDeteccao.class);
    }

    @Override
    public LiberacaoBaseImagemDeteccao getSelected() {
	if (getCurrent() == null) {
	    setCurrent(new LiberacaoBaseImagemDeteccao());

	    setSelectedItemIndex(-1);
	}
	return getCurrent();
    }

    @Override
    public LiberacaoBaseImagemDeteccao prepareCreate() {
	setCurrent(new LiberacaoBaseImagemDeteccao());

	setSelectedItemIndex(-1);
	return getCurrent();
    }

}
