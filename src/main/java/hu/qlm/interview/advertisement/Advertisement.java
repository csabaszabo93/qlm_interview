package hu.qlm.interview.advertisement;

import java.util.Hashtable;

public interface Advertisement {
    // Maximális megjelenési szám.
    int getMaxAppearance();
    // Megjelenési súly.
    double getWeight();
    // Utolsó n napon a megjelenések szám.
    int lastAppearence(int dayIndex, int numberOfDays);
    // Reklám megjelennítése az adott napra
    void showAdvertisement();
    // A reklám összes megjelenése.
    // Hashtable<nap index, napi megjelenések száma>
    Hashtable<Integer,Integer> getAllAppearences();
}
