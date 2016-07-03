package br.edu.ufrpe.uag.projetao.abstracts;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.DetachedCriteria;

import br.edu.ufrpe.uag.projetao.interfaces.InterfaceEntity;
import br.edu.ufrpe.uag.projetao.interfaces.InterfaceFacade;

/**
 *
 * @author Israel Araújo
 * @param <T>
 */
public abstract class AbstractFacade<T extends InterfaceEntity> implements InterfaceFacade<T> {

    private final Class<T> entityClass;

    public AbstractFacade(Class<T> entityClass) {
	this.entityClass = entityClass;
    }

    @Override
    public void create(T entity) {
	getSession().persist(entity);
    }

    @Override
    public void edit(T entity) {
	getSession().merge(entity);
    }

    @Override
    public void remove(T entity) {
	getSession().delete(entity);
    }

    @Override
    public T find(Serializable id) {
	return (T) getSession().load(entityClass, id);
    }

    @Override
    public List<T> findAll() {
	DetachedCriteria query = DetachedCriteria.forClass(entityClass);
	return getEntitiesByDetachedCriteria(query);
    }

    @Override
    public List<T> findRange(int[] range) {
	Criteria q = getSession().createCriteria(entityClass);
	q.setMaxResults(range[1] - range[0]);
	q.setFirstResult(range[0]);
	return q.list();
    }

    @Override
    public int count() {
	return findAll().size();
    }

    @Override
    public T getEntityByDetachedCriteria(DetachedCriteria criteria) {
	return (T) criteria.getExecutableCriteria(getSession()).uniqueResult();
    }

    @Override
    public List<T> getEntitiesByDetachedCriteria(DetachedCriteria criteria) {
	return criteria.getExecutableCriteria(getSession()).list();
    }

    @Override
    public Class<T> getFacadeClass() {
	return entityClass;
    }

}
