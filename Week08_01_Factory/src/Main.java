
// Factory - pattern creational
// familie de clase : sunt inrudite intre ele
// -> mostenesc aceeasi clasa abstracta / implementeaza aceeasi interfata

interface IntPizza {
    void printToppings();
}

abstract class Pizza implements IntPizza {
    public abstract void printToppings();
}

class MargheritaPizza extends Pizza {
    public void printToppings() {
        System.out.println("Sos de Rosii si Mozzarella.");
    }
}

class PepperoniPizza extends Pizza {
    public void printToppings() {
        System.out.println("Sos de Rosii, Mozzarella si Pepperoni.");
    }
}

class DiavolaPizza extends Pizza {
    public void printToppings() {
        System.out.println("Sos de Rosii, Mozzarella, Salam si Ardei iute.");
    }
}

class MeatLoversPizza extends Pizza {
    public void printToppings() {
        System.out.println("Sos de Rosii, Mozzarella, Sunca, Pepperoni, Pui.");
    }
}

class PizzaFactory {
    public static Pizza factory (String pizzaNume) {
        return switch (pizzaNume) {
            case "MeatLovers" -> new MeatLoversPizza();
            case "Pepperoni" -> new PepperoniPizza();
            case "Diavola" -> new DiavolaPizza();
            case "Margherita" -> new MargheritaPizza();
            default -> null;
        };
    }
}

public class Main {
    public static void main(String[] args) {
        String tipPizza = "MeatLovers";
        Pizza pizza = PizzaFactory.factory(tipPizza);

        if (pizza != null) {
            System.out.println("Toppinguri pentru pizza " + tipPizza + ": ");
            pizza.printToppings();
        } else {
            System.out.println("Pizza " + tipPizza + " nu exista.");
        }
    }
}