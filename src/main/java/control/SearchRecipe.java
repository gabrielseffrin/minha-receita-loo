package control;

import java.util.HashMap;

import spark.Request;
import spark.Response;

import dao.GenericDao;
import model.Recipe;
import spark.ModelAndView;

public class SearchRecipe {

    public static ModelAndView search(Request req, Response res) {
        HashMap<String, Object> model = new HashMap<>();
        GenericDao<Recipe> gRecipe = new GenericDao<>();
        Recipe recipe = new Recipe();

        String condition = req.queryParams("inputSearch");

        model.put("allrecipes", gRecipe.search(recipe, condition));
        // res.redirect("/recipes/recipes");
        return new ModelAndView(model, "view/recipes/recipes.vm");
    }
}
