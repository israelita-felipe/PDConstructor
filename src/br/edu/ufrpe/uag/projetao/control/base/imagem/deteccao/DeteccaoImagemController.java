/**
 * 
 */
package br.edu.ufrpe.uag.projetao.control.base.imagem.deteccao;

import br.edu.ufrpe.uag.projetao.abstracts.AbstractDBController;
import br.edu.ufrpe.uag.projetao.model.DeteccaoImagem;

/**
 * @author israel
 *
 */
public class DeteccaoImagemController extends AbstractDBController<DeteccaoImagem> {

    public DeteccaoImagemController() {
	super(DeteccaoImagem.class);
    }

    @Override
    public DeteccaoImagem getSelected() {
	if (getCurrent() == null) {
	    setCurrent(new DeteccaoImagem());

	    setSelectedItemIndex(-1);
	}
	return getCurrent();
    }

    @Override
    public DeteccaoImagem prepareCreate() {
	setCurrent(new DeteccaoImagem());

	setSelectedItemIndex(-1);
	return getCurrent();
    }

}
