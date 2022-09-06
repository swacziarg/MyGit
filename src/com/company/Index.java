package com.company;

import java.io.File;
import java.io.PrintWriter;
import java.util.HashMap;

public class Index {
    HashMap<String, String> map;
    final String INDEX_PATH = "index";

    public Index() {
        File f = new File(INDEX_PATH);
        if(f.exists() && !f.isDirectory()) {
            map = getMapFromFile();
        } else {
            map = new HashMap<>();
        }
    }

    public HashMap<String, String> getMapFromFile() {
        return null;
    }

    public void setFile() {
        try {
            PrintWriter writer = new PrintWriter(INDEX_PATH);
            writer.println("The first line");
            writer.println("The second line");
            writer.close();
        } catch(Exception e) {
            System.out.println(e.toString());
        }
    }

    public void add(String filename) {
        Blob b = new Blob(filename);
        String hash = b.hash;
        map.put(filename, hash);
        setFile();
    }
}
