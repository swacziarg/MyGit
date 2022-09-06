package com.company;

import java.io.*;
import java.util.HashMap;

public class Index {
    HashMap<String, String> map;
    final String INDEX_PATH = "index";

    public Index() {
        map = new HashMap<>();
        File f = new File(INDEX_PATH);
        if(f.exists() && !f.isDirectory()) {
            getMapFromFile(f);
        }
    }

    public void getMapFromFile(File file) {
        HashMap<String, String> m = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                if(line.equals("")) continue;
                String hash = line.substring(line.length()-40);
                String filename = line.substring(0, line.length()-43);
                m.put(filename, hash);
            }
            map = m;
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void setFile() {
        try {
            PrintWriter writer = new PrintWriter(INDEX_PATH);
            for (String key : map.keySet()) {
                writer.println(key + " : " + map.get(key));
            }
            writer.println("");
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

    public void remove(String filename) {
        if(!map.containsKey(filename)) return;

            String path = "objects/" + map.get(filename);
            System.out.println(path);
            File file = new File(path);

            if (file.delete()) {
                System.out.println("File deleted successfully");
            }
            else {
                System.out.println("Failed to delete the file");
            }

            map.remove(filename);
            setFile();
    }
}
