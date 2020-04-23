package br.paduan.comand;

import br.paduan.*;
import java.util.StringTokenizer;

public class Command {

    // *TODO: recebe o comando, separa as partes e chama o método apropriado
    public static boolean run(ManageList lista, String command){
        StringTokenizer tokens;
        tokens = new StringTokenizer(command);

        while(tokens.hasMoreTokens()){
            System.out.println(tokens.nextToken());
        } 

        return true;
    }

    // *TODO: verifica se o comando recebido é válido
    public static boolean isValid(String command){
        return true;
    }

    // *TODO: executa a instrução que adiciona um novo item ao final da lista. Retorna true se inseriu
    protected static boolean runAdd(ManageList lista, int id, String name, String birthday, String phone){
        return true;
    }

    // *TODO: remove o item com o id informado
    protected static Person runDel(ManageList lista, int id){
        return null;
    }

    // *TODO: retorna o item com o id informado
    protected static Person runInfo(ManageList lista, int id){
        return null;
    }

    // *TODO: atualiza o item com id informado para o telefone infomado. Retorna true se atualizou
    protected static boolean runUpdate(ManageList lista, int id, String phone){
        return true;
    }

    // *TODO: retorna uma String com as informações de todos os itens da lista
    protected static String runShow(ManageList lista){
        return "";
    }

}
