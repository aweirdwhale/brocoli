import java.util.ArrayList;
import java.util.List;

public class Lexer {
    private String input;
    private int position;

    public Lexer(String input) {
        this.input = input;
        this.position = 0;
    }

    public List<Token> tokenize() throws LexerException {
        List<Token> tokens = new ArrayList<>();

        while (position < input.length()) {
            char currentChar = input.charAt(position);

            if (Character.isDigit(currentChar)) {
                tokens.add(readNumber());
            } else if (isOperator(currentChar)) {
                tokens.add(new Token(TokenType.OPERATOR, String.valueOf(currentChar)));
                position++;
            } else if (currentChar == '(') {
                tokens.add(new Token(TokenType.L_PARENTHESIS, String.valueOf(currentChar)));
                position++;
            } else if (currentChar == ')'){
                tokens.add(new Token(TokenType.R_PARENTHESIS, String.valueOf(currentChar)));
                position++;
            } else if (currentChar == '"') {
                tokens.add(readString());
            } else if (Character.isWhitespace(currentChar)) {
                position++;
            } else {
                throw new LexerException("Invalid character: " + currentChar);
            }
        }

        return tokens;
    }

    private Token readNumber() {
        StringBuilder sb = new StringBuilder();
        boolean hasDecimal = false;

        while (position < input.length() && (Character.isDigit(input.charAt(position)) || input.charAt(position) == '.')) {
            char currentChar = input.charAt(position);
            if (currentChar == '.') {
                if (hasDecimal) {
                    break; // Only one decimal point allowed
                }
                hasDecimal = true;
            }
            sb.append(currentChar);
            position++;
        }

        if (hasDecimal) {
            return new Token(TokenType.FLOAT, sb.toString());
        } else {
            return new Token(TokenType.INT, sb.toString());
        }
    }

    private Token readString() throws LexerException {
        StringBuilder sb = new StringBuilder();
        position++; // Skip the opening quote
        while (position < input.length() && input.charAt(position) != '"') {
            sb.append(input.charAt(position));
            position++;
        }
        if (position >= input.length() || input.charAt(position) != '"') {
            throw new LexerException("Unterminated string literal");
        }
        position++; // Skip the closing quote
        return new Token(TokenType.STRING, sb.toString());
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }
}
