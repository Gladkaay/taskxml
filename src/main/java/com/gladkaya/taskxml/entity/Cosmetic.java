package com.gladkaya.taskxml.entity;

import java.time.YearMonth;
import java.util.Objects;

public class Cosmetic extends Medicin {
    private ApplicationCosmetic application;
    private Integer volume;

    public Cosmetic(String medicinId, String analog, String name, String pharm, String country, String age,
                    Integer price, YearMonth bestBefore, ApplicationCosmetic application, Integer volume) {
        super(medicinId, analog, name, pharm, country, age, price, bestBefore);
        this.application = application;
        this.volume = volume;
    }

    public Cosmetic() {

    }

    public ApplicationCosmetic getApplication() {
        return application;
    }

    public void setApplication(ApplicationCosmetic application) {
        this.application = application;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Cosmetic cosmetic = (Cosmetic) o;
        return application == cosmetic.application && Objects.equals(volume, cosmetic.volume);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), application, volume);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Cosmetic{");
        sb.append("application=").append(application);
        sb.append(", volume=").append(volume);
        sb.append('}');
        return sb.toString();
    }
}
