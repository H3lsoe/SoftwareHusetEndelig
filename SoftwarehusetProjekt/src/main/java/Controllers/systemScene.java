/*
 *  Oscar Wullf Mandrupsen - s216163
 *  Rasmus Søndergaard - s214925
 *  */

package Controllers;

import Exceptions.OperationNotAllowed;
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


public class systemScene implements Initializable {

    public ListView<String> list_activity;
    public Label activityString;
    public Button projectActivities;
    public Button availableEmployees;
    public ListView<String> listAvailableEmployees;
    public Button add_Employee;
    public TextField add_Employee_holder;
    public TextField add_project_textfield;
    public TextField delete_project_name;
    private ListView<String> myListProject;
    private Alert errorAlert = new Alert(Alert.AlertType.ERROR);


    public static String initials;
    public Label user_label;
    public Button showInit;
    public static PMA pma;
    public String[] myProjectsName;
    public String[] myActivityName;
    public String[] myActivityName1;




    public static void setPma(PMA pma) {
        systemScene.pma = pma;
    }




    public systemScene() {
        if(systemScene.pma != null) {
            showName();
        }
    }

    public void showName(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                user_label.setText("User: " + MainScene.name);
            }
        });
    }

    public static void setInitials(String initials) {
        systemScene.initials = initials;
    }


    @FXML private ListView<String> list_project;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        myProjectsName = new String[pma.projects.size()];
        int i = 0;
        for(Project project: systemScene.pma.projects) {
            myProjectsName[i] = project.name;
            i++;
        }
        ObservableList<String> myProjects = FXCollections.observableArrayList(myProjectsName);
        list_project.setItems(myProjects);
        list_project.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);


    }


    public void showProjectActivities(ActionEvent event) throws IOException {
        String projects = list_project.getSelectionModel().getSelectedItems().get(0);
        ActivityScene.initActivityScene(pma, projects);
        URL url = new File("src/test/resources/fxml/ActivityScene.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    public void showMyProjects(ActionEvent event) throws IOException {
        myProjectScene.initMyProjectScene(pma, systemScene.initials);
        URL url1 = new File("src/test/resources/fxml/myProjectScene.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url1);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void add_Employee(ActionEvent actionEvent) throws OperationNotAllowed {
        if (pma.employeeExist(add_Employee_holder.getText())){
            errorAlert.setContentText("Employee already exist");
            errorAlert.showAndWait();
            throw new OperationNotAllowed("Employee already exist");
        }

        if (add_Employee_holder.getText().length() <= 4 &&
        add_Employee_holder.getText().length() > 0 && add_Employee_holder.getText().matches("^[a-zA-Z]*$")) {
            Employee employee = new Employee(add_Employee_holder.getText());
            pma.addEmployee(employee);
            add_Employee_holder.clear();

        } else if (!(add_Employee_holder.getText().length() <= 4)) {
            errorAlert.setContentText("Illegal input. Input Must be initials of four letters or less");
            errorAlert.showAndWait();
            throw new OperationNotAllowed("Illegal input. Input Must be initials of four letters or less");

        } else if (!(add_Employee_holder.getText().matches("^[a-zA-Z]*$"))) {
            errorAlert.setContentText("Illegal character input. Must be alphabetic letters");
            errorAlert.showAndWait();
            throw new OperationNotAllowed("Illegal character input. Must be alphabetic letters");

        } else if (add_Employee_holder.getText().isEmpty()) {
            errorAlert.setContentText("Field is Empty");
            errorAlert.showAndWait();
            throw new OperationNotAllowed("Field is Empty");

        } else {
            errorAlert.setContentText("Illegal input. User does not exist");
            errorAlert.showAndWait();
            throw new OperationNotAllowed("Illegal input. User does not exist");
        }
    }



    public void add_project_action(ActionEvent event) throws OperationNotAllowed {
        if (add_project_textfield.getText().isEmpty()){
            errorAlert.setContentText("Field is Empty");
            errorAlert.showAndWait();
            throw new OperationNotAllowed("Field is Empty");
        }
        if(!pma.existProjectName(add_project_textfield.getText())) {
            Project project = new Project(add_project_textfield.getText());
            pma.addProject(project);
            myProjectsName = new String[pma.projects.size()];
            int i = 0;

            for(Project projectname: systemScene.pma.projects) {
                myProjectsName[i] = projectname.name;
                i++;
            }
            ObservableList<String> myProjects = FXCollections.observableArrayList(myProjectsName);
            list_project.setItems(myProjects);
            list_project.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            add_project_textfield.clear();
        } else {
            errorAlert.setContentText("project already exists");
            errorAlert.showAndWait();
            throw new OperationNotAllowed("project already exists");
        }

    }

    public void Delete_project_action(ActionEvent event) throws OperationNotAllowed {
        if (delete_project_name.getText().isEmpty()){
            errorAlert.setContentText("Field is Empty");
            errorAlert.showAndWait();
            throw new OperationNotAllowed("Field is Empty");
        }
        String projectname = delete_project_name.getText();

        if(pma.getProject(projectname).getProjectManager() != pma.getEmployee(systemScene.initials)) {
            errorAlert.setContentText("projectmanager is not registered");
            errorAlert.showAndWait();
            throw new OperationNotAllowed("projectmanager is not registered");
        }


        try {
            pma.deleteProject(pma.getProject(projectname));
        } catch (OperationNotAllowed e) {
            errorAlert.setContentText(e.getMessage());
            errorAlert.showAndWait();
            throw new OperationNotAllowed(e.getMessage());
        }

        myProjectsName = new String[pma.projects.size()];
        int i = 0;

        for(Project projectName: systemScene.pma.projects) {
            myProjectsName[i] = projectName.name;
            i++;
        }
        ObservableList<String> myProjects = FXCollections.observableArrayList(myProjectsName);
        list_project.setItems(myProjects);
        list_project.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);


    }
}

