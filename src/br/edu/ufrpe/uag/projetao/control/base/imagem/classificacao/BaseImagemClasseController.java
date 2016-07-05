/**
 * 
 */
package br.edu.ufrpe.uag.projetao.control.base.imagem.classificacao;

import br.edu.ufrpe.uag.projetao.abstracts.AbstractDBController;
import br.edu.ufrpe.uag.projetao.model.BaseImagemClasse;

/**
 * @author Juan Augusto
 *
 */
public class BaseImagemClasseController extends AbstractDBController<BaseImagemClasse> {

    public BaseImagemClasseController() {
	super(BaseImagemClasse.class);
    }

    @Override
    public BaseImagemClasse getSelected() {

	if (getCurrent() == null) {
	    setCurrent(new BaseImagemClasse());

	    setSelectedItemIndex(-1);
	}
	return getCurrent();
    }

    @Override
    public BaseImagemClasse prepareCreate() {

	setCurrent(new BaseImagemClasse());

	setSelectedItemIndex(-1);
	return getCurrent();
    }

}
