import java.util.Random;

public class Main {

    private static int THREAD_COUNT = 10;

    public static void main(String[] args) {
        Port port = new Port(1000, 5000);

        for (int i = 0; i < THREAD_COUNT; i++) {
            new SingleShipLander(port).start();
        }
    }

    static class SingleShipLander extends Thread {

        private final static int DEFAULT_LOAD_SIZE = 10;

        private final Port port;
        private final Random random;

        public SingleShipLander(Port port) {
            this.port = port;
            this.random = new Random();
        }

        @Override
        public void run() {
            while (true) {
                Ship ship = new Ship(random.nextInt(100), 1000);

                boolean haveLanded = ship.land(port);

                if (haveLanded) {
                    try {
                        if (random.nextInt(1000) % 2 == 0) {
                            ship.loadFromPort(DEFAULT_LOAD_SIZE);
                        } else {
                            ship.giveToPort(DEFAULT_LOAD_SIZE);
                        }
                    } catch (IndexOutOfBoundsException e) {
                        System.out.println(ship + " have loading troubles");
                    } finally {
                        ship.unland();
                    }
                } else {
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
