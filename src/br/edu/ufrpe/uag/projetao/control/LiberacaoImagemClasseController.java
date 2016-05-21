package br.edu.ufrpe.uag.projetao.control;

import br.edu.ufrpe.uag.projetao.abstracts.AbstractController;
import br.edu.ufrpe.uag.projetao.model.LiberacaoBaseImagemClasse;
/**
 * 
 * @author Juan Augusto
 *
 */
public class LiberacaoImagemClasseController extends AbstractController<LiberacaoBaseImagemClasse>{

	public LiberacaoImagemClasseController() {
		super(LiberacaoBaseImagemClasse.class);
	}

	@Override
	public LiberacaoBaseImagemClasse getSelected() {
		if (getCurrent() == null) {
		    setCurrent(new LiberacaoBaseImagemClasse());

		    setSelectedItemIndex(-1);
		}
		return getCurrent();
	}

	@Override
	public LiberacaoBaseImagemClasse prepareCreate() {
		setCurrent(new LiberacaoBaseImagemClasse());

		setSelectedItemIndex(-1);
		return getCurrent();
	}

}
