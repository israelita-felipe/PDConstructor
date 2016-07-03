/**
 * 
 */
package br.edu.ufrpe.uag.projetao.control;

import org.hibernate.Query;

import br.edu.ufrpe.uag.projetao.control.hibernate.TransactionManager;
import br.edu.ufrpe.uag.projetao.model.BaseImagemClasse;
import br.edu.ufrpe.uag.projetao.model.BaseImagemDeteccao;
import br.edu.ufrpe.uag.projetao.model.BaseTexto;
import br.edu.ufrpe.uag.projetao.model.BaseVideoDeteccao;

/**
 * @author israel
 *
 */
public class QueryFactory {

    public static Query getTodasBasesImagemClasseHistograma() {
	return TransactionManager.getRequestSession().getNamedQuery("baseImagemClasseHistograma");
    }

    public static Query getBaseImagemClasseHistogramaPorBase(BaseImagemClasse base) {
	Query query = TransactionManager.getRequestSession().getNamedQuery("baseImagemClasseHistogramaPorBase");
	query.setInteger("base", base.getId());
	return query;
    }

    public static Query getTodasBasesTextoHistograma() {
	return TransactionManager.getRequestSession().getNamedQuery("baseTextoHistograma");
    }

    public static Query getBaseTextoHistogramaPorBase(BaseTexto base) {
	Query query = TransactionManager.getRequestSession().getNamedQuery("baseTextoHistogramaPorBase");
	query.setInteger("base", base.getId());
	return query;
    }
    /**
     * 
     * @return
     */    
    public static Query getTodasBasesImagemDeteccaoHistograma() {
    	return TransactionManager.getRequestSession().getNamedQuery("baseImagemDeteccaoHistograma");
        }
    
    public static Query getBaseImagemDeteccaoHistogramaPorBase(BaseImagemDeteccao base) {
	Query query = TransactionManager.getRequestSession().getNamedQuery("baseImagemDeteccaoHistogramaPorBase");
	query.setInteger("base", base.getId());
	return query;
    }
    /**
     * 
     * @return
     */
    public static Query getTodasBasesVideoDeteccaoHistograma() {
    	return TransactionManager.getRequestSession().getNamedQuery("baseVideoDeteccaoHistograma");
        }
    
    public static Query getBaseVideoDeteccaoHistogramaPorBase(BaseVideoDeteccao base) {
	Query query = TransactionManager.getRequestSession().getNamedQuery("baseVideoDeteccaoHistogramaPorBase");
	query.setInteger("base", base.getId());
	return query;
    }

}
