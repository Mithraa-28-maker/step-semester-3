public class SkylineDeliveryFleet {

    static abstract class Drone {

        public abstract String fly();
    }

    interface Trackable {
        String getLocation();
    }

    static class DeliveryDrone extends Drone implements Trackable {

        private String id;

        public DeliveryDrone(String id) {
            this.id = id;
        }

        @Override
        public String fly() {
            return "Delivery drone " + id + " flying";
        }

        @Override
        public String getLocation() {
            return id + " at Sector 4";
        }
    }

    static class ScoutDrone extends Drone {

        private String id;

        public ScoutDrone(String id) {
            this.id = id;
        }

        @Override
        public String fly() {
            return "Scout drone " + id + " flying";
        }
    }

    static class GroundRobot implements Trackable {

        private String id;

        public GroundRobot(String id) {
            this.id = id;
        }

        @Override
        public String getLocation() {
            return id + " at Sector 4";
        }
    }

    static String getLocationIfTrackable(Object o) {

        if (o instanceof Trackable) {
            Trackable t = (Trackable) o;
            return t.getLocation();
        }

        return "Tracking not available";
    }

    public static void main(String[] args) {

        DeliveryDrone d = new DeliveryDrone("DR-1");
        ScoutDrone s = new ScoutDrone("SC-1");
        GroundRobot g = new GroundRobot("GR-1");

        System.out.println(getLocationIfTrackable(d));
        System.out.println(getLocationIfTrackable(s));
        System.out.println(getLocationIfTrackable(g));
    }
}