package ru.vsu.csf.sapegin;

import ru.vsu.csf.sapegin.panels.PanelImage;
import ru.vsu.csf.sapegin.panels.PanelMenu;

import javax.swing.*;
import java.awt.*;

public class GraphicApp {
    private final JFrame mainFrame = new JFrame();
    private final PanelMenu panelMenu;
    private final PanelImage panelStartImage = new PanelImage();
    private final PanelImage panelFinalImage = new PanelImage();
    private final JPanel panelImages = new JPanel();

    public GraphicApp() {
        mainFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        mainFrame.setSize(Toolkit.getDefaultToolkit().getScreenSize());
        panelMenu = new PanelMenu(panelStartImage, panelFinalImage);
        mainFrame.setLayout(new BorderLayout());
        initElements();
    }

    public void start() {
        mainFrame.setVisible(true);
    }

    private void initElements() {
        mainFrame.add(BorderLayout.SOUTH, panelMenu);
        panelImages.setLayout(new GridLayout(1, 2));
        panelImages.add(panelStartImage);
        panelImages.add(panelFinalImage);
        mainFrame.add(BorderLayout.CENTER, panelImages);
    }


}
