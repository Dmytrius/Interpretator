public class LexemItem {

    private int stringNum;
    private String lexem;
    private String description;

    public LexemItem(int stringNum, String lexem, String description) {
        this.stringNum = stringNum;
        this.lexem = lexem;
        this.description = description;
    }

    public int getStringNum() {
        return stringNum;
    }

    public void setStringNum(int stringNum) {
        this.stringNum = stringNum;
    }

    public String getLexem() {
        return lexem;
    }

    public void setLexem(String lexem) {
        this.lexem = lexem;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        if(lexem.contains("\n")){
            lexem = "\\n";
        }
        return "LexemItem {" +
                " stringNum = " + stringNum +
                ", \tlexem = '" + lexem + '\'' +
                ", \tdescription = '" + description + '\'' +
                '}';
    }
}
