package dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import org.hibernate.query.Query;

import model.Recipe;
import model.RecipeOwner;
import util.HibernateUtil;

public class DaoAdm extends GenericDao<RecipeOwner> {

    public List<Recipe> searchRecipesForAprrove() {
        Recipe obj = new Recipe();
        Class classe = obj.getClass();
        String className = classe.getSimpleName().toString();

        Transaction transaction = null;
        List<Recipe> objects = null;

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            String hql = "from " + className + " where approved = :condition";
            Query q = session.createQuery(hql);
            q.setParameter("condition", false + "");

            objects = q.list();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            System.out.println("Search - falhou");
            System.out.println(e.getMessage());
        }

        return objects;
    }

    public List<Recipe> searchRecipesAprroved() {
        Recipe obj = new Recipe();
        Class classe = obj.getClass();
        String className = classe.getSimpleName().toString();

        Transaction transaction = null;
        List<Recipe> objects = null;

        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();

            String hql = "from " + className + " where approved = :condition";
            Query q = session.createQuery(hql);
            q.setParameter("condition", true + "");

            objects = q.list();
            transaction.commit();
        } catch (Exception e) {
            transaction.rollback();
            System.out.println("Search - falhou");
            System.out.println(e.getMessage());
        }

        return objects;
    }
}
