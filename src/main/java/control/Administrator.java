package control;

import java.util.HashMap;

import dao.DaoAdm;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

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
}
