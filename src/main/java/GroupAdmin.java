import java.util.LinkedList;


public class GroupAdmin implements Sender {
    private final UndoableStringBuilder undoableStringBuilder;
    private final LinkedList<Member> members;

    public GroupAdmin(String str) {
        undoableStringBuilder = new UndoableStringBuilder(str);
        members = new LinkedList<>();
    }

    public GroupAdmin() {
        this("");
    }

    public void notifyMembers() {
        members.forEach(observer -> observer.update(undoableStringBuilder));
    }


    @Override
    public void register(Member obj) { members.add(obj);
    }

    @Override
    public void unregister(Member obj) {
        ((ConcreteMember) obj).setUndoableStringBuilder(new UndoableStringBuilder(obj.toString().split(":")[1]));
        members.remove(obj);
    }

    @Override
    public void append(String str) {
        undoableStringBuilder.append(str);
        notifyMembers();

    }

    @Override
    public void insert(int offset, String str) {
        undoableStringBuilder.insert(offset, str);
        notifyMembers();
    }

    @Override
    public void delete(int start, int end) {
        undoableStringBuilder.delete(start, end);
        notifyMembers();
    }

    @Override
    public void undo() {
        undoableStringBuilder.undo();
        notifyMembers();
    }

    @Override
    public String toString() {
        return "{" +
                "\nstring=" + undoableStringBuilder +
                "\nmembers=" + members +
                '}';
    }
}
