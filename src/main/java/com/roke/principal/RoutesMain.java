package com.roke.principal;


import com.formdev.flatlaf.intellijthemes.FlatOneDarkIJTheme;
import com.roke.principal.ui.AppFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;


public class RoutesMain {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FlatOneDarkIJTheme.setup();

            UIManager.put("Button.arc", 15);
            UIManager.put("Component.arc", 10);
            UIManager.put("CheckBox.arc", 10);
            UIManager.put("ComboBox.arc", 10);
            UIManager.put("ProgressBar.arc", 20);
            UIManager.put("TextComponent.arc", 10);
            UIManager.put("ScrollBar.showButtons", true);

            new AppFrame().setVisible(true);
        });        
    }
}
