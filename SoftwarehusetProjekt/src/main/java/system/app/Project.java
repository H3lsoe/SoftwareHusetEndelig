/* Morten Linhardt Helsø - s214927
 *  Andreas Sjögren Fürst - s201189
 *  */

package system.app;
import Exceptions.OperationNotAllowed;

import java.util.ArrayList;

public class Project {
    private static int projectCounter = 0;
    String projectID;
    public String name;
    Employee projectManager;
    public ArrayList<Activity> activities;

    public Project(String name) {
        this.name = name;
        this.activities = new ArrayList<>();
        projectCounter++;
        this.projectID = "" + projectCounter;
    }

    public Employee getProjectManager() {
        return this.projectManager;
    }

    public void setProjectManager(Employee employee) throws OperationNotAllowed{
        if(this.projectManager != null) {
            throw new OperationNotAllowed("project already has a project manager");
        } else {
            this.projectManager = employee;
        }
    }

    public boolean hasProjectManager(){
        if(this.projectManager == null) return false;
        else return true;
    }

    public boolean isProjectManager(Employee employee) throws OperationNotAllowed {
        assert true: "precondition for isProjectManager";
        if(this.projectManager == null || employee != this.projectManager) {
            throw new OperationNotAllowed("Projectmanager is not registered");
        } else {
            assert !(this.projectManager == null || employee != this.projectManager): "postcondition for isProjectManager";
            return true;
        }
    }

    public boolean hasActivity(Activity activity) {
        return activities.contains(activity);
    }

    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    public boolean nameExistActivity(String name) {
        boolean holder = false;
        for(Activity activity: activities) {
            if(activity.name.equals(name)) {
                holder = true;
            }
        }
        return holder;
    }

    public void removeActivity(Activity activity) {
        activities.remove(activity);
    }

    public Activity getActivity(String name) {
        Activity activityHolder = null;
        for(Activity activity: activities) {
            if(activity.name.equals(name)) {
                activityHolder = activity;
            }
        }
        return activityHolder;
    }

    public boolean projectContainsActivities() throws OperationNotAllowed {
        assert true: "precondition for projectContainsActivities";
        if(this.activities.isEmpty()) {
            throw new OperationNotAllowed("No activities available for deleting");
        } else { assert !(this.activities.isEmpty()): "postcondition for projectContainsActivities";
            return false;
        }
    }



}