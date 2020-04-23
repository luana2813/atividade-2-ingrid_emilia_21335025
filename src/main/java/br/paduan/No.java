package br.paduan;

public class No {
    private Person person;
    private No next;

    public No(Person person){
        if(person != null)
            this.person = person;
        else
            this.person = new Person();
        next = null;
    }

    public Person getInfo() {
        return person;
    }

    public No getNext(){
        return next;
    }

    public void setNext(No next){
        this.next = next;
    }
}
