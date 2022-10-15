package util;

import java.util.ArrayList;
import java.util.Iterator;

public class FormatList {

    public static String formatList(String str) {
        String[] ingredients = str.split(";\\s");
        ArrayList<String> ingredienList = new ArrayList<>();
        String ingredientString = "<ul>";

        for (int i = 0; i < ingredients.length; i++)
            ingredienList.add(ingredients[i]);

        for (Iterator<String> aux = ingredienList.iterator(); aux.hasNext();)
            ingredientString += "<li>" + aux.next() + "</li>";
        return ingredientString + "</ul>";
    }
}
