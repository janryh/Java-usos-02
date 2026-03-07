package models;

import java.util.ArrayList;
import java.util.List;

/*
    Kurs - mozna zobaczyc materialy na kursie,
    uczestnikow oraz prowadzacych

    !!  DODAC WYSWIETLANIE PREZENTACJI  !!
        ZABEZPIECZNIE BY DODAWANIE KURSOW
        BYLO OGRANIOCZNE DLA ADMINA
*/

public class Kurs {
    private String nazwa;
    private List<Nauczyciel> prowadzacy;
    private List<String> prezentacje;
    private List<Student> users;
    public int id;

    public Kurs(String nazwa, int id) {
        this.nazwa = nazwa.replace("_", " ");
        this.id = id;
        this.prowadzacy = new ArrayList<>();
        this.users = new ArrayList<>();
        this.prezentacje = new ArrayList<>();
    }

    public String GetName() {
        return this.nazwa;
    }

    public void AddPDF(String pdf) { // !! tak samo jak z prowadzacymi
        this.prezentacje.add(pdf);
    }

    public void AddUser(Student user) {
        this.users.add(user);
    }

    public void AddProwadzacy(Nauczyciel prow) {
        this.prowadzacy.add(prow);
    }

    private void DisplayUczniow() {
        for (Student s : this.users) {
            s.Display();
        }
    }

    public List<String> GetMaterials() {
        return this.prezentacje;
    }

    public List<Nauczyciel> GetProwadzacych() {
        return this.prowadzacy;
    }

    public List<Student> GetStudents() {
        return this.users;
    }

    public Student GetStudent(int id) {
        for (Student student : users) {
            if (student.id == id)
                return student;
        }
        return null;
    }

    public void DisplayInfo() {
        System.out.println("============================================");
        System.out.println(this.id + "\t" + this.nazwa + "\nProwadzacy:");
        this.DisplayProwadzacych();
        System.out.println("\nUczniowie");
        this.DisplayUczniow();
        System.out.println("============================================");
    }

    private void DisplayProwadzacych() {
        for (Nauczyciel prof : prowadzacy) {
            prof.Display();
        }
    }
}