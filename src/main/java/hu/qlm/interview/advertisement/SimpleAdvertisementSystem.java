package hu.qlm.interview.advertisement;

import java.util.*;

public class SimpleAdvertisementSystem implements AdvertisementSystem {
    private final List<Advertisement> advertisements = new LinkedList<>();
    private final AdvertisementComparator adComparator = new AdvertisementComparator();
    private final Queue<Advertisement> adQueue = new PriorityQueue<>(adComparator);

    @Override
    public void registerAdvertisement(Advertisement ad) {
        advertisements.add(ad);
        adQueue.add(ad);
    }

    @Override
    public void showNextAdvertisement(int dayIndex) {
        Advertisement ad = adQueue.poll();
        if (ad == null) {
            System.out.println("All registered advertisements reached their max appearance");
            return;
        }
        ad.showAdvertisement();
        Integer dailyAppearances = ad.getAllAppearences().get(dayIndex);
        if (dailyAppearances == null) {
            dailyAppearances = 0;
        }
        dailyAppearances += 1;
        ad.getAllAppearences().put(dayIndex, dailyAppearances);
        if (ad.lastAppearence(dayIndex, dayIndex) < ad.getMaxAppearance()) {
            adComparator.updateDayIndex(dayIndex);
            adQueue.add(ad);
        }
    }

    @Override
    public List<Advertisement> getAdvertisementList() {
        return List.copyOf(advertisements);
    }

    private static final class AdvertisementComparator implements Comparator<Advertisement> {
        private int dayIndex = 0;

        public void updateDayIndex(int dayIndex) {
            this.dayIndex = dayIndex;
        }

        @Override
        public int compare(Advertisement ad1, Advertisement ad2) {
            double ad1Appearances = ad1.lastAppearence(dayIndex, dayIndex);
            double ad2Appearances = ad2.lastAppearence(dayIndex, dayIndex);
            if (ad2Appearances == 0 && ad1Appearances == 0) {
                return Double.compare(ad2.getWeight(), ad1.getWeight());
            } else if (ad2Appearances == 0) {
                return 1;
            }
            double appearanceRatio = ad1Appearances / ad2Appearances;
            double weightRatio = ad1.getWeight() / ad2.getWeight();
            int compared = Double.compare(appearanceRatio, weightRatio);
            if (compared == 0) {
                return 1;
            }
            return compared;
        }
    }
}
