package logic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import models.*;

/*
    FileHandler - Zapis i odczyt
    z "bazy danych" zeby nie dodawac co chwila
    dane txt w tym folderze logic/
*/

public class FileHandler {
    private String path;

    public FileHandler(String path) {
        this.path = path;
        try {
            File file = new File(this.path);
            if (file.createNewFile()) {
                System.out.println("Stworzono plik " + this.path);
            } else {
                System.out.println("Plik juz istnieje " + this.path);
            }
        } catch (IOException e) {
            System.out.println("Nie udalo sie zainicjlowac danych " + e.getMessage());
        }
    }

    private String infoAdjust(String getinfo) {
        String info = getinfo.replace("\t", " ").replace(",", "");
        return info.replace(" ", ";");
    }

    public void Zapisz(Admin admin) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(this.path))) {
            for (Student student : admin.getStudents()) {
                writer.println("S;" + infoAdjust(student.GetInfo()) + ";" + student.id);
            }
            for (Nauczyciel nauczyciel : admin.getNauczyciele()) {
                writer.println("N;" + infoAdjust(nauczyciel.GetInfo()));
            }
            for (Kurs kurs : admin.getCourses()) {
                writer.println("K;" + kurs.GetName().replace(" ", "_") + ";" + kurs.id);
                for (String document : kurs.GetMaterials()) {
                    writer.println("D;" + document);
                }
                for (Nauczyciel nauczyciel : kurs.GetProwadzacych()) {
                    writer.println("P;" + infoAdjust(nauczyciel.GetInfo()));
                }
                for (Student student : kurs.GetStudents()) {
                    writer.println("U;" + infoAdjust(student.GetInfo()) + ";" + student.id);
                }
            }

        } catch (IOException e) {
            System.out.println("Blad zapisu " + e.getMessage());
        }
    }

    /*
     * Jest niepotymalnie narazie bo jak chce
     * dodac jednego ucznia do kilku kursow
     * tworze kilka razy obiekt
     */

    public void Wczytaj(Admin admin) {
        File file = new File(this.path);
        if (!file.exists())
            return;
        Kurs currKurs = null;
        try (Scanner scan = new Scanner(file)) {
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] dane = line.split(";");
                switch (dane[0]) {
                    case "S":
                        admin.AddStudent(new Student(dane[1], dane[2], dane[3], Integer.parseInt(dane[4])));
                        break;
                    case "N":
                        admin.AddTeacher(new Nauczyciel(dane[1], dane[2], dane[3], dane[4], Integer.parseInt(dane[5])));
                        break;
                    case "K":
                        currKurs = new Kurs(dane[1], Integer.parseInt(dane[2]));
                        admin.AddKurs(currKurs);
                        break;
                    case "P":
                        if (currKurs != null) {
                            currKurs.AddProwadzacy(
                                    new Nauczyciel(dane[1], dane[2], dane[3], dane[4], Integer.parseInt(dane[5])));
                        }
                        break;
                    case "U":
                        if (currKurs != null) {
                            currKurs.AddUser(new Student(dane[1], dane[2], dane[3], Integer.parseInt(dane[4])));
                        }
                        break;
                    case "D":
                        if (currKurs != null) {
                            currKurs.AddPDF(dane[1]);
                        }
                        break;
                    default:
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println("Blad wczytywania " + e.getMessage());
        }
    }
}