package xyz.aweirdwhale.brocolang;


import xyz.aweirdwhale.brocolang.lexer.Lexer;
import xyz.aweirdwhale.brocolang.lexer.LexerException;
import xyz.aweirdwhale.brocolang.lexer.Token;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class Main{
    // The main method : entry point of the program
    // We check if the user has provided a .brcli file, then we start the scan
    // in order to tokenize the file, then we parse the tokens to generate the
    // AST, then we interpret the AST
    // Easy, uh?

    public static void main(String[] args) throws IOException, LexerException {
        if (args.length == 0){
            System.out.println("Usage: brocoli <script.brcli>");
            System.exit(64);
        } else if (args.length == 1 && args[0].endsWith(".brcli")){
            System.out.println("[ Starting Brocolang 0.0.1 ]");
            runFile(args[0]);

        } else {
            runPrompt();
        }

    }

    // runFile method : reads the file and interprets it
    private static void runFile(String path) throws IOException, LexerException {
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        run(new String(bytes, Charset.defaultCharset()));
    }

    // runPrompt method : spawns a prompt for the user to write code
    private static void runPrompt() throws IOException, LexerException {
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);

        for (;;) {
            System.out.print("> ");
            String line = reader.readLine();
            if (line == null) break;
            run(line);
        }
    }

    // run method : tokenizes the code, parses it and interprets the AST
    // for now, just print the source code
    private static void run(String source) throws LexerException {
        // let's use the Lexer
        Lexer lexer = new Lexer(source);

        try{
            List<Token> tokens = lexer.tokenize();
            for (Token token : tokens){
                System.out.println(token);
            }
        } catch (LexerException e){
            System.out.println(e.getMessage());
        }

    }
}