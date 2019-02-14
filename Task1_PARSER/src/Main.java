import java.io.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new FileReader("in.txt"));
        Lexer lexer = new Lexer(reader);
        Parser parser = new Parser(lexer);
        System.out.println(parser.parseExpr());
    }
}
