import dao.LexemItem;
import dao.Lexems;
import dao.Token;
import service.POLIZAnalyzer;
import service.Reader;
import service.RegexTokenizer;
import service.SyntaxAnalyzer;

import java.util.LinkedList;
import java.util.List;

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
        System.out.println("------------------------LEXEM ANALYZE------------------------");
        for(int j =0; j < lexems.size(); j ++){
            System.out.println(lexems.get(j).toString());
        }
        var syntaxes = new SyntaxAnalyzer(lexems);
        syntaxes.getClearTextProgram(lexems); // deleted before program & past end
        syntaxes.deletedComments(lexems);  // deleted comments

        System.out.println("------------------------SYNTAX ANALYZE------------------------");
        for(int j =0; j < lexems.size(); j ++){
            System.out.println(lexems.get(j).toString());
        }

        List<LexemItem> loopList;
        loopList = syntaxes.getLoop((LinkedList<LexemItem>) lexems.clone()); //get loop

        System.out.println("------------------------LOOP------------------------");
        for(int j =0; j < loopList.size(); j ++){
            System.out.println(loopList.get(j).toString());
        }

        List<LexemItem> ifStatment;
        ifStatment = syntaxes.getIfStatment((LinkedList<LexemItem>) lexems.clone()); //get if statment
        System.out.println("------------------------IFSTATMENT------------------------");
        for(int j =0; j < ifStatment.size(); j ++){
            System.out.println(ifStatment.get(j).toString());
        }

        System.out.println("------------------------POLIZ FOR STATMENT------------------------");
        var polizFORStatment = new LinkedList<LexemItem>();
        var polizFOR = new POLIZAnalyzer((LinkedList<LexemItem>) loopList);
        polizFORStatment = polizFOR.getPoliz((LinkedList<LexemItem>) loopList);
        for(int i = 0;i < polizFORStatment.size();i++){
            System.out.println(polizFORStatment.get(i).toString());
        }

        System.out.println("------------------------POLIZ IF STATMENT------------------------");
        var polizIfStatment = new LinkedList<LexemItem>();
        var polizIF = new POLIZAnalyzer((LinkedList<LexemItem>) ifStatment);
        polizIfStatment = polizIF.getPoliz((LinkedList<LexemItem>) ifStatment);
        for (int i=0; i < polizIfStatment.size();i++){
            System.out.println(polizIfStatment.get(i).toString());
        }
    }
}