package br.edu.ufrpe.uag.projetao.interfaces;

import java.util.List;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailException;

public interface InterfaceGmailUtil {

    /**
     * Insere o remetente ao email a ser enviado
     * 
     * @param remetente
     * @throws EmailException
     */
    void setRemetente(String remetente) throws EmailException;

    /**
     * Insere o assunto no email atual
     * 
     * @param assunto
     */
    void setAssunto(String assunto);

    /**
     * Insere a mensagem no email atual
     * 
     * @param mensagem
     * @throws EmailException
     */
    void setMensagem(String mensagem) throws EmailException;

    /**
     * Adiciona destinatários ao email atual
     * 
     * @param destinatarios
     *            <code>List<String></code> e Strings onde cada String
     *            representa um destinatário
     * @throws EmailException
     */
    void setDestinatario(List<String> destinatarios) throws EmailException;

    /**
     * Envia o email atual
     * 
     * @return String de confirmação de envio
     * @throws EmailException
     */
    String enviar() throws EmailException;

    /**
     * Retorna a autenticação do usuário remetente
     * 
     * @return Um org.apache.commons.mail.DefaultAuthenticator que recebe como
     *         parâmetro 1: o usuário ou email a ser autenticado; 2: a senha do
     *         usuário ou email a ser autenticado
     */
    DefaultAuthenticator autenticar();

}