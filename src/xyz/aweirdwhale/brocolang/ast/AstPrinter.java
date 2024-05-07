// useless actually


package xyz.aweirdwhale.brocolang.ast;


public class AstPrinter implements Expr.Visitor<String> { //  implementing the visitor interface.
    public String print(Expr expr) {
        return expr.accept(this);
    }

    @Override
    public String visitBinaryExpr(Expr.Binary expr) {
        return parenthesize(expr.operator.lexeme,
                expr.left, expr.right);
    }

    @Override
    public String visitGroupingExpr(Expr.Grouping expr) {
        return parenthesize("group", expr.expression);
    }

    @Override
    public String visitLiteralExpr(Expr.Literal expr) {
        if (expr.value == null) return "nil";
        return expr.value.toString();
    }

    @Override
    public String visitUnaryExpr(Expr.Unary expr) {
        return parenthesize(expr.operator.lexeme, expr.right);
    }


    /*
     * Literal expressions are easy — they convert the value to a string with a little check to handle Java’s null
     * standing in for Brocolang’s Null. The other expressions have subexpressions,
     * so they use this parenthesize() helper method:
     */

    private String parenthesize(String name, Expr... exprs) {
        StringBuilder builder = new StringBuilder();

        builder.append("(").append(name);
        for (Expr expr : exprs) {
            builder.append(" ");
            builder.append(expr.accept(this));
        }
        builder.append(")");

        return builder.toString();
    }

    /*
    * It takes a name and a list of subexpressions and wraps them all up in parentheses, yielding a string like:
    * (+ 1 2)
    */




}