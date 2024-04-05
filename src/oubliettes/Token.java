package oubliettes;

public class Token {
    private TokenTypes type;
    private String value;

    private String position;

    public Token(TokenTypes type, String value, String position) {
        this.type = type;
        this.value = value;
        this.position = position;
    }

    public TokenTypes getType() {
        return type;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "{" +
                "type=" + type +
                ", value='" + value + '\'' +
                ", position='" + position + '\'' +
                '}';
    }
}
