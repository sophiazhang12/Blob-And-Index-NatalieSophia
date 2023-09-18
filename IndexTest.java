import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.NoSuchAlgorithmException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class IndexTest {
    static Index blobIndex;
    static Blob bob;
        @BeforeAll
        static void setUpBeforeClass() throws Exception {
            
            TesterHelper.writeStringToFile("junit_example.txt", "test file contents");
            bob = new Blob ("junit_example.txt");
            blobIndex = new Index ();
            
            File dir = new File ("objects");
            dir.mkdirs();
            File file = new File ("objects/" + bob.getShaString()); //file = file you write to
            file.createNewFile();
            
            
        }

    @AfterAll
    static void tearDownAfterClass() throws Exception {
        
        TesterHelper.deleteFile("junit_example.txt");
        TesterHelper.deleteFile("index");
        TesterHelper.deleteDirectory("objects");
         
    }
    @Test
    void testAddBlob() throws IOException, NoSuchAlgorithmException {
        // Manually create the files and folders before the 'testAddFile'
        blobIndex.init();
        Index.addBlob ("junit_example.txt");

        try {
        //FancyTester.runTestSuiteMethods("testCreateBlob", myBlob);

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }

        //check if the blob obj exists in the objects folder
        File file_junit1 = new File("objects/" + bob.getShaString());
        assertTrue("Blob file to add not found", file_junit1.exists()); //if this test fails, then "Blob file to add not found" is output

        // Read file contents
        String indexFileContents = TesterHelper.readAFileToAString(bob.getShaString());
        assertEquals("File contents of Blob don't match file contents pre-blob creation", indexFileContents,
                bob.getFileContents());
    }

    @Test
    void testInit() throws IOException {
        blobIndex.init ();

        // check if the file exists
        File file = new File("index");
        Path path = Paths.get("objects");

        assertTrue("File does not exist :(", file.exists());
        assertTrue("File path does not exist :(", Files.exists(path));
    }

    @Test
    void testWriteHashMap() throws IOException {
        // Index ix = new Index();
        // File index = new File("index");
        // index.createNewFile();
        // ix.init ();
        // ix.writeHashMap ();

        // try {
        // File f = new File ("f");
        // PrintWriter pw = new PrintWriter ("f");
        

        // } catch (Exception e) {
        //     System.out.println("An error occurred: " + e.getMessage());
        // }

        // pw.close();
        
        // index.list ();

        // assertEquals("Map is incorrect", , myBlob.getFileContents());
    }

    @Test
    void testRemoveBlob() throws NoSuchAlgorithmException, IOException {
        blobIndex.init();
        blobIndex.removeBlob("junit_example.txt");

        try {
        //FancyTester.runTestSuiteMethods("testRemoveBlob", myBlob);

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }

        //check if the blob obj exists in the objects folder
        File file_junit1 = new File("objects/" + bob.getShaString());
        assertTrue("Blob is not being removed", file_junit1.exists());

        // Read file contents
        String indexFileContents = TesterHelper.readAFileToAString("objects/" + bob.getShaString());
        assertEquals("File contents of Blob don't match file contents pre-blob creation", indexFileContents,
                bob.getFileContents());
    }
}
