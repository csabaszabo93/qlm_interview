package hu.qlm.interview.advertisement;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class SimpleAdvertisementSystem implements AdvertisementSystem {
    private final List<Advertisement> advertisements = new LinkedList<>();
    private final Queue<AdvertisementContainer> adQueue = new PriorityQueue<>();

    @Override
    public void registerAdvertisement(Advertisement ad) {
        advertisements.add(ad);
        adQueue.add(new AdvertisementContainer(ad, 0));
    }

    @Override
    public void showNextAdvertisement(int dayIndex) {
        AdvertisementContainer nextContainer = adQueue.poll();
        if (nextContainer == null) {
            System.out.println("All registered advertisements reached their max appearance");
            return;
        }
        Advertisement nextAd = nextContainer.getAdvertisement();
        nextAd.showAdvertisement();
        Integer dailyAppearances = nextAd.getAllAppearences().get(dayIndex);
        if (dailyAppearances == null) {
            dailyAppearances = 0;
        }
        dailyAppearances += 1;
        nextAd.getAllAppearences().put(dayIndex, dailyAppearances);
        if (nextAd.lastAppearence(dayIndex, dayIndex) < nextAd.getMaxAppearance()) {
            adQueue.add(new AdvertisementContainer(nextAd, dayIndex));
        }
    }

    @Override
    public List<Advertisement> getAdvertisementList() {
        return List.copyOf(advertisements);
    }

    private static final class AdvertisementContainer implements Comparable<AdvertisementContainer> {
        private final Advertisement advertisement;
        private final int dayIndex;

        public AdvertisementContainer(Advertisement advertisement, int dayIndex) {
            this.advertisement = advertisement;
            this.dayIndex = dayIndex;
        }

        public Advertisement getAdvertisement() {
            return advertisement;
        }

        @Override
        public int compareTo(AdvertisementContainer other) {
            double thisAppearances = this.advertisement.lastAppearence(dayIndex, dayIndex);
            double otherAppearances = other.advertisement.lastAppearence(dayIndex, dayIndex);
            if (otherAppearances == 0 && thisAppearances == 0) {
                return Double.compare(other.advertisement.getWeight(), this.advertisement.getWeight());
            } else if (otherAppearances == 0) {
                return 1;
            }
            double appearanceRatio = thisAppearances / otherAppearances;
            double weightRatio = this.advertisement.getWeight() / other.advertisement.getWeight();
            int compared = Double.compare(appearanceRatio, weightRatio);
            if (compared == 0) {
                return 1;
            }
            return compared;
        }
    }
}
