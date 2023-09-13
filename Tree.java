import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Tree {
    ArrayList <String> t; //list of all the entries of the tree

    public Tree ()
    {
        t = new ArrayList <String> ();
    }

    //gets the sha part out of a tree entry
    public String shaPart (String entry)
    {
        int i = entry.indexOf (":") + 2; //index where the sha1 part starts
        String sha1 = entry.substring (i); //sha1 to the end
        if (sha1.indexOf (":") != -1) //if there's another part after the sha1
        {
            int b = sha1.indexOf (":") - 1; //this will be the end of the sha1 (not before the : and the space)
            sha1 = sha1.substring (0,b);
        }

        return sha1;
    }

    //gets the fileName part of the entry; if a tree (no fileName), returns empty
    public String namePart (String entry)
    {
        int i = entry.indexOf (":") + 2; //index where the sha1 part starts
        String sha1 = entry.substring (i); //sha1 to the end
    
        if (sha1.indexOf (":") != -1) //if there's another part after the sha1
        {
            i = sha1.indexOf (":") + 2; //where the fileName part starts
            String n = sha1.substring (i); //just the fileName part
            return n;
        }

        return "";
    }

    public void add (String entry) throws IOException
    {
        t.add (entry);

        File treeFile = new File ("TreeIndex"); //actualFile = file you write to
        treeFile.createNewFile();

        //print entry into the tree
        PrintWriter pw = new PrintWriter (treeFile);
        pw.println(t); //prints out everything in arrayList to the tree index
        pw.close();
    }

    //really slow way of doing things
    //basically copies the tree file into a stringbuilder, sans the line we want to remove
    //then deletes og tree file, writes stringbuilder value into new tree file
    public void remove (String str) throws Exception
    {
        int length = t.size();
        for (int i = 0; i < length; i++)
        {
            String shPart = shaPart (t.get(i));
            String nPart = namePart (t.get(i));
            if (shPart.equals (str))
            {
                //then it is a tree, because that confirms the input was a sha1 value
                t.remove (t.get(i));
            }
            if (nPart.equals(str))
            {
                //then it is a blob, because that confirms that the input was a fileName
                t.remove (t.get(i));
            } 
            
        }
        PrintWriter pw = new PrintWriter ("TreeIndex");
        pw.println (t);
        pw.close();

    }

}
