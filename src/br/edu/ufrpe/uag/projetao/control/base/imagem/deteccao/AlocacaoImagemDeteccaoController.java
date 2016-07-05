/**
 * 
 */
package br.edu.ufrpe.uag.projetao.control.base.imagem.deteccao;

import br.edu.ufrpe.uag.projetao.abstracts.AbstractDBController;
import br.edu.ufrpe.uag.projetao.model.AlocacaoImagemDeteccao;

/**
 * @author israel
 *
 */
public class AlocacaoImagemDeteccaoController extends AbstractDBController<AlocacaoImagemDeteccao> {

    public AlocacaoImagemDeteccaoController() {
	super(AlocacaoImagemDeteccao.class);
	// TODO Auto-generated constructor stub
    }

    @Override
    public AlocacaoImagemDeteccao getSelected() {
	if (getCurrent() == null) {
	    setCurrent(new AlocacaoImagemDeteccao());

	    setSelectedItemIndex(-1);
	}
	return getCurrent();
    }

    @Override
    public AlocacaoImagemDeteccao prepareCreate() {
	setCurrent(new AlocacaoImagemDeteccao());
	setSelectedItemIndex(-1);
	return getCurrent();
    }

}
