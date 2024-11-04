package hu.qlm.interview.advertisement;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class SimpleAdvertisementSystemTest {
    private final SimpleAdvertisementSystem system = new SimpleAdvertisementSystem();

    @Test
    void testWithSingleAd() {
        SimpleAdvertisement ad = new SimpleAdvertisement(1, 0, "1");
        SimpleAdvertisement adSpy = spy(ad);
        system.registerAdvertisement(adSpy);
        system.showNextAdvertisement(0);
        system.showNextAdvertisement(0);
        verify(adSpy).showAdvertisement();
    }

    @Test
    void testWithTwoAds() {
        SimpleAdvertisement ad = new SimpleAdvertisement(2, 0.1, "1");
        SimpleAdvertisement ad2 = new SimpleAdvertisement(2, 0.2, "2");
        SimpleAdvertisement adSpy = spy(ad);
        SimpleAdvertisement adSpy2 = spy(ad2);
        system.registerAdvertisement(adSpy);
        system.registerAdvertisement(adSpy2);
        system.showNextAdvertisement(0);
        verify(adSpy, never()).showAdvertisement();
        verify(adSpy2).showAdvertisement();
        system.showNextAdvertisement(0);
        verify(adSpy).showAdvertisement();
        verify(adSpy2).showAdvertisement();
        system.showNextAdvertisement(0);
        verify(adSpy).showAdvertisement();
        verify(adSpy2, times(2)).showAdvertisement();
        system.showNextAdvertisement(0);
        verify(adSpy, times(2)).showAdvertisement();
        verify(adSpy2, times(2)).showAdvertisement();
        system.showNextAdvertisement(1);
        verify(adSpy, times(2)).showAdvertisement();
        verify(adSpy2, times(2)).showAdvertisement();
    }

    @Test
    void testWithMultipleAds() {
        SimpleAdvertisement ad = new SimpleAdvertisement(1, 0.1, "1");
        SimpleAdvertisement ad2 = new SimpleAdvertisement(5, 0.2, "2");
        SimpleAdvertisement ad3 = new SimpleAdvertisement(3, 0.1, "3");
        SimpleAdvertisement ad4 = new SimpleAdvertisement(6, 0.6, "4");
        SimpleAdvertisement adSpy = spy(ad);
        SimpleAdvertisement adSpy2 = spy(ad2);
        SimpleAdvertisement adSpy3 = spy(ad3);
        SimpleAdvertisement adSpy4 = spy(ad4);
        system.registerAdvertisement(adSpy);
        system.registerAdvertisement(adSpy2);
        system.registerAdvertisement(adSpy3);
        system.registerAdvertisement(adSpy4);
        system.showNextAdvertisement(0);
        system.showNextAdvertisement(0);
        system.showNextAdvertisement(0);
        system.showNextAdvertisement(0);
        system.showNextAdvertisement(1);
        system.showNextAdvertisement(1);
        system.showNextAdvertisement(3);
        system.showNextAdvertisement(3);
        system.showNextAdvertisement(3);
        system.showNextAdvertisement(4);
        system.showNextAdvertisement(8);
        system.showNextAdvertisement(8);
        system.showNextAdvertisement(9);
        verify(adSpy).showAdvertisement();
        verify(adSpy2, times(4)).showAdvertisement();
        verify(adSpy3, times(2)).showAdvertisement();
        verify(adSpy4, times(6)).showAdvertisement();
    }

    @Test
    void testDayIndexDecreaseHandling() {
        SimpleAdvertisement ad = new SimpleAdvertisement(5, 0.1, "1");
        system.registerAdvertisement(ad);
        system.showNextAdvertisement(3);
        assertThrows(IllegalArgumentException.class, () -> system.showNextAdvertisement(2));
    }
}