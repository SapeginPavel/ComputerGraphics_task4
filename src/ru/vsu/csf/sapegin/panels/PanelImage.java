package ru.vsu.csf.sapegin.panels;

import ru.vsu.csf.sapegin.MyRLE;
import ru.vsu.csf.sapegin.Utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class PanelImage extends JPanel {

    public final int WIDTH_OF_IMAGE = Toolkit.getDefaultToolkit().getScreenSize().width / 2 - 100;
    public final int HEIGHT_OF_IMAGE = 600;

    private File uploadedImage;
    private long sizeOfFile;

    public void setImage(String path) throws IOException {
        uploadedImage = new File(path);
        sizeOfFile = uploadedImage.length();
        if (Utils.getSubstringAfterPoint(path).startsWith(MyRLE.MY_RLE_EXTENSION)) {
            path = MyRLE.decompressImage(path);
            uploadedImage = new File(path);
        }
        revalidate();
        repaint();
    }

    public void deleteImage() {
        uploadedImage = null;
        revalidate();
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (uploadedImage != null) {
            BufferedImage image = null;
            try {
                image = ImageIO.read(uploadedImage);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (image != null) {
                int h = image.getHeight();
                int w = image.getWidth();
                int finalH;
                int finalW;
                double coefficientOfScale = (double) w / WIDTH_OF_IMAGE;
                if (h / coefficientOfScale > HEIGHT_OF_IMAGE) {
                    coefficientOfScale = (double) h / HEIGHT_OF_IMAGE;
                    finalH = HEIGHT_OF_IMAGE;
                    finalW = (int) (w / coefficientOfScale);
                } else {
                    finalH = (int) (h / coefficientOfScale);
                    finalW = WIDTH_OF_IMAGE;
                }
                Image img = image.getScaledInstance(finalW, finalH, Image.SCALE_AREA_AVERAGING);
                int x = (this.getWidth() - finalW) / 2;
                int y = (this.getHeight() - finalH) / 2;
                g.drawImage(img, x, y + 20, this);
                g.setFont(new Font("Times New Roman", 0, 20));
                g.drawString(("Размер в байтах: " + sizeOfFile), x, 20);
                g.drawString(("Размер в килобайтах: " + String.format("%.3f", (double) sizeOfFile / 8 / 1024)), x, 40);
            }
        } else {
            removeAll();
        }
    }
}
