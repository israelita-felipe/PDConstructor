/**
 * 
 */
package br.edu.ufrpe.uag.projetao.control;

import org.hibernate.Query;

import br.edu.ufrpe.uag.projetao.control.hibernate.TransactionManager;
import br.edu.ufrpe.uag.projetao.model.BaseImagemClasse;
import br.edu.ufrpe.uag.projetao.model.BaseTexto;

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

}
