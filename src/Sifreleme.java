import java.io.IOException;

public abstract class Sifreleme {
    protected String grDosya;
    protected String ckDosya;
    protected int key;

    public Sifreleme(String grDosya, String ckDosya, int key) {
        this.grDosya = grDosya;
        this.ckDosya = ckDosya;
        this.key = key;
    }

    public abstract void sifreleme() throws IOException;
}
