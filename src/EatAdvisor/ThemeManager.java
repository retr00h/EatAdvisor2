package EatAdvisor;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;

import java.io.ByteArrayInputStream;
import java.util.Base64;

public class ThemeManager {
    private static final String MOON_ICON_BASE_64 = /*"data:image/png;base64," + */"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";
    private static final String SUN_ICON_BASE64 = /*"data:image/png;base64," + */"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";

    private static final byte[] MOON_ICON_BYTEARRAY = Base64.getDecoder().decode(MOON_ICON_BASE_64);
    private static final byte[] SUN_ICON_BYTEARRAY = Base64.getDecoder().decode(SUN_ICON_BASE64);

    public static final Image moonIcon = new Image(new ByteArrayInputStream(MOON_ICON_BYTEARRAY),50,50,true,true);
    public static final Image sunIcon = new Image(new ByteArrayInputStream(SUN_ICON_BYTEARRAY),50,50,true,true);

    private static ThemeManager themeManager;
    private boolean dark;

    public static ThemeManager getThemeManager() {
        if (themeManager == null) {
            themeManager = new ThemeManager();
        }
        return themeManager;
    }

    private ThemeManager () {
        dark = true;
        themeManager = this;
    }

    public void changeTheme(boolean ignoreChange, Pane pane, ImageView imageView) {
        setCss(ignoreChange, pane, imageView);
    }

    public boolean isDark() {
        return dark;
    }

    private void setCss(boolean ignoreChange, Pane pane, ImageView imageView) {
        if (ignoreChange) {
            if (dark) {
                imageView.setImage(sunIcon);
                pane.getStylesheets().clear();
                pane.getStylesheets().add("dark.css");
            } else {
                imageView.setImage(moonIcon);
                pane.getStylesheets().clear();
                pane.getStylesheets().add("light.css");
            }
        } else {
            if (dark) {
                dark = false;
                imageView.setImage(moonIcon);
                pane.getStylesheets().clear();
                pane.getStylesheets().add("light.css");
            } else {
                dark = true;
                imageView.setImage(sunIcon);
                pane.getStylesheets().clear();
                pane.getStylesheets().add("dark.css");
            }
        }
    }

    public boolean alert(Label l, boolean okay) {
        if (dark) {
            // tema scuro
            if (okay) {
                l.setTextFill(Paint.valueOf("00FF9F"));
                return true;
            } else {
                l.setTextFill(Paint.valueOf("D600FF"));
                return false;
            }
        } else {
            // tema chiaro (old)
            if (okay) {
                l.setTextFill(Paint.valueOf("000000"));
                return true;
            } else {
                l.setTextFill(Paint.valueOf("FF0000"));
                return false;
            }
        }
    }
}
