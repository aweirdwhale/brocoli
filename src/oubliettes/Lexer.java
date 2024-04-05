package oubliettes;

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
                tokens.add(new Token(TokenTypes.OPERATOR, String.valueOf(currentChar), String.valueOf(position)));
                position++;
            } else if (currentChar == '(' || currentChar == ')') {
                tokens.add(new Token(TokenTypes.PARENTHESIS, String.valueOf(currentChar), String.valueOf(position)));
                position++;
            } else if (currentChar == '"') {
                tokens.add(readString());
            } else if (Character.isWhitespace(currentChar)) {
                position++;
            } else if (Character.isLetter(currentChar)) {
                StringBuilder sb = new StringBuilder();
                while (position < input.length() && Character.isLetter(input.charAt(position))) {
                    sb.append(input.charAt(position));
                    position++;
                }
                tokens.add(new Token(TokenTypes.STRING, sb.toString(), String.valueOf(position)));
            } else if (currentChar == ';') {
                tokens.add(new Token(TokenTypes.LINEND, ";", String.valueOf(position)));
                position++;
            } else if (currentChar  == '#') {
                // Skip the rest of the line if we encounter a comment
                while (position < input.length() && input.charAt(position) != '\n') {
                    position++;
                }
            }  else {
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
            return new Token(TokenTypes.FLOAT, sb.toString(), String.valueOf(position));
        } else {
            return new Token(TokenTypes.INT, sb.toString(), String.valueOf(position));
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
        return new Token(TokenTypes.STRING, sb.toString(), String.valueOf(position));
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }
}
