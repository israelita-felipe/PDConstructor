package br.edu.ufrpe.uag.projetao.control.hibernate;

import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;

/**
 *
 * @author Israel Araújo
 */
public class FacesContextUtil {

    // ATRIBUTOS
    private static Session session;

    // METODOS
    public static void setRequestSession(Session session) {
	FacesContextUtil.session = session;
    }

    public static Session getRequestSession() {
	if (FacesContextUtil.session == null) {
	    FacesContextUtil.session = HibernateUtil.getSessionFactory().openSession();
	}
	return FacesContextUtil.session;
    }

    public static void begin() {
	FacesContextUtil.getRequestSession().beginTransaction();
    }

    public static void end() throws ConstraintViolationException {
	ConstraintViolationException ex = null;
	Session currentSession = FacesContextUtil.getRequestSession();
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
	    currentSession.close();
	    setRequestSession(null);
	    if (ex != null) {
		throw ex;
	    }
	}
    }

}
