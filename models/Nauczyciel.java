package models;

import logic.*;
import java.util.ArrayList;
import java.util.List;

/*
    Nauczyciel - nadpisuje Display
    moze zmieniac oceny Studenta
    wyswietla Kursy, gdzie jest prowadzacym
*/

public class Nauczyciel extends Czlowiek {
    private String tytul_naukowy;
    private List<Kurs> Kursy;
    public int id;

    public Nauczyciel(String imie, String nazwisko, String mail, String t, int id) {
        super(imie, nazwisko, mail);
        this.id = id;
        this.tytul_naukowy = t;
        this.Kursy = new ArrayList<>();
    }

    public void AddKurs(Kurs k, Czlowiek wprowadzajacy) {
        if (wprowadzajacy instanceof Admin) {
            this.Kursy.add(k);
        }
    }

    public void CourseViewDetails(int id) {
        for (Kurs kurs : Kursy) {
            if (kurs.id == id) {
                kurs.DisplayInfo();
            }
        }
    }

    public boolean WystawOcene(Double k, int s_id, int k_id) {
        if (k > 5.1 || k < 1.9)
            return false;

        for (Kurs kurs : Kursy) {
            if (kurs.id == k_id) {
                kurs.GetStudent(s_id).DodajOcene(k, this);
                return true;
            }
        }
        return false;
    }

    public void DisplayKursy() {
        System.out.println("============================================");
        System.out.println("Kursy:");
        for (Kurs k : this.Kursy) {
            System.out.println(k.id + "\t" + k.GetName());
        }
        System.out.println("============================================");
    }

    @Override
    public String GetInfo() {
        return super.GetInfo() + " " + this.tytul_naukowy.replace(" ", "_") + " " + this.id;
    }

    @Override
    public void Display() {
        System.out.println(this.id + "\t" + this.tytul_naukowy + " " + super.GetInfo());
    }
}