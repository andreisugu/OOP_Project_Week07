Veniti cu exemple simple, cu explicatii despre unde e recomandabil sa se foloseasca.

-------------------------------------------------------
SINGLETON PATTERN
-> cand vrem ca ceva sa apara o singura data (nu are rost sa apara de multiple ori ex: presedintele)
-> vom avea o singura referinta pentru tot proiectul
=> constructor privat <- care va fi apelat din interiorul clasei.
=> vom folosi:  membru static si private -> acelasi tip cu clasa + metoda static si public -> returneaza instanta clasei

Avem 2 metode de instantiere:
- lazy (cream instanta la apelarea metodei)
- eager (cream instanta din definitia clasei)

Exemplu:
- Lazy:
    public class SingletonClass
    {
        private static SingletonClass obj = null;
        private Singleton() {}
        // lazy instantiation
        public static SingletonClass getInstance() {
            // daca clasa nu a fost instantiata inainte, o facem acum
            if (obj == null)
                obj = new SingletonClass();
            return obj;
        }
    }

- Eager:
    public class SingletonClass
    {
        private static SingletonClass obj = new SingletonClass();
        private Singleton() {}
        // eager instantiation - merge la threaduri
        public static SingletonClass getInstance()
        {
            return obj;
        }
    }

-------------------------------------------------------

-------------------------------------------------------
ADAPTER PATTERN
-> folosit pentru a transforma interfata unei clase existente (Adaptee) într-o interfata așteptată de client (Target)
-> permite colaborarea între clase cu interfete incompatibile

Elemente cheie:
=> Adaptee: clasa existentă cu funcționalități utile, dar cu o interfata diferită
=> Target: interfata dorită de client
=> Adapter: clasa care implementează Target și traduce apelurile către Adaptee

Exemplu:

    Definim interfața țintă:

public interface Target {
    void request();
}

    Avem o clasă existentă (Adaptee) cu o metodă specifică:

public class Adaptee {
    public void specificRequest() {
        // implementare specifică
    }
}

    Implementăm adapterul care leagă Adaptee de Target:

public class Adapter implements Target {
    private Adaptee adaptee;

    public Adapter(Adaptee adaptee) {
        this.adaptee = adaptee;
    }

    @Override
    public void request() {
        // adaptăm apelul către metoda specifică
        adaptee.specificRequest();
    }
}

Astfel, Adapter Pattern ajută la reutilizarea codului existent fără a modifica implementarea originală, facilitând integrarea în noi contexte.
-------------------------------------------------------

-------------------------------------------------------
OBSERVER PATTERN
-> relatie one-to-many intre obiecte => un obiect numit "subiect" caruia ii e asociat o colectie, o lista de observatori (obiecte dependente de "subiect"), notificate automat de catre "subiect" (oricand are loc o actiune / o modificare a starii subiectului)
- "Subiectul" menţine referinţe către observatori
- La schimbarea stării, "subiectul" apelează metoda notifyObservers(), care la rândul său invocă update() pentru fiecare observator din listă
- Această abordare asigură o separare între logica de afaceri a "subiectului" și reacţiile observatorilor, promovând astfel o arhitectură flexibilă și scalabilă


========================================
// Interfaţa Observatorului
public interface Observer {
    void update();
}

// Interfaţa Subiectului
public interface Subject {
    void addObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObservers();
}

// Implementarea concretă a subiectului
public class ConcreteSubject implements Subject {
    private List<Observer> observers = new ArrayList<>();
    private String state;

    public void setState(String newState) {
        this.state = newState;
        notifyObservers(); // notificăm observatorii la fiecare modificare a stării
    }

    public String getState() {
        return state;
    }

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update();
        }
    }
}

// Implementarea concretă a observatorului
public class ConcreteObserver implements Observer {
    private ConcreteSubject subject;

    public ConcreteObserver(ConcreteSubject subject) {
        this.subject = subject;
        this.subject.addObserver(this);
    }

    @Override
    public void update() {
        // reacţia observatorului la schimbarea stării
        System.out.println("Starea subiectului a fost actualizată: " + subject.getState());
    }
}
========================================
Pe scurt:
- Subiectul se ocupă de gestionarea observatorilor și notificarea lor la fiecare schimbare de stare
- Observatorii implementează o metodă update() care răspunde la notificări
- Acest pattern ajută la decuplarea componentelor și permite adăugarea dinamică a comportamentelor fără a modifica clasa subiectului


-------------------------------------------------------

-------------------------------------------------------
FACADE PATTERN
-> când vrem să simplificăm interacțiunea cu un sistem complex (mai multe clase/subsisteme)
-> oferim o interfață unificată, ascunzând complexitatea internă
=> clasa Facade va deține referințe către componentele specializate ale sistemului
=> metode publice în Facade vor delega operațiile către aceste componente, oferind o utilizare ușoară

Exemplu:

public class SistemComplexFacade {
    // referințe la subsisteme
    private SubSistem1 ss1;
    private SubSistem2 ss2;

    public SistemComplexFacade() {
         ss1 = new SubSistem1();
         ss2 = new SubSistem2();
    }

    // metoda care simplifică interacțiunea cu sistemul complex
    public void operatiuneSimpla() {
         ss1.metoda1();
         ss2.metoda2();
    }
}
-------------------------------------------------------

