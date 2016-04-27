/**
 * 
 */
package br.edu.ufrpe.uag.projetao.view.jdialog;

import br.edu.ufrpe.uag.projetao.view.listeners.AdicionaArquivoActionListener;
import br.edu.ufrpe.uag.projetao.view.listeners.AdicionaClasseActionListener;
import br.edu.ufrpe.uag.projetao.view.listeners.CriarBaseTextoActionListener;
import br.edu.ufrpe.uag.projetao.view.listeners.FecharActionListener;

/**
 * @author israel
 *
 */
public class SupervisorCriarBaseClassificacaoTextoJDialog extends SupervisorCriarEAtualizarBaseClassificacaoJDialog {

    /**
     * 
     */
    public SupervisorCriarBaseClassificacaoTextoJDialog() {
	// TODO Auto-generated constructor stub
	super(null, "Criar Base de Texto", true);
	addListeners();
    }

    @SuppressWarnings("unchecked")
    private void addListeners() {
	getCriarButton().addActionListener(new CriarBaseTextoActionListener(this));

	getAdicionarClasseButton().addActionListener(
		new AdicionaClasseActionListener(getClasseParaAdicionarTextField(), getClassesList()));

	getBuscarArquivosButton().addActionListener(new AdicionaArquivoActionListener(getArquivosList()));

	getCancelarButton().addActionListener(new FecharActionListener(this));

    }

}
