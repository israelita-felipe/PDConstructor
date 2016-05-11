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
import org.hibernate.exception.ConstraintViolationException;

import br.edu.ufrpe.uag.projetao.abstracts.GmailUtil;
import br.edu.ufrpe.uag.projetao.control.ControllerFactory;
import br.edu.ufrpe.uag.projetao.control.hibernate.TransactionManager;
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
    private InterfaceController<Usuario> controller;
    private final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
	    + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

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

	controller = ControllerFactory.getUsuarioController();

	try {
	    validar();

	    TransactionManager.begin();

	    controller.getSelected().setNome(janela.getUsuarioNomeTextField().getText());
	    controller.getSelected().setEmail(janela.getUsuarioEmailTextField().getText());
	    controller.getSelected().setSenha(getSenha());
	    controller.getSelected().setPerfil((Perfil) janela.getPerfilComboBox().getSelectedItem());
	    controller.create();

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
		g.setRemetente("PDConstructor");
		g.enviar();

		JOptionPane.showMessageDialog(null,
			"Usuário " + controller.getSelected().getNome()
				+ " foi cadastrado com sucesso\nO.B.S.: Senha enviada para o e-mail: "
				+ controller.getSelected().getEmail());

	    } catch (EmailException e1) {
		JOptionPane.showMessageDialog(null,
			"Não foi possível enviar senha para o e-mail especificado, anote a senha:\n"
				+ controller.getSelected().getSenha());
	    } finally {
		janela.dispose();
	    }

	} catch (IllegalArgumentException ex) {
	    JOptionPane.showMessageDialog(null, ex.getMessage());

	} catch (ClassCastException ex) {
	    JOptionPane.showMessageDialog(null, "Perfil inválido");

	} catch (ConstraintViolationException ex) {
	    JOptionPane.showMessageDialog(null,
		    "E-mail com perfil já foi cadastrado no sistema\nTodos os e-mails são associados até dois perfil");

	} catch (Exception ex) {
	    JOptionPane.showMessageDialog(null, "Erro ocorrido, por favor tente novamente\n" + ex);
	} finally {
	    TransactionManager.end();
	}
    }

    /**
     * Valida os campos
     * 
     * @throws IllegalArgumentException
     */
    private void validar() throws IllegalArgumentException {
	if (janela.getUsuarioNomeTextField().getText() == null
		|| janela.getUsuarioNomeTextField().getText().trim().equals("")) {
	    throw new IllegalArgumentException("O nome de usuário deve ser preenchido");
	}
	if (janela.getUsuarioEmailTextField().getText() == null
		|| janela.getUsuarioEmailTextField().getText().trim().equals("")) {
	    throw new IllegalArgumentException("O E-mail de usuário deve ser preenchido");
	}
	if (!janela.getUsuarioEmailTextField().getText().matches(EMAIL_PATTERN)) {
	    throw new IllegalArgumentException("Endereço de E-mail inválido");
	}
    }

    /**
     * Gera uma senha para o usuário
     * 
     * @return
     */
    private String getSenha() {
	String[] carct = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f", "g", "h",
		"i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C",
		"D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X",
		"Y", "Z" };

	String senha = "";

	for (int x = 0; x < 13; x++) {
	    int j = (int) (Math.random() * carct.length);
	    senha += carct[j];
	}
	return senha;
    }

}
