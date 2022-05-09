import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        try {
            URL url = new File("src/test/resources/fxml/MainScene.fxml").toURI().toURL();
            Parent root = FXMLLoader.load(url);
            Scene scene1 = new Scene(root);
            primaryStage.setScene(scene1);
            primaryStage.show();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
