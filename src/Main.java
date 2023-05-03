import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;


public class Main {
    public static void main(String[] args) throws URISyntaxException {
//        Path pathKeyIcon = Paths.get("lab7", "src", "main", "resources", "key.png").toAbsolutePath();
//        Path pathKeyIcon = Paths.get("key.png");
//        System.out.println(pathKeyIcon.toString());

        URL resource = Main.class.getClassLoader().getResource("key.png");
        File file = Paths.get(resource.toURI()).toFile();
        System.out.println(file.getAbsolutePath());
    }
}