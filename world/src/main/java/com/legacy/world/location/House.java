package com.legacy.world.location;

import java.util.UUID;

public class House extends Location {

    private UUID ownerID;

    public House(UUID ownerID) {
        this.ownerID = ownerID;
    }

    public UUID getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(UUID ownerID) {
        this.ownerID = ownerID;
    }
}
