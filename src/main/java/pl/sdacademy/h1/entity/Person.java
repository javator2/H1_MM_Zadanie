package pl.sdacademy.h1.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String lastname;

    @OneToMany(mappedBy = "person", fetch = FetchType.EAGER)
    private Set<Account> accountSet;

    public Person(){

    }

    public int getId() {
        return id;
    }

    public Set<Account> getAccountSet() {
        return accountSet;
    }

    public void setAccountSet(Set<Account> accountSet) {
        this.accountSet = accountSet;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }
}