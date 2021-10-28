package com.github.nikhrom.javatraining.http.locale;

import java.util.Locale;
import java.util.ResourceBundle;

public class LocaleRunner {

    public static void main(String[] args) {
        var translations = ResourceBundle.getBundle("translations", new Locale("ru", "RU"));
        var string = translations.getString("page.login.password");
        System.out.println(string);
    }

}
