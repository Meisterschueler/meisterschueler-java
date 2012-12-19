package de.meisterschueler.basic;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum Variation {
	NONE, EVEN, SYNCOPE, LOMBARDIC;
	
	private static final Map<Variation, List<Double>> myMap;
    static {
        Map<Variation, List<Double>> aMap = new HashMap<Variation, List<Double>>();
        aMap.put(NONE, null);
        aMap.put(EVEN, Arrays.asList(1.0));
        aMap.put(SYNCOPE, Arrays.asList(0.75, 0.25));
        aMap.put(LOMBARDIC, Arrays.asList(0.25, 0.75));
        myMap = Collections.unmodifiableMap(aMap);
    }

	public static List<Double> toList(Variation rhythm) {
		return myMap.get(rhythm);
	}
}
