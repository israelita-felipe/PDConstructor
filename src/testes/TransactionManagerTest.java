/**
 * 
 */
package testes;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.hibernate.Session;
import org.junit.Test;

import br.edu.ufrpe.uag.projetao.control.hibernate.TransactionManager;

/**
 * @author israel
 *
 */
public class TransactionManagerTest {

    /**
     * Test method for
     * {@link br.edu.ufrpe.uag.projetao.control.hibernate.TransactionManager#setRequestSession(org.hibernate.Session)}
     * .
     */
    @Test
    public void testSetRequestSession() {
	Session antes = TransactionManager.getRequestSession();
	TransactionManager.setRequestSession(null);
	Session depois = TransactionManager.getRequestSession();
	assertNotEquals(antes, depois);
    }

    /**
     * Test method for
     * {@link br.edu.ufrpe.uag.projetao.control.hibernate.TransactionManager#getRequestSession()}
     * .
     */
    @Test
    public void testGetRequestSession() {
	assertNotEquals(null, TransactionManager.getRequestSession());
	assertTrue(TransactionManager.getRequestSession().isOpen());
    }

    /**
     * Test method for
     * {@link br.edu.ufrpe.uag.projetao.control.hibernate.TransactionManager#begin()}
     * .
     */
    @Test
    public void testBegin() {
	TransactionManager.end();
	TransactionManager.begin();
	assertTrue(TransactionManager.getRequestSession().getTransaction().isActive());
    }

    /**
     * Test method for
     * {@link br.edu.ufrpe.uag.projetao.control.hibernate.TransactionManager#end()}
     * .
     */
    @Test
    public void testEnd() {
	TransactionManager.end();
	assertFalse(TransactionManager.getRequestSession().getTransaction().isActive());
    }

}
