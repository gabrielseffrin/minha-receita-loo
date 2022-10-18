package control;

import static spark.Spark.*;
import spark.template.velocity.VelocityTemplateEngine;

public class Routes {

    public static void newRoutes() {

        VelocityTemplateEngine engine = new VelocityTemplateEngine();
        staticFiles.location("/public");

        /* links */
        get("/", Common::pageHome, engine);
        get("/header", Common::header, engine);
        get("/login/login", Common::login, engine);
        get("/register/register", Common::register, engine);
        get("/footer", Common::footer, engine);
        get("/minMenu", Common::minMenu, engine);
        get("/recipes/recipes", Common::recipes, engine);
        get("/recipeCreation/recipeCreation", Common::createRecipe, engine);
        get("/userRecipes/recipeBook", Common::recipeBook, engine);

        /* search */
        post("/search", SearchRecipe::search, engine);

        /* recipe detail */
        get("/recipe/:id", RecipeDetail::recipeDetail, engine);

        /* login and register */
        post("/registerUser", LoginRegister::register);
        post("/login", LoginRegister::login);

        /* create recipe */
        post("/newRecipe", CreateRecipe::createRecipe);

        /* administrator */
        get("/adm", Administrator::admArea, engine);
        get("/admRecipeDetail/:id", Administrator::recipeDetail, engine);
        get("/approveRecipe/:id", Administrator::approveRecipe);
        get("/recuseRecipe/:id", Administrator::recuseRecipe);
    }
}