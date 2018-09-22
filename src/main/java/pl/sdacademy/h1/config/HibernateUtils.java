package pl.sdacademy.h1.config;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {

    private static SessionFactory sf =
            new Configuration()
            .configure()
            .buildSessionFactory();

    private static Session session;

    public synchronized static Session getSession(){
        if (session == null) {
            System.out.println("Otwieram Sesje");
            session = sf.openSession();
        }
        return session;
    }

    public static SessionFactory getSessionFactory() {
        if(sf == null ){
            System.out.println("Otwieram nową fabrykę");
            sf = (SessionFactory) new HibernateUtils();
        }
        return sf;
    }

    public static void closeConnection(){
        session.close();
        sf.close();
    }


}
