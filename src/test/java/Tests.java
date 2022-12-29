import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

import java.util.EmptyStackException;

public class Tests {
    public static final Logger logger = LoggerFactory.getLogger(Tests.class);

    // stub method to check external dependencies compatibility
    @Test
    public void test() {
        logger.info(JvmUtilities::jvmInfo);
        GroupAdmin admin = new GroupAdmin("admin value #1");
        //after register, you get update only when notify is called
        ConcreteMember c1 = new ConcreteMember("alice", "first value for alice");

        Assertions.assertEquals("alice: first value for alice", c1.toString());
        admin.register(c1);
        Assertions.assertEquals("alice: first value for alice", c1.toString());
        admin.append("");
        Assertions.assertEquals("alice: admin value #1", c1.toString());

        //if u already register -> can register
        //and if u unregister its unregister only one
        admin.register(c1);
        //c1 register twice
        Assertions.assertEquals("string= admin value #1\nmembers= [alice: admin value #1, alice: admin value #1]", admin.toString());
        admin.unregister(c1);
        admin.delete(13, 14);
        admin.append("2");
        Assertions.assertEquals("alice: admin value #2", c1.toString());//still get updated
        //and still in is members list
        Assertions.assertEquals("string= admin value #2\nmembers= [alice: admin value #2]", admin.toString());

        //if you unregister so u don't get any more updates
        logger.info(() -> JvmUtilities.objectFootprint(c1));
        admin.unregister(c1);
        admin.delete(13, 14);
        admin.append("3");
        Assertions.assertEquals("alice: admin value #2", c1.toString());//doesn't get update
        //and out of members list
        Assertions.assertEquals("string= admin value #3\nmembers= []", admin.toString());

        //if u unregister u lose all the history of the string and have brand new UndoableStringBuilder
        //can see this by the size drop
        logger.info(() -> "After the unregister you have new object as can see the size drop\n" + JvmUtilities.objectFootprint(c1));

        logger.info(() -> JvmUtilities.objectTotalSize(admin));
        admin.undo();
        admin.undo();
        admin.undo();
        admin.undo();
        admin.undo();
        Assertions.assertThrowsExactly(EmptyStackException.class, admin::undo);
        //admin return to is initialized value
        Assertions.assertEquals("string= admin value #1\nmembers= []", admin.toString());
        logger.info(() -> "We can see that after undo to the initialize value the size is dropped\n" + JvmUtilities.objectTotalSize(admin) + "\n\n");

        //--------------------
        //if u register and get updated your history is overridden by observable
        GroupAdmin admin2 = new GroupAdmin("admin2 value #1");
        admin.register(c1);
        admin.delete(13, 14);
        admin.append("2");
        admin.delete(13, 14);
        admin.append("3");
        admin.delete(13, 14);
        admin.append("4");
        admin.delete(13, 14);
        admin.append("5");
        Assertions.assertEquals("alice: admin value #5", c1.toString());
        logger.info(() -> JvmUtilities.objectFootprint(c1));
        admin2.register(c1);
        admin2.delete(14, 15);
        admin2.append("2");
        //after register to another and get updated your history is overridden as shown by size drop
        logger.info(() -> "After register to another and get updated your history is overridden as shown by size drop\n" + JvmUtilities.objectFootprint(c1));
        admin.delete(13, 14);
        admin.append("6");
        // you can register to two observable, so now the size go back up because alice got update from admin
        logger.info(() -> "The size got up again because admin update alice\n" + JvmUtilities.objectFootprint(c1));


    }
}
