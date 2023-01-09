package engine;

import data.DataLoader;
import entity.Player;
import org.junit.*;
import ui.frmBlacksmith;
import ui.frmGameMenu;

import static org.junit.Assert.*;

public class BlacksmithTest {

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
    public void craft_returnSuccess() {
        System.out.println("craft_returnSuccess running");
        Inventory.insertItem("ITM17", "Hunting Item", 1);
        Inventory.insertItem("ITM02", "Hunting Item", 30);
        Inventory.insertItem("ITM03", "Hunting Item", 20);
        Player.money += 1000;
        Blacksmith.craft("ITM31");

        Inventory.insertItem("ITM17", "Hunting Item", 1);
        Inventory.insertItem("ITM01", "Hunting Item", 20);
        Inventory.insertItem("ITM04", "Hunting Item", 30);
        Player.money += 1000;
        Blacksmith.craft("ITM38");

        Inventory.insertItem("ITM17", "Hunting Item", 10);
        Inventory.insertItem("ITM04", "Hunting Item", 50);
        Inventory.insertItem("ITM03", "Hunting Item", 50);
        Player.money += 1000;
        Blacksmith.craft("ITM44");
    }

    @Test(expected = RuntimeException.class)
    public void craft_returnFailed() {
        System.out.println("craft_returnFailed running");
        Blacksmith.craft(null);
    }

    @Test
    public void getCraftItemName_returnSuccess() {
        System.out.println("getCraftItemName_returnSuccess running");
        assertNotEquals(Blacksmith.getCraftItemName("ITM31"), "");
        assertNotEquals(Blacksmith.getCraftItemName("ITM38"), "");
        assertNotEquals(Blacksmith.getCraftItemName("ITM44"), "");
    }

    @Test
    public void getCraftItemName_returnFailed() {
        System.out.println("getCraftItemName_returnFailed running");
        assertEquals(Blacksmith.getCraftItemName(null), "");
    }
}