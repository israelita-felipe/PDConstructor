package br.edu.ufrpe.uag.projetao.control.base.video;

import br.edu.ufrpe.uag.projetao.abstracts.AbstractDBController;
import br.edu.ufrpe.uag.projetao.model.DeteccaoVideo;

/**
 * 
 * @author bruno
 *
 */
public class DeteccaoVideoController extends AbstractDBController<DeteccaoVideo> {

    public DeteccaoVideoController() {
	super(DeteccaoVideo.class);
    }

    @Override
    public DeteccaoVideo getSelected() {
	if (getCurrent() == null) {
	    setCurrent(new DeteccaoVideo());

	    setSelectedItemIndex(-1);
	}
	return getCurrent();
    }

    @Override
    public DeteccaoVideo prepareCreate() {
	setCurrent(new DeteccaoVideo());

	setSelectedItemIndex(-1);
	return getCurrent();

    }

}
