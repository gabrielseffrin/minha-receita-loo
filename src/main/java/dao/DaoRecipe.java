package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import org.hibernate.query.Query;

import model.BaseEntity;
import model.Recipe;
import util.HibernateUtil;

public class DaoRecipe extends GenericDao<Recipe> {

    public List<Recipe> getElementsByIdRecipeOwner(Object obj, long id_recipeOwner) {

        Class classe = obj.getClass();
        String className = classe.getSimpleName().toString();

        Transaction transaction = null;
        List<Recipe> objects = null;
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            // start the transaction
            transaction = session.beginTransaction();
            // get the students
            String hql = "from " + className + " where owner_id_owner = :id_recipeOwner";
            Query q = session.createQuery(hql);
            q.setParameter("id_recipeOwner", id_recipeOwner);
            objects = q.list();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                System.out.println("ListALL - abriu transaction mas falhou");
            }
        }

        return objects;
    }

    public ArrayList<Recipe> listRecipesPublicAndApproved(Recipe obj) {

        Class<? extends BaseEntity> classe = obj.getClass();
        String className = classe.getSimpleName().toString();

        Transaction transaction = null;
        ArrayList<Recipe> objects = new ArrayList<>();
        try {
            Session session = HibernateUtil.getSessionFactory().openSession();
            // start the transaction
            transaction = session.beginTransaction();
            // get the objects
            List<Recipe> auxList = null;

            auxList = session.createQuery("from " + className).list();

            for (Recipe recipe : auxList) {
                if (recipe.isPublic() && recipe.getStatus()) {
                    objects.add(recipe);
                }
            }

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