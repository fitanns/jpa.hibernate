package hwJpaHibernate1;

import javax.persistence.*;


@Entity
@Table(name = "MenuRest")
@NamedQueries({
        @NamedQuery(name = "Menu.findAll", query = "SELECT m FROM Menu m"),
        @NamedQuery(name = "Menu.discount", query = "SELECT m FROM Menu m WHERE m.discount = :discount"),
        @NamedQuery(name = "Menu.weight", query = "SELECT m FROM Menu m WHERE m.weight <= :weight"),
        @NamedQuery(name = "Menu.fromTo", query = "SELECT m FROM Menu m WHERE m.price >= :fr AND m.price <= :to")
})
public class Menu {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private long id;

    @Column(name = "dish", nullable = false)
    private String dish;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "weight", nullable = false)
    private double weight;

    @Column(name = "discount", nullable = false)
    private boolean discount;

    public Menu() {
    }

    public Menu(String dish, double price, double weight, boolean discount) {
        this.dish = dish;
        this.price = price;
        this.weight = weight;
        this.discount = discount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDish() {
        return dish;
    }

    public void setDish(String dish) {
        this.dish = dish;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public boolean isDiscount() {
        return discount;
    }

    public void setDiscount(boolean discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "id=" + id +
                ", dish='" + dish + '\'' +
                ", price=" + price +
                ", weight=" + weight +
                ", discount=" + discount +
                '}';
    }
}
