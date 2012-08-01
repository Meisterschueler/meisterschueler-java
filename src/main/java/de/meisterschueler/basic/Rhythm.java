package de.meisterschueler.basic;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public enum Rhythm {
	NONE, EVEN, SYNCOPE, LOMBARDIC;
	
	private static final Map<Rhythm, List<Double>> myMap;
    static {
        Map<Rhythm, List<Double>> aMap = new HashMap<Rhythm, List<Double>>();
        aMap.put(NONE, null);
        aMap.put(EVEN, Arrays.asList(1.0));
        aMap.put(SYNCOPE, Arrays.asList(0.75, 0.25));
        aMap.put(LOMBARDIC, Arrays.asList(0.25, 0.75));
        myMap = Collections.unmodifiableMap(aMap);
    }

	public static List<Double> toList(Rhythm rhythm) {
		return myMap.get(rhythm);
	}
}
