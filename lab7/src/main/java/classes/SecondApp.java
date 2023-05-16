package classes;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;


public class SecondApp extends Application {
    public int count = 0;


    public static void clear(Elem[][] elemMas, Image image){
        for(Elem[] elems : elemMas){
            for(Elem elem : elems){
                elem.imageView.setImage(image);
                elem.status = false;
            }
        }
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
        Answer answer = new Answer(array, "Кто-то ещё");
        answers[0] = answer;

        array = new ArrayList<>();
        array.add(0);
        array.add(0);
        Collections.sort(array);
        answer = new Answer(array, "Валерий");
        answers[1] = answer;

        // creating the pane containing a graphic key
        BorderPane borderPane = new BorderPane();
        Path pathKeyIcon = Paths.get("orange_box.jpg");
        Path pathWhiteBoxIcon= Paths.get("white_box.png");
        Image imageKey = new Image(pathKeyIcon.toString());
        Image imageWhiteBox = new Image(pathWhiteBoxIcon.toString());

        ArrayList<Integer> arrayList = new ArrayList<>();
        Elem[][] elemMas = new Elem[10][10];
        for(int i = 0; i < elemMas.length; i++){
            for(int j = 0; j < elemMas.length; j++){

                Elem elem = new Elem(imageWhiteBox, i, j);
                EventHandler eventHandler = event -> {
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
                };
                elem.imageView.setX(i * 40+2);
                elem.imageView.setY(j * 40+2);
                elem.imageView.setOnMouseClicked(eventHandler);
                elemMas[i][j] = elem;
                borderPane.getChildren().add(elem.imageView);
            }
        }

        Button buttonRefresh = new Button("Очистить");
        buttonRefresh.setPrefWidth(150);
        buttonRefresh.setPrefHeight(150);
        buttonRefresh.setOnAction(actionEvent -> {
            clear(elemMas, imageWhiteBox);
            arrayList.clear();
        });

        Text name = new Text("Имя: ");
        Font font = new Font(18);
        name.setFont(font);

        TextField textField = new TextField();
        textField.setMaxWidth(100);

        Button buttonSubmit = new Button("Подтвердить");
        buttonSubmit.setOnAction(actionEvent -> {
            Answer answer1 = new Answer(arrayList, textField.getText());
            boolean check = false;
            for (Answer i : answers) {
                if(i.arrayList.equals(answer1.arrayList) && i.str.equals(answer1.str)){
                    check = true;
                    Text text = new Text("Авторизация прошла успешно!");
                    BorderPane borderPane1 = new BorderPane();
                    borderPane1.setCenter(text);
                    Scene scene = new Scene(borderPane1, 800, 600);
                    stage.setScene(scene);
                    break;
                }
            }
            if (!check) {
                Alert alert1 = new Alert(Alert.AlertType.WARNING, "Введены неверные данные, попыток осталось: " + (2 - count));
                alert1.showAndWait();
                count++;
            }
            if (count == 3) {
                Alert alert2 = new Alert(Alert.AlertType.INFORMATION, "У вас закончились попытки. Пожалуйста, подождите 3 секунды.");
                alert2.showAndWait().ifPresent(response -> {
                    if (response == ButtonType.OK) {
                        alert2.hide();
                    }
                });
                try {
                    count = 0;
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                Alert alert3 = new Alert(Alert.AlertType.INFORMATION, "Вы можете попытаться авторизоваться снова.");
                alert3.showAndWait();
            }
        });

        // creating a menu bar
        MenuBar menuBar = new MenuBar();
        Menu menu1 = new Menu("Приложение");
        Menu menu2 = new Menu("Помощь");
        MenuItem menuItem = new MenuItem("Выйти");
        menuItem.setOnAction(x -> {
            System.exit(11);
        });
        menu1.getItems().add(menuItem);
        menuBar.getMenus().add(menu1);
        menuBar.getMenus().add(menu2);

        // creating the left pane
        GridPane gridPane = new GridPane();
        // (node, columnIndex, rowIndex, colspan, rowspan)
        gridPane.add(name, 0, 0 );
        gridPane.add(textField, 1, 0);
        gridPane.add(buttonSubmit, 1, 1);
        VBox leftPane = new VBox(gridPane, borderPane);
        leftPane.setSpacing(40);
        leftPane.setPadding(new Insets(20));
        leftPane.setAlignment(Pos.TOP_CENTER);

        // creating the right pane
        VBox rightPane = new VBox(buttonRefresh);
        rightPane.setPadding(new Insets(10));
        rightPane.setAlignment(Pos.CENTER);

        BorderPane mainPane = new BorderPane();
        mainPane.setTop(menuBar);
        mainPane.setLeft(leftPane);
        mainPane.setRight(rightPane);
//        mainPane.setBottom(buttonRefresh);

        Scene scene = new Scene(mainPane, 1000, 1000);
        stage.setTitle("Графический ключ");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) { launch(); }
}