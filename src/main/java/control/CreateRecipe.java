package control;

import dao.GenericDao;
import model.Recipe;
import spark.Request;
import spark.Response;
import util.MyException;

public class CreateRecipe {

    public static Object createRecipe(Request req, Response res) {
        SessionControl session = SessionControl.getInstance();

        if (session.getUser() != null) {

            try {

                String nameRecipe = req.queryParams("inputNameRecipe");
                String ingredients = req.queryParams("inputIngredients");
                String preparationMode = req.queryParams("inputPrapararionMode");
                String note = req.queryParams("inputNote");
                String catogory = req.queryParams("inputCategory");
                boolean isPublic = Boolean.parseBoolean(req.queryParams("inputIsPublic"));
                boolean status = false;

                Recipe recipe = new Recipe();
                recipe.setNameRecipe(nameRecipe);
                recipe.setIngredients(ingredients);
                recipe.setPreparationMode(preparationMode);
                recipe.setNote(note);
                recipe.setCategory(catogory);
                recipe.setIsPublic(isPublic);
                recipe.setStatus(status);

                System.out.println(session.getUser());
                recipe.setOwner(session.getUser());

                GenericDao<Recipe> gDaoRecipe = new GenericDao<>();
                if (ingredients.contains(";") && preparationMode.contains(";"))
                    gDaoRecipe.save(recipe);
                else
                    throw new MyException("Deve conter ';' para identação");

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }

            res.redirect("/recipes/recipes");
            return "ok";
        }

        res.redirect("/login/login");
        return "fail";

    }
}
