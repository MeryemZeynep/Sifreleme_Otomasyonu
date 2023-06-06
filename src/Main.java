import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    private static String girisDosyaAdi;
    private static String cikitiDosyaAdi;
    private static String sifrelemeSinifi;
    private static String selected = null;

    private static int key;
    static int choice;
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {


        System.out.println("Hoşgeldiniz! Lütfen yapmak istediğiniz işlemi seçiniz:");
        System.out.println("1 - Dosya Oku");
        System.out.println("2 - Dosya Şifrele");
        System.out.println("3 - Şifre Çöz");
        System.out.println("4 - Şifreleme Seç");
        System.out.println("5 - Çıkış");

        do {
            System.out.print("Seçiminiz: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> readFile();
                case 2 -> fileEncrypter();
                case 3 -> fileDecrypter();
                case 4 -> isSelected();
                case 5 -> System.out.println("Çıkış yapılıyor.");
                default -> System.out.println("Geçersiz seçim.");
            }
        } while (choice != 5);

    }

    private static void readFile() throws IOException {
        System.out.print("Dosya adı: ");
        girisDosyaAdi = scanner.next();
        FileReader reader = new FileReader(girisDosyaAdi);
        BufferedReader bufferedReader = new BufferedReader(reader);
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            System.out.println(line);
        }
        bufferedReader.close();
    }

    private static void fileEncrypter() throws IOException {
        System.out.print("Sifrelenek dosya adı: ");
        girisDosyaAdi = scanner.next();
        System.out.print("Şifreli dosya adı: ");
        cikitiDosyaAdi = scanner.next();
        System.out.print("Şifreleme anahtarı: ");
        key = scanner.nextInt();
        if (selected == null) {
            System.out.print("Şifreleme Sınfı(4A Sezar 4B XOR 4C DES): ");
            sifrelemeSinifi = scanner.next();
        } else {
            sifrelemeSinifi = selected;
        }
        switch (sifrelemeSinifi) {
            case "4A" -> {
                SezarSifreleme encrypter = new SezarSifreleme(girisDosyaAdi, cikitiDosyaAdi, key);
                encrypter.sifreleme();
            }
            case "4B" -> {
                XORSifreleme sifreleme = new XORSifreleme(girisDosyaAdi, cikitiDosyaAdi, key);
                sifreleme.sifreleme();
            }
            case "4C" -> {
                DESSifreleme sifreleme = new DESSifreleme(girisDosyaAdi, cikitiDosyaAdi, key);
                sifreleme.sifreleme();
            }
            default -> System.out.print("Geçersiz Şifreleme Sınfı");
        }
    }

    private static void isSelected() {
        System.out.print("Şifreleme Sınfı(4A Sezar 4B XOR 4C DES): ");
        selected = scanner.next();
    }

    private static void fileDecrypter() throws IOException {
        System.out.print("Çözümlenecek dosya adı: ");
        girisDosyaAdi = scanner.next();
        System.out.print("Çözülmüş dosya adı: ");
        cikitiDosyaAdi = scanner.next();
        System.out.print("Şifreleme anahtarı: ");
        key = scanner.nextInt();
        if (selected == null) {
            System.out.print("Şifreleme Sınfı(4A Sezar 4B XOR 4C DES): ");
            sifrelemeSinifi = scanner.next();
        } else {
            sifrelemeSinifi = selected;
        }
        switch (sifrelemeSinifi) {
            case "4A" -> {
                SezarCozme encrypter = new SezarCozme(girisDosyaAdi, cikitiDosyaAdi, key);
                encrypter.cozumleme();
            }
            case "4B" -> {
                XORCozumleme sifreleme = new XORCozumleme(girisDosyaAdi, cikitiDosyaAdi, key);
                sifreleme.cozumleme();
            }
            case "4C" -> {
                DESCozumleme sifreCozme = new DESCozumleme(girisDosyaAdi, cikitiDosyaAdi, key);
                sifreCozme.cozumleme();
            }
            default -> System.out.print("Geçersiz Şifreleme Sınfı");
        }
    }


}


