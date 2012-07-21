package de.meisterschueler.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.MultiValueMap;
import org.apache.commons.lang3.StringUtils;

import de.meisterschueler.alignment.NeedlemanWunsch;
import de.meisterschueler.basic.Key;
import de.meisterschueler.basic.MidiEventPair;
import de.meisterschueler.basic.NamedChord;
import de.meisterschueler.basic.Score;



public class ChordServiceImpl implements ChordService {

	private static final Map<String, String> chordsByPrefix = createMap();
	private GuidoService guidoService = new GuidoService();
	private MatchingService matchingService = new MatchingServiceImpl();

	private List<NamedChord> namedChords = new ArrayList<NamedChord>();

	private static Map<String, String> createMap() {
		Map<String, String> map = new HashMap<String, String>();
		// ----- Akkorde -----
		// Dur
		map.put("",			  "{c1,e,g}");            // maj, major
		map.put("2",          "{c1,d,g}");            // sus2
		map.put("4",          "{c1,f,g}");            // sus4, m(sus4)
		map.put("(b5)",       "{c1,e,g&}");           // -
		map.put("6",          "{c1,e,g,a}");          // add6, maj6, add23
		map.put("maj7",       "{c1,e,g,b}");          // j7, M7, Δ
		map.put("maj7(b5)",   "{c1,e,g&,b}");         // -
		map.put("maj7(#5)",   "{c1,e,g#,b}");         // -
		map.put("add9",       "{c1,e,g,d2}");         // add2
		map.put("add21",      "{c1,e,g,f2}");         // add4
		map.put("6/9",        "{c1,e,g,a,d2}");       // maj6/9
		map.put("maj7(b9)",   "{c1,e,g,b,d&2}");      // -
		map.put("maj7(#9)",   "{c1,e,g,b,d#2}");      // -
		map.put("maj7/#11",   "{c1,e,g,b,f#2}");      // -
		map.put("maj7/13",    "{c1,e,g,b,a2}");       // -
		map.put("maj9",       "{c1,e,g,b,d2}");       // maj7/9
		map.put("6/9/#11",    "{c1,e&,g,a,d2,f#}");   // -
		map.put("maj7(#9)11", "{c1,e,g,b,d#2,f}");    // -
		map.put("maj9/13",    "{c1,e,g,b,d2,a}");     // maj7/9/13
		map.put("maj11",      "{c1,e,g,b,d2,f}");     // maj7/9/11, maj9/11
		
		// Moll
		map.put("m",          "{c1,e&,g}");           // min, minor, moll
		map.put("m6",         "{c1,e&,g,a}");         // min6
		map.put("m7",         "{c1,e&,g,b&}");        // -7, mi7, min7
		map.put("m(maj7)",    "{c1,e&,g,b}");         // m(j7)
		map.put("m(add9)",    "{c1,e&,g,d2}");        // m(add2)
		map.put("m(add21)",   "{c1,e&,g,f2}");        // m(add4)
		map.put("m6/9",       "{c1,e&,g,a,d2}");      // min6/9
		map.put("m7/11",      "{c1,e&,g,b&,f2}");     // min7/11
		map.put("m7/11(b5)",  "{c1,e&,g&,b&,f2}");    // min7/11(b5)
		map.put("m7/b13",     "{c1,e&,g,b&,a&2}");    // min7/b13
		map.put("m7/13",      "{c1,e&,g,b&,a2}");     // min7/13
		map.put("m(maj9)",    "{c1,e&,g,b,d2}");      // m(j9)
		map.put("m9",         "{c1,e&,g,b&,d}");      // min9
		map.put("m6/9/11",    "{c1,e&,g,a,d2,f}");    // min6/9/11
		map.put("m11",        "{c1,e&,g,b&,d2,f}");   // min11, m7/9/11, m9/11
		map.put("m13",        "{c1,e&,g,b&,d2,f,a}"); // min13

		// Dominant-Sept
		map.put("7",          "{c1,e,g,b&}");        // dom7
		map.put("7/4",        "{c1,f,g,b&}");        // 7sus4
		map.put("7(b5)",      "{c1,e,g&,b&}");       // Ø
		map.put("7(#5)",      "{c1,e,g#,b&}");       // -
		map.put("7(b9)",      "{c1,e,g,b&,d&2}");    // dom(b9)
		map.put("7(b9)4",     "{c1,f,g,b&,d&2}");    // -
		map.put("7(#9)",      "{c1,e,g,b&,d#2}");    // dom(#9)
		map.put("7/11",       "{c1,e,g,b&,f2}");     // -
		map.put("7/#11",      "{c1,e,g,b&,f#2}");    // -
		map.put("7/b13",      "{c1,e,g,b&,a&2}");    // -
		map.put("7/13",       "{c1,e,g,b&,a2}");     // -
		map.put("9",          "{c1,e,g,b&,d2}");     // dom9, 7/9
		map.put("9/4",        "{c1,f,g,b&,d2}");     // 9sus4, 11/4, 11sus4
		map.put("9(b5)",      "{c1,e,g&,b&,d2}");    // 7/9(b5), 9(#11)
		map.put("9(#5)",      "{c1,e,g#,b&,d2}");    // 7/9(#5)
		map.put("7(b9)#11",   "{c1,e,g,b&,d&2,f#}"); // -
		map.put("7(b9)b13",   "{c1,e,g,b&,d&2,a&}"); // -
		map.put("7(b9)13",    "{c1,e,g,b&,d&2,a}");  // -
		map.put("7(#9)#11",   "{c1,e,g,b&,d#2,f#}"); // -
		map.put("7(#9)b13",   "{c1,e,g,b&,d#2,a&}"); // -
		map.put("9/#11",      "{c1,e,g,b&,d2,f#}");  // -
		map.put("9/b13",      "{c1,e,g,b&,d2,a&}");  // -
		map.put("9/13",       "{c1,e,g,b&,d2,a}");   // 9/6, 7/9/13
		map.put("13/4",       "{c1,f,g,b&,d2,a}");   // 13sus4, 9/13sus4
		map.put("11",         "{c1,e,g,b&,d2,f}");   // dom11
		map.put("11(b9)",     "{c1,e,g,b&,d&2,f}");  // -
		map.put("11(#9)",     "{c1,e,g,b&,d#2,f}");  // -
		map.put("7(#9)#11",   "{c1,e,g,b&,d#2,f#}"); // -
		map.put("9/#11/13",   "{c1,e,g,b&,d2,f#,a}"); // -
		map.put("13",         "{c1,e,g,b&,d2,f,a}"); // dom13
		map.put("13(b9)",     "{c1,e,g,b&,d&2,f,a}"); // -
		map.put("13(#9)",     "{c1,e,g,b&,d#2,f,a}"); // -

		return Collections.unmodifiableMap(map);
	}

