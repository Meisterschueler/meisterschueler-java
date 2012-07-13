package alignment;

public class MatchingState {
	
    public static final char MATCH = 'm';  // s1[i] == s2[j]
    public static final char MISSED = 'x'; // s1[i] nicht in s2 (Taste übersprungen)
    public static final char EXTRA = 'e';  // s2[j] nicht in s1 (überflüssige Taste gedrückt)
    public static final char WRONG = 'w';  // falsche Taste gedrückt
    public static final char OPEN = 'o';   // Außerhalb des Matchbereichs  
}
