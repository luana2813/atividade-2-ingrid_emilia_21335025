package br.paduan;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import br.paduan.comand.*;

/**
 * AppCRUD
 */
public final class AppCRUD_file {
    private AppCRUD_file() {
    }

    public static void main(String[] args) {
        ManageList lista = new ManageList();

        try {
          FileReader arq = new FileReader("entrada.txt");
          BufferedReader lerArq = new BufferedReader(arq);
          String comando; 

          do{

            comando = lerArq.readLine(); // lê uma linha do arquivo

            if(comando != null && comando.length() > 0){
                Command.run(lista, comando);
            }

          }while (comando != null); // "linha" recebe o valor "null" ao atingir o final do arquivo
     
          arq.close();
        } catch (IOException e) {
            System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
        }
     
        System.out.println("Fim de processamento.");
    }

}
