package hu.qlm.interview.advertisement;

import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

class SimpleAdvertisementSystemTest {
    private final SimpleAdvertisementSystem system = new SimpleAdvertisementSystem();

    @Test
    void testWithSingleAd() {
        SimpleAdvertisement ad = new SimpleAdvertisement(1, 0, "1");
        SimpleAdvertisement adSpy = spy(ad);
        system.registerAdvertisement(adSpy);
        system.showNextAdvertisement(0);
        system.showNextAdvertisement(0);
        system.showNextAdvertisement(1);
        verify(adSpy).showAdvertisement();
    }

    @Test
    void testWithTwoAds() {
        SimpleAdvertisement ad = new SimpleAdvertisement(8, 0.1, "1");
        SimpleAdvertisement ad2 = new SimpleAdvertisement(8, 0.2, "2");
        SimpleAdvertisement ad3 = new SimpleAdvertisement(8, 0.21, "3");
        SimpleAdvertisement adSpy = spy(ad);
        SimpleAdvertisement adSpy2 = spy(ad2);
        system.registerAdvertisement(adSpy);
        system.registerAdvertisement(adSpy2);
        system.registerAdvertisement(ad3);
        system.showNextAdvertisement(0);
        system.showNextAdvertisement(0);
        system.showNextAdvertisement(0);
        system.showNextAdvertisement(0);
        system.showNextAdvertisement(0);
        system.showNextAdvertisement(0);
        system.showNextAdvertisement(0);
        system.showNextAdvertisement(0);
        system.showNextAdvertisement(0);
        system.showNextAdvertisement(0);
        system.showNextAdvertisement(0);
        system.showNextAdvertisement(0);
    }
}