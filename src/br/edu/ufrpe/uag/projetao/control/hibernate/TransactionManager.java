package br.edu.ufrpe.uag.projetao.control.hibernate;

import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author Israel Araújo
 */
public class TransactionManager {

    private static Session session;

    /**
     * Inserção da sessão
     * 
     * @param session
     */
    public static void setRequestSession(Session session) {
	TransactionManager.session = session;
    }

    /**
     * Instancia uma sessão e abre caso não existe alguma ativa
     * 
     * @return
     */
    public static Session getRequestSession() {
	if (TransactionManager.session == null) {
	    TransactionManager.session = HibernateUtil.getSessionFactory().openSession();
	}
	return TransactionManager.session;
    }

    /**
     * Inicia uma transação
     */
    public static synchronized void begin() {
	TransactionManager.getRequestSession().beginTransaction();
    }

    /**
     * Finaliza uma transação dando commit nas operações
     * 
     * @throws ConstraintViolationException
     *             caso haja dados já cadastrados com os mesmos dados
     */
    public static synchronized void end() throws ConstraintViolationException {
	ConstraintViolationException ex = null;
	Session currentSession = TransactionManager.getRequestSession();
	try {
	    currentSession.getTransaction().commit();
	} catch (ConstraintViolationException e) {
	    if (currentSession.getTransaction().isActive()) {
		currentSession.getTransaction().rollback();
	    }
	    ex = e;
	} catch (Exception e) {
	    if (currentSession.getTransaction().isActive()) {
		currentSession.getTransaction().rollback();
	    }
	} finally {
	    // currentSession.close();
	    // setRequestSession(null);
	    if (ex != null) {
		throw ex;
	    }
	}
    }

}
