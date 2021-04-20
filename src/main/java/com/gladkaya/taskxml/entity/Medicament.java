package com.gladkaya.taskxml.entity;

import java.time.YearMonth;
import java.util.Objects;

public class Medicament extends Medicin{
    private VersionMedicament version;
    private Integer amount;

    public Medicament(String medicinId, String analog, String name, String pharm, String country, String age,
                      Integer price, YearMonth bestBefore, VersionMedicament version, Integer amount) {
        super(medicinId, analog, name, pharm, country, age, price, bestBefore);
        this.version = version;
        this.amount = amount;
    }

    public Medicament() {

    }

    public VersionMedicament getVersion() {
        return version;
    }

    public void setVersion(VersionMedicament version) {
        this.version = version;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Medicament that = (Medicament) o;
        return version == that.version && Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), version, amount);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Medicament{");
        sb.append("version=").append(version);
        sb.append(", amount=").append(amount);
        sb.append('}');
        return sb.toString();
    }
}
