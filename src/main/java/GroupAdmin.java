import java.util.LinkedList;

/**
 * class that composition {@link UndoableStringBuilder} and implement the observers design pattern.
 * <br>
 * this class implement the observable and {@link  ConcreteMember} implement the observer
 *
 * @author Eitan Ankri, oz kfir
 */
public class GroupAdmin implements Sender {
    private final UndoableStringBuilder stringBuilder;
    private final LinkedList<Member> members;

    /**
     * Constructs a {@link UndoableStringBuilder} initialized to the contents of the specified string.
     * <br>
     * and also manage list of {@link #members} that notify about every change that happens on {@link #stringBuilder}
     */
    public GroupAdmin(String str) {
        stringBuilder = new UndoableStringBuilder(str);
        members = new LinkedList<>();
    }

    /**
     * call {@link #GroupAdmin(String)} while parms str is equal "" (empty string)
     */
    public GroupAdmin() {
        this("");
    }

    /**
     * for each of members in {@link  #members} it called the function {@link Member#update(UndoableStringBuilder)}
     */
    public void notifyMembers() {
        members.forEach(observer -> observer.update(stringBuilder));
    }


    /**
     * you can register multiple times, and register override obj history
     *
     * @param obj add to members list, and obj is not updated until {@link #notifyMembers()} is called
     */
    @Override
    public void register(Member obj) {
        members.add(obj);
    }

    /**
     * upon unregister obj lose all history and retain is current value
     * if you registered multiple time, unregister remove only one
     *
     * @param obj remove from the members list and will not notify
     */
    @Override
    public void unregister(Member obj) {
        members.remove(obj);
        //only reset if is not member, possible if register more than once
        if (!members.contains(obj))
            ((ConcreteMember) obj).resetUndoableString(obj.toString().split(": ")[1]);
    }

    /**
     * @see UndoableStringBuilder#append(String)
     * addionlly in notify all the members
     */
    @Override
    public void append(String str) {
        stringBuilder.append(str);
        notifyMembers();

    }

    /**
     * @see UndoableStringBuilder#insert(int, String)
     * addionlly in notify all the members
     */
    @Override
    public void insert(int offset, String str) {
        stringBuilder.insert(offset, str);
        notifyMembers();
    }

    /**
     * call {@linkplain   UndoableStringBuilder#delete(int, int)}
     * <br>
     * addionlly in notify all the members
     */
    @Override
    public void delete(int start, int end) {
        stringBuilder.delete(start, end);
        notifyMembers();
    }

    /**
     * @see UndoableStringBuilder#undo()
     * addionlly in notify all the members
     */
    @Override
    public void undo() {
        stringBuilder.undo();
        notifyMembers();
    }
    /**
     *
     * @return string of the object as follow:
     * <br>
     *  {{@link #stringBuilder}}
     * <br>
     *  [all {@link #members}]
     *
     */
    @Override
    public String toString() {
        return "string= " + this.stringBuilder.toString() +
                "\nmembers= " + members;

    }
}
