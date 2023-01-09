package engine;

import data.DataLoader;
import data.DataSaver;
import data.DataStorage;
import entity.Boss;
import entity.Player;
import org.hamcrest.CoreMatchers;
import org.junit.*;

import java.util.Objects;

import static org.junit.Assert.*;

public class DungeonTest {

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
        DataSaver.saveDungeonData();
    }

    @Test
    public void afterBattleAction_returnWin() {
        Boss boss = DataStorage.getBossEnemy("ENM005");
        assert boss != null;
        Player.health = 100;
        boss.setHealth(0);
        DataSaver.savePlayerData();
        String afterBattleAction = Dungeon.afterBattleAction(boss, "");
        assertTrue(afterBattleAction.contains("You win"));
        Player.heal(100);
        DataSaver.savePlayerData();
    }

    @Test
    public void afterBattleAction_returnLose() {
        System.out.println("afterBattleAction_returnLose running");
        Boss boss = DataStorage.getBossEnemy("ENM005");
        Player.health = 0;
        DataSaver.savePlayerData();
        assert boss != null;
        String afterBattleAction = Dungeon.afterBattleAction(boss, "");
        assertTrue(afterBattleAction.contains("You lose"));
        Player.heal(100);
        DataSaver.savePlayerData();
    }

    @Test
    public void afterBattleAction_returnProcess() {
        System.out.println("afterBattleAction_returnLose running");
        Boss boss = DataStorage.getBossEnemy("ENM005");
        String action = Dungeon.dungeonBattle(boss, "Attack", "Attack" );
        assert boss != null;
        boss.setHealth(100);
        Player.heal(100);
        DataSaver.savePlayerData();
        String afterBattleAction = Dungeon.afterBattleAction(boss, action);
        assertNotEquals(afterBattleAction, "");
        boss.setHealth(100);
        Player.heal(100);
        DataSaver.savePlayerData();
    }

    @Test
    public void dungeonBattle_returnMoveExist() {
        System.out.println("dungeonBattle_returnMoveExist running");
        Boss boss = DataStorage.getBossEnemy("ENM026");
        assertNotEquals(Dungeon.dungeonBattle(boss, "Attack", "Attack" ), "");
        Player.heal(Player.totalMaxHealth);
        assertNotEquals(Dungeon.dungeonBattle(boss, "Block", "Attack" ), "");
        Player.heal(Player.totalMaxHealth);
        assertNotEquals(Dungeon.dungeonBattle(boss, "Heal", "Attack" ), "");
        Player.heal(Player.totalMaxHealth);
        assertNotEquals(Dungeon.dungeonBattle(boss, "Attack", "Block" ), "");
        Player.heal(Player.totalMaxHealth);
        assertNotEquals(Dungeon.dungeonBattle(boss, "Block", "Block" ), "");
        Player.heal(Player.totalMaxHealth);
        assertNotEquals(Dungeon.dungeonBattle(boss, "Heal", "Block" ), "");
        Player.heal(Player.totalMaxHealth);
        assertNotEquals(Dungeon.dungeonBattle(boss, "Attack", "Special Attack" ), "");
        Player.heal(Player.totalMaxHealth);
        assertNotEquals(Dungeon.dungeonBattle(boss, "Block", "Special Attack" ), "");
        Player.heal(Player.totalMaxHealth);
        assertNotEquals(Dungeon.dungeonBattle(boss, "Heal", "Special Attack" ), "");
        Player.heal(Player.totalMaxHealth);
    }

    @Test
    public void dungeonBattle_returnMoveNotExist() {
        System.out.println("dungeonBattle_returnMoveNotExist running");
        Boss boss = DataStorage.getBossEnemy("ENM026");
        assertEquals(Dungeon.dungeonBattle(boss, "", "" ), "");
        assertEquals(Dungeon.dungeonBattle(boss, "", "" ), "");
        assertEquals(Dungeon.dungeonBattle(boss, "", "" ), "");
        assertEquals(Dungeon.dungeonBattle(boss, "", "" ), "");
        assertEquals(Dungeon.dungeonBattle(boss, "", "" ), "");
        assertEquals(Dungeon.dungeonBattle(boss, "", "" ), "");
        assertEquals(Dungeon.dungeonBattle(boss, "", "" ), "");
        assertEquals(Dungeon.dungeonBattle(boss, "", "" ), "");
        assertEquals(Dungeon.dungeonBattle(boss, "", "" ), "");
    }

    @Test
    public void updateDungeon_returnSuccess() {
        System.out.println("updateDungeon_returnSuccess running");
        Dungeon.updateDungeon("DG01", "MP01", "ENM005", "Defeated");
        Dungeon.updateDungeon("DG01", "MP01", "ENM005", "Available");
    }

    @Test(expected = RuntimeException.class)
    public void updateDungeon_returnFailed() {
        System.out.println("updateDungeon_returnFailed running");
        Dungeon.updateDungeon(null, null, null, null);
    }
}