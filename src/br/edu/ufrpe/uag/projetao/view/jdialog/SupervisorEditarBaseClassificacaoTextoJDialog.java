/**
 * 
 */
package br.edu.ufrpe.uag.projetao.view.jdialog;

import br.edu.ufrpe.uag.projetao.interfaces.InterfaceController;
import br.edu.ufrpe.uag.projetao.model.BaseTexto;
import br.edu.ufrpe.uag.projetao.view.listeners.AdicionaClasseActionListener;
import br.edu.ufrpe.uag.projetao.view.listeners.AtualizarBaseTextoActionListener;
import br.edu.ufrpe.uag.projetao.view.listeners.FecharActionListener;

/**
 * @author israel
 *
 */
public class SupervisorEditarBaseClassificacaoTextoJDialog extends SupervisorCriarEAtualizarBaseClassificacaoJDialog {

    private InterfaceController<BaseTexto> baseTextoController;

    /**
     * 
     */
    public SupervisorEditarBaseClassificacaoTextoJDialog(InterfaceController<BaseTexto> baseTextoController) {
	// TODO Auto-generated constructor stub
	super(null, "Editar Base de Texto", true);
	this.baseTextoController = baseTextoController;
	addListeners();
	preencheCampos();
    }

    private void addListeners() {
	getCriarButton().addActionListener(new AtualizarBaseTextoActionListener(this, baseTextoController));

	getAdicionarClasseButton().addActionListener(
		new AdicionaClasseActionListener(getClasseParaAdicionarTextField(), getClassesList()));

	getCancelarButton().addActionListener(new FecharActionListener(this));

    }

    private void preencheCampos() {

	getTituloBaseTextField().setText(baseTextoController.getSelected().getTitulo());
	getDescricaoBaseTextArea().setText(baseTextoController.getSelected().getDescricao());

	getClasseParaAdicionarTextField().setEnabled(false);
	getAdicionarClasseButton().setEnabled(false);
	getClassesList().setEnabled(false);

	getBuscarArquivosButton().setEnabled(false);
	getArquivosList().setEnabled(false);
    }
}