	public ChordServiceImpl() {
		for (Map.Entry<String, String> entry : chordsByPrefix.entrySet()) {

			String postfix = entry.getKey();
			String gmnString = entry.getValue();
			List<String> gmnStrings = guidoService.gmnToInvertedChords(gmnString);

			for (String s : gmnStrings) {
				NamedChord namedChord = new NamedChord();
				List<Score> scores = guidoService.gmnToScores(s);
				String intervalString = matchingService.scoresToIntervalSequence(scores);

				namedChord.setPostfix(postfix);
				namedChord.setScores(scores);
				namedChord.setIntervalString(intervalString);
				namedChord.setInversion(gmnStrings.indexOf(s));

				namedChords.add(namedChord);
			}
		}
	}

	@Override
	public NamedChord getNamedChord(List<MidiEventPair> notes) {
		MultiValueMap qualityMap = new MultiValueMap();

		// Store namedChords in a map ordered by matching quality
		String seq2 = matchingService.midiEventsToIntervalSequence(notes);
		String alignment = null;
		for (NamedChord namedChord : namedChords) {
			alignment = NeedlemanWunsch.getAlignments(namedChord.getIntervalString(), seq2);
			qualityMap.put(weighting(alignment), namedChord);
		}

		// Get the best chords and find the one with the lowest inversion
		List<Double> l = new ArrayList<Double>();
		l.addAll(qualityMap.keySet());
		Collections.sort(l);
		
		double bestValue = l.get(l.size()-1);
		List<NamedChord> bestChords = (List<NamedChord>) qualityMap.get(bestValue);
		
		Map<Integer, NamedChord> inversionMap = new HashMap<Integer, NamedChord>();
		for (NamedChord bestChord : bestChords) {
			inversionMap.put(bestChord.getInversion(), bestChord);
		}

		NamedChord best = (NamedChord) inversionMap.entrySet().iterator().next().getValue();
		alignment = NeedlemanWunsch.getAlignments(best.getIntervalString(), seq2);

		int idx = StringUtils.indexOf(alignment, "m");
		if (idx != -1) {
			Score score = findScore(best, idx);

			int transposition = notes.get(idx).getNoteOn().getNoteValue() - score.getPitch();

			// map transposition to [0:11]
			best.setKey(Key.C.transposeByPitch(transposition));
		}

		return best;
	}

	private Score findScore(NamedChord best, int idx) {
		// get the transposition

		int idxFound = -1;
		for (Score score : best.getScores()) {
			idxFound++;
			if (idx == idxFound) {
				return score;
			}
			while (score.getSibling() != null) {
				score = score.getSibling();
				idxFound++;
				if (idx == idxFound) {
					return score;
				}
			}
		}
		return null;
	}

	private double weighting(String alignment) {
		int matches = StringUtils.countMatches(alignment, "m");
		int wrong = StringUtils.countMatches(alignment, "w");
		int open = StringUtils.countMatches(alignment, "o");
		int extra = StringUtils.countMatches(alignment, "e");
		int missed = StringUtils.countMatches(alignment, "x");

		double result = (matches - wrong - open - extra - missed)/(double)alignment.length();
		return result;
	}
}
