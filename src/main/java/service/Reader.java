package service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Reader {

    String link;

    public Reader() {
    }

    public Reader(String link) throws Exception {
        this.link = link;
    }

    public String read() {
        var s = "";
        int i;
        try {
            var fr = new FileReader(link);
            while((i = fr.read()) != -1){
                s = s + (char) i;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException ex){
            ex.printStackTrace();
        }
        return s;
    }
}
