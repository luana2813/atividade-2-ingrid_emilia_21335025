package br.paduan;

import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.Before;

public class ListTest {

    private List lista;

    private final Person pessoa1 = new Person(1, "Pessoa 1", "01/01/01", "91111-1111");
    private final Person pessoa2 = new Person(2, "Pessoa 2", "02/01/01", "92222-2222");
    private final Person pessoa3 = new Person(3, "Pessoa 3", "03/01/01", "93333-3333");

    @Before
    public void setup() {
        lista = new List();
    }

    @Test
    public void testIsEmpty() {
        boolean vazia = lista.isEmpty();

        assertTrue("Uma lista nova deve iniciar vazia.", vazia);
    }

    @Test
    public void testNotEmpty() {

        lista.addEnd(pessoa1);
        boolean vazia = lista.isEmpty();

        assertFalse("Uma lista com um elemento não deve estar vazia.", vazia);
    }

    @Test
    public void testAddEndOnEmptyList() {

        lista.addEnd(pessoa1);
        String out = lista.show();

        String expected = pessoa1.toString();

        assertEquals("Após inserção deve ser exibido dados de "+ pessoa1, expected, out);
    }

    @Test
    public void testAddEndOnNotEmptyList() {

        lista.addEnd(pessoa1);
        lista.addEnd(pessoa2);
        String out = lista.show();

        String expected = pessoa1 + ";" + pessoa2;

        assertEquals("Após inserção de dois nomes amobos deve ser exibidos respeitando a ordem" , expected, out);
    }

    @Test
    public void testAddStartOnEmptyList() {

        lista.addStart(pessoa1);
        String out = lista.show();

        String expected = pessoa1+"";

        assertEquals("Após inserção deve ser exibido o nome "+ pessoa1, expected, out);
    }

    @Test
    public void testAddStartOnNotEmptyList() {

        lista.addStart(pessoa1);
        lista.addStart(pessoa2);
        String out = lista.show();

        String expected = pessoa2 + ";" + pessoa1;

        assertEquals("Após inserção de dois nomes amobos deve ser exibidos respeitando a ordem", expected, out);
    }

    @Test
    public void testRemoveStartOnNotEmptyList() {

        lista.addStart(pessoa1);
        lista.addStart(pessoa2);

        Person pessoa = lista.removeStart().getInfo();

        assertEquals("Deve ser removido o nome que estava no início da lista", pessoa2, pessoa);
    }

    @Test
    public void testRemoveStartOnEmptyList() {

        No removido = lista.removeStart();

        assertEquals("Remoção de uma lista vazia deve retornar null", null, removido);
    }

    @Test
    public void testRemoveEndOnNotEmptyList() {

        lista.addStart(pessoa1);
        lista.addStart(pessoa2);

        Person pessoa = lista.removeEnd().getInfo();

        assertEquals("Deve ser removido o nome que estava no início da lista", pessoa1, pessoa);
    }

    @Test
    public void testRemoveEndOnEmptyList() {

        No removido = lista.removeEnd();

        assertEquals("Remoção de uma lista vazia deve retornar null", null, removido);
    }

    @Test
    public void testRemoveLast() {

        lista.addStart(pessoa1);

        Person pessoa = lista.removeEnd().getInfo();
        boolean vazia = lista.isEmpty();

        assertEquals("Deve ser removido o nome que estava no início da lista", pessoa1, pessoa);
        assertTrue("Ao remover o ultimo item, a lista fica vazia", vazia);
    }
    
    @Test
    public void testRemoveByIdOnEmptyList() {

        Person pessoa = lista.removeById(pessoa1.getId());

        assertEquals("Remoção de uma lista vazia deve retornar null", null, pessoa);
    }

    @Test
    public void testRemoveByIdOnNotPresentId() {

        lista.addStart(pessoa1);

        Person pessoa = lista.removeById(pessoa2.getId());
        boolean vazia = lista.isEmpty();

        assertEquals("Ao receber id inválido, não remove", null, pessoa);
        assertFalse("Ao falhar a remoção, a lista não fica vazia", vazia);
    }

    @Test
    public void testRemoveByIdLastElement() {

        lista.addStart(pessoa1);

        Person pessoa = lista.removeById(pessoa1.getId());
        boolean vazia = lista.isEmpty();

        assertEquals("Deve ser removido o nome que estava no início da lista", pessoa1, pessoa);
        assertTrue("Ao remover o ultimo item, a lista fica vazia", vazia);
    }

    @Test
    public void testRemoveByIdFirstItem() {

        lista.addStart(pessoa1);
        lista.addStart(pessoa2);

        Person pessoa = lista.removeById(pessoa1.getId());
        boolean vazia = lista.isEmpty();

        assertEquals("Deve ser removido o nome que estava no início da lista", pessoa1, pessoa);
        assertFalse("Ao remover o um item apenas, a lista não fica vazia", vazia);
    }

    @Test
    public void testRemoveByIdLastItem() {

        lista.addStart(pessoa1);
        lista.addEnd(pessoa2);

        Person pessoa = lista.removeById(pessoa2.getId());
        boolean vazia = lista.isEmpty();

        assertEquals("Deve ser removido o nome que estava no final da lista", pessoa2, pessoa);
        assertFalse("Ao remover o um item apenas, a lista não fica vazia", vazia);
    }

    @Test
    public void testRemoveByIdOnCenterItem() {

        lista.addStart(pessoa1);
        lista.addEnd(pessoa2);
        lista.addEnd(pessoa3);

        Person pessoa = lista.removeById(pessoa2.getId());
        boolean vazia = lista.isEmpty();

        assertEquals("Deve ser removido o nome que estava no meio da lista", pessoa2, pessoa);
        assertFalse("Ao remover o um item apenas, a lista não fica vazia", vazia);
    }

    @Test
    public void testFindIdOnEmptyList() {

        boolean achou = lista.existId(pessoa1);

        assertFalse("Procura o ID em uma lista vazia", achou);
    }

    @Test
    public void testFindIdPresent() {

        lista.addStart(pessoa1);
        lista.addStart(pessoa2);

        boolean achou = lista.existId(pessoa1);

        assertTrue("Encontra o ID presente na lista", achou);
    }

    @Test
    public void testFindIdNotPresent() {

        lista.addStart(pessoa1);

        boolean achou = lista.existId(pessoa2);

        assertFalse("Encontra o ID presente na lista", achou);
    }

    @Test
    public void testGetByIdPresent() {

        lista.addStart(pessoa1);
        lista.addStart(pessoa2);

        Person pessoa = lista.getById(pessoa2.getId());

        assertEquals("Encontra o ID presente na lista", pessoa2, pessoa);
    }

    @Test
    public void testGetByIdNotPresent() {

        lista.addStart(pessoa1);

        Person pessoa = lista.getById(pessoa2.getId());

        assertEquals("Encontra o ID presente na lista", null, pessoa);
    }

}
