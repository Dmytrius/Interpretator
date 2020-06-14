package service;

import dao.LexemItem;

import java.util.LinkedList;
import java.util.List;

public class SyntaxAnalyzer {

    List<LexemItem> lexems;

    public SyntaxAnalyzer(LinkedList<LexemItem> lexems) {
        this.lexems = lexems;
    }

    private LinkedList<LexemItem> getClearFirstText(LinkedList<LexemItem> list, String string){
        if(!list.getFirst().getLexem().equals(string)){
            list.removeFirst();
        }
        return list;
    }
    private LinkedList<LexemItem> getClearLastText(LinkedList<LexemItem> list, String string){
        if(!list.getLast().getLexem().equals(string)){
            list.removeLast();
        }
        return list;
    }

    public LinkedList<LexemItem> getClearTextProgram(LinkedList<LexemItem> lexems) {
        while(!lexems.getFirst().getLexem().equals("program")){
            getClearFirstText(lexems, "program");
        }
        while(!lexems.getLast().getLexem().equals("end")){
            getClearLastText(lexems, "program");
        }
        return lexems;
    }

    public LinkedList<LexemItem> deletedComments(LinkedList<LexemItem> lexems) {
        int index = 0;
        for(int i = 0; i < lexems.size(); i++) {
            if (lexems.get(i).getDescription().equals("COMMENT")) {
                index = lexems.get(i).getStringNum();
                System.out.println(index);
            }
            for(int j = 0; j < lexems.size(); j++) {
                if(lexems.get(j).getStringNum() == index) {
                    lexems.remove(j);
                }
            }
        }
        return lexems;
    }
}
