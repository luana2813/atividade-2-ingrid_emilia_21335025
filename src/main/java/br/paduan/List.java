package br.paduan;

public class List {
    private No first;

    public List() {
        first = null;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void addEnd(Person pessoa) {
        No newNode = new No(pessoa);
        if (isEmpty()) {
            first = newNode;
        } else {
            No aux = first;
            while (aux.getNext() != null) {
                aux = aux.getNext();
            }
            aux.setNext(newNode);
        }
    }

    public void addStart(Person pessoa) {
        No newNode = new No(pessoa);
        if (isEmpty()) {
            first = newNode;
        } else {
            newNode.setNext(first);
            first = newNode;
        }
    }

    public No removeStart() {
        if (isEmpty()) {
            return null;
        }
        No aux = first;

        first = first.getNext();

        return aux;
    }

    public No removeEnd() {
        if (isEmpty()) {
            return null;
        }
        No aux = first;
        No previous = null;

        while (aux.getNext() != null) {
            previous = aux;
            aux = aux.getNext();
        }

        if (previous == null) {
            first = null;
        } else {
            previous.setNext(null);
        }
        return aux;
    }

    public Person removeById(int id) {
        if (isEmpty()) {
            return null;
        }

        No aux = first;
        No previous = null;

        while (aux != null && aux.getInfo().getId() != id) {
            previous = aux;
            aux = aux.getNext();
        }

        if(aux == null){ //não localizou o id
            return null;
        }

        if (previous == null) { //achou no primeiro da lista
            first = null;
        } else {
            previous.setNext(aux.getNext());
        }
        return aux.getInfo();
    }

    public Person peek() {
        if (isEmpty()) {
            return null;
        }
        return first.getInfo();
    }

    public String show() {
        String out = "";

        No aux = first;

        while (aux != null) {
            out += aux.getInfo() + ";";
            aux = aux.getNext();
        }
        if (out.length() > 0) {
            out = out.substring(0, out.length() - 1);
        }

        return out;
    }

    public boolean existId(Person pessoa) {
        if (!isEmpty()) {
            No aux = first;
            while (aux != null) {
                if (aux.getInfo().getId() == pessoa.getId()) {
                    return true;
                }
                aux = aux.getNext();
            }
        }
        return false;
    }

    /* TODO: escrever este método baseado no método existId() */
    public Person getById(int id) {
        return null;
    }

}
