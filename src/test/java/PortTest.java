import org.junit.Test;

public class PortTest {

    @Test
    public void testThatWeCanCreateEmptyPort() throws Exception {
        Port port = new Port(990);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testThatSizeIsBiggerThanLimits() throws Exception {
        Port port = new Port(1100, 1000);
    }

}