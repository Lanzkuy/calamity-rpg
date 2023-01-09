package engine;

import data.DataLoader;
import data.DataSaver;
import data.DataStorage;
import entity.HuntEnemy;
import items.HuntingItem;
import org.junit.*;
import org.junit.rules.ExpectedException;

import static org.junit.Assert.*;

public class InventoryTest {

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
        DataSaver.saveInventoryData();
    }

    @Test
    public void isItemExist_returnTrue() {
        System.out.println("isItemExist_Success running");
        Inventory.insertItem("ITM01", "Hunting Item", 1);
        assertTrue(Inventory.isItemExist("ITM01"));
    }

    @Test
    public void isItemExist_returnFalse() {
        System.out.println("isItemExist_returnFalse running");
        assertFalse(Inventory.isItemExist("ITM200"));
    }

    @Test()
    public void insertItem_returnSuccess() {
        System.out.println("insertItem_Success running");
        HuntingItem hi = DataStorage.getHuntingItem("ITM02");
        assert hi != null;
        Inventory.insertItem(hi.getItemID(), hi.getType(), 1 );
    }

    @Test(expected = RuntimeException.class)
    public void insertItem_returnFailed() {
        System.out.println("insertItem_Failed running");
        Inventory.insertItem(null, null, 0);
    }

    @Test
    public void updateItem_returnSuccess() {
        System.out.println("insertItem_Failed running");
        HuntingItem hi = DataStorage.getHuntingItem("ITM02");
        assert hi != null;
        Inventory.updateItem(hi.getItemID(), 1);
    }

    @Test(expected = RuntimeException.class)
    public void updateItem_returnFailed() {
        System.out.println("insertItem_Failed running");
        Inventory.updateItem(null, 0);
    }

    @Test
    public void deleteItem_returnSuccess() {
        System.out.println("deleteItem_returnSuccess running");
        Inventory.deleteItem("ITM02");
    }

    @Test(expected = RuntimeException.class)
    public void deleteItem_returnFailed() {
        System.out.println("deleteItem_returnFailed running");
        Inventory.deleteItem(null);
    }
}