package br.paduan;

public class Person {
    private int id;
    private String name;
    private String birthday;
    private String phone;

    public Person() {
        this.id = 0;
        this.name = "";
        this.birthday = "";
        this.phone = "";
    }

    public Person(int id, String name, String birthday, String phone) {
        this.id = id;
        this.name = name;
        this.birthday = birthday;
        this.phone = phone;
    }

    /* TODO: implementar as validações dos atributos */
    public boolean isValid(){
        return false;
    }
    
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String toString() {
        return id + ":" + name + ":" + birthday + ":" + phone;
    }

}
