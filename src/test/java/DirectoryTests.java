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

public class DirectoryTests {
    Directory d ;

    String path = "C:\\Root" ;

    @Before
    public void getPath() throws IOException {


        File root = new File("C:\\Root" );
        if(!root.exists())
            root.mkdir();
        d = new Directory("logs\\directoryLog.log");


    }
    @Test
    public void directoryTest() throws IOException {
        d.createNew(this.path + "\\newfolder1");
        d.createNew(this.path + "\\newfolder2");
        assertEquals(2,d.sizeDir(this.path));
        List<String> list = new ArrayList<>();
        list.add("newfolder1");
        list.add("newfolder2");
        List<String> list2 =d.showDirectory(this.path);
        assertEquals(true, list.equals(list2));

        assertEquals(false,d.delete(this.path));
        assertEquals(true,d.delete(this.path + "\\newfolder1"));


        d.createNew(this.path + "\\newfolder3");
        List<String> list3 = new ArrayList<>();

        list3.add("newfolder2");
        list3.add("newfolder3");
        List<String> list4 =d.showDirectory(this.path);
        assertEquals(true, list3.equals(list4));
        assertEquals(2,d.sizeDir(this.path));


        assertEquals(this.path + "\\newfolder2",d.changeDirectory(this.path +"\\newfolder2"));
        assertEquals(this.path,d.changeDirectory(this.path + "\\randomfolder"));


        assertEquals(this.path,d.getParentDirectory(this.path + "\\newfolder3"));


        assertEquals(true,d.rename(this.path + "\\newfolder4", this.path + "\\newfolder3"));
        assertEquals(false,d.rename(this.path + "\\newfolder4",this.path + "\\newfolder33"));


        List<String> list5 = new ArrayList<>();
        list5.add("newfolder4");
        d.move("newfolder4",this.path + "\\newfolder2");
        List<String> l6 = d.showDirectory(this.path);
        assertEquals(true, list5.equals(l6));



        d.createNew(this.path + "\\newfolder5");
        d.createNew(this.path + "\\newfolder6");
        List<String> list8 = new ArrayList<>();
        list8 = d.moveContent(this.path,"newfolder4");
        List<String> list9 = new ArrayList<>();
        list9.add("newfolder4");
        assertEquals(true,list8.equals(list9));



    }


}