-------------------------------------------------------
DECORATOR PATTERN
-> folosit pentru a adăuga dinamic funcționalități suplimentare obiectelor, fără a modifica codul existent (ex: adăugarea de opțiuni la o interfață)
-> permite compunerea flexibilă a comportamentului prin învelirea obiectelor inițiale

=> Componentă de bază (interfață sau clasă abstractă) -> definește operațiunile de bază
=> Decorator abstract -> implementează aceeași interfață și deține o referință la un obiect de tipul componentei de bază
=> Decoratori concreți -> extind funcționalitățile decoratului prin suprascrierea metodelor și apelarea metodei din obiectul învelit

Exemplu:

// Componenta de bază
public interface Component {
    void operation();
}

// Implementare concretă
public class ConcreteComponent implements Component {
    public void operation() {
        // operație de bază
    }
}

// Decorator abstract
public abstract class Decorator implements Component {
    protected Component component;
    public Decorator(Component component) {
        this.component = component;
    }
    public void operation() {
        component.operation();
    }
}

// Decorator concret
public class ConcreteDecorator extends Decorator {
    public ConcreteDecorator(Component component) {
        super(component);
    }
    public void operation() {
        super.operation();
        // funcționalitate suplimentară
    }
}

-> Permite combinarea mai multor decoratori pentru a obține comportamente complexe
-> Ideal pentru situații în care se dorește extensibilitate și reutilizare fără moștenire excesivă

-------------------------------------------------------


Incercati o comparatie
- Adapter Pattern
- Observer Pattern
- Facade Pattern
- Decorator Pattern

-------------------------------------------------------

Voici compararea dintre Adapter Pattern, Observer Pattern, Facade Pattern și Decorator Pattern în format text:

---

### Comparare: Adapter Pattern vs. Observer Pattern vs. Facade Pattern vs. Decorator Pattern

#### 1. Scop
- Adapter Pattern: Transformă o interfață existentă într-una compatibilă cu clientul.
- Observer Pattern: Permite notificarea automată a observatorilor când un subiect își schimbă starea.
- Facade Pattern: Simplifică utilizarea unui sistem complex printr-o interfață unificată.
- Decorator Pattern: Adaugă comportament suplimentar unui obiect, fără a-i modifica codul.

#### 2. Tip de pattern
- Adapter Pattern: Structural
- Observer Pattern: Comportamental
- Facade Pattern: Structural
- Decorator Pattern: Structural

#### 3. Modifică interfața existentă?
- Adapter Pattern: Da, convertește interfața unei clase existente într-una nouă.
- Observer Pattern: Nu, doar adaugă un mecanism de notificare între obiecte.
- Facade Pattern: Nu, doar ascunde complexitatea unui sistem.
- Decorator Pattern: Nu, doar extinde funcționalitatea obiectelor existente.

#### 4. Modifică comportamentul obiectului?
- Adapter Pattern: Nu, doar adaptează interfața.
- Observer Pattern: Da, permite reacții la schimbări de stare.
- Facade Pattern: Nu, doar simplifică accesul la sistem.
- Decorator Pattern: Da, prin adăugarea dinamică de funcționalități.

#### 5. Dependențe între obiecte
- Adapter Pattern: 1-to-1 (adaptează o clasă la o altă interfață).
- Observer Pattern: 1-to-many (subiectul notifică mai mulți observatori).
- Facade Pattern: 1-to-1 sau 1-to-many (fațada gestionează mai multe subsisteme).
- Decorator Pattern: 1-to-1 (decoratorul învelește un obiect de bază).

#### 6. Flexibilitate
- Adapter Pattern: Medie – introduce compatibilitate între clase incompatibile.
- Observer Pattern: Mare – permite observatorilor să fie adăugați sau eliminați dinamic.
- Facade Pattern: Mică – oferă doar o metodă simplificată de interacțiune cu un sistem.
- Decorator Pattern: Foarte mare – permite extinderea funcționalității fără moștenire.

#### 7. Impact asupra codului existent
- Adapter Pattern: Necesită adăugarea unui nou nivel de abstractizare (clasa adapter).
- Observer Pattern: Nu modifică subiectul sau observatorii, doar definește relații între ele.
- Facade Pattern: Nu modifică subsistemele, doar oferă un punct de acces mai simplu.
- Decorator Pattern: Nu modifică obiectul inițial, doar îl „împachetează” cu funcționalități noi.

#### 8. Exemplu de utilizare
- Adapter Pattern: Integrarea unei biblioteci externe cu o interfață diferită.
- Observer Pattern: Model de notificare în UI, fluxuri de evenimente, sistem de mesaje.
- Facade Pattern: Unificarea accesului la un API complex.
- Decorator Pattern: Adăugarea de caracteristici unui obiect UI (ex: scrollbar la o fereastră).

---

### Concluzie
- Adapter Pattern e util când trebuie să facem două sisteme incompatibile să colaboreze.
- Observer Pattern e potrivit pentru scenarii în care avem nevoie de actualizări automate între obiecte dependente.
- Facade Pattern ajută la reducerea complexității interacțiunii cu sisteme mari.
- Decorator Pattern permite extinderea comportamentului unui obiect fără a-l modifica.

Fiecare pattern are un scop clar și trebuie utilizat în contextul potrivit pentru a obține beneficii maxime.

