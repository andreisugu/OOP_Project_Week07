public class User {
    private final String prenume; // obligatoriu
    private final String nume; // obligatoriu
    private final int varsta; // optional
    private final String telefon; // optional
    private final String adresa; // optional

    // Constructor
    User(UserBuilder builder) {
        this.prenume = builder.prenume;
        this.nume = builder.nume;
        this.varsta = builder.varsta;
        this.telefon = builder.telefon;
        this.adresa = builder.adresa;
    }

    // Getters
    public String getPrenume() {
        return prenume;
    }
    public String getNume() {
        return nume;
    }
    public int getVarsta() {
        return varsta;
    }
    public String getTelefon() {
        return telefon;
    }
    public String getAdresa() {
        return adresa;
    }

    public String toString() {
        return "User: "+this.prenume+" "+this.nume+" "+this.varsta+" "+this.telefon+" "+this.adresa;
    }

    public static class UserBuilder {
        // obligatorii
        private final String prenume;
        private final String nume;
        // optionale
        private int varsta;
        private String telefon;
        private String adresa;

        // Constructor (cu obligatorii)
        public UserBuilder(String prenume, String nume) {
            this.prenume = prenume;
            this.nume = nume;
        }

        // Optionale
        public UserBuilder setVarsta(int varsta) {
            this.varsta = varsta;
            return this;
        }

        public UserBuilder setTelefon(String telefon) {
            this.telefon = telefon;
            return this;
        }

        public UserBuilder setAdresa(String adresa) {
            this.adresa = adresa;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
