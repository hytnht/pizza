package org.hy.pizza.model;

public enum City {
    HN("HA_NOI"),
    DN("DA_NANG"),
    HCM("HO_CHI_MINH");

    private final String value;

    City(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public City fromCity(String value) throws Exception {
        switch (value) {
            case "HA_NOI":
                return HN;
            case "HO_CHI_MINH":
                return HCM;
            case "DA_NANG":
                return DN;
            default: throw new Exception("We are not in this city yet");
        }
    }
}
