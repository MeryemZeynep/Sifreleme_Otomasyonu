import java.io.IOException;

public abstract class Cozme {
    protected String grDosya;
    protected String ckDosya;
    protected int key;

    public Cozme(String grDosya, String ckDosya, int key) {
        this.grDosya = grDosya;
        this.ckDosya = ckDosya;
        this.key = key;
    }

    public abstract void cozumleme() throws IOException;
}
