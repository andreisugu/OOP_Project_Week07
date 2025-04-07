// cele 2 familii de produse ce le vom folosi
interface Pizza {
    void prepare();
}

interface Sauce {
    void addSauce();
}

// factory abstract -> pt crearea celor 2 produse
interface PizzaFactory {
    Pizza makePizza();
    Sauce makeSauce();
}

// clasele in sine -> implementari specifice pt interfete
class MargheritaPizza implements Pizza {
    public void prepare() {
        System.out.println("Preparing Margherita Pizza.");
    }
}

class BBQPizza implements Pizza {
    public void prepare() {
        System.out.println("Preparing BBQ Pizza.");
    }
}

class TomatoSauce implements Sauce {
    public void addSauce() {
        System.out.println("Adding Tomato Sauce.");
    }
}

class BBQSauce implements Sauce {
    public void addSauce() {
        System.out.println("Adding BBQ Sauce.");
    }
}

// Implementarile de factory
class ItalianPizzaFactory implements PizzaFactory {
    public Pizza makePizza() {
        return new MargheritaPizza();
    }

    public Sauce makeSauce() {
        return new TomatoSauce();
    }
}

class AmericanPizzaFactory implements PizzaFactory {
    public Pizza makePizza() {
        return new BBQPizza();
    }

    public Sauce makeSauce() {
        return new BBQSauce();
    }
}

// Clasa client -> primeste o instanta pizzaFactory si foloseste metodele
// acesteia pt a pregati pizza si sosul
class PizzaStore {
    private PizzaFactory factory;

    public PizzaStore(PizzaFactory factory) {
        this.factory = factory;
    }

    public void orderPizza() {
        Pizza pizza = factory.makePizza();
        Sauce sauce = factory.makeSauce();
        pizza.prepare();
        sauce.addSauce();
        System.out.println("Pizza is ready!");
    }
}


public class Main {
    public static void main(String[] args) {
        // 1. Pizzerie italiana
        PizzaFactory factory = new ItalianPizzaFactory();
        PizzaStore pizzaStore = new PizzaStore(factory);

        System.out.println("Comandam de la pizzeria italiana:");
        pizzaStore.orderPizza();

        // 2. Pizzerie americana
        PizzaFactory factoryAmerican = new AmericanPizzaFactory();
        PizzaStore pizzerieAmericana = new PizzaStore(factoryAmerican);

        System.out.println("\nComandam de la pizzeria americana:");
        pizzerieAmericana.orderPizza();
    }
}