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
import br.edu.ufrpe.uag.projetao.control.DetachedCriteriaFactory;
import br.edu.ufrpe.uag.projetao.control.UsuarioController;
import br.edu.ufrpe.uag.projetao.control.util.imagem.ClassificacaoBaseImagemPaginator;
import br.edu.ufrpe.uag.projetao.control.util.imagem.ImagemDigital;
import br.edu.ufrpe.uag.projetao.interfaces.InterfaceClassificacao;
import br.edu.ufrpe.uag.projetao.model.AlocacaoImagemClasse;
import br.edu.ufrpe.uag.projetao.model.ClasssificacaoImagemClasse;
import br.edu.ufrpe.uag.projetao.model.EscolhaImagemClasse;
import br.edu.ufrpe.uag.projetao.model.LiberacaoBaseImagemClasse;
import br.edu.ufrpe.uag.projetao.view.GenericTable;
import br.edu.ufrpe.uag.projetao.view.jdialog.ClassificarBaseImagemClasseJDialog;
import br.edu.ufrpe.uag.projetao.view.scrollPanel.ClassificarImagemClasseJScrollPane;

/**
 * @author Juan Augusto
 *
 */
public class NovaClassificacaoImagemClasseActionListener implements ActionListener {

    private InterfaceClassificacao<ClassificarImagemClasseJScrollPane, EscolhaImagemClasse> classificacaoBaseImagemClasseDialog;
    private GenericTable<LiberacaoBaseImagemClasse> tabela;
    private AbstractPaginador<LiberacaoBaseImagemClasse, ClasssificacaoImagemClasse, EscolhaImagemClasse, AlocacaoImagemClasse> paginador;

    /**
     * 
     */
    public NovaClassificacaoImagemClasseActionListener(GenericTable<LiberacaoBaseImagemClasse> tabela) {
	// TODO Auto-generated constructor stub
	this.tabela = tabela;

    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed(ActionEvent e) {
	if (tabela.getSelectedRow() != -1) {

	    try {

		this.paginador = ClassificacaoBaseImagemPaginator
			.getInstance(tabela.getValueAt(tabela.getSelectedRow()));

		// preenchimento da GUI
		classificacaoBaseImagemClasseDialog = new ClassificarBaseImagemClasseJDialog();
		addListeners();

		DefaultComboBoxModel<EscolhaImagemClasse> model = new DefaultComboBoxModel<>();
		for (EscolhaImagemClasse classe : paginador.getAlocacaoAtual().getEscolhaImagemClasses()) {
		    model.addElement(classe);
		}
		classificacaoBaseImagemClasseDialog.getClasseComboBox().setModel(model);
		BufferedImage imagem = ImagemDigital
			.toImage(paginador.getAlocacaoAtual().getImagemClasse().getObjeto());
		if (imagem != null) {
		  
		    imagem = ImagemDigital.resize(imagem, ImagemDigital.getWidth(), ImagemDigital.getHeight());
		    classificacaoBaseImagemClasseDialog.getMediaComponet().getImagemLabel()
			    .setIcon(new ImageIcon(imagem));
		}
		classificacaoBaseImagemClasseDialog.getClasseComboBox()
			.setSelectedItem(paginador.getItemAtual().getEscolhaImagemClasse());

		// mostra descrição da base na inicialização
		JOptionPane.showMessageDialog(null, paginador.getAlocacaoAtual().getBaseImagemClasse().getDescricao(),
			"Classificação de Bases", JOptionPane.INFORMATION_MESSAGE);

		classificacaoBaseImagemClasseDialog.setVisible(true);

	    } catch (Exception ex) {

		JOptionPane.showMessageDialog(null, "Não foi possível acessar a liberação contacte o supervisor");
		ex.printStackTrace();

	    } finally {
		// atualiza a tabela de liberações, exibindo somente as que
		// estão
		// liberadas e não foram finalizadas
		atualizaTabela();
	    }
	}

    }

    /**
     * Adiciona escutadores ao jdialog de classificação
     */
    private void addListeners() {
	classificacaoBaseImagemClasseDialog.getProximoButton().addActionListener(
		new ClassificarBaseImagemClasseProximoActionListener(classificacaoBaseImagemClasseDialog, paginador));
	classificacaoBaseImagemClasseDialog.getAnteriorButton().addActionListener(
		new ClassificarBaseImagemClasseAnteriorActionListener(classificacaoBaseImagemClasseDialog, paginador));
    }

    /**
     * Atualização de tabela de liberação
     */
    private void atualizaTabela() {
	tabela.clear();
	tabela.addAll(ControllerFactory.getLiberacaoBaseImagemClasseController().getItemsFromCriteria(
		DetachedCriteriaFactory.getLiberacoesBaseImagemClasseDoEscravo(UsuarioController.currentEscravo)));
    }

}
