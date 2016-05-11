/**
 * 
 */
package br.edu.ufrpe.uag.projetao.view.jdialog;

import java.awt.BorderLayout;

import br.edu.ufrpe.uag.projetao.abstracts.AbstractCriaEAtualizaBase;
import br.edu.ufrpe.uag.projetao.view.JPane.BaseTextoJPanel;
import br.edu.ufrpe.uag.projetao.view.listeners.AdicionaArquivoActionListener;
import br.edu.ufrpe.uag.projetao.view.listeners.CriarBaseTextoActionListener;

/**
 * @author israel
 *
 */
public class CriarBaseClassificacaoTextoJDialog extends AbstractCriaEAtualizaBase<BaseTextoJPanel> {

    /**
     * 
     */
    public CriarBaseClassificacaoTextoJDialog() {
	// TODO Auto-generated constructor stub
	super(null, "Criar Base de Texto", true);
    }

    @Override
    public void init() {
	// TODO Auto-generated method stub
	super.init();
	BaseTextoJPanel baseTextoPanel = new BaseTextoJPanel();
	getContentPane().add(baseTextoPanel, BorderLayout.CENTER);
	setMediaComponent(baseTextoPanel);
    }

    @Override
    public void addListeners() {
	super.addListeners();
	getSalvarButton().addActionListener(new CriarBaseTextoActionListener(this));
	getMediaComponent().getListaArquivos().getBuscarArquivoButton().addActionListener(
		new AdicionaArquivoActionListener(getMediaComponent().getListaArquivos().getArquivosList()));
    }

    @Override
    public void preencheCampos() {
    }

}
