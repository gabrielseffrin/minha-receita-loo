package control;

import java.util.HashMap;

import dao.DaoRecipe;
import model.Category;
import model.Recipe;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class Common {

    private static SessionControl session = SessionControl.getInstance();

    public static ModelAndView pageHome(Request req, Response res) {
        HashMap<String, Object> model = new HashMap<>();
        return new ModelAndView(model, "view/index.vm");
    }

    public static ModelAndView header(Request req, Response res) {
        HashMap<String, Object> model = new HashMap<>();
        String aux;

        try {
            aux = SessionControl.getInstance().getUser().getId() == 1
                    ? "<li class='nav-item'><a class='nav-link' href='/adm'>administração</a></li>"
                    : "";
        } catch (Exception e) {
            aux = "";
        }
        model.put("adm", aux);
        return new ModelAndView(model, "view/header.vm");
    }

    public static ModelAndView login(Request req, Response res) {
        HashMap<String, Object> model = new HashMap<>();
        return new ModelAndView(model, "view/login/login.vm");
    }

    public static ModelAndView register(Request req, Response res) {
        HashMap<String, Object> model = new HashMap<>();
        return new ModelAndView(model, "view/register/userRegister.vm");
    }

    public static ModelAndView footer(Request req, Response res) {
        HashMap<String, Object> model = new HashMap<>();
        return new ModelAndView(model, "view/footer.vm");
    }

    public static ModelAndView minMenu(Request req, Response res) {
        HashMap<String, Object> model = new HashMap<>();
        return new ModelAndView(model, "view/minMenu.vm");
    }

    public static ModelAndView recipes(Request req, Response res) {
        HashMap<String, Object> model = new HashMap<>();
        DaoRecipe gRecipe = new DaoRecipe();
        Recipe recipe = new Recipe();
        model.put("allrecipes", gRecipe.listRecipesPublicAndApproved(recipe));
        return new ModelAndView(model, "view/recipes/recipes.vm");
    }

    public static ModelAndView createRecipe(Request req, Response res) {
        HashMap<String, Object> model = new HashMap<>();

        if (session.getUser() != null) {
            model.put("categorys", Category.values());
            return new ModelAndView(model, "view/recipeCreation/recipeCreation.vm");
        }

        return new ModelAndView(model, "view/login/login.vm");
    }

    public static ModelAndView recipeBook(Request req, Response res) {
        HashMap<String, Object> model = new HashMap<>();

        if (session.getUser() != null) {

            DaoRecipe gRecipe = new DaoRecipe();
            Recipe recipe = new Recipe();
            model.put("allrecipes", gRecipe.getElementsByIdRecipeOwner(recipe, session.getUser().getId()));
            return new ModelAndView(model, "view/userRecipes/recipeBook.vm");
        }

        return new ModelAndView(model, "view/login/login.vm");
    }
}