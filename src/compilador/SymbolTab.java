/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador;

import java.util.Hashtable;

/**
 *
 * @author Marcos
 */
public class SymbolTab {
    
    public static Hashtable symbolTab;
    private static byte idprox = 1; //proximo simbolo a ser inserido na tabela
    
    public static final byte ARROBA = 1;
    public static final byte PARENTHESES1 = 2;
    public static final byte PARENTHESES2 = 3;
    public static final byte DEF = 4;
    public static final byte ARROW = 5;
    public static final byte COLON = 6;
    public static final byte EQUAL = 7;
    public static final byte COMMA = 8;
    public static final byte ASTERISK = 9;
    public static final byte TWOASTERISK = 10;
    public static final byte MOREEQUAL = 11;
    public static final byte SMALLEREQUAL = 12;
    public static final byte ASTERISKEQUAL = 13;
    public static final byte ARROBAEQUAL = 14;
    public static final byte BAREQUAL = 15; 
    public static final byte PERCENTEQUAL =16;
    public static final byte AMPERSANDEQUAL = 17; 
    public static final byte VERTICALBAREQUAL = 18; 
    public static final byte POTENCYEQUAL = 19;
    public static final byte DEL = 20;
    public static final byte PASS = 21;
    public static final byte BREAK = 22;
    public static final byte CONTINUE = 23;
    public static final byte RETURN = 24;
    public static final byte RAISE = 25;
    public static final byte FROM = 26;
    public static final byte IMPORT = 27;
    public static final byte FALSE = 28;
    public static final byte POINT = 29;
    public static final byte THREEPOINTS = 30;
    public static final byte AS = 31;
    public static final byte GLOBAL = 32;
    public static final byte NONLOCAL = 33;
    public static final byte ASSERT = 34;
    public static final byte IF = 35;
    public static final byte ELIF = 36;
    public static final byte ELSE = 37;
    public static final byte FOR = 38;
    public static final byte IN = 39;
    public static final byte TRY = 40;
    public static final byte FINALLY = 41;
    public static final byte WITH = 42;
    public static final byte EXCEPT = 43;
    public static final byte LAMBDA = 44;
    public static final byte OR = 45;
    public static final byte AND = 46;
    public static final byte NOT = 47;
    public static final byte CLASS = 48;
    public static final byte YIELD = 49;
    public static final byte LARGEREQUAL = 50;
    public static final byte EQUALEQUAL = 51;
    public static final byte SMALLERLARGER = 52;
    public static final byte EXCLAMATIONEQUAL = 53;
    public static final byte IS = 54;
    public static final byte SMALLERSMALLER = 55;
    public static final byte LARGERLARGER = 56;
    public static final byte MORE = 57;
    public static final byte LESS = 58;
    public static final byte BARBAR = 59;
    public static final byte TILDE = 60;
    public static final byte BRACKETS = 61;
    public static final byte NONE = 62;
    public static final byte TRUE = 63;
    public static final byte POINTCOMMA = 64;
    public static final byte SMALLERSMALLEREQUAL = 65;
    public static final byte LARGERLARGEREQUAL = 66;
    public static final byte ASTERISKASTERISKEQUAL = 67;
    public static final byte BARBAREQUAL = 68;
    public static final byte WHILE = 69;
    public static final byte NOTIN = 70;
    public static final byte ISNOT = 71;
    public static final byte POTENCY = 72;
    public static final byte VERTICALBAR = 73;
    public static final byte AMPERSAND = 74;
    public static final byte SMALLER = 75;
    public static final byte LARGER = 76;
    
            
    public void initialize(){
        symbolTab = new Hashtable();
        
        insertSimb("@", ARROBA);
        insertSimb("(", PARENTHESES1);
        insertSimb(")", PARENTHESES2);
        insertSimb("def", DEF);
        insertSimb("->", ARROW);
        insertSimb(":", COLON);
        insertSimb("*", ASTERISK);
        insertSimb(",", COMMA);
        insertSimb("=", EQUAL);
        insertSimb("**", TWOASTERISK);
        insertSimb(";", POINTCOMMA);
        insertSimb("+=", MOREEQUAL);
        insertSimb("-=", SMALLEREQUAL);
        insertSimb("*=", ASTERISKEQUAL);
        insertSimb("@=", ARROBAEQUAL);
        insertSimb("/=", BAREQUAL);
        insertSimb("%=", PERCENTEQUAL);
        insertSimb("&=", AMPERSANDEQUAL);
        insertSimb("|=", VERTICALBAREQUAL);
        insertSimb("^=", POTENCYEQUAL);
        insertSimb("<<=", SMALLERSMALLEREQUAL);
        insertSimb(">>=", LARGERLARGEREQUAL);
        insertSimb("**=", ASTERISKASTERISKEQUAL);
        insertSimb("//=", BARBAREQUAL);
        insertSimb("del", DEL);
        insertSimb("pass", PASS);
        insertSimb("break", BREAK);
        insertSimb("continue", CONTINUE);
        insertSimb("return", RETURN);
        insertSimb("raise", RAISE);
        insertSimb("from", FROM);
        insertSimb("import", IMPORT);
        insertSimb("as", AS);
        insertSimb(".", POINT);
        insertSimb("global", GLOBAL);
        insertSimb("nonlocal", NONLOCAL);
        insertSimb("assert", ASSERT);
        insertSimb("if", IF);
        insertSimb("elif", ELIF);
        insertSimb("else", ELSE);
        insertSimb("while", WHILE);
        insertSimb("for", FOR);
        insertSimb("in", IN);
        insertSimb("try", TRY);
        insertSimb("finally", FINALLY);
        insertSimb("with", WITH);
        insertSimb("except", EXCEPT);
        insertSimb("lambda", LAMBDA);
        insertSimb("or", OR);
        insertSimb("and", AND);
        insertSimb("not", NOT);
        insertSimb("<", SMALLER);
        insertSimb(">", LARGER);
        insertSimb("==", EQUALEQUAL);
        insertSimb(">=", LARGEREQUAL);
        insertSimb("<=", SMALLEREQUAL);
        insertSimb("<>", SMALLERLARGER);
        insertSimb("!=", EXCLAMATIONEQUAL );
        insertSimb("in", IN);
        insertSimb("not", NOT);
        insertSimb("not in", NOTIN);
        insertSimb("is", IS);
        insertSimb("is not", ISNOT);
        insertSimb("^", POTENCY);
        insertSimb("&", AMPERSAND );
        insertSimb("class", CLASS);
        insertSimb("yield",YIELD );
        

        
    }
                
    public void insertSimb(String lex, byte token) {
        String min; //Variável auxilar para transformar em minuscula
        min = lex.toLowerCase();//transforma em minuscula
        symbolTab.put(min, token + "");
        idprox++;
    }
    

    /**
     * metodo que retorna a "posicao" do proximo item da tabela.
     */
    public static byte getIdProx(){
        return idprox;
    }//end getIdprox
    
    
    /**
     * retorna o token referente ao lexema
     */
    public byte getToken(String lex) {
        String min; //VariÃ¡vel auxilar para transformar em minuscula
        min = lex.toLowerCase();//transforma em minuscula
        //System.out.println("" + hash.get(min));
        return Byte.parseByte("" + symbolTab.get(min));
    }//end getToken
    
    /** 
     * verifica se a tabela de simbolos contem o lexema
     */
    public boolean contains(String lex) {
        String min; //VariÃ¡vel auxilar para transformar em minuscula
        min = lex.toLowerCase();//transforma em minuscula
        return symbolTab.containsKey(min);
    }//end contains
    
}
