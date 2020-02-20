import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {

        var s = "";
        int i;
        try {
            var fr = new FileReader("./langFiles/MyProgram.mylang");
            while((i = fr.read()) != -1){
                s = s + (char) i;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException ex){
            ex.printStackTrace();
        }

        var lexems = new HashMap<Integer, LexemItem>();
        var tokenizer = new RegexTokenizer(s, Lexems.values());
        int stringNum = 1;
        int index = 0;
        while (tokenizer.hasMoreElements()) {
            Token token = tokenizer.nextElement();
            if(token.getType() == Lexems.NEWROW){
                stringNum++;
            }
            lexems.put(
                    index,
                    new LexemItem(stringNum,token.getText(), token.getType().toString()));
            index++;
        }
        lexems.entrySet().forEach(entry ->{
            System.out.println(entry.getValue().toString());
        });
    }
}