import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SezarSifreleme extends Sifreleme {

    public SezarSifreleme(String grDosya, String ckDosya, int key) {
        super(grDosya, ckDosya, key);
    }

    @Override
    public void sifreleme() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(grDosya));
        FileWriter writer = new FileWriter(ckDosya);
        int character;
        while ((character = reader.read()) != -1) {
            if (Character.isUpperCase(character)) {
                character = (character + key - 65) % 26 + 65;
            } else if (Character.isLowerCase(character)) {
                character = (character + key - 97) % 26 + 97;
            }
            writer.write(character);
        }
        reader.close();
        writer.close();
        System.out.println("Dosya başarıyla şifrelendi.");
    }
}
