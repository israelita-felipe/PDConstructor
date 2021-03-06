package br.edu.ufrpe.uag.projetao.control.base.imagem.classificacao;

import br.edu.ufrpe.uag.projetao.abstracts.AbstractDBController;
import br.edu.ufrpe.uag.projetao.model.AlocacaoImagemClasse;

/**
 * 
 * @author Juan Augusto
 *
 */
public class AlocacaoImagemClasseController extends AbstractDBController<AlocacaoImagemClasse> {

    public AlocacaoImagemClasseController() {
	super(AlocacaoImagemClasse.class);
    }

    @Override
    public AlocacaoImagemClasse getSelected() {
	if (getCurrent() == null) {
	    setCurrent(new AlocacaoImagemClasse());

	    setSelectedItemIndex(-1);
	}
	return getCurrent();
    }

    @Override
    public AlocacaoImagemClasse prepareCreate() {
	setCurrent(new AlocacaoImagemClasse());

	setSelectedItemIndex(-1);
	return getCurrent();
    }

}
