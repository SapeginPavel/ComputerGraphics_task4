package ru.vsu.csf.sapegin;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class RLE {

    public void compress(String source, String dis) {
        try {
            FileInputStream in = new FileInputStream(source);
            FileOutputStream out = new FileOutputStream(dis);
            int next = 0;
            int count = in.read(); //считываем по байту
            while ((next = in.read()) >= 0) { //пока есть что считывать
//                System.out.println(next);
                int counter = 1; //считаем кол-во повторений байта
                if (count == next) {
                    counter++;
                    while (next == (count = in.read())) {
                        counter++;
                    } // байт ... повторяется counter раз
                    //Каждые 63 байта * 8 бит =
                    while (counter >= 63) { //2^6 - 1
                        out.write(255); //2^8 - 1
                        out.write(next);
                        counter -= 63;
                    }
                    if (counter > 1) {
                        out.write(0xc0 + counter);
                        out.write(next);
                    }
                } else {
                    if (count <= 0xc0) { //192
                        out.write(count);
                        count = next;
                    } else {
                        out.write(0xc1); //193
                        out.write(count);
                        count = next;
                    }
                }
            }
            if (count <= 0xc0) {
                out.write(count);
            } else {
                out.write(0xc1);
                out.write(count);
            }
            in.close();
            out.close();
        } catch (IOException e) {
        }
    }

    public void decompress(String source, String dis) {
        try {
            FileInputStream in = new FileInputStream(source);
            FileOutputStream out = new FileOutputStream(dis);
            int count = 0;
            while ((count = in.read()) >= 0) {
                if (count == 0xc1) {
                    out.write(in.read());
                } else if (count <= 0xc0) {
                    out.write(count);
                } else if (count > 0xc1) {
                    int next = in.read();
                    for (int i = 0; i < (count - 0xc0); i++) {
                        out.write(next);
                    }
                }
            }
        } catch (Exception e) {
        }
    }
}