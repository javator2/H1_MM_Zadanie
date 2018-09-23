package pl.sdacademy.h1;

import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.sdacademy.h1.config.HibernateUtils;
import pl.sdacademy.h1.entity.Account;
import pl.sdacademy.h1.entity.Person;

import java.util.List;

public class Main {

    private static Session session = HibernateUtils.getSession();


    public static void prepareDB(){
        Person person = new Person();
        person.setName("Jan");
        person.setLastname("Kowalski");

        Account account = new Account();
        account.setTitle("Konto");
        account.setPerson(person);

        Transaction transaction = session.beginTransaction();
        session.save(account);
        //dodaje konto razem z osobą, obiekt person po dodaniu bedzie miał przypisane ID
        //nie chce tworzyć nowej osoby, tylko jedną przypisać do wielu rekordów
        transaction.commit();
        System.out.println("Id osoby" + account.getPerson().getId());

        Account account1 = new Account();
        account1.setTitle("mBank");
        //pobieram osobę, która już istnieje w bazie
        account1.setPerson(session.get(Person.class, account.getPerson().getId()));

        Account account2 = new Account();
        account2.setTitle("AliorBank");
        //pobieram osobę, która już istnieje w bazie
        account2.setPerson(session.get(Person.class, account.getPerson().getId()));

        transaction.begin();
        session.save(account1);
        session.save(account2);
        transaction.commit();
        session.close();
    }

    public static void main(String[] args) {

     //   prepareDB();

//        List<Person> personList = session.createQuery("FROM Person p").list();

        List<Person> personList = session
              .createQuery("select distinct p from Person p join fetch p.accountSet")
              .list();

        for (Person person : personList) {
            System.out.println("Osoba: " + person.getName() + " : "
                    + person.getLastname());
            System.out.println("----- Banki -----");
            for (Account account : person.getAccountSet()) {
                System.out.println("Nazwa banku: " + account.getTitle());
            }
        }


        HibernateUtils.closeConnection();
    }
}