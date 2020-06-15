package service;

import dao.LexemItem;
import java.util.LinkedList;

public class POLIZAnalyzer {

    LinkedList<LexemItem> lexems;

    public POLIZAnalyzer (LinkedList<LexemItem> lexems) {
        this.lexems = lexems;
    }


    public LinkedList<LexemItem> getPolizIFS(LinkedList<LexemItem> list) {
        LinkedList<LexemItem> stack = new LinkedList<LexemItem>();
        LinkedList<LexemItem> out = new LinkedList<LexemItem>();
        while (!list.isEmpty()){
            switch (list.getFirst().getDescription()) {
                case ("KEYWORD"):
                    if (!stack.isEmpty()) {
                        while(!stack.isEmpty()){
                            out.add(stack.getLast());
                            stack.removeLast();
                        }
                    }
                    stack.add(list.getFirst());
                    list.removeFirst();
                    break;
                case ("SYMBOLS"):
                    stack.add(list.getFirst());
                    list.removeFirst();
                    break;
                case ("IDENTIFICATOR"):
                case ("NUMBER"):
                    out.add(list.getFirst());
                    list.removeFirst();
                    break;
                case ("WHITESPACE"):
                case ("NEWROW"):
                    list.removeFirst();
                    break;
            }
        }
//        for(int j =0; j < out.size(); j ++){
//            System.out.println(out.get(j).toString());
//        }
        return out;
    }
}
