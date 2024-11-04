package hu.qlm.interview.studentseater;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class StudentSeater {

	/**
	 * Metódus, ami megmondja, hogy hányféleképp lehet két diákot egymás mellé ültetni egy tanteremben.
	 * Ez azt jelenti, hogy az egyik diák bal oldalon van a másik diák pedig a jobb oldalán vagy közvetlenül
	 * felette / alatta.
	 *
	 * A padok egyszemélyesek és két oszlopban vannak elhelyezve,
	 * ahol az első asztal a bal felső sarokban van, a második asztal pedig a jobb felső sarokban,
	 * a harmadik az első alatt, a negyedik pedig a második alatt és így tovább...
	 *
	 * @param classroom a tanterem mérete és ülésrend a következő formátumban:
	 *           [K, r1, r2, r3, ...] ahol K a padok száma a tanteremben,
	 *           a többi integer a tömbben pedig növekvő sorrendben van és a már foglalt padokat jelöli.
	 *           K 2 és 24 közé eshet és csak páros szám lehet.
	 *           K után a foglalt padok száma a tömbben 0 és K között van.
	 *
	 * @return a szám ahányféleképp két diákot egymás mellé lehet ültetni
	 * @throws IllegalArgumentException
	 *           ha a tanterem null vagy üres
	 *           ha K nem páros szám 2 és 24 között
	 *           ha a foglalt padok listája érvénytelen, például kevesebb mint egy, több, mint K vagy nem
	 *           rendezett sorban vannak
	 **/
	public int seatingStudents(int[] classroom) {
		if (classroom == null || classroom.length == 0) {
			throw new IllegalArgumentException("classroom array can't be null or empty");
		}
		int roomSize = getRoomSize(classroom);
		int[] reservedSeats = getReservedSeats(classroom);
		int seatPairs = (int) (1.5 * roomSize - 2);
		RemovedPairsStore removedPairs = new RemovedPairsStore(roomSize);
		for (int i = 0; i < reservedSeats.length; i++) {
			int seat = reservedSeats[i];
			if (i < reservedSeats.length - 1) {
				int nextSeat = reservedSeats[i + 1];
				if (nextSeat <= seat) {
					throw new IllegalArgumentException("unordered reserved seat array");
				}
				if (seat % 2 == 1) {
					removedPairs.increaseOf(seat + 1);
				}
			}
			if (i < reservedSeats.length - 2) {
				removedPairs.increaseOf(seat + 2);
			}
			int seatPairDecrease = 3 - removedPairs.get(seat);
			seatPairs -= seatPairDecrease;
		}
		return seatPairs * 2;
	}

	private int getRoomSize(int[] classroom) {
		int roomSize = classroom[0];
		if (roomSize % 2 == 1 || roomSize < 2 || roomSize > 24) {
			throw new IllegalArgumentException("invalid room size, it has to be even and between 2 and 24");
		}
		return roomSize;
	}

	private int[] getReservedSeats(int[] classroom) {
		int[] reservedSeats = Arrays.copyOfRange(classroom, 1, classroom.length);
		if (reservedSeats.length > classroom[0]) {
			throw new IllegalArgumentException("invalid amount of reserved seats");
		}
		return reservedSeats;
	}

	private static final class RemovedPairsStore {
		private final Map<Integer, Integer> corrections = new HashMap<>();

		public RemovedPairsStore(int size) {
			this.increaseOf(1);
			this.increaseOf(2);
			this.increaseOf(size - 1);
			this.increaseOf(size);
		}

		public void increaseOf(int idx) {
			corrections.compute(idx, (ignored, value) -> value == null ? 1 : value + 1);
		}

		public int get(int idx) {
			return corrections.getOrDefault(idx, 0);
		}
	}
}
