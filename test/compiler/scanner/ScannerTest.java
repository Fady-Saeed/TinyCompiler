package compiler.scanner;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;

public class ScannerTest {
    @Test
    public void testScanner(){
        final ArrayList<String> input = new ArrayList<String>(
                Arrays.asList(
                        "fact:=fact*x;",

                        "read x;",

                        ";;;",

                        "read x;\n"+
                        "x := x - 1;\n"+
                        "write x ;",

                        "{sample program in TINY language-computes factorial}\n" +
                        "read x;{input an int}\n" +
                        "if 0<x then {don't compute if x<=0}\n" +
                        "\tfact:=1;\n" +
                        "\trepeat\n" +
                        "\t\tfact:=fact*x;\n"+
                        "\t\tx:=x-1;\n" +
                        "\tuntil x=0;\n" +
                        "\twrite fact{output factorial of x}\n" +
                        "end"
                )
        );
        final ArrayList<ArrayList<Token>> expectedOutput = new ArrayList<ArrayList<Token>>(
                Arrays.asList(
                        new ArrayList<Token>(Arrays.asList(
                                new Token("fact",new TokenType(TokenType.TYPE.IDENTIFIER)),
                                new Token(":=",new TokenType(TokenType.TYPE.SPECIAL_SYMBOL)),
                                new Token("fact",new TokenType(TokenType.TYPE.IDENTIFIER)),
                                new Token("*",new TokenType(TokenType.TYPE.SPECIAL_SYMBOL)),
                                new Token("x",new TokenType(TokenType.TYPE.IDENTIFIER)),
                                new Token(";",new TokenType(TokenType.TYPE.SPECIAL_SYMBOL))
                        )),
                        new ArrayList<>(Arrays.asList(
                                new Token("read",new TokenType(TokenType.TYPE.RESEVED_WORD)),
                                new Token("x",new TokenType(TokenType.TYPE.IDENTIFIER)),
                                new Token(";",new TokenType(TokenType.TYPE.SPECIAL_SYMBOL))
                        )),
                        new ArrayList<>(Arrays.asList(
                                new Token(";",new TokenType(TokenType.TYPE.SPECIAL_SYMBOL)),
                                new Token(";",new TokenType(TokenType.TYPE.SPECIAL_SYMBOL)),
                                new Token(";",new TokenType(TokenType.TYPE.SPECIAL_SYMBOL))
                        )),
                        new ArrayList<>(Arrays.asList(
                                new Token("read",new TokenType(TokenType.TYPE.RESEVED_WORD)),
                                new Token("x",new TokenType(TokenType.TYPE.IDENTIFIER)),
                                new Token(";",new TokenType(TokenType.TYPE.SPECIAL_SYMBOL)),
                                new Token("x",new TokenType(TokenType.TYPE.IDENTIFIER)),
                                new Token(":=",new TokenType(TokenType.TYPE.SPECIAL_SYMBOL)),
                                new Token("x",new TokenType(TokenType.TYPE.IDENTIFIER)),
                                new Token("-",new TokenType(TokenType.TYPE.SPECIAL_SYMBOL)),
                                new Token("1",new TokenType(TokenType.TYPE.NUMBER)),
                                new Token(";",new TokenType(TokenType.TYPE.SPECIAL_SYMBOL)),
                                new Token("write",new TokenType(TokenType.TYPE.RESEVED_WORD)),
                                new Token("x",new TokenType(TokenType.TYPE.IDENTIFIER)),
                                new Token(";",new TokenType(TokenType.TYPE.SPECIAL_SYMBOL))
                        )),
                        new ArrayList<>(Arrays.asList(
                                new Token("read",new TokenType(TokenType.TYPE.RESEVED_WORD)),
                                new Token("x",new TokenType(TokenType.TYPE.IDENTIFIER)),
                                new Token(";",new TokenType(TokenType.TYPE.SPECIAL_SYMBOL)),
                                new Token("if",new TokenType(TokenType.TYPE.RESEVED_WORD)),
                                new Token("0",new TokenType(TokenType.TYPE.NUMBER)),
                                new Token("<",new TokenType(TokenType.TYPE.SPECIAL_SYMBOL)),
                                new Token("x",new TokenType(TokenType.TYPE.IDENTIFIER)),
                                new Token("then",new TokenType(TokenType.TYPE.RESEVED_WORD)),
                                new Token("fact",new TokenType(TokenType.TYPE.IDENTIFIER)),
                                new Token(":=",new TokenType(TokenType.TYPE.SPECIAL_SYMBOL)),
                                new Token("1",new TokenType(TokenType.TYPE.NUMBER)),
                                new Token(";",new TokenType(TokenType.TYPE.SPECIAL_SYMBOL)),
                                new Token("repeat",new TokenType(TokenType.TYPE.RESEVED_WORD)),
                                new Token("fact",new TokenType(TokenType.TYPE.IDENTIFIER)),
                                new Token(":=",new TokenType(TokenType.TYPE.SPECIAL_SYMBOL)),
                                new Token("fact",new TokenType(TokenType.TYPE.IDENTIFIER)),
                                new Token("*",new TokenType(TokenType.TYPE.SPECIAL_SYMBOL)),
                                new Token("x",new TokenType(TokenType.TYPE.IDENTIFIER)),
                                new Token(";",new TokenType(TokenType.TYPE.SPECIAL_SYMBOL)),
                                new Token("x",new TokenType(TokenType.TYPE.IDENTIFIER)),
                                new Token(":=",new TokenType(TokenType.TYPE.SPECIAL_SYMBOL)),
                                new Token("x",new TokenType(TokenType.TYPE.IDENTIFIER)),
                                new Token("-",new TokenType(TokenType.TYPE.SPECIAL_SYMBOL)),
                                new Token("1",new TokenType(TokenType.TYPE.NUMBER)),
                                new Token(";",new TokenType(TokenType.TYPE.SPECIAL_SYMBOL)),
                                new Token("until",new TokenType(TokenType.TYPE.RESEVED_WORD)),
                                new Token("x",new TokenType(TokenType.TYPE.IDENTIFIER)),
                                new Token("=",new TokenType(TokenType.TYPE.SPECIAL_SYMBOL)),
                                new Token("0",new TokenType(TokenType.TYPE.NUMBER)),
                                new Token(";",new TokenType(TokenType.TYPE.SPECIAL_SYMBOL)),
                                new Token("write",new TokenType(TokenType.TYPE.RESEVED_WORD)),
                                new Token("fact",new TokenType(TokenType.TYPE.IDENTIFIER)),
                                new Token("end",new TokenType(TokenType.TYPE.RESEVED_WORD))
                        ))
                )
        );

        for(int testCasesCounter = 0; testCasesCounter < input.size(); testCasesCounter++)
            assertArrayEquals(expectedOutput.get(testCasesCounter).toArray(),Scanner.scan(input.get(testCasesCounter)).toArray());


    }
}
