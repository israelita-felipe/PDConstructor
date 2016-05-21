/**
 * 
 */
package br.edu.ufrpe.uag.projetao.control;

import br.edu.ufrpe.uag.projetao.abstracts.AbstractController;
import br.edu.ufrpe.uag.projetao.model.ClasssificacaoImagemClasse;

/**
 * @author Juan Augusto
 *
 */
public class ClassificacaoImagemClasseController extends AbstractController<ClasssificacaoImagemClasse> {

	public ClassificacaoImagemClasseController() {
		super(ClasssificacaoImagemClasse.class);
	}

	@Override
	public ClasssificacaoImagemClasse getSelected() {
		if (getCurrent() == null) {
		    setCurrent(new ClasssificacaoImagemClasse());

		    setSelectedItemIndex(-1);
		}
		return getCurrent();
	}

	@Override
	public ClasssificacaoImagemClasse prepareCreate() {
		setCurrent(new ClasssificacaoImagemClasse());

		setSelectedItemIndex(-1);
		return getCurrent();
	}

}
