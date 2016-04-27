/**
 * 
 */
package br.edu.ufrpe.uag.projetao.control;

import br.edu.ufrpe.uag.projetao.abstracts.AbstractController;
import br.edu.ufrpe.uag.projetao.model.ClassificacaoTexto;
import br.edu.ufrpe.uag.projetao.model.ClassificacaoTextoId;

/**
 * @author israel
 *
 */
public class ClassificacaoTextoController extends AbstractController<ClassificacaoTexto> {

    protected ClassificacaoTextoController() {
	super(ClassificacaoTexto.class);
	// TODO Auto-generated constructor stub
    }

    @Override
    public void setEmbeddableKeys() {
	// TODO Auto-generated method stub
	getCurrent().setId(new ClassificacaoTextoId());
    }

    @Override
    public void initializeEmbeddableKey() {
	// TODO Auto-generated method stub
	setEmbeddableKeys();
    }

    @Override
    public ClassificacaoTexto getSelected() {
	if (getCurrent() == null) {
	    setCurrent(new ClassificacaoTexto());
	    initializeEmbeddableKey();
	    setSelectedItemIndex(-1);
	}
	return getCurrent();
    }

    @Override
    public ClassificacaoTexto prepareCreate() {
	setCurrent(new ClassificacaoTexto());
	initializeEmbeddableKey();
	setSelectedItemIndex(-1);
	return getCurrent();
    }

}
