/* Morten Linhardt Helsø - s214927
 *  Andreas Sjögren Fürst - s201189
 *  Oscar Wullf Mandrupsen - s216163
 *  Rasmus Søndergaard - s214925
 *  */

package dtu.softwarehuset;
import Exceptions.OperationNotAllowed;
import org.junit.Test;
import system.app.Activity;
import system.app.Employee;
import system.app.PMA;
import system.app.Project;

public class DesignByContractTest {
    PMA pma = new PMA();
    Project project = new Project("name");
    Activity activity = new Activity("Aktivitet");


    @Test
    public void existProjectNameTest(){
        pma.projects.add(project);
        pma.existProjectName(project.name);
    }
    @Test
    public void isProjectManagerTest() throws OperationNotAllowed {
        Employee employee1 = new Employee("hans");
        pma.addProject(project);
        pma.getProject("name").setProjectManager(employee1);
        pma.getProject("name").isProjectManager(employee1);
    }

    @Test
    public void availableEmployeesTest(){
        Employee employee2 = new Employee("tolk");
        pma.employees.add(employee2);
        pma.availableEmployees();
    }

    @Test
    public void projectContainsActivitiesTest() throws OperationNotAllowed {
        project.activities.add(activity);
        project.projectContainsActivities();
    }
}
