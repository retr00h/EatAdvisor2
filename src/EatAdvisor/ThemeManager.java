package EatAdvisor;

import java.io.File;

public class ThemeManager {
    private static final String LIGHT_THEME_PATH = ".." + File.separator + ".." + File.separator + "res" + File.separator + "light.css";
    private static final String DARK_THEME_PATH = ".." + File.separator + ".." + File.separator + "res" + File.separator + "dark.css";

    private String lightThemeUrl;
    private String darkThemeUrl;

    private boolean dark;

    public ThemeManager () {
        dark = false;
//        lightThemeUrl = getClass().getResource("light.css").toExternalForm();
//        darkThemeUrl = getClass().getResource("dark.css").toExternalForm();
    }

    private void changeTheme() {
        if (dark) {
            dark = false;
            setTheme(lightThemeUrl);
        } else {
            dark = true;
            setTheme(darkThemeUrl);
        }
    }

    private void setTheme(String themeUrl) {

    }
}
