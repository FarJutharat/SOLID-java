import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;

class Journal{
    private java.util.ArrayList<String> entries =  new ArrayList<>();
    private static int count = 0;

    public void addEntry(String text){
        count++;
        entries.add(count + " : " + text);
    }

    public void removeEntry(int index){
        
        entries.remove(index);
    }
    @Override
    public String toString(){
        return String.join(System.lineSeparator(), entries); //แปลง array list เป็น string ตัวเดียว
    }

    
}

class Persistance {
    public void saveToFile (String filename, boolean overwrite) throws FileNotFoundException {
        if (overwrite || new File(filename).exists()){
            try (PrintStream out = new PrintStream(filename))
                {
                out.println(toString());
                }
            }
        }
}

public class SOLID_S {
    public static void main (String[] args) throws Exception{
        Journal j = new Journal();
        j.addEntry("I cried today");
        j.addEntry("I ate bug");
        System.out.println(j);
        Persistance p = new Persistance();
        String filename = "journal.txt";
        p.saveToFile(filename,true);
        Runtime.getRuntime().exec("notepad.exe " + filename);

        
    }
}

