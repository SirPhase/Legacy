package com.legacy.world.location;

import com.legacy.character.career.Career;
import com.legacy.character.npc.NPC;
import com.legacy.character.npc.NPCUtils;

import java.util.List;

public class School extends Location {

    private List<NPC> students;
    private List<NPC> staff;
    private NPC administrator;

    public School() {
        super();
        administrator = NPCUtils.generateNPC();
        administrator.setCareer(new Career("Principal", 1000));
    }
}
