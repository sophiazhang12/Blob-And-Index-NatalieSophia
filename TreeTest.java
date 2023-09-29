import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TreeTest {
    static Tree tr;
    static Blob bob;
    //before stuff runs, it should create a new file and initialize the index and blob
        @BeforeAll
        static void setUpBeforeClass() throws Exception {
            
            File dir = new File ("objects");
            dir.mkdirs();
            File file_junit1 = new File ("objects/" + "junit_example.txt"); //file = file you write to
            file_junit1.createNewFile();

            TesterHelper.writeStringToFile("junit_example.txt", "test file contents");
            tr = new Tree ();
            bob = new Blob ("junit_example.txt");
    
            
        }

        //after file runs, it should delete the test files and test directories
    @AfterAll
    static void tearDownAfterClass() throws Exception {
        
        TesterHelper.deleteFile("junit_example.txt");
        TesterHelper.deleteFile("testFile");
        TesterHelper.deleteDirectory("objects");
         
    }

    //tests if file is being correctly added
    @Test
    void testAdd() throws IOException {
        
        tr.add ("junit_example.txt");

        try {
        //FancyTester.runTestSuiteMethods("testCreateBlob", myBlob);

        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }

        //check if the blob obj exists in the objects folder
        File file_junit1 = new File("objects/" + tr.shaPart("blob : cbaedccfded0c768295aae27c8e5b3a0025ef340 : junit_example.txt"));
        assertTrue("Blob file to add not found", file_junit1.exists()); 
        //if file does not exist, then "Blob file to add not found" is output

        // Read file contents
        String indexFileContents = TesterHelper.readAFileToAString(bob.getShaString());
        assertEquals("File contents of Blob don't match file contents pre-blob creation", indexFileContents,
                bob.getFileContents());
            //if the file contents of both tests don't match up, then the test will return "file contents of blob..."
    }

    //tests if can correctly get the name part from an entry...does not work???
    @Test
    void testNamePart() {
        String n = tr.namePart ("blob : cbaedccfded0c768295aae27c8e5b3a0025ef340 : junit_example.txt");
        String nn = "junit_example.txt";

        assertEquals("Name do not match up", n,
                nn);
                //if the two names are not equal, then somethign is wrong
    }

    //tests if the stuff is going into the objects folder correctly
    @Test
    void testPutInObjects() throws IOException {

        tr.putInObjects();
        
        // check if the file exists
        File file = new File("index");
        Path path = Paths.get("objects");

        assertTrue("File does not exist :(", file.exists());
        //if file does not exist, returns "file does not exist :("
        assertTrue("File path does not exist :(", Files.exists(path));
        //if the file path does not exist, return "file path does not exist :("
    }

    //tests if removal is successful
    @Test
    void testRemove() throws IOException {

        File f = new File ("junit_example.txt");

        tr.add ("blob : cbaedccfded0c768295aae27c8e5b3a0025ef340 : junit_example.txt");
        tr.remove ("junit_example.txt");

        assertTrue("Removal not successful", f.exists());

    }

    //tests if you can get the sha part of an entry; does not work?? bro this was working in my testerrrr
    @Test
    void testShaPart() {
        String sh = tr.shaPart ("blob : cbaedccfded0c768295aae27c8e5b3a0025ef340 : junit_example.txt");
        String shh = "cbaedccfded0c768295aae27c8e5b3a0025ef340";

        assertEquals("Sha1 values do not match up", sh,
                shh);
                //if the two sha values are not equal, then somethign is wrong

    }
}
