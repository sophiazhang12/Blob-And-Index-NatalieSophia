import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;

public class Tree {
    File t;

    public Tree ()
    {
        t = new File ("Tree");
    }

    public void add (String entry) throws FileNotFoundException
    {
        PrintWriter pw = new PrintWriter (t);
        pw.println(entry);
        pw.close();
        
        //natalie idk what these lines of code mean, i copied it from ur blob.java
        Files.move(Paths.get("/Users/zhang/Desktop/HonorsTopics/Blob-And-Index-NatalieSophia/" + entry),
           (Paths.get("/Users/zhang/Desktop/HonorsTopics/Blob-And-Index-NatalieSophia/objects/" + entry)));

        File dir = new File ("objects");
        dir.mkdirs();

        File actualFile = new File ("objects/" + sha1); //actualFile = file you write to
        actualFile.createNewFile();

    }

    //really slow way of doing things
    //basically copies the tree file into a stringbuilder, sans the line we want to remove
    //then deletes og tree file, writes stringbuilder value into new tree file
    public void remove (String sha) throws IOException
    {
        BufferedReader reader = new BufferedReader (new FileReader (t));
        StringBuilder sb = new StringBuilder ("");

        while (reader.ready())
        {
            String line = reader.readLine();
            if (!line.contains (sha))
            {
                sb.append (line + "\n");
            }
        }

        t.delete();
        
        t = new File ("Tree");

        PrintWriter pw = new PrintWriter (t);
        pw.print (sb);

        pw.close();
        reader.close();
    }

}
