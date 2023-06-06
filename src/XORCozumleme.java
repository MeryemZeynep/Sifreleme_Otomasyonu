import java.io.*;

public class XORCozumleme extends Cozme {
    public XORCozumleme(String grDosya, String ckDosya, int key) {
        super(grDosya, ckDosya, key);
    }

    @Override
    public void cozumleme() throws IOException {
        try (InputStream in = new FileInputStream(grDosya);
             OutputStream out = new FileOutputStream(ckDosya)) {
            int read;
            while ((read = in.read()) != -1) {
                out.write(read ^ key);
            }
        }
        System.out.println("Dosyanın şifresi başarıyla çözüldü.");
    }
}
