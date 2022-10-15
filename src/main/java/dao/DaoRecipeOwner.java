package dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import org.hibernate.query.Query;

import model.RecipeOwner;
import util.HibernateUtil;

public class DaoRecipeOwner extends GenericDao<RecipeOwner> {

    public RecipeOwner getObjectByEmailAndPassword(RecipeOwner obj, String email, String condition) {

        Class classe = obj.getClass();
        String className = classe.getSimpleName().toString();

        Transaction transaction = null;
        RecipeOwner objects = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            // start the transaction
            transaction = session.beginTransaction();
            // get the students
            String hql = "from " + className + " where password = :condition and email = :conditionTwo";
            Query q = session.createQuery(hql);
            q.setParameter("condition", condition);
            q.setParameter("conditionTwo", email);
            objects = (RecipeOwner) q.uniqueResult();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                System.out.println("ListALL - abriu transaction mas falhou");
            }
        }

        return objects;
    }

}
