/*
 * @project ${File System}
 * @author ${Enis Mustafaj}
 *
 */

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class IntegrationTest {
    Directory d;
    Files f;
    String path = "C:\\Root" ;
    @Before
    public void getPath() throws IOException {


        File root = new File("C:\\Root");
        if (!root.exists())
            root.mkdir();
        d = new Directory("logs\\IntegrationLog.log");
        f = new Files("logs\\IntegrationLog.log");


    }

    @Test
    public void IntegrationDFTest() throws IOException {

        d.createNew(this.path+"\\newfolder");
        f.createNew(this.path + "\\newfile.txt");
        f.createNew(this.path + "\\newfile2.txt");
        f.createNew(this.path + "\\newfile8.txt");
        d.createNew(this.path + "\\newfolder2");
        List<String> directoryList = d.showDirectory("C:\\Root");
        List<String> checkList = new ArrayList<>();

        checkList.add("newfile.txt");
        checkList.add("newfile2.txt");
        checkList.add("newfile8.txt");
        checkList.add("newfolder");
        checkList.add("newfolder2");


        assertEquals(true, directoryList.equals(checkList));


        f.writeFile(this.path + "\\newfile.txt","this is a text");
        List<String> readList  = f.readFile(this.path + "\\newfile.txt");
        List<String> checkRead = new ArrayList<>();
        checkRead.add("this is a text");
        assertEquals(true,readList.equals(checkRead) );


        assertEquals(false,f.rename(this.path+"\\newfile4.txt",this.path + "\\newfile3.txt"));
        assertEquals(true,f.rename(this.path+"\\newfile4.txt",this.path + "\\newfile2.txt"));


        f.appendToFile(this.path + "\\newfile.txt"," appended text");
        List<String> l5;
        l5 = f.readFile(this.path + "\\newfile.txt");
        checkRead.clear();
        checkRead.add("this is a text appended text");
        assertEquals(true,l5.equals(checkRead));


        List<String> moveList;
        f.move("newfolder", this.path +"\\newfile8.txt");
        moveList = d.showDirectory(this.path);
        List <String> checkMove = new ArrayList<>();
        checkMove.add("newfile.txt");
        checkMove.add("newfile4.txt");
        checkMove.add("newfolder");
        checkMove.add("newfolder2");
        assertEquals(true,moveList.equals(checkMove));


        assertEquals(true,f.delete(this.path + "\\newfile.txt"));
        assertEquals(false,f.delete(this.path + "\\newfile9.txt"));


        List<String > contentList = d.moveContent(this.path,"newfolder2");
        List<String> checkContent = new ArrayList<>();
        checkContent.add("newfolder2");
        assertEquals(true,checkContent.equals(contentList));


    }


}
