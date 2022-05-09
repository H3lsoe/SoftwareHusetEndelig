/*
 *  Andreas Sjögren Fürst - s201189
 *  */

package Controllers;

import Exceptions.OperationNotAllowed;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import system.app.Activity;
import system.app.Employee;
import system.app.PMA;
import system.app.Project;

import java.io.File;
import java.io.IOException;
import java.net.URL;


public class MainScene  {
    public static String name;
    private Stage stage;
    private Scene scene;
    private Parent root;
    private Alert errorAlert = new Alert(Alert.AlertType.ERROR);
    public PMA pma;

    @FXML
    public TextField Initials_holder;
    public Button loginbutton;
    String user_initials;


    public void goToSystem(ActionEvent event) throws IOException, OperationNotAllowed {
        initEmployees();
        URL url = new File("src/test/resources/fxml/systemScene.fxml").toURI().toURL();
        Parent root = FXMLLoader.load(url);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        name = Initials_holder.getText();
        login();
    }

    public void login() throws IOException, OperationNotAllowed {

        if (Initials_holder.getText().length() <=4 && Initials_holder.getText().matches("^[a-zA-Z]*$") && pma.getEmployee(name) != null ){
            stage.setScene(scene);
            initSystem();
            stage.show();
        }  else if (!(Initials_holder.getText().length() <=4)){
            errorAlert.setContentText("Illegal input. Input Must be initials of four letters or less");
            errorAlert.showAndWait();
            throw new OperationNotAllowed("Illegal input. Input Must be initials of four letters or less");

        } else if (!(Initials_holder.getText().matches("^[a-zA-Z]*$"))){
            errorAlert.setContentText("Illegal character input. Must be alphabetic letters");
            errorAlert.showAndWait();
            throw new OperationNotAllowed("Illegal character input. Must be alphabetic letters");

        } else {
            errorAlert.setContentText("Illegal input. User does not exist");
            errorAlert.showAndWait();
            throw new OperationNotAllowed("Illegal input. User does not exist");
        }
    }

    public void initSystem() {
        String user_initials = Initials_holder.getText();
        systemScene.setInitials(user_initials);
    }


    public void initEmployees() throws OperationNotAllowed {
        pma = new PMA();
        Project project0 = new Project("Project0");
        Project project1 = new Project("Project1");
        Project project2 = new Project("Project2");
        pma.addProject(project0);
        pma.addProject(project1);
        pma.addProject(project2);
        Employee employee0 = new Employee("Hans");
        Employee employee1 = new Employee("Jens");
        Employee employee2 = new Employee("Sof");
        Employee employee3 = new Employee("Kurt");
        Employee employee4 = new Employee("Abdi");
        Employee employee5 = new Employee("Max");
        pma.addEmployee(employee0);
        pma.addEmployee(employee1);
        pma.addEmployee(employee2);
        pma.addEmployee(employee3);
        pma.addEmployee(employee4);
        pma.addEmployee(employee5);
        Activity activity0 = new Activity("Make Cards");
        Activity activity1 = new Activity("Make Diagrams");
        Activity activity2 = new Activity("Make CRC Cards");
        Activity activity3 = new Activity("Coding");
        Activity activity4 = new Activity("Sequence Diagrams");
        Activity activity5 = new Activity("Eat lunch");
        Activity activity6 = new Activity("Testing");
        Activity activity7 = new Activity("Take calls");
        Activity activity8 = new Activity("Answer Mails");
        activity0.staffActivity(employee1);
        activity1.staffActivity(employee2);
        activity2.staffActivity(employee3);
        activity3.staffActivity(employee4);
        activity4.staffActivity(employee5);
        activity5.staffActivity(employee1);
        activity6.staffActivity(employee2);
        activity7.staffActivity(employee1);
        activity8.staffActivity(employee2);
        pma.getProject("Project0").addActivity(activity0);
        pma.getEmployee(activity0.getEmployeeId()).projects.add(project0);

        pma.getProject("Project0").addActivity(activity1);
        pma.getEmployee(activity1.getEmployeeId()).projects.add(project0);

        pma.getProject("Project0").addActivity(activity3);
        pma.getEmployee(activity3.getEmployeeId()).projects.add(project0);

        pma.getProject("Project0").addActivity(activity4);
        pma.getEmployee(activity4.getEmployeeId()).projects.add(project0);

        pma.getProject("Project0").addActivity(activity2);
        pma.getEmployee(activity2.getEmployeeId()).projects.add(project0);

        pma.getProject("Project1").addActivity(activity8);
        pma.getEmployee(activity8.getEmployeeId()).projects.add(project1);

        pma.getProject("Project1").addActivity(activity0);
        pma.getEmployee(activity0.getEmployeeId()).projects.add(project1);

        pma.getProject("Project1").addActivity(activity1);
        pma.getEmployee(activity1.getEmployeeId()).projects.add(project1);

        pma.getProject("Project2").addActivity(activity5);
        pma.getEmployee(activity5.getEmployeeId()).projects.add(project2);

        pma.getProject("Project2").addActivity(activity6);
        pma.getEmployee(activity6.getEmployeeId()).projects.add(project2);

        pma.getProject("Project2").addActivity(activity1);
        pma.getEmployee(activity1.getEmployeeId()).projects.add(project2);

        pma.getEmployee(activity1.getEmployeeId()).projects.add(project1);

        pma.getProject("Project0").setProjectManager(employee1);
        pma.getProject("Project1").setProjectManager(employee1);
        pma.getProject("Project2").setProjectManager(employee2);

        systemScene.setPma(pma);
    }
}

