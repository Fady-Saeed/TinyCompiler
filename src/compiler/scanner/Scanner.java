package compiler.scanner;

import java.util.ArrayList;

public class Scanner{
    private static ArrayList<Token> Tokens = new ArrayList<>();
    private enum STATE{
        START,
        INCOMMENT,
        INNUM,
        INID,
        INASSIGN
    };

    private static final String WHITE_SPACE = "\\s";
    private static final String DIGIT = "[0-9]";
    private static final String LETTER = "[a-zA-Z_]";
    private static final String CURELY_BRACES_OPENING = "\\{";
    private static final String CURELY_BRACES_CLOSING = "\\}";
    private static final String COLON = ":";
    private static final String EQUAL = "=";

    private static StringBuilder tokenAccumlator = new StringBuilder();

    private static STATE currentState = STATE.START;

    public static ArrayList<Token> scan(String code){
        String nextLetter;

        currentState = STATE.START;
        tokenAccumlator = new StringBuilder();
        Tokens = new ArrayList<>();

        code += " ";

        for(int counter = -1; counter < code.length() - 1; counter++){
            nextLetter = Character.toString(code.charAt(counter+1));

            switch(currentState) {
                case START:
                    tokenAccumlator = new StringBuilder();
                    if (nextLetter.matches(WHITE_SPACE)) {
                        currentState = STATE.START;
                    } else if (nextLetter.matches(CURELY_BRACES_OPENING)) {
                        currentState = STATE.INCOMMENT;
                    } else if (nextLetter.matches(DIGIT)) {
                        tokenAccumlator.append(nextLetter);
                        currentState = STATE.INNUM;
                    } else if (nextLetter.matches(LETTER)) {
                        tokenAccumlator.append(nextLetter);
                        currentState = STATE.INID;
                    } else if (nextLetter.matches(COLON)) {
                        tokenAccumlator.append(nextLetter);
                        currentState = STATE.INASSIGN;
                    } else {
                        tokenAccumlator.append(nextLetter);
                        setDoneState(nextLetter);
                    }
                    break;
                case INCOMMENT:
                    if (nextLetter.matches(CURELY_BRACES_CLOSING))
                        currentState = STATE.START;
                    break;
                case INNUM:
                    if (nextLetter.matches(DIGIT)) {
                        currentState = STATE.INNUM;
                        tokenAccumlator.append(nextLetter);
                    } else {
                        setDoneState(nextLetter);
                        counter--;
                    }
                    break;
                case INID:
                    if (nextLetter.matches(LETTER) || nextLetter.matches(DIGIT)) {
                        currentState = STATE.INID;
                        tokenAccumlator.append(nextLetter);
                    } else {
                        setDoneState(nextLetter);
                        counter--;
                    }
                    break;
                case INASSIGN:
                    if (nextLetter.matches(EQUAL)){
                        tokenAccumlator.append(nextLetter);
                    }
                    setDoneState(nextLetter);
                    break;
            }
        }
        return Tokens;
    }
    private static void setDoneState(String currentLetter){
        if(!tokenAccumlator.toString().replaceAll("\\s+","").equals(""))
            Tokens.add(new Token(tokenAccumlator.toString().trim()));
        currentState = STATE.START;
        tokenAccumlator = new StringBuilder();
    }
}