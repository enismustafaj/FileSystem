import java.util.List;

public interface Operations {
    public boolean delete(String path);
    public boolean rename(String newDir,String path);
    public void createNew(String path);
    public void move(String dest, String file);
}