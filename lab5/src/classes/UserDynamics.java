package classes;


public record UserDynamics(double idealTime, double deviation) {
    @Override
    public String toString() {
        return "ideal time: " + idealTime + ", deviation: " + deviation;
//        return String.format("ideal time: %.3f, deviation: %.3f", idealTime, deviation);
    }
}
