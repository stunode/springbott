import com.sun.istack.internal.Nullable;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * ${DESCRIPTION}
 *
 * @author
 * @create 2017-07-04 16:28
 */
public class HelloWorldControllerTest {

    @Test
    public void testSayHello() {
    }

    @Test
    public void Clazz() {

        indirectPathToA(null);
        directPathToA(null);
    }

    public void indirectPathToA(Integer y) {
        directPathToA(y);
    }

    public void directPathToA(@Nullable Integer x) {
        x.toString(); // do stuff to x
    }


}
