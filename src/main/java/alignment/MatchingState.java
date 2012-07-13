package alignment;

public class MatchingState {
	
    public static final char MATCH = 'm';  // s1[i] == s2[j]
    public static final char MISSED = 'x'; // s1[i] nicht in s2 (Taste �bersprungen)
    public static final char EXTRA = 'e';  // s2[j] nicht in s1 (�berfl�ssige Taste gedr�ckt)
    public static final char WRONG = 'w';  // falsche Taste gedr�ckt
    public static final char OPEN = 'o';   // Au�erhalb des Matchbereichs  
}
