/**
 * 
 */
package br.edu.ufrpe.uag.projetao.control;

import br.edu.ufrpe.uag.projetao.abstracts.AbstractController;
import br.edu.ufrpe.uag.projetao.model.ImagemDeteccao;

/**
 * @author israel
 *
 */
public class ImagemDeteccaoController extends AbstractController<ImagemDeteccao> {

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
