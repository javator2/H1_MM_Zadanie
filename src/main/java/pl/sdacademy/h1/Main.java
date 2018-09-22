package pl.sdacademy.h1;

import org.hibernate.Session;
import org.hibernate.Transaction;
import pl.sdacademy.h1.config.HibernateUtils;
import pl.sdacademy.h1.entity.Account;
import pl.sdacademy.h1.entity.Person;

public class Main {

    public static void main(String[] args) {

        Session session = HibernateUtils.getSession();


        Person person = new Person();
        person.setName("Jan");
        person.setLastname("Kowalski");

        Account account = new Account();
        account.setTitle("Konto");
        account.setPerson(person);

        Account account1 = new Account();
        account1.setTitle("mBank");
        account1.setPerson(person);

        Account account2 = new Account();
        account2.setTitle("AliorBank");
        account2.setPerson(person);

        Transaction transaction = session.beginTransaction();

        session.save(account);
        transaction.commit();

        transaction.begin();
        session.save(account1);
        transaction.commit();

        transaction.begin();
        session.save(account2);
        transaction.commit();

        session.close();
        HibernateUtils.closeConnection();


    }
}
