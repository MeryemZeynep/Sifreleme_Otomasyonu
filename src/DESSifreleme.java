import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class DESSifreleme extends Sifreleme{
    public DESSifreleme(String grDosya, String ckDosya, int key) {
        super(grDosya, ckDosya, key);
    }

    @Override
    public void sifreleme() throws IOException {
        try {
            FileInputStream fis = new FileInputStream(grDosya);
            FileOutputStream fos = new FileOutputStream(ckDosya);

            byte[] keyBytes = Integer.toString(key).getBytes("UTF-8");
            byte[] fixedKey = new byte[8];
            System.arraycopy(keyBytes, 0, fixedKey, 0, Math.min(keyBytes.length, 8));

            DESKeySpec desKeySpec = new DESKeySpec(fixedKey);
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey secretKey = keyFactory.generateSecret(desKeySpec);

            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            CipherOutputStream cos = new CipherOutputStream(fos, cipher);

            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = fis.read(buffer)) != -1) {
                cos.write(buffer, 0, bytesRead);
            }

            cos.close();
            fis.close();

            System.out.println("Dosya şifreleme işlemi tamamlandı.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
