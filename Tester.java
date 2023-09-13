import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;

public class Tester {
    public static void main (String [] args) throws FileNotFoundException, NoSuchAlgorithmException, IOException {
        Index index = new Index ();
        index.init();
        Index.addBlob("example1");
        Index.addBlob("example2");
        Index.addBlob("example3");
        Index.addBlob("example.txt");
        Index.addBlob("example.txt");
        Index.removeBlob("example1");
    }
}
