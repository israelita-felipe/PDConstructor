/**
 * 
 */
package br.edu.ufrpe.uag.projetao.view.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import br.edu.ufrpe.uag.projetao.control.ControllerFactory;
import br.edu.ufrpe.uag.projetao.control.UsuarioController;
import br.edu.ufrpe.uag.projetao.control.hibernate.TransactionManager;
import br.edu.ufrpe.uag.projetao.control.util.imagem.ImagemDigital;
import br.edu.ufrpe.uag.projetao.interfaces.InterfaceController;
import br.edu.ufrpe.uag.projetao.interfaces.InterfaceCriaEAtualiza;
import br.edu.ufrpe.uag.projetao.model.AlocacaoImagemClasse;
import br.edu.ufrpe.uag.projetao.model.BaseImagemClasse;
import br.edu.ufrpe.uag.projetao.model.EscolhaImagemClasse;
import br.edu.ufrpe.uag.projetao.model.ImagemClasse;
import br.edu.ufrpe.uag.projetao.view.JPane.BaseImagemClasseJPanel;

/**
 * @author Juan Augusto
 *
 */
public class CriarBaseImagemClasseActionListener implements ActionListener {
	private InterfaceCriaEAtualiza<BaseImagemClasseJPanel> jdialog;
	/**
	 * 
	 */
	public CriarBaseImagemClasseActionListener(InterfaceCriaEAtualiza<BaseImagemClasseJPanel> jdialog) {
		this.jdialog = jdialog;
	}
	

	/* (non-Javadoc)
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	//Imagens s�o como textos , os pedestres s�o como os banhistas 
	@Override
	public void actionPerformed(ActionEvent e) {
		try {

		    validar();

		    TransactionManager.begin();

		    InterfaceController<BaseImagemClasse> base = ControllerFactory.getBaseImagemClasseController();
		    InterfaceController<EscolhaImagemClasse> escolhaImagemClasse = ControllerFactory
			    .getEscolhaClasseImagemClasseController();
		    InterfaceController<AlocacaoImagemClasse> alocacaoImagemClasse = ControllerFactory.getAlocacaoImagemClasseController();
		    InterfaceController<ImagemClasse> imagemClasseController = ControllerFactory.getImagemClasseController();

		    // criação da base
		    base.prepareCreate();
		    base.getSelected().setTitulo(jdialog.getMediaComponent().getTituloTextField().getText());
		    base.getSelected().setDescricao(jdialog.getMediaComponent().getDescricaoEditorPane().getText());
		    base.getSelected().setUsuario(UsuarioController.currrentSupervisor);
		    base.create();

		    // criação dos textos
		    for (int i = 0; i < jdialog.getMediaComponent().getArquivosList().getArquivosList().getModel()
			    .getSize(); i++) {
			
				imagemClasseController.prepareCreate();
				imagemClasseController.getSelected().setObjeto(ImagemDigital.carregarImagemCor(jdialog.getMediaComponent()
					    .getArquivosList().getArquivosList().getModel().getElementAt(i).getAbsolutePath()));
				imagemClasseController.getSelected().setUsuario(UsuarioController.currrentSupervisor);
				imagemClasseController.create();
			    // aloca um texto para uma base
			    alocacaoImagemClasse.prepareCreate();
			    alocacaoImagemClasse.getSelected().setBaseImagemClasse(base.getSelected());
			    alocacaoImagemClasse.getSelected().setUsuario(UsuarioController.currrentSupervisor);
			    alocacaoImagemClasse.getSelected().setImagemClasse(imagemClasseController.getSelected());
			    alocacaoImagemClasse.create();

			    // criação das classes
			    for (int j = 0; j < jdialog.getMediaComponent().getClassesList().getClassesList().getModel()
				    .getSize(); j++) {

				// aloca uma classe para uma alocacao de texto
				escolhaImagemClasse.prepareCreate();
				escolhaImagemClasse.getSelected().setAlocacaoImagemClasse(alocacaoImagemClasse.getSelected());
				escolhaImagemClasse.getSelected().setDescricao(jdialog.getMediaComponent().getClassesList()
					.getClassesList().getModel().getElementAt(j).toString());
				escolhaImagemClasse.create();
			    }			
		    }
		    jdialog.dispose();
		    JOptionPane.showMessageDialog(null, "Base " + base.getSelected().getId() + " criada com sucesso");
		    
		} catch (IllegalArgumentException ex) {
		    JOptionPane.showMessageDialog(null, ex.getMessage());
		    
		} catch (Exception ex) {
		    JOptionPane.showMessageDialog(null, "Não foi possível gravar, tente novamente");
		    
		} finally {
		    TransactionManager.end();
		}

	}

    /**
     * Executa a validação dos campos
     * 
     * @throws IllegalArgumentException
     */
    private void validar() throws IllegalArgumentException {
	if (jdialog.getMediaComponent().getTituloTextField().getText() == null
		|| jdialog.getMediaComponent().getTituloTextField().getText().trim().equals("")) {
	    throw new IllegalArgumentException("Campo de título não pode estar vazio");
	}
	if (jdialog.getMediaComponent().getDescricaoEditorPane().getText() == null
		|| jdialog.getMediaComponent().getDescricaoEditorPane().getText().equals("")) {
	    throw new IllegalArgumentException("Campo de descrição não pode estar vazio");
	}
	if (jdialog.getMediaComponent().getClassesList().getClassesList().getModel().getSize() == 0) {
	    throw new IllegalArgumentException("Lista de classes não pode estar vazia");
	}
	if (jdialog.getMediaComponent().getArquivosList().getArquivosList().getModel().getSize() == 0) {
	    throw new IllegalArgumentException("Lista de arquivos não pode estar vazia");
	}
    }


}
