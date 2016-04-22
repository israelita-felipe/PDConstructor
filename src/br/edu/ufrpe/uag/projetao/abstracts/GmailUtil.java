/**
 * 
 */
package br.edu.ufrpe.uag.projetao.abstracts;

import java.util.List;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import br.edu.ufrpe.uag.projetao.interfaces.InterfaceGmailUtil;

/**
 * @author israel
 *
 */
public abstract class GmailUtil implements InterfaceGmailUtil {

    Email email;

    /**
     * 
     */
    public GmailUtil() {
	// TODO Auto-generated constructor stub
	this.email = new SimpleEmail();
	this.email.setHostName("smtp.gmail.com");
	this.email.setStartTLSRequired(true);
	this.email.setStartTLSEnabled(true);
	this.email.setStartTLSEnabled(true);
	this.email.setSmtpPort(587);
	this.email.setAuthenticator(autenticar());
	this.email.setSSLOnConnect(true);
    }

    /* (non-Javadoc)
     * @see br.edu.ufrpe.uag.projetao.control.util.InterfaceGmailUtil#setRemetente(java.lang.String)
     */
    @Override
    public void setRemetente(String remetente) throws EmailException {
	this.email.setFrom(remetente);
    }

    /* (non-Javadoc)
     * @see br.edu.ufrpe.uag.projetao.control.util.InterfaceGmailUtil#setAssunto(java.lang.String)
     */
    @Override
    public void setAssunto(String assunto) {
	this.email.setSubject(assunto);
    }

    /* (non-Javadoc)
     * @see br.edu.ufrpe.uag.projetao.control.util.InterfaceGmailUtil#setMensagem(java.lang.String)
     */
    @Override
    public void setMensagem(String mensagem) throws EmailException {
	this.email.setMsg(mensagem);
    }

    /* (non-Javadoc)
     * @see br.edu.ufrpe.uag.projetao.control.util.InterfaceGmailUtil#setDestinatario(java.util.List)
     */
    @Override
    public void setDestinatario(List<String> destinatarios) throws EmailException {
	for (String destinatario : destinatarios) {
	    this.email.addTo(destinatario);
	}
    }

    /* (non-Javadoc)
     * @see br.edu.ufrpe.uag.projetao.control.util.InterfaceGmailUtil#enviar()
     */
    @Override
    public String enviar() throws EmailException {
	return this.email.send();
    }

}
