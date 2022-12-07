package ru.vsu.csf.sapegin;

import javax.imageio.ImageIO;
import java.awt.image.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        GraphicApp graphicApp = new GraphicApp();
        graphicApp.start();

//        RLE rle = new RLE();
//        rle.compress("D:\\car.jpg", "D:\\del.rle");
//        rle.decompress("D:\\del.rle", "D:\\res.bmp");
//        rle.decompress("D:\\del.rle", "D:\\res2.jpg");

//        List<Byte> bytes = MyRLE.compress(MyRLE.readImg("D:\\car.jpg"));
//        MyRLE.saveImg("D:\\myTest.compressed", bytes);
//        MyRLE.saveImg("D:\\myTestDecompressed.jpg", MyRLE.decompress(MyRLE.readImg("D:\\myTest.compressed")));

    }
}
