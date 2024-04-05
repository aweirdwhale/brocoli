package xyz.aweirdwhale.brocolang;



import xyz.aweirdwhale.brocolang.lexer.Scanner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

public class Main{
    // storing static vars
    static boolean hadError = false;



    // The main method : entry point of the program
    // We check if the user has provided a .brcli file, then we start the scan
    // in order to tokenize the file, then we parse the tokens to generate the
    // AST, then we interpret the AST
    // Easy, uh?

    public static void main(String[] args) throws IOException {
        if (args.length == 1 && Objects.equals(args[0], "--cli")){
            System.out.println("[ BROCOLANG by Aweirdwhale ]");
            runPrompt();
        } else if (args.length == 1 && args[0].endsWith(".brcli")){
            System.out.println("[ Starting Brocolang 0.0.1 ]");
            runFile(args[0]);

        } else if (args.length == 1 && (Objects.equals(args[0], "--version") || Objects.equals(args[0], "-V"))){
            System.out.println("[ Brocolang v0.0.1 Made with love by A weird whale ! ]");
        } else {
            System.out.println("[ BROCOLANG by Aweirdwhale ] \nHelp : \nbrocolang <script.brcli> or brocolang --cli \nbrocolang -V, --version : show the current version");
            System.out.println("\n");
            runPrompt();
        }

    }

    // runFile method : reads the file and interprets it
    private static void runFile(String path) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(path));
        run(new String(bytes, Charset.defaultCharset()));
        if (hadError) System.exit(65);
    }

    // runPrompt method : spawns a prompt for the user to write code
    private static void runPrompt() throws IOException {
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(input);

        for (;;) {
            System.out.print("Broco-hacker $> ");
            String line = reader.readLine();
            if (line == null) break;
            run(line);
            hadError = false;
        }
    }

    // run method : tokenizes the code, parses it and interprets the AST
    // for now, just print the source code
    private static void run(String source) throws IOException {

        Scanner lexemes = new Scanner(source);

        System.out.println(lexemes.scanTokens());


    }

    // Error handling
    // Always separate code that generates errors and code that reports it ;)
    public static void error(int line, String message) {
        report(line, "", message);
    }

    private static void report(int line, String where, String message) {
        System.err.println(
                "Error line " + line + " : " + message);
        hadError = true;
    }
}