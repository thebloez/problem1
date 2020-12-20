import com.kitabisa.test.Main;
import org.junit.Assert;
import org.junit.Test;

public class UnitTest {

    @Test
    public void parseWithPositiveNumber(){
        Assert.assertEquals(4, Main.eval("8-2-2"),0);
        Assert.assertEquals(8, Main.eval("6+6-4"),0);
    }

    @Test
    public void parseWithResultNegativeNumber(){
        Assert.assertEquals(-35, Main.eval("-12-23"),0);
        Assert.assertEquals(-2, Main.eval("12+20-34"),0);
    }

    @Test(expected = RuntimeException.class)
    public void expectValidationError(){
        Main.eval("abse1+2+3");
    }
}
