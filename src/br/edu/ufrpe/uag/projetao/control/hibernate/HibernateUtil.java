package br.edu.ufrpe.uag.projetao.control.hibernate;

import java.io.FileInputStream;
import java.util.Properties;

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
    private static SessionFactory sessionFactory;
    private static Properties resources;

    /**
     * metodo estático que retorna um fábria de sessão
     *
     * @return
     */
    public static SessionFactory getSessionFactory() {
	try {
	    resources = new Properties();
	    resources.load(new FileInputStream("config_pt_BR.properties"));

	    Configuration configuration = new Configuration().configure();

	    // lê arquivo de configurações
	    String url = "jdbc:postgresql://" + resources.getProperty("hibernate.connection.url.ip") + ":"
		    + resources.getProperty("hibernate.connection.url.port") + "/pdconstructor";

	    // sobreescreve a configuração de url de conecção com o banco
	    configuration.setProperty("hibernate.connection.url", url);

	    StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
		    .applySettings(configuration.getProperties());

	    sessionFactory = configuration.buildSessionFactory(builder.build());
	} catch (Exception ex) {
	    throw new ExceptionInInitializerError(ex);
	}

	return sessionFactory;
    }
}
