/*
 * @project ${File System}
 * @author ${Enis Mustafaj}
 *
 */

import java.io.*;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Scanner;
import java.util.logging.*;

public class Files implements Operations {
    LoggerSingleton lg;
    static Scanner input;


    public Files(String log) throws IOException {
        lg = LoggerSingleton.getInstance(log);
    }

    public static void search(File file, List<String> list) {
        for (File f : file.listFiles()) {
            list.add(f.getName());
        }

    }
    @Override
    public void createNew(String newfile) {
        Formatter file;
        try {
            file = new Formatter(newfile);
            lg.myLogger.log(Level.CONFIG, "File is created");
            file.close();
        } catch (Exception e) {
            lg.myLogger.severe(String.valueOf(e.getStackTrace()));
        }
    }


    public List<String> readFile(String file) throws FileNotFoundException {

        List<String> list = new ArrayList<>();

            if(new File(file).exists()) {

                    input = new Scanner(new File(file));
                    //read the file line by line
                    while (input.hasNextLine()) {
                        String line = input.nextLine();
                        lg.myLogger.info(line);
                        list.add(line);
                    }
                    input.close();
                    return list;

                }
                else {
                lg.myLogger.warning("File does not exist");
                return list;
            }

                }






    //write to a file
    public void writeFile(String fileName, String text) {
        Formatter x;
        try {
            x = new Formatter(fileName);
            x.format("%s", text);
            x.close();
            lg.myLogger.config("Text is written to file");

        } catch (Exception e) {
            lg.myLogger.severe(String.valueOf(e.getStackTrace()));
        }

    }


    @Override
    public boolean rename(String newFile, String oldFile) {
        File f = new File(oldFile);
        File f2 = new File(newFile);
        if (f.exists()&& !f2.exists()) {
            f.renameTo(f2);
            lg.myLogger.config("File is renamed");
            return true;
        }else{
            lg.myLogger.warning("File with this name does not exist");
            return false;
        }
    }

    //append a text to a file
    public void appendToFile(String file, String text) {
        try {
            OutputStream os = new FileOutputStream(new File(file), true);
            os.write(text.getBytes(), 0, text.length());
            os.close();
            lg.myLogger.config("Text appended");
        } catch (Exception e) {
            lg.myLogger.severe(String.valueOf(e.getStackTrace()));
        }
    }


    @Override
    public void move(String dest, String file) {

        try {
            File sourceFile = new File(file);
            if(sourceFile.exists() && new File("C:\\Root\\" + dest).exists()) {
                File ff = new File("C:\\Root\\" + dest + "\\" + sourceFile.getName());
                ff.delete();
                if (sourceFile.renameTo(new File("C:\\Root\\" + dest + "\\" + sourceFile.getName()))) {
                    System.out.println("File moved");
                }
            }
        }catch(Exception e){
            String a = e.getStackTrace().toString();

        }



    }

    @Override
    public boolean delete(String path) {
        File del_file = new File(path);
        if (del_file.exists()) {
            del_file.delete();
            lg.myLogger.config("File deleted");
            return true;
        } else {
            lg.myLogger.warning("File does not exists");
            return false;
        }
    }
}
