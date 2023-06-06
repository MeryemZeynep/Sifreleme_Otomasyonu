import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class DESCozumleme extends Cozme {

    public DESCozumleme(String grDosya, String ckDosya, int key) {
        super(grDosya, ckDosya, key);
    }

    @Override
    public void cozumleme() throws IOException {
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
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            CipherInputStream cis = new CipherInputStream(fis, cipher);

            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = cis.read(buffer)) != -1) {
                fos.write(buffer, 0, bytesRead);
            }

            fos.close();
            cis.close();

            System.out.println("Dosya çözümleme işlemi tamamlandı.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
