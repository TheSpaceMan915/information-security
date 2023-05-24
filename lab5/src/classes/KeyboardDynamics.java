package classes;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class KeyboardDynamics {
    private final int wordLength = 5;
    private final long[] arrInputTimes = new long[wordLength];
    private final List<String> listWords = new ArrayList<>();


    public void calculateInputTimes() {
        Scanner scanner = new Scanner(System.in);
        StringBuilder builder = new StringBuilder(wordLength);

        // calculating input times
        for (int i = 0; i < arrInputTimes.length; i++) {
            long startTime = System.nanoTime();
            String line = scanner.nextLine();
            long endTime = System.nanoTime();
            long time = endTime - startTime;
            arrInputTimes[i] = time;
            builder.append(line);
        }

        // saving the entered word
        listWords.add(builder.toString());
        builder.setLength(0);
    }

    public double calculateIdealTime() {
        long sum = 0;
        for (int i = 0; i < arrInputTimes.length; i++) {
            sum += arrInputTimes[i];
        }

        double idealTime = (double) sum / wordLength;
        return idealTime;
    }

    public double calculateDeviation(double idealTime) {
        double sum = 0.0;
        for (int i = 0; i < arrInputTimes.length; i++) {
            double difference = arrInputTimes[i] - idealTime;
            sum += difference;
        }

        double deviation = sum / wordLength;
        return deviation;
    }

    public static void authenticateUser(UserDynamics firstDynamics, UserDynamics secondDynamics) {
        final double eps = 2.5;
        if ((firstDynamics.idealTime() - secondDynamics.idealTime()) < eps) {
            if ((firstDynamics.deviation() - secondDynamics.deviation()) < eps) {
                System.out.println("You have been successfully authenticated");
            } else {
                System.out.println("The time difference > eps");
                System.out.println("Authentication has been failed");
            }
        } else {
            System.out.println("The deviation difference > eps");
            System.out.println("Authentication has been failed");
        }
    }

    public void printListWords() {
        System.out.println("entered words: " + listWords);
        System.out.println();
    }

    public static void main(String[] args) {
        // the first try to get keystroke dynamics
        KeyboardDynamics dynamics = new KeyboardDynamics();
        System.out.println("Enter a word:");
        dynamics.calculateInputTimes();
        double firstIdealTime = dynamics.calculateIdealTime();
        double firstDeviation = dynamics.calculateDeviation(firstIdealTime);
        UserDynamics firstDynamics = new UserDynamics(firstIdealTime, firstDeviation);
        System.out.println(firstDynamics);
        dynamics.printListWords();

        // the second try to get keystroke dynamics
        System.out.println("Enter a word:");
        dynamics.calculateInputTimes();
        double secondIdealTime = dynamics.calculateIdealTime();
        double secondDeviation = dynamics.calculateDeviation(secondIdealTime);
        UserDynamics secondDynamics = new UserDynamics(secondIdealTime, secondDeviation);
        System.out.println(secondDynamics);
        dynamics.printListWords();

        KeyboardDynamics.authenticateUser(firstDynamics, secondDynamics);
    }
}