import logic.*;
import models.*;
import java.util.Scanner;

/*
    pp user interface

    Admin:
    - Dashboard
    - Dodawanie nauczycieli/uczniow/kursow
    - Prypisywanie do kursow

    Student:
    - Oceny
    - Zajecia, szczegoly do zajec

    Teacher:
    - Wglad do kursow
    - Wystawianie ocen studentom
*/

class UI {
    private static Scanner scan = new Scanner(System.in);

    private static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void startUi(Admin a, Student s, Nauczyciel t) {
        loginChoice(a, s, t);
    }

    private static void loginChoice(Admin a, Student s, Nauczyciel t) {
        boolean start = true;
        while (start) {
            clearScreen();
            System.out.println("1)\tLog as admin");
            System.out.println("2)\tLog as student");
            System.out.println("3)\tLog as teacher");
            switch (scan.nextInt()) {
                case 0: // quit
                    start = false;
                    break;
                case 1:
                    asAdmin(a);
                    break;
                case 2:
                    asStudent(s);
                    break;
                case 3:
                    asTeacher(t);
                    break;
                default:
                    break;
            }
        }
    }

    private static void asAdmin(Admin adm) {
        boolean a = true;
        while (a) {
            clearScreen();
            System.out.println("1)\tView dashboard");
            System.out.println("2)\tAdd teacher/student/course");
            System.out.println("3)\tAssign teacher/student/documents to course");
            switch (scan.nextInt()) {
                case 0: // back
                    a = false;
                    break;
                case 1:
                    adm.DisplayAdminPanel();
                    scan.nextLine();
                    scan.nextLine();

                    break;
                case 2:
                    System.out.println("\n1)\tAdd teacher [imie nazwisko mail tytul id]");
                    System.out.println("2)\tAdd student [imie nazwisko mail id]");
                    System.out.println("3)\tAdd course  [nazwa id]");
                    System.out.println("\nWpisz dane");
                    switch (scan.nextInt()) {
                        case 1:
                            adm.AddTeacher(
                                    new Nauczyciel(scan.next(), scan.next(), scan.next(), scan.next(), scan.nextInt()));
                            break;
                        case 2:
                            adm.AddStudent(new Student(scan.next(), scan.next(), scan.next(), scan.nextInt()));
                            break;
                        case 3:
                            adm.AddKurs(new Kurs(scan.next(), scan.nextInt()));
                            break;
                        default:
                            break;
                    }
                    break;
                case 3:
                    adm.DisplayAdminPanel();
                    System.out.println("\n1)\tAssign teacher   [id_teacher id_kurs]");
                    System.out.println("2)\tAssign student   [id_student id_kurs]");
                    System.out.println("3)\tAssign documents [dokument id_kurs]");
                    switch (scan.nextInt()) {
                        case 1:
                            adm.AssignTeacher(scan.nextInt(), scan.nextInt());
                            break;
                        case 2:
                            adm.AssignStudent(scan.nextInt(), scan.nextInt());
                            break;
                        case 3:
                            adm.AddMaterials(scan.next(), scan.nextInt());
                            break;
                        default:
                            break;
                    }
                    break;
                default:
                    a = false;
            }
        }
    }

    private static void asStudent(Student student) {
        boolean s = true;
        while (s) {
            clearScreen();
            student.Display();
            System.out.println("1)\tSprawdz kursy");
            System.out.println("2)\tSprawdz oceny");
            switch (scan.nextInt()) {
                case 0: // back
                    s = false;
                    break;
                case 1:
                    student.DisplayZajecia();
                    System.out.println("\tRozwin szczegoly kursu [id kursu]\n0)\tIgnore");
                    int x = scan.nextInt();
                    while (x != 0) {
                        student.CourseViewDetails(x);
                        System.out.println(")\tRozwin szczegoly kursu [id kursu]\n0)\tIgnore");
                        x = scan.nextInt();
                    }
                    break;
                case 2:
                    student.DisplayOceny();
                    scan.nextLine();
                    scan.nextLine();
                    break;
                default:
                    break;
            }
        }
    }

    private static void asTeacher(Nauczyciel teacher) {
        boolean t = true;
        while (t) {
            clearScreen();
            teacher.Display();
            teacher.DisplayKursy();
            System.out.println(")\tWybierz kurs [id kursu]\n0)\tBack");
            int x = scan.nextInt();
            if (x != 0) {
                teacher.CourseViewDetails(x);
                System.out.println(")\tWystaw ocene [ocena (double), student id, kurs id]\n0)\tIgnore");
                Double y = scan.nextDouble();
                if (y > 0) {
                    if (teacher.WystawOcene(y, scan.nextInt(), scan.nextInt())) {
                        System.out.println("Pomyslnie wystawiono ocene");
                    } else {
                        System.out.println("Nie udalo sie wystawic oceny");
                    }
                }
            } else {
                t = false;
            }
            scan.nextLine();
            scan.nextLine();
        }
    }

}
