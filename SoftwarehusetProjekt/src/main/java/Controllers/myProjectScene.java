/*

 *  Rasmus Søndergaard - s214925
 *  */


package Controllers;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import system.app.Employee;
import system.app.PMA;
import system.app.Project;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class myProjectScene implements Initializable {
    private static Project project;
    private static Employee employee;
    public static PMA pma;
    public static String projectname;
    public static String employeeId;
    public Button myProjectActivities;
    public String[] myProjectsName;
    public Label user_label;


    public static void initMyProjectScene(PMA pma, String employeeId) {
        myProjectScene.pma = pma;
        myProjectScene.employeeId = employeeId;
    }



    public void showName(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                user_label.setText("User: " + MainScene.name);
            }
        });
    }

    @FXML
    private ListView<String> list_myproject;

    public String[] getEmployeeProjects(Employee employee) {
        String[] projectsName = new String[employee.projects.size()];
        int i = 0;
        for(Project project: employee.projects) {
                projectsName[i] = project.name;
                i++;
        }
        return projectsName;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Employee employee = myProjectScene.pma.getEmployee(myProjectScene.employeeId);

        showName();
        ObservableList<String> myProjects = FXCollections.observableArrayList(getEmployeeProjects(employee));
        list_myproject.setItems(myProjects);
        list_myproject.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    //metoden for når man trykker på tilbage button
    public void back_action(ActionEvent event) throws IOException {
        systemScene.setPma(pma);
        URL url = new File("src/test/resources/fxml/systemScene.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }


    public void showMyProjectActivities(ActionEvent event) throws IOException {
        String projectName = list_myproject.getSelectionModel().getSelectedItems().get(0);
        myActivityScene.myInitActivityScene(myProjectScene.pma,projectName,employeeId);
        URL url = new File("src/test/resources/fxml/MyActivityScene.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
