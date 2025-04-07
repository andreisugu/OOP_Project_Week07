

public class Main {
    public static void main(String[] args) {
        User user1 = new User.UserBuilder("Loki", "Laufeyson")
                .setVarsta(5000)
                .setAdresa("9 Trickster's Crescent")
                .setTelefon("+12 345 678 910")
                .build();

        System.out.println(user1);

        User user2 = new User.UserBuilder("Sans", "the skeleton")
                .setVarsta(1001)
                .setAdresa("Snowdin Forest")
                .build();
        System.out.println(user2);
    }
}