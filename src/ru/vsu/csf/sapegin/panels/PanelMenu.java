package ru.vsu.csf.sapegin.panels;

import ru.vsu.csf.sapegin.GraphicApp;
import ru.vsu.csf.sapegin.MyRLE;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class PanelMenu extends JPanel {

    private final JButton buttonChoose = new JButton("Выбрать картинку для сжатия");
    private final JButton buttonCompress = new JButton("Сжать");
    private final JFileChooser fileChooser = new JFileChooser();

    private File selectedFile = null;

    public PanelMenu(PanelImage panelStartImage, PanelImage panelFinalImage) {
        setLayout(new FlowLayout());
        setPreferredSize(new Dimension(100, 100));
        setMaximumSize(new Dimension(100, 100));
        initElements();

        buttonChoose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fileChooser.showOpenDialog(getThis());
            }
        });
        fileChooser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedFile = fileChooser.getSelectedFile();
                try {
                    panelFinalImage.deleteImage();
                    panelStartImage.setImage(selectedFile.getPath());
                    add(buttonCompress);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
        buttonCompress.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String pathToCompressibleImg = MyRLE.compressImage(selectedFile.getPath());
                    panelFinalImage.setImage(pathToCompressibleImg);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    private JPanel getThis() {
        return this;
    }

    private void initElements() {
        add(buttonChoose);
        fileChooser.setSize(new Dimension(300, 300));
        fileChooser.setCurrentDirectory(new File((GraphicApp.class.getResource("images")).getPath()));
        fileChooser.setLocation(-500, -100);
    }
}
