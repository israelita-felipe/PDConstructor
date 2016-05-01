package br.edu.ufrpe.uag.projetao.control.hibernate;

import org.hibernate.Session;

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

    public static void end() {
	Session currentSession = FacesContextUtil.getRequestSession();
	try {
	    currentSession.getTransaction().commit();
	} catch (Exception e) {
	    if (currentSession.getTransaction().isActive()) {
		currentSession.getTransaction().rollback();
	    }
	}finally {
	    currentSession.close();
	    setRequestSession(null);
	}
    }

}
