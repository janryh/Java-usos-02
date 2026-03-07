package logic;

import models.*;
import java.util.ArrayList;
import java.util.List;

/*
    Admin - DisplayDashboar
    moze swobodnie dodawac nauczycieli kursy i studentow
    oraz przypisywac ich do siebie

    !!  DODAC USUWANIE  !!
 */

public class Admin extends Czlowiek {
    private List<Student> studenci;
    private List<Nauczyciel> nauczyciele;
    private List<Kurs> kursy;
    public int id;

    public Admin(String imie, String nazwisko, String mail, int id) {
        super(imie, nazwisko, mail);
        this.id = id;
        this.studenci = new ArrayList<>();
        this.nauczyciele = new ArrayList<>();
        this.kursy = new ArrayList<>();
    }

    private Kurs GetKurs(int k) {
        for (Kurs kurs : this.kursy) {
            if (kurs.id == k)
                return kurs;
        }
        return null;
    }

    protected List<Nauczyciel> getNauczyciele() {
        return this.nauczyciele;
    }

    protected List<Student> getStudents() {
        return this.studenci;
    }

    protected List<Kurs> getCourses() {
        return this.kursy;
    }

    public Nauczyciel GetTeacher(int k) {
        for (Nauczyciel nauczyciel : this.nauczyciele) {
            if (nauczyciel.id == k)
                return nauczyciel;
        }
        return null;
    }

    public Student GetStudent(int k) {
        for (Student student : this.studenci) {
            if (student.id == k)
                return student;
        }
        return null;
    }

    public void AddKurs(Kurs k) {
        this.kursy.add(k);
    }

    public void AddTeacher(Nauczyciel t) {
        this.nauczyciele.add(t);
    }

    public void AddStudent(Student s) {
        this.studenci.add(s);
    }

    public void AssignStudent(int s_id, int k_id) {
        Student s = this.GetStudent(s_id);
        Kurs k = this.GetKurs(k_id);
        if (s == null) {
            System.out.println("Nie znaleziono studenta o indeksie: " + s_id);
        } else {
            if (k == null) {
                System.out.println("Nie znaleziono kursu - " + k_id);
            } else {
                k.AddUser(s);
                s.AddZajecia(k, this);
            }
        }
    }

    public void AssignTeacher(int t_id, int k_id) {
        Nauczyciel t = this.GetTeacher(t_id);
        Kurs k = this.GetKurs(k_id);
        if (t == null) {
            System.out.println("Nie znaleziono nauczyciela o indeksie: " + t_id);
        } else {
            if (k == null) {
                System.out.println("Nie znaleziono kursu - " + k_id);
            } else {
                k.AddProwadzacy(t);
                t.AddKurs(k, this);
            }
        }
    }

    public void AddMaterials(String s, int k_id) {
        Kurs k = this.GetKurs(k_id);
        if (k == null) {
            System.out.println("Nie znaleziono kursu - " + k_id);
        } else {
            k.AddPDF(s);
        }
    }

    public void DisplayAdminPanel() {
        System.out.println("========================================");
        System.out.println("Administrator -> " + super.GetInfo());
        System.out.println("\nNauczyciele:");
        for (Nauczyciel n : nauczyciele) {
            n.Display();
        }
        System.out.println("\nStudenci:");
        for (Student s : studenci) {
            s.Display();
        }
        System.out.println("\nKursy:");
        for (Kurs k : kursy) {
            System.out.println(k.id + "\t" + k.GetName());
        }
        System.out.println("========================================");
    }

}