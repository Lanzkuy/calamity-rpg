package engine;

import data.DataLoader;
import data.DataStorage;
import entity.HuntEnemy;
import org.junit.*;

import static org.junit.Assert.*;
public class HuntTest {

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
    }

    @Test
    public void huntBattle_returnSuccess() {
        System.out.println("huntBattle_returnSuccess running");
        HuntEnemy enemy = DataStorage.getRandomHuntEnemy();
        String hunt = Hunt.huntBattle(enemy);

        assertNotNull("Hunt is null", hunt);
        assertNotEquals(hunt, "");
    }

    @Test
    public void huntBattle_returnFailed() {
        System.out.println("huntBattle_returnFailed running");
        String hunt = Hunt.huntBattle(null);

        assertNull("Hunt not null", hunt);
    }
}