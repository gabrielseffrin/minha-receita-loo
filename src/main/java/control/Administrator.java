package control;

import spark.Request;
import spark.Response;
import util.FormatList;

import java.util.ArrayList;
import java.util.HashMap;

import org.eclipse.jetty.util.thread.TryExecutor;

import dao.GenericDao;
import model.Recipe;
import spark.ModelAndView;

import dao.DaoAdm;

public class Administrator {

    public static ModelAndView admArea(Request req, Response res) {
        HashMap<String, Object> model = new HashMap<>();

        if (SessionControl.getInstance().getUser().getId() != 1) {
            res.redirect("/login");
        }

        try {
            DaoAdm adm = new DaoAdm();
            model.put("recipesForApprove", adm.searchRecipesForAprrove());

        } catch (Exception e) {
            // TODO: handle exception
        }
        return new ModelAndView(model, "view/adm/adm.vm");
    }

    public static ModelAndView recipeDetail(Request req, Response res) {
        HashMap<String, Object> model = new HashMap<>();
        GenericDao<Recipe> gDao = new GenericDao<>();
        Recipe recipe = new Recipe();

        ArrayList<String> dataRecipe = new ArrayList<>();

        try {
            long id_recipe = Long.parseLong(req.params("id"));

            recipe = gDao.getObjectById(recipe, id_recipe);

            dataRecipe.add(FormatList.formatList(recipe.getIngredients()));
            dataRecipe.add(FormatList.formatList(recipe.getPreparationMode()));
            dataRecipe.add(FormatList.formatList(recipe.getNote()));

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("erro no banco");
        }

        String aux = SessionControl.getInstance().getUser().getId() == 1
                ? "<a href='/approveRecipe' >"
                        + "<button type='button' class='btn btn-danger text-white'>aprovar receita</button></a>" +
                        "<a href='/recuseRecipe' ><button type='button' class='btn btn-danger text-white'>aprovar receita</button></a>"
                : "";

        model.put("edit", aux);
        model.put("dataRecipe", dataRecipe);
        model.put("recipe", recipe);
        return new ModelAndView(model, "view/recipeDetail/recipeDetail.vm");
    }

    public static Object approveRecipe(Request req, Response res) {

        String id = req.queryParams("id");
        long idd;

        try {
            idd = Long.parseLong(id);
            System.out.println(idd);
        } catch (Exception e) {
            // TODO: handle exception
        }

        res.redirect("");
        return "ok";
    }

    public static Object recuseRecipe(Request req, Response res) {
        res.redirect("");
        return "ok";
    }
}
