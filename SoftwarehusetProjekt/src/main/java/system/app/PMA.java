/* Morten Linhardt Helsø - s214927
 *  Andreas Sjögren Fürst - s201189
 *  Oscar Wullf Mandrupsen - s216163
 *  Rasmus Søndergaard - s214925
 *  */

package system.app;

import Exceptions.OperationNotAllowed;

import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class PMA {
    public ArrayList<Employee> employees;
    public ArrayList<Project> projects;

    public PMA() {
        projects = new ArrayList<Project>();
        employees = new ArrayList<Employee>();
    }

    public boolean employeeExist(String employeeId) {
        boolean holdername = false;
        for(Employee employee: employees) {
            if(employee.employeeId.equals(employeeId)) {
                holdername = true;
            }
        }
        return holdername;
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public void addProject(Project project) {
        projects.add(project);
    }

    public void deleteProject(Project project) throws OperationNotAllowed{

        if(!projects.contains(project)) {
            throw new OperationNotAllowed("project doesn't exist");
        } else {
            projects.remove(project);
        }
    }

    public boolean existProject(Project project) {
        return projects.contains(project);
    }


    public Project getProject(String name) {
        Project projectholder = null;
        for (Project project : projects) {
            if (project.name.equals(name)) {
                projectholder = project;
            }
        }
        return projectholder;
    }

    public boolean existProjectName(String projectname) {
        assert true: "precondition existProjectName";
        boolean holdername = false;
        for (Project project : projects) {
            if (project.name.equals(projectname)) {
                assert project.name.equals(projectname): "postcondition existProjectName";
                holdername = true;

            }}
        return holdername;
    }



    public ArrayList<Employee> availableEmployees() {
        ArrayList<Employee> availableEmployees = new ArrayList<>();

        assert availableEmployees.isEmpty(): "precondition for availableEmployees";
        for(Employee employee : employees) {
            if(employee.EmployeeAvailable()){ availableEmployees.add(employee);}
            assert availableEmployees.contains(employee): "postcondition for availableEmployees";
        }
        return availableEmployees;
    }


    public Employee getEmployee(String employee_id) {
        Employee holderEmployee = null;
        for(Employee employee: employees) {
            if(employee.employeeId.equals(employee_id)) {
                holderEmployee = employee;
            }}
        return holderEmployee;
    }

    public boolean isEmployeeAvailable(String name) throws OperationNotAllowed {
        for(Employee employee: employees) {
            if(employee.employeeId.equals(name)) {
                return employee.EmployeeAvailable();
            }}throw new OperationNotAllowed("Employee is not available");}
}