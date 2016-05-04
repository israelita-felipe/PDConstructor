package br.edu.ufrpe.uag.projetao.control.hibernate;

// IMPORTS
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * FÁBRICA DE SESSÃO DO HIBERNATE
 *
 * @author Israel Araújo
 */
public class HibernateUtil {

    /**
     * ATRIBUTOS
     */
    private static final SessionFactory sessionFactory;

    /**
     * construtor estático da fábrica de sessões
     */
    static {
	try {
	    Configuration configuration = new Configuration().configure();

	    StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
		    .applySettings(configuration.getProperties());

	    sessionFactory = configuration.buildSessionFactory(builder.build());
	} catch (Exception ex) {
	    throw new ExceptionInInitializerError(ex);
	}

    }

    /**
     * metodo estático que retorna um fábria de sessão
     *
     * @return
     */
    public static SessionFactory getSessionFactory() {
	return sessionFactory;
    }
}
