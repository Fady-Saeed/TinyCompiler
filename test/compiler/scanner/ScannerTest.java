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
                        "read x;"
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
                ))
            )
        );

        for(int testCasesCounter = 0; testCasesCounter < input.size(); testCasesCounter++)
            assertArrayEquals(expectedOutput.get(testCasesCounter).toArray(),Scanner.scan(input.get(testCasesCounter)).toArray());


    }
}
