/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compilador;

import java.io.IOException;

/**
 *
 * @author Marcos
 */
public class Compilador {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        // TODO code application logic here
        String path = "D:\\Dropbox\\Cefet-Engenharia\\7oPeríodo\\Compilador\\Compilador Pyton para Js\\Arquivos De Testes\\ENTRADACOMPLETA.txt";
        LexicalAnalyzer lexical = new LexicalAnalyzer();
        lexical.exec(path);
    }
    
}


