package logic;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

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
                System.out.println("Plik w juz istnieje " + this.path);
            }
        } catch (IOException e) {
            System.out.println("Nie udalo sie zainicjlowac danych " + e);
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

    public void Wczytaj(Admin admin) {

    }
}