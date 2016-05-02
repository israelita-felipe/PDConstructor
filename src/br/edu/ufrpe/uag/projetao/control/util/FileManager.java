/**
 * 
 */
package br.edu.ufrpe.uag.projetao.control.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author israel
 *
 */
public class FileManager {

    private static Scanner leitor;
    private static FileWriter escritor;

    private static Scanner getLeitor(String file) throws FileNotFoundException {
	leitor = new Scanner(new FileInputStream(new File(file)));
	return leitor;
    }

    private static FileWriter getEscritor(String file) throws IOException {
	escritor = new FileWriter(file);
	return escritor;
    }

    public static String lerArquivo(String arquivo) throws FileNotFoundException {
	String retorno = "";
	Scanner leitor = getLeitor(arquivo);
	while (leitor.hasNextLine()) {
	    retorno += leitor.nextLine();
	}
	return retorno;
    }

    public static void escreverArquivo(String arquivo, String texto) throws IOException {
	getEscritor(arquivo).write(texto);
    }

}
