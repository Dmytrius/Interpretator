import dao.LexemItem;
import dao.Lexems;
import dao.Token;
import service.Reader;
import service.RegexTokenizer;
import service.SyntaxAnalyzer;

import java.util.LinkedList;

public class Main {

    public static void main(String[] args) throws Exception {

        String link = "./langFiles/MyProgram.mylang";
        String reader = new Reader(link).read();

        var lexems = new LinkedList<LexemItem>();
        var tokenizer = new RegexTokenizer(reader, Lexems.values());
        int stringNum = 1;
        int index = 0;
        while (tokenizer.hasMoreElements()) {
            Token token = tokenizer.nextElement();
            if(token.getType() == Lexems.NEWROW){
                stringNum++;
            }
            lexems.add(
                    index,
                    new LexemItem(stringNum,token.getText(), token.getType().toString()));
            index++;
        }
//        for(int j =0; j < lexems.size(); j ++){
//            System.out.println(lexems.get(j).toString());
//        }
        System.out.println("------------------------SYNTAX------------------------");
        var syntaxes = new SyntaxAnalyzer(lexems);
        syntaxes.getClearTextProgram(lexems); // deleted before program & past end
        syntaxes.deletedComments(lexems);  // deleted comments
        for(int j =0; j < lexems.size(); j ++){
            System.out.println(lexems.get(j).toString());
        }
    }
}