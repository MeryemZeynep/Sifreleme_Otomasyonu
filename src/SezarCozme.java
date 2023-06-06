import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SezarCozme extends Cozme {


    public SezarCozme(String grDosya, String ckDosya, int key) {
        super(grDosya, ckDosya, key);
    }

    @Override
    public void cozumleme() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(grDosya));
        FileWriter writer = new FileWriter(ckDosya);
        int character;
        while ((character = reader.read()) != -1) {
            if (Character.isUpperCase(character)) {
                character = (character - key - 65 + 26) % 26 + 65;
            } else if (Character.isLowerCase(character)) {
                character = (character - key - 97 + 26) % 26 + 97;
            }
            writer.write(character);
        }
        reader.close();
        writer.close();
        System.out.println("Dosya başarıyla şifresi çözüldü.");
    }
}
