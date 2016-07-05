package br.edu.ufrpe.uag.projetao.control.base.video;

import br.edu.ufrpe.uag.projetao.abstracts.AbstractDBController;
import br.edu.ufrpe.uag.projetao.model.VideoDeteccao;

/**
 * 
 * @author bruno
 *
 */
public class VideoDeteccaoController extends AbstractDBController<VideoDeteccao> {

    public VideoDeteccaoController() {
	super(VideoDeteccao.class);
    }

    @Override
    public VideoDeteccao getSelected() {
	if (getCurrent() == null) {
	    setCurrent(new VideoDeteccao());

	    setSelectedItemIndex(-1);
	}
	return getCurrent();
    }

    @Override
    public VideoDeteccao prepareCreate() {
	setCurrent(new VideoDeteccao());

	setSelectedItemIndex(-1);
	return getCurrent();

    }

}
