/*
 * @project ${File System}
 * @author ${Enis Mustafaj}
 *
 */


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Directory implements Operations {

    LoggerSingleton lg;


    public Directory(String log) throws IOException {
        lg = LoggerSingleton.getInstance(log);
    }

    //iterate the directory and get the file names
    public static void search(File file, List<String> list) {
        for (File f : file.listFiles()) {
            list.add(f.getName());
        }

    }
    public int sizeDir(String path){
        List<String> list = new ArrayList<>();
        File source = new File(path);
        search(source, list);
        return list.size();

    }

    //find directory and print its content
    public List<String> showDirectory(String path) {
        File source = new File(path);
        List<String> list = new ArrayList<>();
        search(source, list);
        for (String s : list) {
            lg.myLogger.info(s);
        }
        return list;
    }

    @Override
    public boolean delete(String path) {
        File del_file = new File(path);
        //check if the directory exists or if root directory is being deleted
        if (del_file.exists() && !path.equals("C:\\Root")) {
            del_file.delete();
            lg.myLogger.config("Directory deleted");
            return true;
        } else {
            lg.myLogger.warning("Root directory cannot be deleted");
            return false;
        }
    }

    public String changeDirectory(String path) {
        File file = new File(path);
        if(file.exists()){
            lg.myLogger.info("Your path is: " + path);
            return path;
        }else{
            String sub = path.substring(path.lastIndexOf('\\'));
            path = path.replace(sub, "");
            lg.myLogger.info("Your path is:" + path);
            return path;
        }


    }

    @Override
    public void createNew(String path) {

        try {
            File f = new File(path);
            //directory cannot be created if a directory with the same name exists
            if (f.exists()) {
                lg.myLogger.warning("Dirctory already exists");
            } else {
                f.mkdir();
                lg.myLogger.config("Directory created");
            }
        } catch (Exception e) {

            lg.myLogger.severe(String.valueOf(e.getStackTrace()));
        }
    }

    public String getParentDirectory(String path) {
        String sub = path.substring(path.lastIndexOf('\\'));
        path = path.replace(sub, "");
        lg.myLogger.info("Your path is:" + path);
        return path;
    }
    //put directory into another directory
    @Override
    public void move(String dest, String dir) {

            File f = new File(dir);
            //create the destination path by adding the destination directory and file name
            String destination = "C:\\Root\\" + dest + "\\" + f.getName();


            List<String> list = new ArrayList<>();


            if (!new File(destination).exists() && new File(dir).exists()) {
                String sub = dir.substring(dir.lastIndexOf('\\'));
                dir = dir.replace(sub, "");
                    f.renameTo(new File(destination));
                    lg.myLogger.config("Directory moved");


            } else {
                lg.myLogger.warning("A directory with the same name already exists");

            }

    }

    @Override
    public boolean rename(String newName, String oldName) {

            File f = new File(oldName);
            File f2 = new File(newName);
            if (f.exists()&&f.renameTo(f2)) {
                lg.myLogger.config("Directory is renamed");
                return true;
            }else{
                lg.myLogger.warning("Directory with this name does not exist");
                return false;
            }

    }
    //move the content of source directory to destination directory without moving the source directory
    public List<String> moveContent(String sourceDir, String destDir) {
        File source = new File(sourceDir);
        List<File> list = new ArrayList<>();
        List<String> list2 = new ArrayList<>();
        search2(source, list);
        for (File s : list) {
            s.renameTo(new File("C:\\Root\\" + destDir + "\\" + s.getName()));
        }
        lg.myLogger.config("Files Moved");
        search(new File(sourceDir),list2);
        return list2;

    }

    public void search2(File file, List<File> list) {
        for (File f : file.listFiles()) {
            list.add(f);
        }

    }
}

