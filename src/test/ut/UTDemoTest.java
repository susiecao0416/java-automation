import org.junit.Before;
import org.junit.Test;
import org.testng.Assert;



public class UTDemoTest {

    UTDemo utDemo = new UTDemo();

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testAdd(){
        int y = utDemo.add(3, 4);
        Assert.assertEquals(y, 7);

    }


}