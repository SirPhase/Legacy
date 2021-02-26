package com.legacy.character.slave;

import com.legacy.character.body.BodyUtils;
import com.legacy.character.gender.Gender;
import com.legacy.character.npc.NPC;
import com.legacy.character.player.Player;
import com.legacy.character.race.Race;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SlaveTest {

    private static Player player;

    @BeforeClass
    public static void init() {
        player = new Player();
        player.setGender(Gender.MALE);
        player.setRace(Race.HUMAN);
        BodyUtils.fixCharacter(player);
    }

    @Test
    public void testSlaveOwner() throws Exception {
        Slave fatherSlave = SlaveUtils.generateSlave(player.getId(), Gender.MALE, Race.RABBIT);
        Slave motherSlave = SlaveUtils.generateSlave(player.getId(), Gender.FEMALE, Race.RABBIT);
        assertEquals(player.getId(), fatherSlave.getOwnerID());
        assertEquals(player.getId(), motherSlave.getOwnerID());
        assertEquals(fatherSlave, player.getSlave(fatherSlave.getId()));
        assertEquals(motherSlave, player.getSlave(motherSlave.getId()));

        Slave slaveChild = motherSlave.impregnatedBy(fatherSlave);
        assertEquals(player.getId(), slaveChild.getOwnerID());
        assertEquals(motherSlave.getId(), slaveChild.getMotherID());
        assertEquals(fatherSlave.getId(), slaveChild.getFatherID());
        assertEquals(slaveChild, player.getSlave(slaveChild.getId()));

        Slave playerChild = motherSlave.impregnatedBy(player);
        assertEquals(player.getId(), playerChild.getOwnerID());
        assertEquals(motherSlave.getId(), playerChild.getMotherID());
        assertEquals(player.getId(), playerChild.getFatherID());
        assertEquals(playerChild, player.getSlave(playerChild.getId()));
    }

    @Test
    public void testEnslave() throws Exception {
        NPC npc = new NPC();
        Slave slave = npc.enslave(player.getId());
        assertTrue(npc.equals(slave));
    }
}
