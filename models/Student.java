package models;

import logic.*;
import java.util.ArrayList;
import java.util.List;

/*
    Student - Nadpisuje Display czlowieka
    ma uprawnienia do sprawdzenia wlasnego planu oraz ocen

    Oceny mozna zamienic na osobna klase np Dziennik,
    ktora bedzie przechowywac jeszcze wlasnosci ocen:
    kto wystawil, z czego, z jakiego kursu
*/

public class Student extends Czlowiek {
    private List<Kurs> zajecia;
    private ArrayList<Double> oceny;
    public int id;

    public Student(String imie, String nazwisko, String mail, int id) {
        super(imie, nazwisko, mail);
        this.id = id;
        this.oceny = new ArrayList<>();
        this.zajecia = new ArrayList<>();
    }

    void DodajOcene(Double k, Czlowiek wystawiajacy) {
        if (wystawiajacy instanceof Nauczyciel || wystawiajacy instanceof Admin)
            this.oceny.add(k);
    }

    public void AddZajecia(Kurs kurs, Czlowiek wprowadzajacy) {
        if (wprowadzajacy instanceof Admin) {
            this.zajecia.add(kurs);
        }
    }

    public void DisplayZajecia() {
        System.out.println("\nZajecia:");
        for (Kurs s : zajecia) {
            System.out.println(s.id + "\t" + s.GetName());
        }
    }

    public void CourseViewDetails(int id) {
        for (Kurs kurs : zajecia) {
            if (kurs.id == id) {
                kurs.DisplayInfo();
            }
        }
    }

    public void DisplayOceny() {
        System.out.println("\nOceny:");
        for (double ocena : oceny) {
            System.out.println("\t" + ocena);
        }
    }

    @Override
    public void Display() {
        System.out.println(this.id + "\t" + super.GetInfo());
    }
}