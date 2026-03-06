package models;

public class Czlowiek {
    private String imie;
    private String nazwisko;
    private String mail;

    protected Czlowiek(String imie, String nazwisko, String mail) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.mail = mail;
    }

    public String GetInfo() {
        return this.imie + " " + this.nazwisko + ",\t" + this.mail;
    }

    public void Display() {
        System.out.println(this.GetInfo());
    }
}
