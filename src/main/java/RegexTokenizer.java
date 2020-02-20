import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class RegexTokenizer implements Enumeration<Token> {

    private final String content;
    private final ITokenType[] tokenTypes;
    private final Matcher matcher;
    private int currentPosition = 0;

    public RegexTokenizer(String content, ITokenType[] tokenTypes) {
        this.content = content;
        this.tokenTypes = tokenTypes;

        List<String> regexList = new ArrayList<>();
        for (int i = 0; i < tokenTypes.length; i++) {
            ITokenType tokenType = tokenTypes[i];
            regexList.add("(?<g" + i + ">" + tokenType.getRegex() + ")");
        }
        String regex = regexList.stream().collect(Collectors.joining("|"));

        var pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(content);
        matcher.find();
    }

    protected Token createToken(String content, ITokenType tokenType, int start, int end) {
        return new Token(content.substring(start, end), tokenType, start);
    }

    @Override
    public boolean hasMoreElements() {
        return currentPosition < content.length();
    }

    @Override
    public Token nextElement() {
        boolean found = currentPosition > matcher.start() ? matcher.find() : true;

        int start = found ? matcher.start() : content.length();
        int end = found ? matcher.end() : content.length();

        if(found && currentPosition == start) {
            currentPosition = end;
            // Лексема найдена- определяем тип
            for (int i = 0; i < tokenTypes.length; i++) {
                String si = "g" + i;
                if (matcher.start(si) == start && matcher.end(si) == end) {
                    return createToken(content, tokenTypes[i], start, end);
                }
            }
        }
        throw new IllegalStateException("Не удается определить лексему в позиции " + currentPosition);
    }
}