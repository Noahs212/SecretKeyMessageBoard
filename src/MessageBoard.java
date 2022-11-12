
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.control.CheckBox;
import javafx.scene.image.Image;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author Noah
 * @version 1
 * Creates a message board class.
 */
public class MessageBoard extends Application {
    private int chatcount = 0;
    @Override
    /**
     * starts a message board.
     */
    public void start(Stage primaryStage) throws FileNotFoundException {

        Font font = new Font("Verdana", 12);
        primaryStage.setTitle("Noah's CS1331 Message Board");

        //Stackpane for background and all other panes
        FileInputStream girrafe = new
                FileInputStream("C:\\Users\\nwsta\\OneDrive\\CS1331\\Homework4\\src\\girrafe.jpg");
        FileInputStream geometric = new
                FileInputStream("C:\\Users\\nwsta\\OneDrive\\CS1331\\Homework4\\src\\geometric.jpg.crdownload");
        Image image = new Image(geometric);
        Image girrafeimage = new Image(girrafe);
        ImageView imageview = new ImageView(image);
        ImageView girrafeimageview = new ImageView(girrafeimage);
        StackPane background = new StackPane();


        //VBOX pain that will have  Vbox on top slot, StackPane in mdi for error, and hbox on in bottom
        VBox mainpain = new VBox(5);
        HBox chatscontainer = new HBox(5);
        HBox controls = new HBox(5);
        StackPane error = new StackPane();
        background.getChildren().addAll(imageview, mainpain);
        //blank label for margin
        Label blank = new Label();
        blank.setPrefHeight(10);
        blank.setText("        ");
        mainpain.getChildren().addAll(blank, chatscontainer, error, controls);

        //Hbox and Vbox for language selection and checkboxes
        VBox chats = new VBox(10);
        VBox languages = new VBox(5);
        CheckBox mandarin = new CheckBox("Mandarin");
        mandarin.setFont(font);
        mandarin.setTextFill(Color.BLACK);
        CheckBox english = new CheckBox("English");
        english.setFont(font);
        english.setTextFill(Color.BLACK);
        english.setSelected(true);
        CheckBox german = new CheckBox("German");
        german.setFont(font);
        german.setTextFill(Color.BLACK);
        languages.getChildren().addAll(mandarin, english, german);
        //labels for chats
        Label[] chat = new Label[19];
        for (int i = 0; i < chat.length; i++) {
            chat[i] = new Label("                      "
                    +
                    "                            "
                    + "               ");
            chat[i].setFont(font);
            chat[i].setTextFill(Color.BLACK);
            chats.getChildren().add(chat[i]);
            chat[i].setMaxSize(300, 50);
            chat[i].setAlignment(Pos.CENTER_LEFT);
        }

        //blank label for horizontal margin
        Label hblank = new Label();
        hblank.setPrefWidth(10);
        chatscontainer.getChildren().addAll(hblank, chats, languages);

        //Scene for main chat
        Scene scene =  new Scene(background, 400, 600);
        primaryStage.setScene(scene);
        primaryStage.show();

        //Error Message
        Label errormsgLabel = new Label("No INPUT!");
        errormsgLabel.setFont(font);
        errormsgLabel.setTextFill(Color.BLACK);
        Rectangle errorblock = new Rectangle(400, 25);
        errorblock.setFill(Color.RED);

        //button and textfields for controls;
        Button post = new Button("Post");
        post.setFont(font);
        post.setTextFill(Color.BLACK);
        TextField inputfield = new TextField();
        inputfield.setPromptText("Message: ");
        inputfield.setFont(font);
        TextField nameinput = new TextField();
        nameinput.setPromptText("Name: ");
        nameinput.setFont(font);

        post.setOnAction(e -> {
            if (!inputfield.getText().equals("") && !nameinput.getText().equals("")) {
                chat[chatcount].setText(nameinput.getText() + ": " + inputfield.getText());
                if (inputfield.getText().equals("Giraffe") || inputfield.getText().equals("giraffe")) {
                    background.getChildren().remove(0);
                    background.getChildren().add(0, girrafeimageview);
                }
                chatcount++;
                inputfield.setText("");
                nameinput.setText("");
                error.getChildren().remove(errormsgLabel);
                error.getChildren().remove(errorblock);
            } else {
                error.getChildren().addAll(errorblock, errormsgLabel);
            }
            System.out.println("works");
        });
        controls.setAlignment(Pos.BOTTOM_CENTER);
        controls.getChildren().addAll(post, nameinput, inputfield);

        //event handlers for checkboxes
        german.setOnAction(e -> {
            mandarin.setSelected(false);
            english.setSelected(false);
            primaryStage.setTitle("Noah's CS1331 Nachrichtenforum");
            post.setText("Senden");
            nameinput.setPromptText("Name: ");
            inputfield.setPromptText("Botschaft: ");
            english.setText("Englisch");
            mandarin.setText("Chinesisch");
            german.setText("Deutsch");
        });

        mandarin.setOnAction(e -> {
            german.setSelected(false);
            english.setSelected(false);
            primaryStage.setTitle("诺亚的CS1331贴吧");
            post.setText("送了");
            nameinput.setPromptText("名字：");
            inputfield.setPromptText("信息：");
            english.setText("英语");
            mandarin.setText("中文");
            german.setText("德文");
        });

        english.setOnAction(e -> {
            mandarin.setSelected(false);
            german.setSelected(false);
            primaryStage.setTitle("Noah's CS1331 Message Board");
            post.setText("Post");
            nameinput.setPromptText("Name: ");
            inputfield.setPromptText("Message: ");
            english.setText("English");
            mandarin.setText("Mandarin");
            german.setText("German");
        });
    }

    /**
     *
     * @param args argument
     */
    public static void main(String[] args) {
        launch(args);
    }

}
