import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class ShipTest {

    @Test
    public void testThatWeCanCreateEmptyShip() throws Exception {
        Ship ship = new Ship(990);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testThatSizeIsBiggerThanLimits() throws Exception {
        Ship ship = new Ship(1100, 1000);
    }

    @Test
    public void testThatLandWorksCorrect() throws Exception {
        Ship ship = new Ship(100, 1000);
        ship.land(new Port(200));
    }

    @Test
    public void testThatGetFromPortWorksProperly() throws Exception {
        Ship ship = new Ship(300);
        Port port = new Port(400);

        ship.land(port);
        ship.loadFromPort(200);

        assertThat(ship.getContainers(), is(500));
        assertThat(port.getContainers(), is(200));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testThatGetFromPortThrowsOnOverLimitsPort() throws Exception {
        Ship ship = new Ship(300);
        Port port = new Port(100);

        ship.land(port);
        ship.loadFromPort(200);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testThatGetFromPortThrowsOnOverLimitsShip() throws Exception {
        Ship ship = new Ship(900);
        Port port = new Port(300);

        ship.land(port);
        ship.loadFromPort(200);
    }
}