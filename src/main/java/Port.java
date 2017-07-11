import java.util.ArrayList;
import java.util.List;

public class Port {
    private static final int DEFAULT_SIZE = 1000;

    private final int size;
    private final List<Pier> pierces = new ArrayList<>();
    private int count;
    private int PIERCES_COUNT = 10;

    public Port(int count) {
        checkBounds(count, DEFAULT_SIZE);

        this.size = DEFAULT_SIZE;
        this.count = count;

        initPierces();
    }

    public Port(int count, int size) {
        checkBounds(count, size);

        this.size = size;
        this.count = count;

        initPierces();
    }

    private void initPierces() {
        for (int i = 0; i < PIERCES_COUNT; i++) {
            pierces.add(new Pier());
        }
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

    public int getContainers() {
        return count;
    }

    public void setContainers(int count) {
        this.count = count;
    }

    public Pier getFreePierce() {
        for (int i = 0; i < pierces.size(); i++) {
            if (pierces.get(i).isFree()) {
                return pierces.get(i);
            }
        }
        return null;
    }
}
