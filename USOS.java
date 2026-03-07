import logic.*;

public class USOS {
    public static void main(String[] args) {
        System.out.println("test");
        Admin adm = new Admin("Jan", "Nowak", "nowak@gmail.com", 0);
        FileHandler fileHandler = new FileHandler("logic/dane.txt");

        fileHandler.Wczytaj(adm);
        UI.startUi(adm, adm.GetStudent(0), adm.GetTeacher(0));
        fileHandler.Zapisz(adm);
    }
}
