/**
 * 
 */
package br.edu.ufrpe.uag.projetao.view.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import br.edu.ufrpe.uag.projetao.abstracts.AbstractPaginador;
import br.edu.ufrpe.uag.projetao.control.ControllerFactory;
import br.edu.ufrpe.uag.projetao.control.hibernate.TransactionManager;
import br.edu.ufrpe.uag.projetao.control.util.imagem.ImagemDigital;
import br.edu.ufrpe.uag.projetao.interfaces.InterfaceClassificacao;
import br.edu.ufrpe.uag.projetao.interfaces.InterfaceController;
import br.edu.ufrpe.uag.projetao.model.AlocacaoImagemClasse;
import br.edu.ufrpe.uag.projetao.model.ClasssificacaoImagemClasse;
import br.edu.ufrpe.uag.projetao.model.EscolhaImagemClasse;
import br.edu.ufrpe.uag.projetao.model.LiberacaoBaseImagemClasse;
import br.edu.ufrpe.uag.projetao.model.enumerate.StatusDeLiberacao;
import br.edu.ufrpe.uag.projetao.view.scrollPanel.ClassificarImagemClasseJScrollPane;

/**
 * @author Juan Augusto
 *
 */
public class ClassificarBaseImagemClasseProximoActionListener implements ActionListener {

    private InterfaceController<LiberacaoBaseImagemClasse> liberacaoBaseImagemClasseController;

    private AbstractPaginador<LiberacaoBaseImagemClasse, ClasssificacaoImagemClasse, EscolhaImagemClasse, AlocacaoImagemClasse> paginador;
    private InterfaceClassificacao<ClassificarImagemClasseJScrollPane, EscolhaImagemClasse> classificacaoBaseImagemClasseDialog;

    /**
     * 
     */
    public ClassificarBaseImagemClasseProximoActionListener(
	    InterfaceClassificacao<ClassificarImagemClasseJScrollPane, EscolhaImagemClasse> classificacaoBaseImagemClasseDialog,
	    AbstractPaginador<LiberacaoBaseImagemClasse, ClasssificacaoImagemClasse, EscolhaImagemClasse, AlocacaoImagemClasse> paginador) {
	this.classificacaoBaseImagemClasseDialog = classificacaoBaseImagemClasseDialog;
	this.paginador = paginador;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {

	if (paginador.getItemAtual().getEscolhaImagemClasse() == null) {
	    paginador.setInserindo();
	} else {
	    paginador.setEditando();
	}
	paginador.getItemAtual().setEscolhaImagemClasse(
		(EscolhaImagemClasse) classificacaoBaseImagemClasseDialog.getClasseComboBox().getSelectedItem());
	paginador.gravaAlteracoes();

	selecionaProximoItem();
    }

    private void selecionaProximoItem() {

	try {
	    paginador.proximo();
	    // preenchendo as classe
	    DefaultComboBoxModel<EscolhaImagemClasse> model = new DefaultComboBoxModel<>();
	    for (EscolhaImagemClasse classe : paginador.getAlocacaoAtual().getEscolhaImagemClasses()) {
		model.addElement(classe);
	    }

	    classificacaoBaseImagemClasseDialog.getClasseComboBox()
		    .setSelectedItem(paginador.getItemAtual().getEscolhaImagemClasse());
	    BufferedImage imagem = ImagemDigital.toImage(paginador.getAlocacaoAtual().getImagemClasse().getObjeto());
	    if (imagem != null) {
		imagem = ImagemDigital.resize(imagem, ImagemDigital.getWidth(), ImagemDigital.getHeight());
		classificacaoBaseImagemClasseDialog.getMediaComponet().getImagemLabel().setIcon(new ImageIcon(imagem));
	    }

	} catch (IllegalArgumentException ex) {
	    aprovaFechamentoDaBase();
	}

    }

    /**
     * Verifica se o usuário deseja fechar a base que está trabalhando.
     */
    private void aprovaFechamentoDaBase() {
	// verifica se o último ítem precisa ser gravado

	int aprovacao = JOptionPane.showConfirmDialog(null, "Parabéns, tudo finalizado, deseja encerrar a base?",
		"Finalizado", JOptionPane.YES_NO_OPTION);
	if (aprovacao == JOptionPane.YES_OPTION) {

	    // iniciar o fechamento da base
	    liberacaoBaseImagemClasseController = ControllerFactory.getLiberacaoBaseImagemClasseController();

	    try {

		TransactionManager.begin();

		liberacaoBaseImagemClasseController
			.prepareEdit(liberacaoBaseImagemClasseController.getItems().indexOf(paginador.getLiberacao()));
		liberacaoBaseImagemClasseController.getSelected().setStatus(StatusDeLiberacao.BLOQUEADO);
		liberacaoBaseImagemClasseController.update();

	    } catch (Exception ex) {
		JOptionPane.showMessageDialog(null, "Não foi possível finalizar a base, tente novamente");

	    } finally {
		TransactionManager.end();
	    }

	    this.classificacaoBaseImagemClasseDialog.dispose();
	}
    }

}
