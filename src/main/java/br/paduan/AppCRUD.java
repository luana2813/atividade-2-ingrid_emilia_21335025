package br.paduan;

import br.paduan.comand.Command;

/**
 * AppCRUD!
 */
public final class AppCRUD {
    private AppCRUD() {
    }

    public static void main(String[] args) {
        ManageList lista = new ManageList();

        String sampleCommand = "add 123 Huguinho 20/01/2020 +55-11-91234-4321";

        Command.run(lista, sampleCommand);

    }
}
