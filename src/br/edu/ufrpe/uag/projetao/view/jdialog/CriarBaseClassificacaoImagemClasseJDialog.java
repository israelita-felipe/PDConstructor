/**
 * 
 */
package br.edu.ufrpe.uag.projetao.view.jdialog;

import java.awt.BorderLayout;

import br.edu.ufrpe.uag.projetao.abstracts.AbstractCriaEAtualizaBase;
import br.edu.ufrpe.uag.projetao.view.JPane.BaseImagemClasseJPanel;
import br.edu.ufrpe.uag.projetao.view.listeners.AdicionaImagemActionListener;
import br.edu.ufrpe.uag.projetao.view.listeners.CriarBaseImagemClasseActionListener;

/**
 * @author Juan Augusto
 *
 */
public class CriarBaseClassificacaoImagemClasseJDialog extends AbstractCriaEAtualizaBase<BaseImagemClasseJPanel> {

    public CriarBaseClassificacaoImagemClasseJDialog() {
	super(null, "Criar Base de Imagem sem fuleragem", true);
    }

    @Override
    public void init() {
	// TODO Auto-generated method stub
	super.init();
	BaseImagemClasseJPanel baseImagemClassePanel = new BaseImagemClasseJPanel();
	getContentPane().add(baseImagemClassePanel, BorderLayout.CENTER);
	setMediaComponent(baseImagemClassePanel);
    }

    @Override
    public void addListeners() {
	super.addListeners();
	getSalvarButton().addActionListener(new CriarBaseImagemClasseActionListener(this));
	getMediaComponent().getArquivosList().getBuscarArquivoButton().addActionListener(
		new AdicionaImagemActionListener(getMediaComponent().getArquivosList().getArquivosList()));
    }

    @Override
    public void preencheCampos() {
	// TODO Auto-generated method stub

    }

}
