import java.util.List;

public class Main {
    public static void main(String[] args) {
        //open .brcli file and run the Lexer on it



        Lexer lexer = new Lexer(expression);

        try {
            List<Token> tokens = lexer.tokenize();
            for (Token token : tokens) {
                System.out.println(token);
            }
        } catch (LexerException e) {
            System.err.println("Lexer error: " + e.getMessage());
        }
    }
}
