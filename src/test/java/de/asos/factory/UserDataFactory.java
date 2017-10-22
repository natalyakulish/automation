package de.asos.factory;

import de.asos.models.UserDataModel;

public class UserDataFactory {

    private static String email = System.currentTimeMillis() + "@mailinator.com";
    private static String password = "q!w@e#r$1234";

    public static UserDataModel getUserData() {
        return new UserDataModel(email, password, "Natalie", "Kulish", "27", "October", "1989", "female");
    }

    public static UserDataModel getExistingUserData() {
        return new UserDataModel("shoes@mailinator.com", password, "Natalie", "Kulish", "27", "October", "1989", "female");
    }
}
