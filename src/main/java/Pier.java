public class Pier {
    private Ship ship;

    public boolean isFree() {
        return ship == null;
    }

    public boolean land(Ship ship) {
        if (isFree()) {
            this.ship = ship;
            return true;
        }
        return false;
    }

    public void unland() {
        ship = null;
    }
}
