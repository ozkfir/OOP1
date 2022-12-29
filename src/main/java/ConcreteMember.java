/**
 * class that composition {@link UndoableStringBuilder} and implement the observers design pattern.
 * <br>
 * this class implement the observer and {@link  GroupAdmin} implement the observable
 *
 * @author Eitan Ankri, oz kfir
 */
public class ConcreteMember implements Member {
    private UndoableStringBuilder stringBuilder;
    //for print unique name
    private String name;
    private static int nameIndex = 1;

    /**
     * Constructs a {@link UndoableStringBuilder} initialized to the contents of the specified string.
     *
     * @param name the name of the object
     * @param str  value to initialize {@link #stringBuilder}
     */
    public ConcreteMember(String name, String str) {
        this.name = name;
        stringBuilder = new UndoableStringBuilder(str);
    }

    /**
     * call {@link #ConcreteMember(String, String)} with argument of (name, ""(empty string))
     */
    public ConcreteMember(String name) {
        this(name, "");
    }

    /**
     * call {@link #ConcreteMember(String, String)} with argument of (default_name, ""(empty string))
     */
    public ConcreteMember() {
        this("Member#" + nameIndex++, "");
    }

    /**
     * @param usb value from the observable to update the observer, by shallow copy
     */
    @Override
    public void update(UndoableStringBuilder usb) {
        this.stringBuilder = usb;
    }

    /**
     * called by {@link GroupAdmin#unregister(Member)} to complete detach the shallow copy
     *
     * @param str - value to initialize new UndoableStringBuilder for complete detach
     */
    protected void resetUndoableString(String str) {
        this.stringBuilder = new UndoableStringBuilder(str);

    }

    /**
     * @return {@link #name}
     */
    public String getName() {
        return name;
    }

    /**
     * @param name set to {@link #name}
     */
    public void setName(String name) {
        this.name = name;
    }


    /**
     * @return string of the object as follow:
     * <br>
     * {{@link #name}}  :{{@link #stringBuilder}}
     */
    @Override
    public String toString() {
        return this.name + ": " + this.stringBuilder.toString();
    }
}
