package classes;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Elem {
    ImageView imageView;
    boolean status;
    int i, j;
    Elem(Image image, int i, int j){
        this.imageView = new ImageView(image);
        this.status = false;
        this.i = i;
        this.j = j;
    }
}
