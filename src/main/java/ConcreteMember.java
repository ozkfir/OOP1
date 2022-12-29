import java.util.Observable;

public class ConcreteMember implements Member {
    private UndoableStringBuilder undoableStringBuilder;
    private String name;
    private static int nameIndex = 1;

    public ConcreteMember(String name) {
        this.name = name;
        undoableStringBuilder = new UndoableStringBuilder();
    }

    public ConcreteMember() {
        this("Member#"+nameIndex++);
    }

    @Override
    public void update(UndoableStringBuilder usb) {
        this.undoableStringBuilder = usb;
    }

    public void setUndoableStringBuilder(UndoableStringBuilder undoableStringBuilder) {
        this.undoableStringBuilder = undoableStringBuilder;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return this.name + " :" + this.undoableStringBuilder.toString();
    }
}
