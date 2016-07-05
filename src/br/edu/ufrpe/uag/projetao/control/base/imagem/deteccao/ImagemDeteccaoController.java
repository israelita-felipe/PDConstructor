/**
 * 
 */
package br.edu.ufrpe.uag.projetao.control.base.imagem.deteccao;

import br.edu.ufrpe.uag.projetao.abstracts.AbstractDBController;
import br.edu.ufrpe.uag.projetao.model.ImagemDeteccao;

/**
 * @author israel
 *
 */
public class ImagemDeteccaoController extends AbstractDBController<ImagemDeteccao> {

    public ImagemDeteccaoController() {
	super(ImagemDeteccao.class);

    }

    @Override
    public ImagemDeteccao getSelected() {
	if (getCurrent() == null) {
	    setCurrent(new ImagemDeteccao());

	    setSelectedItemIndex(-1);
	}
	return getCurrent();
    }

    @Override
    public ImagemDeteccao prepareCreate() {
	setCurrent(new ImagemDeteccao());

	setSelectedItemIndex(-1);
	return getCurrent();
    }

}
