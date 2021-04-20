package com.gladkaya.taskxml.entity;

public enum MedicinXmlTag {
    MEDICINS("medicins"),
    ID("id"),
    ANALOG("analog"),
    MEDICIN("medicin"),
    NAME("name"),
    PHARM("pharm"),
    COUNTRY("country"),
    AGE("age"),
    PRICE("price"),
    BEST_BEFORE("best-before"),
    MEDICAMENT("medicament"),
    COSMETIC("cosmetic"),
    VERSION("version"),
    AMOUNT("amount"),
    APPLICATION("application"),
    VOLUME("volume");
    private String value;

    MedicinXmlTag(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
