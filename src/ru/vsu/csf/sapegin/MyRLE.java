package ru.vsu.csf.sapegin;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MyRLE {

    public static final String MY_RLE_EXTENSION = "compressedRLE";

    /**
     * @param path - path for compressed image [для сжимаемого файла]
     * @return path to saved image
     * @throws IOException
     */
    public static String compressImage(String path) throws IOException {
        List<Byte> startBytes = readImage(path);
        List<Byte> compressedBytes = compress(startBytes);
        String destination = Utils.getSubstringBeforePoint(path) + "." + MY_RLE_EXTENSION + Utils.getSubstringAfterPoint(path);
        saveImg(destination, compressedBytes);
        return destination;
    }

    public static String decompressImage(String path) throws IOException {
        List<Byte> startBytes = readImage(path);
        List<Byte> decompressedBytes = decompress(startBytes);
        int oldExtension = path.indexOf("." + MY_RLE_EXTENSION) + MY_RLE_EXTENSION.length() + 1;
        String destination = Utils.getSubstringBeforePoint(path) + " (decompressed)" + "." + path.substring(oldExtension);
        saveImg(destination, decompressedBytes);
        return destination;
    }

    private static List<Byte> decompress(List<Byte> bytes) {
        List<Byte> decompressed = new ArrayList<>();
        int pos = 1;
        byte counter = 0;
        for (byte b : bytes) {
            if (pos % 2 == 1) {
                counter = b;
            } else {
                for (int i = 0; i < counter; i++) {
                    decompressed.add(b);
                }
            }
            pos++;
        }
        return decompressed;
    }

    private static void saveImg(String destination, List<Byte> bytesList) throws IOException {
        FileOutputStream out = new FileOutputStream(destination);
        byte[] bytesArr = new byte[bytesList.size()];
        int i = 0;
        for (byte b : bytesList) {
            bytesArr[i] = b;
            i++;
        }
        out.write(bytesArr);
        out.close();
    }

    private static List<Byte> readImage(String path) throws IOException {
        FileInputStream in = new FileInputStream(path);
        List<Byte> bytes = new ArrayList<>();
        int iterator = in.read();
        while (iterator >= 0) {
            bytes.add((byte) iterator);
            iterator = in.read();
        }
        bytes.add((byte) iterator);
        in.close();
        return bytes;
    }

    private static List<Byte> compress(List<Byte> bytes) {
        int counter = 0;
        List<Byte> compressed = new ArrayList<>();
        byte prev = bytes.get(0);
        for (byte b : bytes) {
            if (prev == b) {
                counter++;
            } else {
                while (counter >= 127) {
                    compressed.add((byte) 127);
                    compressed.add(prev);
                    counter -= 127;
                }
                if (counter >= 1) {
                    compressed.add((byte) counter);
                    compressed.add(prev);
                }
                counter = 1;
                prev = b;
            }
        }
        return compressed;
    }
}

//в теории можно использовать знаковый бит + если counter=127 очень много раз, то можно это тоже оптимизировать. А вначале
//сделать байт-флаг (будет двойное сжатие как бы)