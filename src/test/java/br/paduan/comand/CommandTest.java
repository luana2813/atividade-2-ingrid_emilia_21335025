package br.paduan.comand;

import org.junit.Test;

import br.paduan.*;

import static org.junit.Assert.*;

import org.junit.Before;

public class CommandTest {

    private ManageList lista;

    @Before
    public void setup() {
        lista = new ManageList();
    }

    /* Testes de validação de diferentes strings dos comandos */

    @Test
    public void testIsValidWithInexistentComand() {
        boolean teste = Command.isValid("aval 1 nome data telefone");
        assertFalse("Comando invalido nao deve ser executado", teste);
    }

    @Test
    public void testIsValidAddWithoutId() {
        boolean teste = Command.isValid("add nome data telefone");
        assertFalse("Comando invalido nao deve ser executado", teste);
    }

    @Test
    public void testIsValidAddWithInvalidId() {
        boolean teste = Command.isValid("add xx nome data telefone");
        assertFalse("Comando invalido nao deve ser executado", teste);
    }

    @Test
    public void testIsValidWithWrongNumberOfParameters() {
        boolean teste = Command.isValid("add 1 data telefone");
        assertFalse("Comando invalido nao deve ser executado", teste);
    }

    @Test
    public void testIsValidDeleteWithWrongNumberOfParameters() {
        boolean teste = Command.isValid("del 1 nome");
        assertFalse("Comando invalido nao deve ser executado", teste);
    }

    @Test
    public void testIsValidUpdateMissingParameters() {
        boolean teste = Command.isValid("update 1");
        assertFalse("Comando invalido nao deve ser executado", teste);
    }

    @Test
    public void testIsValidUpdateWithWrongNumberOfParameters() {
        boolean teste = Command.isValid("update 1 nome data telefone");
        assertFalse("Comando invalido nao deve ser executado", teste);
    }

    @Test
    public void testIsValidAdd() {
        boolean teste = Command.isValid("add 1 nome data telefone");
        assertTrue("Comando valido deve ser executado", teste);
    }

    @Test
    public void testIsValidDelete() {
        boolean teste = Command.isValid("del 1");
        assertTrue("Comando valido deve ser executado", teste);
    }

    @Test
    public void testIsValidInfo() {
        boolean teste = Command.isValid("info 1");
        assertTrue("Comando valido deve ser executado", teste);
    }

    @Test
    public void testIsValidUpdate() {
        boolean teste = Command.isValid("update 1 telefone");
        assertTrue("Comando valido deve ser executado", teste);
    }

    /* Testes dos comandos individuais (após interpretados) */

    @Test
    public void testAddFirstElement() {
        boolean inseriu = Command.runAdd(lista, 1, "Nome1", "01/01/01", "91111-1111");

        assertTrue("Inserção realizada com sucesso", inseriu);
        assertFalse("Após inserção a lista não está vazia", lista.isEmpty());
    }

    @Test
    public void testAddElementWithDuplicatedId() {
        Command.runAdd(lista, 1, "Nome1", "01/01/01", "91111-1111");
        boolean inseriu = Command.runAdd(lista, 1, "Nome1", "01/01/01", "91111-1111");

        assertFalse("Inserção não deve ser realizada pois ID já existe na lista", inseriu);
    }

    @Test
    public void testInfoElementNotExistOnList() {
        Command.runAdd(lista, 1, "Nome1", "01/01/01", "91111-1111");
        Person pessoa = Command.runInfo(lista, 2);

        assertNull("Não deve encontrar o elemento que não existe na lista", pessoa);
    }

    @Test
    public void testInfoElementExistOnList() {
        Command.runAdd(lista, 1, "Nome1", "01/01/01", "91111-1111");
        Person pessoa = Command.runInfo(lista, 1);

        assertNotNull("Deve encontrar o elemento que existe na lista", pessoa);

        String nome = pessoa.getName();

        assertEquals("O nome deve ser da pessoa encontada", "Nome1", nome);
    }

    @Test
    public void testDelOnEmptyList() {
        Person pessoa = Command.runDel(lista, 1);

        assertNull("Remoção em lista vazia retorna null", pessoa);
    }

    @Test
    public void testDelElementNotExist() {
        Command.runAdd(lista, 1, "Nome1", "01/01/01", "91111-1111");
        Person pessoa = Command.runDel(lista, 2);

        assertNull("Remoção de pessoa que não existe na lista", pessoa);
    }

