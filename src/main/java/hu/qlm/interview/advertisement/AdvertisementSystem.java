package hu.qlm.interview.advertisement;

import java.util.List;

public interface AdvertisementSystem {
    // Reklám regisztrálása.
    void registerAdvertisement(Advertisement ad);
    // Következı reklám megjelenítése a megadott napon.
    void showNextAdvertisement(int dayIndex);
    // Reklámok listája
    List<Advertisement> getAdvertisementList();
}
