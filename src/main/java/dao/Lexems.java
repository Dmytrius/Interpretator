package dao;

public enum Lexems implements ITokenType {

    KEYWORD("\\b(?:program|end|true|false|if|then|else|for|rof|to|by)\\b"),
    IDENTIFICATOR("[a-z][A-Za-z0-9]*"),
    NEWROW("\n"),
    REAL_NUMBER("[0-9]*\\.[0-9]*"),
    NUMBER("[0-9]+"),
    CHAR("'[^']*'"),
    WHITESPACE("\\s+"),
    COMMENT("\\-\\-[^\\n\\r]*"),
    SYMBOLS("[+\\-\\--\\[\\]\\*/.=\\(\\)\\^]");

    private final String regex;

    Lexems(String regex) {
        this.regex = regex;
    }

    @Override
    public String getRegex() {
        return regex;
    }

}