    @Test
    public void testDelElementExist() {
        boolean inseriu = Command.runAdd(lista, 1, "Nome1", "01/01/01", "91111-1111");
        
        Person pessoa = Command.runDel(lista, 1);
        String nome = pessoa.getName();

        assertTrue("Remoção realizada com sucesso", inseriu);
        assertEquals("O nome da pessoa removida deve ser da pessoa inserida", "Nome1", nome);
    }

    @Test
    public void testUpdateElementOnEmptyList() {
        boolean atualizou = Command.runUpdate(lista, 1, "91010-1010");

        assertFalse("Atualização não realizada pois lista está vazia", atualizou);
    }

    @Test
    public void testUpdateElementNotExist() {
        Command.runAdd(lista, 1, "Nome1", "01/01/01", "91111-1111");
        boolean atualizou = Command.runUpdate(lista, 2, "91010-1010");

        assertFalse("Atualização não realizada pois elemento não existe na lista", atualizou);
    }

    @Test
    public void testUpdateElementExist() {
        Command.runAdd(lista, 1, "Nome1", "01/01/01", "91111-1111");
        
        boolean atualizou = Command.runUpdate(lista, 1, "91010-1010");

        Person pessoa = Command.runInfo(lista, 1);
        String phone = pessoa.getPhone();

        assertTrue("Atualização realizada com sucesso", atualizou);
        assertEquals("O telefone deve ter sido atualizado", "91010-1010", phone);
    }

    /* Testes das chamdas do método run com os comandos para serem interpretados */

    @Test
    public void testRunAddCorrectCommand() {
        boolean inseriu = Command.run(lista, "add 1 Nome1 01/01/01 91111-1111");

        assertTrue("Inserção realizada com sucesso", inseriu);
    }

    @Test
    public void testRunAddIncorrectCommand() {
        boolean inseriu = Command.run(lista, "add 1 01/01/01 91111-1111");

        assertFalse("Inserção não realizada ", inseriu);
    }

    @Test
    public void testRunInfoCorrectCommand() {
        Command.runAdd(lista, 1, "Nome1", "01/01/01", "91111-1111");

        boolean encontrou = Command.run(lista, "info x");

        assertTrue("Busca deve falhar pois o comando está incorreto", encontrou);
    }

    @Test
    public void testRunInfoCorrectId() {
        Command.runAdd(lista, 1, "Nome1", "01/01/01", "91111-1111");

        boolean encontrou = Command.run(lista, "info 1");

        assertTrue("Busca realizada com sucesso", encontrou);
    }

    @Test
    public void testRunInfoIncorrectId() {
        Command.run(lista, "add 1 01/01/01 91111-1111");

        boolean encontrou = Command.run(lista, "info 2");

        assertFalse("Busca deve falhar pois o ID está incorreto", encontrou);
    }

    @Test
    public void testRemoveIncorrectCommand() {
        Command.runAdd(lista, 1, "Nome1", "01/01/01", "91111-1111");

        boolean removeu = Command.run(lista, "del 1 Nome1");

        assertFalse("Remoção deve falhar pois o comando está incorreto", removeu);
    }

    @Test
    public void testRemoveCorrectId() {
        Command.runAdd(lista, 1, "Nome1", "01/01/01", "91111-1111");

        boolean removeu = Command.run(lista, "del 1");

        assertTrue("Remoção realizada com sucesso", removeu);
    }

    @Test
    public void testRemoveIncorrectId() {
        Command.run(lista, "add 1 01/01/01 91111-1111");

        boolean removeu = Command.run(lista, "del 2");

        assertFalse("Remoção não realizada pois ID não existe", removeu);
    }

    @Test
    public void testUpdateIncorrectCommand() {
        Command.runAdd(lista, 1, "Nome1", "01/01/01", "91111-1111");

        boolean atualizou = Command.run(lista, "update 1");

        assertFalse("Remoção deve falhar pois o comando está incorreto", atualizou);
    }

    @Test
    public void testUpdateCorrectId() {
        Command.runAdd(lista, 1, "Nome1", "01/01/01", "91111-1111");

        boolean atualizou = Command.run(lista, "update 1 91010-1010");

        assertTrue("Remoção realizada com sucesso", atualizou);
    }

    @Test
    public void testupdateIncorrectId() {
        Command.run(lista, "add 1 01/01/01 91111-1111");

        boolean atualizou = Command.run(lista, "update 2 91010-1010");

        assertFalse("Remoção não realizada pois ID não existe", atualizou);
    }

}
