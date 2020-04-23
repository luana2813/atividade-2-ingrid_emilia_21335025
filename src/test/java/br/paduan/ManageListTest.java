package br.paduan;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class ManageListTest {

    private ManageList gerenciaLista;

    private final int idPessoa1 = 1;
    private final int idPessoa2 = 2;
    private final Person pessoa1 = new Person(idPessoa1, "Pessoa 1", "01/01/01", "91111-1111");
    private final Person pessoa2 = new Person(idPessoa2, "Pessoa 2", "02/01/01", "92222-2222");
    private final Person pessoa3 = new Person(3, "Pessoa 3", "03/01/01", "93333-3333");
    private final Person pessoaInvalidId = new Person(-1, "Dados invalidos", "03/01/01", "93333-3333");
    private final Person pessoaInvalidName = new Person(4, "", "03/01/01", "93333-3333");
    private final Person pessoaInvalidBirthday = new Person(5, "Dados invalidos", "", "93333-3333");
    private final Person pessoaInvalidPhone = new Person(6, "Dados invalidos", "03/01/01", "");

    @Before
    public void setup() {
        gerenciaLista = new ManageList();
    }

    @Test
    public void testIsEmpty() {
        boolean vazia = gerenciaLista.isEmpty();

        assertTrue("Nova lista inicia vazia.", vazia);
    }

    @Test
    public void testAddOnEmptyList() {
        boolean insert = gerenciaLista.add(pessoa1);
        boolean vazia = gerenciaLista.isEmpty();

        assertTrue("Inserção em uma lista vazia", insert);
        assertFalse("Após a inserção a lista não está vazia.", vazia);
    }

    @Test
    public void testAddOnNotEmptyList() {
        gerenciaLista.add(pessoa1);
        boolean insert = gerenciaLista.add(pessoa2);

        assertTrue("Inserção em uma lista não vazia", insert);
    }

    @Test
    public void testAddInvalidData() {
        boolean insert1 = gerenciaLista.add(pessoaInvalidId);
        boolean insert2 = gerenciaLista.add(pessoaInvalidName);
        boolean insert3 = gerenciaLista.add(pessoaInvalidBirthday);
        boolean insert4 = gerenciaLista.add(pessoaInvalidPhone);
        boolean vazia = gerenciaLista.isEmpty();

        assertFalse("Inserção com ID invalido", insert1);
        assertFalse("Inserção com nome invalido", insert2);
        assertFalse("Inserção com nome invalido", insert3);
        assertFalse("Inserção com nome invalido", insert4);
        assertTrue("Após inserções invalidas, a lista permanece vazia.", vazia);

    }

    @Test
    public void testDeleteOnEmptyList() {
        Person pessoa = gerenciaLista.del(1);

        assertNull("A remoção em uma lista vazia retorna null", pessoa);
    }

    @Test
    public void testDeleteOnOneElementList() {
        gerenciaLista.add(pessoa1);
        Person pessoa = gerenciaLista.del(idPessoa1);
        boolean vazia = gerenciaLista.isEmpty();

        assertEquals("A remoção em uma lista com o elemento retorna a pessoa encontrada", pessoa1, pessoa);
        assertTrue("Após remoção do último elemento a lista deve ficar vazia.", vazia);
    }

    @Test
    public void testDeleteOnNotEmptyList() {
        gerenciaLista.add(pessoa1);
        gerenciaLista.add(pessoa2);
        Person pessoa = gerenciaLista.del(idPessoa2);
        boolean vazia = gerenciaLista.isEmpty();

        assertEquals("A remoção em uma lista com o elemento retorna a pessoa encontrada", pessoa2, pessoa);
        assertFalse("Após a remoção a lista não fica vazia.", vazia);
    }

    @Test
    public void testInfoOnEmptyList() {
        Person pessoa = gerenciaLista.info(idPessoa1);

        assertEquals("Os dados da pessoa em uma lista vazia deve ser uma String vazia", null, pessoa);
    }

    @Test
    public void testInfoForPersonOutOfList() {
        gerenciaLista.add(pessoa2);
        gerenciaLista.add(pessoa3);
        Person pessoa = gerenciaLista.info(idPessoa1);

        assertEquals("Os dados da pessoa ausente da lista deve ser uma String vazia", null, pessoa);
    }

    @Test
    public void testInfoForPersonInList() {
        gerenciaLista.add(pessoa1);
        gerenciaLista.add(pessoa2);
        Person pessoa = gerenciaLista.info(idPessoa2);

        assertEquals("Os dados da pessoa ausente da lista deve ser uma String vazia", pessoa2, pessoa);
    }

    @Test
    public void testUpdateOnEmptyList() {
        boolean atualizou = gerenciaLista.update(idPessoa1, "91212-1212");

        assertFalse("update em uma lista vazia deve retornar false.", atualizou);
    }

    @Test
    public void testUpdateForPersonOutOfList() {
        gerenciaLista.add(pessoa1);
        boolean atualizou = gerenciaLista.update(idPessoa2, "91212-1212");

        assertFalse("update em uma pessoa que não está na lista deve retornar false.", atualizou);
    }

    @Test
    public void testUpdateForPersonInList() {
        gerenciaLista.add(pessoa1);
        gerenciaLista.add(pessoa2);

        String novoTelefone = "91212-1212";
        boolean atualizou = gerenciaLista.update(idPessoa2, novoTelefone);

        assertTrue("update em uma pessoa que está na lista deve retornar true.", atualizou);

        Person pessoa = gerenciaLista.info(idPessoa2);
        String telefone = pessoa.getPhone();

        assertEquals("Telefone deve ser atualizado com o novo número", novoTelefone, telefone);
    }

    @Test
    public void testShowOnEmptyList() {

        String dados = gerenciaLista.show();

        assertEquals("Os dados da lista vazia deve ser uma String vazia", "", dados);
    }

    @Test
    public void testShow() {
        gerenciaLista.add(pessoa1);
        gerenciaLista.add(pessoa2);

        String dados = gerenciaLista.show();
        String expected = pessoa1.toString() + ";" + pessoa2.toString();

        assertEquals("Os dados das pessoas na lista deve ser formatados como exigido", expected, dados);
    }

}
