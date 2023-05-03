package classes;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;


public class AppSecond extends Application {
    public int count = 0;


    public static void clear(Elem[][] elemMas, Image image){
        for(Elem[] elems : elemMas){
            for(Elem elem : elems){
                elem.imageView.setImage(image);
                elem.status = false;
            }
        }
    }

    public static void printArray(ArrayList<Integer> arrays){
        for(int i : arrays){
            System.out.print(i + " ");
        }
        System.out.println();
    }

    @Override
    public void start(Stage stage) {

        Answer[] answers = new Answer[2];
        ArrayList<Integer> array = new ArrayList<>();
        array.add(0);
        array.add(0);
        array.add(0);
        array.add(1);
        array.add(1);
        array.add(2);
        Collections.sort(array);
        Answer answer = new Answer(array, "Michael");
        answers[0] = answer;

        array = new ArrayList<>();
        array.add(0);
        array.add(0);
        Collections.sort(array);
        answer = new Answer(array, "User75");
        answers[1] = answer;





        BorderPane borderPane = new BorderPane();

        Path pathKeyIcon = Paths.get("key.png");
        Path pathWhiteBoxIcon= Paths.get("white_box.png");
        Image imageKey = new Image(pathKeyIcon.toString());
        Image imageWhiteBox = new Image(pathWhiteBoxIcon.toString());

        ArrayList<Integer> arrayList = new ArrayList<>();
        Elem[][] elemMas = new Elem[10][10];
        for(int i = 0; i < elemMas.length; i++){
            for(int j = 0; j < elemMas.length; j++){

                Elem elem = new Elem(imageWhiteBox, i, j);
                EventHandler eventHandler = new EventHandler() {
                    @Override
                    public void handle(Event event) {
                        if(!elem.status){
                            elem.imageView.setImage(imageKey);
                            elem.status = true;
                            arrayList.add(elem.i);
                            arrayList.add(elem.j);
                        } else {
                            elem.imageView.setImage(imageWhiteBox);
                            elem.status = false;
                            arrayList.remove(elem.i);
                            arrayList.remove(elem.j);
                        }
                        Collections.sort(arrayList);
                    }
                };
                elem.imageView.setX(i * 40+2);
                elem.imageView.setY(j * 40+2);
                elem.imageView.setOnMouseClicked(eventHandler);
                elemMas[i][j] = elem;
                borderPane.getChildren().add(elem.imageView);
            }
        }

        Button clear = new Button("Clear");
        clear.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                clear(elemMas, imageWhiteBox);
                arrayList.clear();
            }
        });
        clear.setTranslateX(-225);
        clear.setTranslateY(15);

        borderPane.setTranslateX(750);
        borderPane.setTranslateY(75);



        Text name = new Text("Login:");
        name.setTranslateX(45);
        name.setTranslateY(47.5);
        Font font = new Font(18);
        name.setFont(font);

        TextField textField = new TextField();
        textField.setMaxWidth(150);
        textField.setTranslateX(50);
        textField.setTranslateY(50);

        Button buttonEnter = new Button("Enter");
        buttonEnter.setOnAction(actionEvent -> {
            Answer answer1 = new Answer(arrayList, textField.getText());
            boolean check = false;
            for(Answer i : answers){
                if(i.arrayList.equals(answer1.arrayList) && i.str.equals(answer1.str)){
                    check = true;
                    Text text = new Text("Success!");
                    BorderPane borderPane1 = new BorderPane();
                    borderPane1.setCenter(text);
                    Scene scene = new Scene(borderPane1, 800, 600);
                    stage.setScene(scene);
                    System.out.println("Ура");
                    break;
                }
            }
            if(!check){
                System.out.println("Неправильно, осталось попыток: " + (3 - count));
                count++;
            }
            if(count == 3){
                System.out.println("Попытки закончились, ждите 5 сек");
                try {
                    count = 0;
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("Можете снова попытаться взломать нас");
            }
        });

        buttonEnter.setTranslateX(50);
        buttonEnter.setTranslateY(55);

        BorderPane leftPane = new BorderPane();
        leftPane.setLeft(name);
        leftPane.setRight(textField);
        leftPane.setBottom(buttonEnter);

        leftPane.setTranslateX(100);
        leftPane.setTranslateY(150);

        // creating a menu bar
        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("App");
        MenuItem menuItem = new MenuItem("Exit");
        menuItem.setOnAction(x -> {
            System.exit(1);
        });

        menu.getItems().add(menuItem);
        menuBar.getMenus().add(menu);

        BorderPane borderPane1 = new BorderPane();
        // borderPane1.setStyle("-fx-background-color: silver");
        borderPane1.getChildren().add(borderPane);
        borderPane1.setTop(menuBar);
        borderPane1.setRight(clear);
        borderPane1.getChildren().add(leftPane);

        Scene scene = new Scene(borderPane1, 1200, 600);
        stage.setTitle("Graphic Key");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}