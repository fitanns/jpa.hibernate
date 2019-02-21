package hwJpaHibernate1;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.*;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static EntityManagerFactory emf;
    public static EntityManager em;

    public static void main(String[] args) {
        try {
            try {
                emf = Persistence.createEntityManagerFactory("Jpaw1");
                em = emf.createEntityManager();
                Scanner sc = new Scanner(System.in);
                Methods methods = new Methods();
                methods.initMenu();
                methods.menu();

            } finally {
                em.close();
                emf.close();
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return;
        }
    }
}
