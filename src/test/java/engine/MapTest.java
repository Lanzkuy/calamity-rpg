package engine;

import data.DataLoader;
import data.DataSaver;
import org.junit.*;

import static org.junit.Assert.*;

public class MapTest {

    @BeforeClass
    public static void start() {
        DataLoader.loadData();
    }

    @AfterClass
    public static void end() {
        DataLoader.clearData();
    }

    @Before
    public void setUp() {
        System.out.println("Testing method start");
    }

    @After
    public void tearDown() {
        System.out.println("Testing method end");
        DataSaver.saveMapData();
    }

    @Test
    public void openNewMap_Success() {
        System.out.println("openNewMap_Success running");
        Map.openNewMap("MP02");
    }

    @Test(expected = RuntimeException.class)
    public void openNewMap_Failed() {
        System.out.println("openNewMap_Failed running");
        Map.openNewMap(null);
    }
}