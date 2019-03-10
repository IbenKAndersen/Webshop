package Webshop.entity;

/**
 *
 * @author ibenk
 */
public class Cupcake {
    
    private final Toppings top;
    private final Bottoms bottom;
    private final int totalPrice;

    public Cupcake(Toppings top, Bottoms bottom) {
        this.top = top;
        this.bottom = bottom;
        this.totalPrice = top.getPrice() + bottom.getPrice();
    }

    public Toppings getTop() {
        return top;
    }

    public Bottoms getBottom() {
        return bottom;
    }
    
    public int getPrice() {
        return totalPrice;
    }

    @Override
    public String toString() {
        return bottom.getName() + " bottom with " + top.getName() + 
                " as topping, with totalPrice = " + totalPrice +  " $";
    }
    
}
