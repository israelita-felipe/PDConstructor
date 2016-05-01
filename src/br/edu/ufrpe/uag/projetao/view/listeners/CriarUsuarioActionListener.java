/**
 * 
 */
package br.edu.ufrpe.uag.projetao.view.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JOptionPane;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;

import br.edu.ufrpe.uag.projetao.abstracts.GmailUtil;
import br.edu.ufrpe.uag.projetao.control.ControllerFactory;
import br.edu.ufrpe.uag.projetao.control.hibernate.FacesContextUtil;
import br.edu.ufrpe.uag.projetao.interfaces.InterfaceController;
import br.edu.ufrpe.uag.projetao.model.Perfil;
import br.edu.ufrpe.uag.projetao.model.Usuario;
import br.edu.ufrpe.uag.projetao.view.jdialog.CriarEditarUsuarioJDialog;

/**
 * @author israel
 *
 */
public class CriarUsuarioActionListener implements ActionListener {

    private CriarEditarUsuarioJDialog janela;

    /**
     * 
     */
    public CriarUsuarioActionListener(CriarEditarUsuarioJDialog janela) {
	// TODO Auto-generated constructor stub
	this.janela = janela;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	InterfaceController<Usuario> controller = ControllerFactory.getUsuarioController();
	FacesContextUtil.begin();

	controller.getSelected().setNome(janela.getUsuarioNomeTextField().getText());
	controller.getSelected().setEmail(janela.getUsuarioEmailTextField().getText());
	controller.getSelected().setSenha(getSenha());
	controller.getSelected().setPerfil((Perfil) janela.getPerfilComboBox().getSelectedItem());
	controller.create();

	FacesContextUtil.end();
	try {
	    GmailUtil g = new GmailUtil() {

		@Override
		public DefaultAuthenticator autenticar() {
		    return new DefaultAuthenticator("israelita.felipe@gmail.com", "1509199215091992");
		}
	    };
	    
	    g.setAssunto("Senha PDConstructor");
	    g.setDestinatario(Arrays.asList(controller.getSelected().getEmail()));
	    g.setMensagem(controller.getSelected().getSenha());
	    g.setRemetente("israelita.felipe@gmail.com");
	    g.enviar();
	    
	} catch (EmailException e1) {
	    // TODO Auto-generated catch block
	    e1.printStackTrace();
	}
	janela.dispose();
	JOptionPane.showMessageDialog(null,
		"Usuário " + controller.getSelected().getNome()
			+ " foi cadastrado com sucesso\nO.B.S.: Senha enviada para o e-mail: "
			+ controller.getSelected().getEmail());
    }

    private String getSenha() {
	String[] carct = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h",
		"i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C",
		"D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X",
		"Y", "Z" };

	String senha = "";

	for (int x = 0; x < 9; x++) {
	    int j = (int) (Math.random() * carct.length);
	    senha += carct[j];
	}
	return senha;
    }

}
