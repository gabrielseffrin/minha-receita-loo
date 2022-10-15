package control;

import model.RecipeOwner;

public class SessionControl {

    private static SessionControl instance;
    public RecipeOwner user;

    private SessionControl() {
    }

    public void setUser(RecipeOwner user) {
        this.user = user;
    }

    public RecipeOwner getUser() {
        return user;
    }

    public static SessionControl getInstance() {
        if (instance == null) {
            synchronized (SessionControl.class) {
                if (instance == null) {
                    instance = new SessionControl();
                }
            }
        }

        return instance;
    }
}