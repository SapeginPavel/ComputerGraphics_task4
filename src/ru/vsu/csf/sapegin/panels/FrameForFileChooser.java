package ru.vsu.csf.sapegin.panels;

import javax.swing.*;
import java.awt.*;

public class FrameForFileChooser extends JFrame {

    private JFileChooser fileChooser;

    public FrameForFileChooser(JFileChooser fileChooser) throws HeadlessException {
        this.fileChooser = fileChooser;
        setSize(new Dimension(500, 500));
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        add(fileChooser);
    }

}
