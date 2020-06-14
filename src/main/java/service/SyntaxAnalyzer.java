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
}
