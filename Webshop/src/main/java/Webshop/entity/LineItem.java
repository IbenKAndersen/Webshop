package Webshop.entity;
import Webshop.entity.Cupcake;
import java.util.Objects;

public class LineItem {

    /**
     * When a cupcake is added, a new Line Item is created and added to the
     * cart. If the user order the same cake twice we can add yet another
     * LineItem (or the quantity of an already existing Line Item can be
     * incremented).
     */
    
    private int qty;
    private final Cupcake cupcake;

    public LineItem(Cupcake cupcake) {
        this.cupcake = cupcake;
    }
    
    public int getQuantity() {
        return qty;
    }

    public Cupcake getCupcake() {
        return cupcake;
    }

    public void addQuantity(int quantity) {
        this.qty = this.qty + quantity;
    }

    public void setQuantity(int qty) {
        this.qty = qty;
    }
    
    public int getTotalPrice() {
        return cupcake.getPrice() * qty;
    }

    @Override
    public String toString() {
        return cupcake.toString() + " Quantity: " + this.qty + " Total Price: " + getTotalPrice() + " $";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final LineItem other = (LineItem) obj;
        if (!Objects.equals(this.cupcake, other.cupcake)) {
            return false;
        }
        return true;
    }

}
