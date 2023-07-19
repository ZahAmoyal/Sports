package Tests;

import org.junit.Test;

public class SportFiveTests extends BaseTest {

    @Test
    public void sportNews() throws InterruptedException {
        sportFiveFlow.flowSportFive();
        oneFlow.flowOne();
    }
}
