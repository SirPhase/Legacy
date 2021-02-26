package com.legacy.character.body;

import com.legacy.character.body.lower.Leg;
import com.legacy.character.body.lower.Tail;
import com.legacy.character.body.lower.genitals.*;
import com.legacy.character.body.upper.Arm;
import com.legacy.character.body.upper.Chest;
import com.legacy.character.body.upper.Wing;
import com.legacy.character.body.upper.head.*;
import com.legacy.utils.Builder;

public class Body {

    // Required;
    private Arm arm;
    private Butt butt;
    private Chest chest;
    private Ear ear;
    private Eye eye;
    private Face face;
    private Hair hair;
    private Leg leg;
    private Skin skin;

    // Sex Organs
    private Penis penis;
    private Testicle testicle;
    private Vagina vagina;
    private Ovary ovary;
    private Anus anus;

    // Optional
    private Horn horn;
    private Tail tail;
    private Wing wing;

    private BodyHair bodyHair;
    private PubicHair pubicHair;

    public Arm getArm() {
        return arm;
    }

    public void setArm(Arm arm) {
        this.arm = arm;
    }

    public Butt getButt() {
        return butt;
    }

    public void setButt(Butt butt) {
        this.butt = butt;
    }

    public Chest getChest() {
        return chest;
    }

    public void setChest(Chest chest) {
        this.chest = chest;
    }

    public Ear getEar() {
        return ear;
    }

    public void setEar(Ear ear) {
        this.ear = ear;
    }

    public Eye getEye() {
        return eye;
    }

    public void setEye(Eye eye) {
        this.eye = eye;
    }

    public Face getFace() {
        return face;
    }

    public void setFace(Face face) {
        this.face = face;
    }

    public Hair getHair() {
        return hair;
    }

    public void setHair(Hair hair) {
        this.hair = hair;
    }

    public Leg getLeg() {
        return leg;
    }

    public void setLeg(Leg leg) {
        this.leg = leg;
    }

    public Skin getSkin() {
        return skin;
    }

    public void setSkin(Skin skin) {
        this.skin = skin;
    }

    public Penis getPenis() {
        return penis;
    }

    public void setPenis(Penis penis) {
        this.penis = penis;
    }

    public Testicle getTesticle() {
        return testicle;
    }

    public void setTesticle(Testicle testicle) {
        this.testicle = testicle;
    }

    public Vagina getVagina() {
        return vagina;
    }

    public void setVagina(Vagina vagina) {
        this.vagina = vagina;
    }

    public Ovary getOvary() {
        return ovary;
    }

    public void setOvary(Ovary ovary) {
        this.ovary = ovary;
    }

    public Anus getAnus() {
        return anus;
    }

    public void setAnus(Anus anus) {
        this.anus = anus;
    }

    public Horn getHorn() {
        return horn;
    }

    public void setHorn(Horn horn) {
        this.horn = horn;
    }

    public Tail getTail() {
        return tail;
    }

    public void setTail(Tail tail) {
        this.tail = tail;
    }

    public Wing getWing() {
        return wing;
    }

    public void setWing(Wing wing) {
        this.wing = wing;
    }

    public BodyHair getBodyHair() {
        return bodyHair;
    }

    public void setBodyHair(BodyHair bodyHair) {
        this.bodyHair = bodyHair;
    }

    public PubicHair getPubicHair() {
        return pubicHair;
    }

    public void setPubicHair(PubicHair pubicHair) {
        this.pubicHair = pubicHair;
    }

    public static class BodyBuilder implements Builder<Body> {

        // Required;
        private final Arm arm;
        private final Butt butt;
        private final Chest chest;
        private final Ear ear;
        private final Eye eye;
        private final Face face;
        private final Hair hair;
        private final Leg leg;
        private final Skin skin;

        // Sex Organs
        private Penis penis;
        private Testicle testicle;
        private Vagina vagina;
        private Ovary ovary;
        private Anus anus;

        // Optional
        private Horn horn;
        private Tail tail;
        private Wing wing;

        private BodyHair bodyHair;
        private PubicHair pubicHair;

        public BodyBuilder(Arm arm, Butt butt, Chest chest, Ear ear, Eye eye, Face face, Hair hair, Leg leg, Skin skin) {
            this.arm = arm;
            this.butt = butt;
            this.chest = chest;
            this.ear = ear;
            this.eye = eye;
            this.face = face;
            this.hair = hair;
            this.leg = leg;
            this.skin = skin;
        }

        public BodyBuilder appendPenis(Penis penis) {
            this.penis = penis;
            return this;
        }

        public BodyBuilder appendTesticle(Testicle testicle) {
            this.testicle = testicle;
            return this;
        }

        public BodyBuilder appendVagina(Vagina vagina) {
            this.vagina = vagina;
            return this;
        }

        public BodyBuilder appendOvary(Ovary ovary) {
            this.ovary = ovary;
            return this;
        }

        public BodyBuilder appendAnus(Anus anus) {
            this.anus = anus;
            return this;
        }

        public BodyBuilder appendHorn(Horn horn) {
            this.horn = horn;
            return this;
        }

        public BodyBuilder appendTail(Tail tail) {
            this.tail = tail;
            return this;
        }

        public BodyBuilder appendWing(Wing wing) {
            this.wing = wing;
            return this;
        }

        public BodyBuilder appendBodyHair(BodyHair bodyHair) {
            this.bodyHair = bodyHair;
            return this;
        }

        public BodyBuilder appendPubicHair(PubicHair pubicHair) {
            this.pubicHair = pubicHair;
            return this;
        }

        @Override
        public Body build() {
            return new Body(this);
        }
    }

    private Body(BodyBuilder builder) {
        arm = builder.arm;
        butt = builder.butt;
        chest = builder.chest;
        ear = builder.ear;
        eye = builder.eye;
        face = builder.face;
        hair = builder.hair;
        leg = builder.leg;
        skin = builder.skin;

        penis = builder.penis;
        testicle = builder.testicle;
        vagina = builder.vagina;
        ovary = builder.ovary;
        anus = builder.anus;

        horn = builder.horn;
        tail = builder.tail;
        wing = builder.wing;

        bodyHair = builder.bodyHair;
        pubicHair = builder.pubicHair;
    }
}
