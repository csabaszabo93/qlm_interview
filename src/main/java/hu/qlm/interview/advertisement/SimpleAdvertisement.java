package hu.qlm.interview.advertisement;

import java.util.Hashtable;
import java.util.stream.IntStream;

public class SimpleAdvertisement implements Advertisement {
    private final Hashtable<Integer, Integer> appearances = new Hashtable<>();
    private final int maxAppearance;
    private final double weight;
    private final String message;

    public SimpleAdvertisement(int maxAppearance, double weight, String message) {
        if (weight < 0 || weight > 1) {
            throw new IllegalArgumentException("Weight must be between 0 and 1");
        }
        if (maxAppearance < 0) {
            throw new IllegalArgumentException("max appearance can't be lower than 0");
        }
        this.maxAppearance = maxAppearance;
        this.weight = weight;
        this.message = message;
    }

    @Override
    public int getMaxAppearance() {
        return maxAppearance;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public int lastAppearence(int dayIndex, int numberOfDays) {
        return IntStream.range(dayIndex, dayIndex + numberOfDays)
                .map(idx -> appearances.getOrDefault(idx, 0))
                .sum();
    }

    @Override
    public void showAdvertisement() {
        System.out.println(message);
    }

    @Override
    public Hashtable<Integer, Integer> getAllAppearences() {
        return appearances;
    }
}
