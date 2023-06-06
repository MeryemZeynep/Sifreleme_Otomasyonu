import java.io.*;

public class XORSifreleme extends Sifreleme {
    public XORSifreleme(String grDosya, String ckDosya, int key) {
        super(grDosya, ckDosya, key);
    }

    @Override
    public void sifreleme() throws IOException {
        try (InputStream in = new FileInputStream(grDosya);
             OutputStream out = new FileOutputStream(ckDosya)) {
            int read;
            while ((read = in.read()) != -1) {
                out.write(read ^ key);
            }
        }
        System.out.println("Dosya başarıyla şifrelendi.");
    }
}
