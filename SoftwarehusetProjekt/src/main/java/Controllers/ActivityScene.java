/* Morten Linhardt Helsø - s214927
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
import system.app.*;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ActivityScene implements Initializable {

    private static Project project;
    public Label user_label;
    public String[] myActivityNames;
    public Label project_name;
    public TitledPane Activity_box_name;
    public Label employee_name;
    public static PMA pma;
    public static String projectname;
    public TextField employye_add;
    public Button Done_button;
    public TextField name_activity_add;
    public Button availableEmployees;
    public ListView listAvailableEmployees;
    public TextField projectManagerName;
    public TextField edit_activity_name;
    public TextField edit_activity_new_name;
    public TextField edit_activity_old_name;
    public TextField edit_activity_employee_activity_name;
    public TextField edit_activity_employee_new_employee;
    public TextField staff_activity_employee;
    public TextField staff_activity_name;

    public Label projectManager_name_show;
    private Alert errorAlert = new Alert(Alert.AlertType.ERROR);


    public static void initActivityScene(PMA pma, String projectname) {
        ActivityScene.pma = pma;
        ActivityScene.projectname = projectname;
    }

    public void showName() {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                user_label.setText("User: " + MainScene.name);
                projectManager_name_show.setText("ProjectManager: " + pma.getProject(projectname).getProjectManager().employeeId);
            }
        });
    }


    public ActivityScene() {
        ActivityScene.project = ActivityScene.pma.getProject(projectname);
        myActivityNames = new String[ActivityScene.project.activities.size()];
        int i = 0;
        for (Activity activity : ActivityScene.project.activities) {
            myActivityNames[i] = activity.getName();
            i++;
        }


    }


    @FXML
    private ListView<String> list_activity;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Initialiser Projectname
        project_name.setText("ProjectName: " + ActivityScene.project.name);

        //starter med at gøre activitybox empty
        Activity_box_name.setVisible(false);

        showName();
        //gør de tre bokse og knap usynlige til at starte med
        employye_add.setVisible(false);
        Done_button.setVisible(false);
        name_activity_add.setVisible(false);


        //Initialiser viewList
        ObservableList<String> myProjects = FXCollections.observableArrayList(myActivityNames);
        list_activity.setItems(myProjects);
        list_activity.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

    }

    public void initActivity_box_name() {
        //set boksens navn og gør den synlig
        String activityName = list_activity.getSelectionModel().getSelectedItems().get(0);
        Activity_box_name.setText(activityName);
        Activity_box_name.setVisible(true);

        //initialiser for employee
        if (!project.getActivity(activityName).isActivityStaffed()) {
            employee_name.setText("Employee: None");
        } else {
            employee_name.setText("Employee: " + project.getActivity(activityName).getEmployeeId());
        }

    }


    public void show_button_action(ActionEvent event) {
        initActivity_box_name();

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

    public void Add_activity_action(ActionEvent event) throws OperationNotAllowed {
        employye_add.setVisible(true);
        Done_button.setVisible(true);
        name_activity_add.setVisible(true);
    }


    public Activity createActivty() throws OperationNotAllowed {
        if (name_activity_add.getText().isEmpty()){
            errorAlert.setContentText("Field is Empty");
            errorAlert.showAndWait();
            throw new OperationNotAllowed("Field is Empty");
        }
        //get values from boksene
        String nameActivity = name_activity_add.getText();
        String employeeId = employye_add.getText();


        if(!employeeId.equals("")) {
            try {
                pma.isEmployeeAvailable(employeeId);
            } catch (Exception e) {
                errorAlert.setContentText(e.getMessage());
                errorAlert.showAndWait();
                throw new OperationNotAllowed(e.getMessage());
            }
        }



        //create activity
        Activity activity = new Activity(nameActivity);
        if(!employeeId.equals("")) {
            for (Employee employee : pma.employees) {
                if (employee.employeeId.equals(employeeId)) {
                    activity.staffActivity(employee);
                    break;
                }
            }
        }
        return activity;
    }

    public void Done_action(ActionEvent event) throws OperationNotAllowed {
        Employee employee = pma.getEmployee(MainScene.name);

        if (pma.getProject(ActivityScene.projectname).nameExistActivity(name_activity_add.getText())) {
            errorAlert.setContentText("Activity already exists");
            errorAlert.showAndWait();
            throw new OperationNotAllowed("Activity already exists");

        }
        try {
            pma.getProject(ActivityScene.projectname).isProjectManager(employee);
        } catch (Exception e) {
            errorAlert.setContentText(e.getMessage());
            errorAlert.showAndWait();
            throw new OperationNotAllowed(e.getMessage());
        }


        pma.getProject(ActivityScene.projectname).addActivity(createActivty());
        ActivityScene.project = ActivityScene.pma.getProject(projectname);


        //fjerner tekst fra boksene
        name_activity_add.clear();
        employye_add.clear();


        myActivityNames = new String[ActivityScene.project.activities.size()];
        int i = 0;
        for (Activity activity : ActivityScene.project.activities) {
            myActivityNames[i] = activity.getName();
            i++;
        }

        ObservableList<String> myProjects = FXCollections.observableArrayList(myActivityNames);
        list_activity.setItems(myProjects);
        list_activity.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

    }

    public void delete_activity_action(ActionEvent event) throws OperationNotAllowed {
        Employee employee = pma.getEmployee(MainScene.name);
        try {
            pma.getProject(ActivityScene.projectname).isProjectManager(employee);
        } catch (Exception e) {
            errorAlert.setContentText(e.getMessage());
            errorAlert.showAndWait();
            throw new OperationNotAllowed(e.getMessage());
        }


        try {
            pma.getProject(projectname).projectContainsActivities();
        } catch (OperationNotAllowed e) {
            errorAlert.setContentText(e.getMessage());
            errorAlert.showAndWait();
            throw new OperationNotAllowed(e.getMessage());
        }

        String activityName = list_activity.getSelectionModel().getSelectedItems().get(0);
        Activity removedActivity = ActivityScene.pma.getProject(ActivityScene.projectname).getActivity(activityName);
        ActivityScene.pma.getProject(ActivityScene.projectname).removeActivity(removedActivity);


        myActivityNames = new String[ActivityScene.project.activities.size()];
        int i = 0;
        for (Activity activity : ActivityScene.project.activities) {
            myActivityNames[i] = activity.getName();
            i++;
        }

        ObservableList<String> myProjects = FXCollections.observableArrayList(myActivityNames);
        list_activity.setItems(myProjects);
        list_activity.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }



    public String[] getAvailableEmployees(PMA pma) {
        String[] availableEmployees = new String[pma.availableEmployees().size()];
        int i = 0;
        for(Employee employee : pma.employees) {
            if(employee.EmployeeAvailable()) {
                availableEmployees[i] = employee.employeeId;
            }
            i++;
        }
        return availableEmployees;
    }

    public void showAvailableEmployees(ActionEvent actionEvent) throws OperationNotAllowed {
        try {
            pma.getProject(projectname).isProjectManager(pma.getEmployee(MainScene.name));
            ObservableList<String> employees = FXCollections.observableArrayList(getAvailableEmployees(pma));
            listAvailableEmployees.setItems(employees);
            listAvailableEmployees.setVisible(true);
        } catch (Exception e) {
            errorAlert.setContentText(e.getMessage());
            errorAlert.showAndWait();
            throw new OperationNotAllowed(e.getMessage());
            }
        }

    public void setProjectManager(ActionEvent event) throws OperationNotAllowed {
        Employee employee = pma.getEmployee(projectManagerName.getText());
        try {
            pma.getProject(projectname).setProjectManager(employee);
            projectManagerName.clear();
            projectManager_name_show.setText("ProjectManager: " + pma.getProject(projectname).getProjectManager().employeeId);
        } catch (OperationNotAllowed e) {
            errorAlert.setContentText(e.getMessage());
            errorAlert.showAndWait();
            throw new OperationNotAllowed(e.getMessage());
        }

    }

    public void edit_activity_name_action(ActionEvent event) throws OperationNotAllowed {
        Employee employee = pma.getEmployee(MainScene.name);
        try {
            pma.getProject(projectname).isProjectManager(employee);
        } catch (Exception e) {
            errorAlert.setContentText(e.getMessage());
            errorAlert.showAndWait();
            throw new OperationNotAllowed(e.getMessage());
        }

        pma.getProject(projectname).getActivity(edit_activity_old_name.getText()).setName(edit_activity_new_name.getText());

        myActivityNames = new String[pma.getProject(projectname).activities.size()];
        int i = 0;
        for (Activity activity : pma.getProject(projectname).activities) {
            myActivityNames[i] = activity.getName();
            i++;
        }
        ObservableList<String> myProjects = FXCollections.observableArrayList(myActivityNames);
        list_activity.setItems(myProjects);
        list_activity.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    public void edit_activity_employee_action(ActionEvent event) throws OperationNotAllowed {
        Employee employee = pma.getEmployee(MainScene.name);
        try {
            pma.getProject(projectname).isProjectManager(employee);
        } catch (Exception e) {
            errorAlert.setContentText(e.getMessage());
            errorAlert.showAndWait();
            throw new OperationNotAllowed(e.getMessage());
        }

        Employee new_employee = pma.getEmployee(edit_activity_employee_new_employee.getText());
        pma.getProject(projectname).getActivity(edit_activity_employee_activity_name.getText()).staffActivity(new_employee);

        myActivityNames = new String[pma.getProject(projectname).activities.size()];
        int i = 0;
        for (Activity activity : pma.getProject(projectname).activities) {
            myActivityNames[i] = activity.getName();
            i++;
        }
        ObservableList<String> myProjects = FXCollections.observableArrayList(myActivityNames);
        list_activity.setItems(myProjects);
        list_activity.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

    }

    public void staff_employee_action(ActionEvent event) throws OperationNotAllowed {
        Employee employee = pma.getEmployee(MainScene.name);
        try {
            pma.getProject(projectname).isProjectManager(employee);
        } catch (Exception e) {
            errorAlert.setContentText(e.getMessage());
            errorAlert.showAndWait();
            throw new OperationNotAllowed(e.getMessage());
        }
        Employee employee1 = pma.getEmployee(staff_activity_employee.getText());

        if(pma.getProject(projectname).getActivity(staff_activity_name.getText()).isActivityStaffed()) {
            errorAlert.setContentText("Activity is already staffed");
            errorAlert.showAndWait();
            throw new OperationNotAllowed("Activity is already staffed");
        }

        pma.getProject(projectname).getActivity(staff_activity_name.getText()).staffActivity(employee1);

    }
}
