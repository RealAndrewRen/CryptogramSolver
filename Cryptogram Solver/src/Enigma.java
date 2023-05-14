import javax.swing.text.StyledEditorKit.ForegroundAction;

public class Enigma {
	private char[] lookupTable;

	public Enigma(int letters) {
		lookupTable = new char[26];
		for (int i = 0; i < letters; i++) {
			lookupTable[i] = '-';
		}
	}

	public void setSubstitution(int i, char ch) {
		lookupTable[i] = ch;
	}

	public String decode(String text) {
		char[] chars = text.toCharArray();
		int i = 0;
		for (char c : chars) {
			if (Character.isLetter(chars[i])) {
				chars[i] = lookupTable[Character.getNumericValue(Character.toUpperCase(c))
						- Character.getNumericValue('A')];
			}
			i++;
		}
		return new String(chars);
	}

	public String getHints(String text, String lettersByFrequency) {
		int[] counts = countLetters(text);
		char[] letters = lettersByFrequency.toCharArray();
		char[] returnHint = new char[counts.length];
		int currentHighest = 0;
		for (int i = 0; i < counts.length; i++) {
			{
				int rank = 0;
				for (int j = i; j >= 0; j--) {
					if (counts[j] <= counts[i])
						rank++;
				}
				if (rank > currentHighest) {
					currentHighest = rank;
				}
				returnHint[i] = letters[rank];
			}
		}
		return new String(returnHint);
	}

	private int[] countLetters(String text) {
		int[] counts = new int[26];
		char[] chars = text.toCharArray();
		for (char c : chars) {
			if (Character.isLetter(c)) {
				counts[Character.getNumericValue(Character.toUpperCase(c)) - Character.getNumericValue('A')] += 1;
			}
		}
		return counts;
	}
}
