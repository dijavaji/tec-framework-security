package ec.com.technoloqie.framework.security.ejb.persistence.businesslogic;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import ec.com.technoloqie.framework.security.ejb.commons.model.User;
import ec.com.technoloqie.framework.security.ejb.persistence.business.UserManagerLocal;

@Stateless
public class UserManager extends AbstractManager<User> implements UserManagerLocal{

	@PersistenceContext(unitName = "frameworkSecurity-ejbPU")
    private EntityManager em; 
	
	public UserManager(){
		super(User.class);
	}

    protected EntityManager getEntityManager(){
    	return em;
    }

	@Override
	public User getUser(String email, String password) throws SecurityException{
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<User> criteria = cb.createQuery(User.class);
        Root<User> member = criteria.from(User.class);
        Predicate predicateEmail = cb.equal(member.get("email"),email);
        Predicate predicatePass = cb.equal(member.get("pass"),password);
        criteria.select(member).where(cb.and(predicateEmail,predicatePass));
        return em.createQuery(criteria).getSingleResult();
	}
}
