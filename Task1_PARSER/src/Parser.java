public class Parser {

    private Lexer lexer;
    private Lexeme current;

    public Parser(Lexer lexer) throws Exception {
        this.lexer = lexer;
        current = this.lexer.GetNext();
    }

    public int parseExpr() throws Exception {
        int term = parseTerm();

        while ((current.getType() == LexemeType.MINUS) || (current.getType() == LexemeType.PLUS)) {

            if (current.getType() == LexemeType.MINUS) {
                current = lexer.GetNext();
                term -= parseTerm();
            }

            if (current.getType() == LexemeType.PLUS) {
                current = lexer.GetNext();
                term += parseTerm();
            }
        }
        return term;
    }

    public int parseTerm() throws Exception {
        int factor = parseFactor();

        while ((current.getType() == LexemeType.MULT) || (current.getType() == LexemeType.DIV)) {

            if (current.getType() == LexemeType.MULT){
                current = lexer.GetNext();
                factor *= parseFactor();
            }

            if (current.getType() == LexemeType.DIV) {
                current = lexer.GetNext();
                factor /= parseFactor();
            }
        }
        return factor;
    }

    public int parseFactor() throws Exception {
        int power = parsePower();

        if (current.getType() == LexemeType.POW) {
            current = lexer.GetNext();
            power = (int) Math.pow(power, parseFactor());
        }
        return power;
    }

    public int parsePower() throws Exception {

        if (current.getType() == LexemeType.MINUS) {
            current = lexer.GetNext();
            return -parseAtom();
        }
        return parseAtom();
    }

    public int parseAtom() throws Exception {

        if(current.getType() == LexemeType.NUM) {
            int tmp = Integer.parseInt(current.getLexeme());
            current = lexer.GetNext();
            return tmp;
        }
        if(current.getType() == LexemeType.OPEN) {
            current = lexer.GetNext();
            int tmp = parseExpr();

            if(current.getType() != LexemeType.CLOSE) {
                throw new Exception("Parser class close brackets exception");
            }

            else {
                current = lexer.GetNext();
                return tmp;
            }
        }
        throw new Exception("Parser class atom exception");
    }

}