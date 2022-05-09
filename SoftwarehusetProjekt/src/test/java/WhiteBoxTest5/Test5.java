package WhiteBoxTest5;
import java.util.ArrayList;
import org.junit.Test;
import static org.junit.Assert.*;

public class Test5 {
    @Test
    public void testDataSetA() throws Exception{
        Project project1 = new Project();
        try{
            project1.projectContainsActivities();
        } catch (Exception e){
            assertEquals(e.getMessage(), "No activities available for deleting");
        }
    }

    @Test
    public void testDataSetB() throws Exception {
        Project project2 = new Project();
        project2.activities.add(new Activity());
        assertFalse(project2.projectContainsActivities());
    }

}
class Activity { Activity() {} }
class Project {
    ArrayList<Activity> activities = new ArrayList<>();
    Project() {}

    public boolean projectContainsActivities() throws Exception {
        if(this.activities.isEmpty()) {                                   // 1
            throw new Exception("No activities available for deleting");  // 1.a
        } else {                                                          // 2
            return false;                                                 // 2.a
        }
    }
}

