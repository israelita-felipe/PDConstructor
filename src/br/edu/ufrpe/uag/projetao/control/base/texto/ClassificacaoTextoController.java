/**
 * 
 */
package br.edu.ufrpe.uag.projetao.control.base.texto;

import br.edu.ufrpe.uag.projetao.abstracts.AbstractController;
import br.edu.ufrpe.uag.projetao.model.ClassificacaoTexto;

/**
 * @author israel
 *
 */
public class ClassificacaoTextoController extends AbstractController<ClassificacaoTexto> {

    public ClassificacaoTextoController() {
	super(ClassificacaoTexto.class);
	// TODO Auto-generated constructor stub
    }

    @Override
    public ClassificacaoTexto getSelected() {
	if (getCurrent() == null) {
	    setCurrent(new ClassificacaoTexto());

	    setSelectedItemIndex(-1);
	}
	return getCurrent();
    }

    @Override
    public ClassificacaoTexto prepareCreate() {
	setCurrent(new ClassificacaoTexto());

	setSelectedItemIndex(-1);
	return getCurrent();
    }

}
