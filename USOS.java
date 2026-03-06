import logic.*;
import models.*;

public class USOS {
    static void addTestData(Admin a) { // Dodanie jakies wygenerowanych przez chata ludkow i relacje miedzy nimi
        // Kursy
        a.AddKurs(new Kurs("Fizyka dla opornych", 0));
        a.AddKurs(new Kurs("Informatyka zaawansowana", 1));
        a.AddKurs(new Kurs("Matematyka elementarna", 2));

        // Testowe dodanie uczniow
        a.AddStudent(new Student("Marek", "Aureliusz", "marek@rzym.it", 1));
        a.AddStudent(new Student("Oktawian", "August", "august@empire.com", 2));
        a.AddStudent(new Student("Tyberiusz", "Klaudiusz", "tybi@op.pl", 3));

        a.AddStudent(new Student("Robert", "Lewandowski", "rl9@kadra.pl", 4));
        a.AddStudent(new Student("Iga", "Swiatek", "iga@tenis.pl", 5));
        a.AddStudent(new Student("Jeremy", "Sochan", "sochan@nba.com", 6));

        a.AssignStudent(0, 0);
        a.AssignStudent(0, 2);

        a.AssignStudent(1, 0);
        a.AssignStudent(1, 1);

        a.AssignStudent(2, 0);
        a.AssignStudent(3, 2);

        // Add Nauczyciele

        a.AddTeacher(new Nauczyciel("Dawid", "Pasieka", "pasiekafiz@op.pl", "Profesor", 1));
        a.AddTeacher(new Nauczyciel("Adam", "Pitolejev", "adipol4444@op.pl", "Doktor Habilitowanyr", 2));

        a.AssignTeacher(0, 2);
        a.AssignTeacher(2, 2);
        a.AssignTeacher(1, 0);
        a.AssignTeacher(2, 0);

    }

    public static void main(String[] args) {
        System.out.println("test");
        Admin adm = new Admin("Jan", "Nowak", "nowak@gmail.com", 0);
        // test dla jednego ucznia zeby mozna bylo sprawdzic
        Nauczyciel teach = new Nauczyciel("Dariusz", "Kuterka", "kutr@wp.pl", "Magister", 0);
        Student stu = new Student("Kamil", "Plockowski", "mam@on.com", 0);
        adm.AddTeacher(teach);
        adm.AddStudent(stu);
        addTestData(adm);
        adm.AssignTeacher(0, 0);
        UI.startUi(adm, stu, teach);
    }
}
