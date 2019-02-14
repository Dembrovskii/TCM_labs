import java.io.IOException;
import java.io.Reader;

public class Lexer {

    private Reader reader;
    private int current;

    public Lexer(Reader reader) throws IOException {
        this.reader = reader;
        current = this.reader.read();
    }

    public Lexeme GetNext() throws Exception {
        StringBuffer buf = new StringBuffer();

        switch (current){
            case '+':
                current = reader.read();
                return new Lexeme("+", LexemeType.PLUS);

            case '-':
                current = reader.read();
                return new Lexeme("-", LexemeType.MINUS);

            case '*':
                current = reader.read();
                return new Lexeme("*", LexemeType.MULT);

            case '/':
                current = reader.read();
                return new Lexeme("/", LexemeType.DIV);

            case '^':
                current = reader.read();
                return new Lexeme("^", LexemeType.POW);

            case '(':
                current = reader.read();
                return new Lexeme("(", LexemeType.OPEN);
            case ')':
                current = reader.read();
                return new Lexeme(")", LexemeType.CLOSE);

            case '0':case '1':case '2':case '3':case '4':case '5':case '6':case '7':case '8':case '9':

                while (current >= '0' && current <= '9') {
                    buf.append((char) current);
                    current = reader.read();
                }
                return new Lexeme(buf.toString(), LexemeType.NUM);

            case -1:
                return new Lexeme( "EOF", LexemeType.EOF);

            default:
                throw new Exception("Lexer class exception");
        }
    }
}