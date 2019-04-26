package com.example.peceventsystem;

public class eventinfoclass {
    private String key, name,Club_society,etdate,venue,or1name,or1contact,or2name,or2contact;

    public eventinfoclass(String key, String name, String club_society, String etdate, String venue, String or1name, String or1contact, String or2name, String or2contact) {
        this.key = key;
        this.name = name;
        Club_society = club_society;
        this.etdate = etdate;
        this.venue = venue;
        this.or1name = or1name;
        this.or1contact = or1contact;
        this.or2name = or2name;
        this.or2contact = or2contact;

    }

    public String getKey() {
        return key;
    }

    public String getName() {
        return name;
    }

    public String getClub_society() {
        return Club_society;
    }

    public String getEtdate() {
        return etdate;
    }

    public String getVenue() {
        return venue;
    }

    public String getOr1name() {
        return or1name;
    }

    public String getOr1contact() {
        return or1contact;
    }

    public String getOr2name() {
        return or2name;
    }

    public String getOr2contact() {
        return or2contact;
    }
}
