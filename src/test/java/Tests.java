import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;

public class Tests {
    public static final Logger logger = LoggerFactory.getLogger(Tests.class);
    // stub method to check external dependencies compatibility
    @Test
    public void test(){

        //אם הרגע התחברת אז עדיין אין את המצב הקיים עד הupdate

        //if you unregister so u dont get any more updates

        //if u already register -> can register

        //if u unregister its unregister only one

        //if u unregister u lose all the history of the string

        //if u register and get updated your history is override by observable


        GroupAdmin admin = new GroupAdmin();
        GroupAdmin admin2 = new GroupAdmin();
        ConcreteMember c1 = new ConcreteMember();
        ConcreteMember c2 = new ConcreteMember();
        ConcreteMember c3 = new ConcreteMember();
        ConcreteMember c4 = new ConcreteMember();

        admin.register(c1);
        admin.register(c2);
        admin.register(c3);
        admin2.register(c3);

        admin.append("asdfghjkl");
        System.out.println(admin);
        System.out.println(c1);
        System.out.println(c2);
        admin.unregister(c1);
        admin.append("1234");
        admin.register(c4);
        System.out.println(c1);
        System.out.println(c2);
        System.out.println(c4);










        String s1 = "Alice";
        String s2 = "Bob";

        logger.info(()->JvmUtilities.objectFootprint(s1));

        logger.info(()->JvmUtilities.objectFootprint(s1,s2));

        logger.info(()->JvmUtilities.objectTotalSize(s1));

        logger.info(JvmUtilities::jvmInfo);
    }
}
