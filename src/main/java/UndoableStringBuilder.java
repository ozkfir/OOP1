import java.util.EmptyStackException;
import java.util.Stack;

/**
 * StringBuilder with option to undo the following methods:
 * <p>
 * {@link #append(String)},
 * {@link #delete(int, int)},
 * {@link #insert(int, String)},
 * {@link #replace(int, int, String)},
 * {@link #reverse()}.
 *
 * @author Eitan Ankri, oz kfir
 */
public class UndoableStringBuilder {

    /**
     * {@link StringBuilder}
     */
    private StringBuilder string;

    /**
     * {@link Stack} to save changes to the StringBuilder
     */
    private final Stack<String> stringsHistory;

    /**
     * Constructs a string builder initialized to the contents of the specified string.
     * and saving all changes to this object.
     *
     * @param str the initial content. if getting null handel as "null".
     */
    public UndoableStringBuilder(String str) {
        this.string = new StringBuilder(str);
        this.stringsHistory = new Stack<>();
    }

    /**
     * pass empty string to following method {@link UndoableStringBuilder#UndoableStringBuilder(String str)}.
     */
    public UndoableStringBuilder() {
        this("");
    }

    /**
     * Appends the specified string to this character sequence.
     *
     * @param str the specified string. if getting null handel as "null".
     * @return this
     */
    public UndoableStringBuilder append(String str) {
        String tmp = this.string.toString();
        this.stringsHistory.push(tmp);
        this.string.append(str);
        return this;
    }

    /**
     * Removes the characters in a substring of this sequence. The substring begins
     * at the specified start and extends to the character at index
     * end - 1 or to the end of the sequence if no such character exists.
     * If start is equal to end, no changes are made.
     *
     * @param start The beginning index, inclusive.
     * @param end   The ending index, exclusive.
     * @return this
     * @throws StringIndexOutOfBoundsException if start is negative, greater than length(), or greater than end
     */
    public UndoableStringBuilder delete(int start, int end) {

        stringsHistory.push(this.string.toString());
        this.string.delete(start, end);

        return this;
    }

    /**
     * Inserts the string into this character sequence.
     *
     * @param offset where to inset.
     * @param str    witch string to insert. if str is null handel it as "null"
     * @return this
     * @throws StringIndexOutOfBoundsException if the offset less than  0, or  greater than the length.
     */
    public UndoableStringBuilder insert(int offset, String str) {

        String tmp = this.string.toString();
        this.string.insert(offset, str);
        stringsHistory.push(tmp);

        return this;
    }

    /**
     * Replaces the characters in a substring of this sequence with characters in
     * the specified String. The substring begins at the specified start and
     * extends to the character at index end - 1 or to the end of the sequence if
     * no such character exists. First the characters in the substring are removed
     * and then the specified String is inserted at start. (This sequence will be
     * lengthened to accommodate the specified String if necessary).
     *
     * @param start The beginning index, inclusive.
     * @param end   The ending index, exclusive.
     * @param str   String that will replace previous contents.
     * @return this
     * @throws NullPointerException            if str is null
     * @throws StringIndexOutOfBoundsException if start is negative, greater than length(), or greater than end
     */
    public UndoableStringBuilder replace(int start, int end, String str) {
        String tmp = this.string.toString();
        this.string.replace(start, end, str);
        stringsHistory.push(tmp);
        return this;
    }

    /**
     * Causes this character sequence to be replaced by the reverse of the
     * sequence.
     *
     * @return this
     */
    public UndoableStringBuilder reverse() {
        this.stringsHistory.push(this.string.toString());
        this.string.reverse();
        return this;
    }

    /**
     * change {@link #string} to is previous value.
     *
     * @throws EmptyStackException if try undo the initialized value
     */
    public void undo() {
        this.string = new StringBuilder(this.stringsHistory.pop());
    }

    /**
     * @return {@link #string}
     */
    @Override
    public String toString() {
        return this.string.toString();
    }
}