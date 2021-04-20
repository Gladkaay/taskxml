package com.gladkaya.taskxml.entity;

import java.time.YearMonth;
import java.util.Objects;

public class Medicin {
    private String medicinId;
    private String analog;
    private String name;
    private String pharm;
    private String country;
    private String age;
    private Integer price;
    private YearMonth bestBefore;

    public Medicin() {
    }

    public Medicin(String medicinId, String analog, String name, String pharm, String country, String age,
                   Integer price, YearMonth bestBefore) {
        this.medicinId = medicinId;
        this.analog = analog;
        this.name = name;
        this.pharm = pharm;
        this.country = country;
        this.age = age;
        this.price = price;
        this.bestBefore = bestBefore;
    }
    public String getMedicinId(){
        return medicinId;
    }

    public void setMedicinId(String medicinId) {
        this.medicinId = medicinId;
    }

    public String getAnalog() {
        return analog;
    }

    public void setAnalog(String analog) {
        this.analog = analog;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPharm() {
        return pharm;
    }

    public void setPharm(String pharm) {
        this.pharm = pharm;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public YearMonth getBestBefore() {
        return bestBefore;
    }

    public void setBestBefore(YearMonth bestBefore) {
        this.bestBefore = bestBefore;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MedicinEntity{");
        sb.append("medicinId='").append(medicinId).append('\'');
        sb.append(", analog='").append(analog).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", pharm='").append(pharm).append('\'');
        sb.append(", country='").append(country).append('\'');
        sb.append(", age='").append(age).append('\'');
        sb.append(", price=").append(price);
        sb.append(", bestBefore=").append(bestBefore);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medicin medicin = (Medicin) o;
        return Objects.equals(medicinId, medicin.medicinId) && Objects.equals(analog, medicin.analog) && Objects.equals(name, medicin.name) && Objects.equals(pharm, medicin.pharm) && Objects.equals(country, medicin.country) && Objects.equals(age, medicin.age) && Objects.equals(price, medicin.price) && Objects.equals(bestBefore, medicin.bestBefore);
    }

    @Override
    public int hashCode() {
        return Objects.hash(medicinId, analog, name, pharm, country, age, price, bestBefore);
    }
}
