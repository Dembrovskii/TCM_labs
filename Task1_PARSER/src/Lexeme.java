public class Lexeme {

    private String lexeme;
    private LexemeType type;

    public Lexeme(String lexeme, LexemeType type) {
        this.lexeme = lexeme;
        this.type = type;
    }

    public String getLexeme() {
        return lexeme;
    }

    public LexemeType getType() {
        return type;
    }
}
