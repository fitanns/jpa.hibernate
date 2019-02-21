package hwJpaHibernate1;

import javax.persistence.Query;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import static hwJpaHibernate1.Main.em;


public class Methods {
    public static Menu menu;

    public static Query query;

    public static void initMenu() throws SQLException {

        em.getTransaction().begin();
        try {
            Menu menu1 = new Menu("pizza", 12.30, 745, false);
            Menu menu2 = new Menu("sushi", 17.50, 1120, true);
            Menu menu3 = new Menu("pasta", 15.45, 300, false);
            em.persist(menu1);
            em.persist(menu2);
            em.persist(menu3);
            em.getTransaction().commit();
        } catch (Exception ex) {
            em.getTransaction().rollback();
        }


    }

    public static void menu() throws SQLException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("WELCOME TO OUR RESTAURANT!!!");
            System.out.println("Choose one of the varients of our dishes to select for parameters");
            System.out.println("1: Cost: From -> To");
            System.out.println("2: Only for Discounts");
            System.out.println("3: Less than 1 kg (< 1000 g)");
            System.out.println("4: View all menu of restaurant");
            System.out.print("-> ");

            String s = sc.nextLine();
            switch (s) {
                case "1":
                    fromTo(sc);
                    break;
                case "2":
                    discount(sc);
                    break;
                case "3":
                    less1000(sc);
                    break;
                case "4":
                    viewAll();
                    break;
                default:
                    return;
            }
        }
    }

    public static void fromTo(Scanner sc) throws SQLException {
        System.out.println("Input your preferred cost of dish from and to ");
        System.out.println("Input FROM:");
        String fromS = sc.nextLine();
        System.out.println("Input To:");
        String toS = sc.nextLine();
        double fr = Double.parseDouble(fromS);
        double to = Double.parseDouble(toS);


        em.getTransaction().begin();
        try {
            query = em.createNamedQuery("Menu.fromTo", Menu.class);
            query.setParameter("fr", fr);
            query.setParameter("to", to);

            List<Menu> menuL = query.getResultList();

            for (Menu menus : menuL) {
                System.out.println(menus);
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }

    public static void discount(Scanner sc) throws SQLException {
        System.out.println("Your dish with discount:");
        try {
            em.getTransaction().begin();
            query = em.createNamedQuery("Menu.discount", Menu.class);
            query.setParameter("discount", true);
            Menu menut = (Menu) query.getSingleResult();
            System.out.println(menut);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }


    }

    public static void less1000(Scanner sc) throws SQLException {
        System.out.println("Input your preferred weight on grams:");
        String gramS = sc.nextLine();
        double gram = Double.parseDouble(gramS);
        double w = 0;
        if (gram >= 1000) {
            System.out.println("Your order equals or more than 1000 g. Please input less than 1000 g");

        } else if (gram > 1120) {
            System.out.println("We don't have such a big dishes in our menu. Please choose your prefered weight again.");
            System.out.println();
            menu();
        } else if (gram < 300) {
            System.out.println("We don't have such a small dishes in our menu. Please choose your prefered weight again.");
            System.out.println();
            menu();

        } else {

            em.getTransaction().begin();
            try {
                query = em.createNamedQuery("Menu.weight", Menu.class);
                query.setParameter("weight", gram);
                List<Menu> menuList = query.getResultList();

                for (Menu menus : menuList) {
                    w += menus.getWeight();
                    if (w >= 1000) {
                        break;

                    } else {
                        System.out.println(menus);
                        System.out.println(w + "grams");
                    }
                }

                em.getTransaction().commit();
            } catch (Exception e) {
                em.getTransaction().rollback();
            }
        }
    }

    public static void viewAll() throws SQLException {
        em.getTransaction().begin();
        try {

            query = em.createNamedQuery("Menu.findAll", Menu.class);
            List<Menu> menuList = query.getResultList();

            for (Menu menus : menuList) {
                System.out.println(menus);
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
        }
    }
}
