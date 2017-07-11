import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Ship {
    private static final int DEFAULT_SIZE = 1000;

    private int size = DEFAULT_SIZE;
    private int count;
    private Port port;
    private Pier pier;

    private Lock lock = new ReentrantLock();

    public Ship(int count) {
        checkBounds(count, size);

        this.count = count;
    }

    public Ship(int count, int size) {
        checkBounds(count, size);

        this.size = size;
        this.count = count;
    }

    public void checkBounds(int count, int size) {
        if (count > size || count < 0) {
            throw new IndexOutOfBoundsException();
        }
    }

    public void checkBounds(int count) {
        if (count > size || count < 0) {
            throw new IndexOutOfBoundsException();
        }
    }

    public boolean land(Port port) {
        lock.lock();
        pier = port.getFreePierce();
        if (pier != null) {
            pier.land(this);
            this.port = port;
            System.out.println(this + " have landed to " + pier);
            lock.unlock();
            return true;
        } else {
            lock.unlock();
            return false;
        }
    }

    public int getContainers() {
        return count;
    }

    public void loadFromPort(int countToGetFromPort) {
        lock.lock();
        int portContainers = port.getContainers() - countToGetFromPort;
        port.checkBounds(portContainers);
        port.setContainers(portContainers);
        System.out.println(this + " have loaded " + countToGetFromPort + " conts from port [" +port.getContainers()+"]");
        lock.unlock();

        int shipContainers = this.count + countToGetFromPort;
        this.checkBounds(shipContainers);
        this.count = shipContainers;
    }

    public void giveToPort(int countToGiveToPort) {
        lock.lock();
        int portContainers = port.getContainers() + countToGiveToPort;
        port.checkBounds(portContainers);
        port.setContainers(portContainers);
        System.out.println(this + "have get " + countToGiveToPort + " conts from port [" +port.getContainers()+"]");
        lock.unlock();

        int shipContainers = this.count - countToGiveToPort;
        this.checkBounds(shipContainers);
        this.count = shipContainers;
    }

    public void unland() {
        pier.unland();
        port = null;
    }
}
