package compilador;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LexicalAnalyzer {

    public static enum TokenType {

        BINARYOP("(>>|<<|<>|>=|<=|==|=|<|>|//|/|\\*\\*|\\*|x|\\+|-|/|:|!=|!|&|\\|=|->)"), TAB("\\t"), RETORNOCARRO("\n"), WHITESPACE("[\\s]+"), COMMENTS("#.*$"), PALAVRA("[\\w|\\d|ç|à-ú|À-Ú|_]+"), NUMBERINT("-?[0-9]+"), NUMBERDECI("(-?[0-9])+.(-?[0-9])"),
        PARENTESES("(\\(|\\)|\\[|\\]|\\{|\\}|;)"), PONTOVIRGULA("(\\,|\\.)"), ASPASSIMPLES("'"), TRESASPASDUPLAS("\"\"\""), ASPASDUPLAS("\""), CONTRABARRACARACTER("\\\\\"|\\\\n|\\\\");
        public final String pattern;

        private TokenType(String pattern) {
            this.pattern = pattern;
        }
    }

    public static List<Token> tokenList = new ArrayList<>();

    public static class Token {

        private TokenType type;
        private String data;
        private int linha;

        public Token(TokenType type, String data, int linha) {
            this.type = type;
            this.data = data;
            this.linha = linha;
        }

        @Override
        public String toString() {
            return String.format("%d %s %s", linha, getType().name(), getData());
        }

        /**
         * @return the linha
         */
        public int getLinha() {
            return linha;
        }

        /**
         * @param linha the linha to set
         */
        public void setLinha(int linha) {
            this.linha = linha;
        }

        /**
         * @return the type
         */
        public TokenType getType() {
            return type;
        }

        /**
         * @param type the type to set
         */
        public void setType(TokenType type) {
            this.type = type;
        }

        /**
         * @return the data
         */
        public String getData() {
            return data;
        }

        /**
         * @param data the data to set
         */
        public void setData(String data) {
            this.data = data;
        }
    }

    public static ArrayList<Token> lex(String input, int linha) {
        // The tokens to return
        ArrayList<Token> tokens = new ArrayList<Token>();

        // Lexer logic begins here
        StringBuffer tokenPatternsBuffer = new StringBuffer();
        for (TokenType tokenType : TokenType.values()) {
            tokenPatternsBuffer.append(String.format("|(?<%s>%s)", tokenType.name(), tokenType.pattern));
        }
        Pattern tokenPatterns = Pattern.compile(new String(tokenPatternsBuffer.substring(1)));

        // Begin matching tokens
        Matcher matcher = tokenPatterns.matcher(input);
        while (matcher.find()) {

            if (matcher.group(TokenType.COMMENTS.name()) != null) {
                tokens.add(new Token(TokenType.COMMENTS, matcher.group(TokenType.COMMENTS.name()), linha));
                continue;
            } else if (matcher.group(TokenType.NUMBERINT.name()) != null) {
                tokens.add(new Token(TokenType.NUMBERINT, matcher.group(TokenType.NUMBERINT.name()), linha));
                continue;
            } else if (matcher.group(TokenType.PALAVRA.name()) != null) {
                tokens.add(new Token(TokenType.PALAVRA, matcher.group(TokenType.PALAVRA.name()), linha));
                continue;
            } else if (matcher.group(TokenType.CONTRABARRACARACTER.name()) != null) {
                tokens.add(new Token(TokenType.CONTRABARRACARACTER, matcher.group(TokenType.CONTRABARRACARACTER.name()), linha));
                continue;
            } else if (matcher.group(TokenType.NUMBERDECI.name()) != null) {
                tokens.add(new Token(TokenType.NUMBERDECI, matcher.group(TokenType.NUMBERDECI.name()), linha));
                continue;
            } else if (matcher.group(TokenType.PARENTESES.name()) != null) {
                tokens.add(new Token(TokenType.PARENTESES, matcher.group(TokenType.PARENTESES.name()), linha));
                continue;
            } else if (matcher.group(TokenType.PONTOVIRGULA.name()) != null) {
                tokens.add(new Token(TokenType.PONTOVIRGULA, matcher.group(TokenType.PONTOVIRGULA.name()), linha));
                continue;
            } else if (matcher.group(TokenType.BINARYOP.name()) != null) {
                tokens.add(new Token(TokenType.BINARYOP, matcher.group(TokenType.BINARYOP.name()), linha));
                continue;
            } else if (matcher.group(TokenType.ASPASSIMPLES.name()) != null) {
                tokens.add(new Token(TokenType.ASPASSIMPLES, matcher.group(TokenType.ASPASSIMPLES.name()), linha));
                continue;
            } else if (matcher.group(TokenType.TRESASPASDUPLAS.name()) != null) {
                tokens.add(new Token(TokenType.TRESASPASDUPLAS, matcher.group(TokenType.TRESASPASDUPLAS.name()), linha));
                continue;
            } else if (matcher.group(TokenType.ASPASDUPLAS.name()) != null) {
                tokens.add(new Token(TokenType.ASPASDUPLAS, matcher.group(TokenType.ASPASDUPLAS.name()), linha));
                continue;
            } else if (matcher.group(TokenType.TAB.name()) != null) {
                tokens.add(new Token(TokenType.TAB, matcher.group(TokenType.TAB.name()), linha));
                continue;
            } else if (matcher.group(TokenType.RETORNOCARRO.name()) != null) {
                tokens.add(new Token(TokenType.RETORNOCARRO, matcher.group(TokenType.RETORNOCARRO.name()), linha));
                continue;
            } else if (matcher.group(TokenType.WHITESPACE.name()) != null) {
                tokens.add(new Token(TokenType.WHITESPACE, matcher.group(TokenType.WHITESPACE.name()), linha));
                continue;
            }

        }

        return tokens;
    }

    public static Archive arq;

    public void exec(String nomeArq) throws FileNotFoundException, IOException {
        //arq = new Archive (nomeArq); //carrega arquivo para memoria        
        File file = new File(nomeArq);
        BufferedReader reader = new BufferedReader(new FileReader(file));

        System.out.println(file.getParent());
        System.out.println(file.getName().replace(".txt", ""));

        String lineInput = reader.readLine();
        int i = 1;

        FileWriter arquivoSaida = new FileWriter(file.getParent() + "\\" + file.getName().replace(".txt", "") + "SAIDATOKENS" + ".txt");
        PrintWriter gravarArq = new PrintWriter(arquivoSaida);
        /*
        for (i=1; i<=10; i++) { 
            gravarArq.printf("|%2d |%n", i); 
        }*/

        do {
            // Create tokens and print them
            ArrayList<Token> tokens = lex(lineInput, i);
            //gravarArq.printf("%s%n", lineInput);

            //tokenList.add(tokens.get(j));
            for (Token token : tokens) {
                tokenList.add(token);
                System.out.println(token);
            }
            //gravarArq.printf("%n");
            lineInput = reader.readLine();
            i++;
        } while (lineInput != null);

        //Tenta Unir Tokens de Tres aspas Duplas
        List<Token> tokenListFinal = new ArrayList<>();
        int linha = 0;
        reader = new BufferedReader(new FileReader(file));

        for (int j = 0; j < tokenList.size(); j++) {

            Token token = tokenList.get(j);
            boolean isIncio = false;

            if (linha != token.linha) {
                isIncio = true;

                for (int k = linha; k < token.linha; k++) {
                    lineInput = reader.readLine();
                    gravarArq.printf("%n%s%n", lineInput);
                }
                linha = token.linha;
            }

            if (token.getType().equals(TokenType.TRESASPASDUPLAS)) {
                TokenType tipo = token.type;
                Token tokenCopia;
                //int linhatoken = token.linha;
                do {
                    j++;
                    tokenCopia = tokenList.get(j);

                    if (linha != tokenCopia.linha) {
                        token.data += "\r\n";
                        isIncio = true;

                        for (int k = linha; k < tokenCopia.linha; k++) {
                            lineInput = reader.readLine();
                            gravarArq.printf("%s%n", lineInput);
                        }
                        linha = tokenCopia.linha;
                    }
                    if (tokenCopia.type.equals(TokenType.TAB)) {
                        System.err.println("AqUI");
                        token.data += "\t";
                    }

                    token.data += tokenCopia.getData();
                } while ((!tokenCopia.type.equals(tipo)) && (((j + 1) < tokenList.size())));
            }

            if ((!token.type.equals(TokenType.WHITESPACE)) && ((!token.type.equals(TokenType.TAB)))) {
                tokenListFinal.add(token);
                gravarArq.printf("%s%n", token.toString());
                isIncio = false;
            } else if (isIncio == true) {
                tokenListFinal.add(token);
                gravarArq.printf("%s%n", token.toString());
            }

        }

        linha = 0;
        for (int j = 0; j < tokenListFinal.size(); j++) {
            System.out.println(tokenListFinal.get(j));

            /*if (linha != tokenListFinal.get(j).linha){
                for (int k = 0; k < tokenListFinal.get(j).linha; k++) {
                    lineInput = reader.readLine();
                    gravarArq.printf("%n%s%n", lineInput);
                }
            }*/
            //gravarArq.printf("%s%n", tokenListFinal.get(j).toString());
        }
        /*
        if ((token.getType().equals(TokenType.ASPASSIMPLES))||(token.getType().equals(TokenType.ASPASDUPLAS))) {
                    TokenType tipo = token.type;
                    Token tokenCopia;
                    do {
                        
                        j++;
                        tokenCopia = tokens.get(j);
                        token.data += tokenCopia.getData();
                    } while ((!tokenCopia.type.equals(tipo))&&(((j+1) < tokens.size())));
                    
                }
         */

        arquivoSaida.close();
    }
}
