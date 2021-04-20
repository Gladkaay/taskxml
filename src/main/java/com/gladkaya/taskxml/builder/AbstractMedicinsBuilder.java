package com.gladkaya.taskxml.builder;

import com.gladkaya.taskxml.entity.Medicin;
import com.gladkaya.taskxml.exception.ProjectException;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractMedicinsBuilder {
    protected Set<Medicin> medicins;

    public AbstractMedicinsBuilder() {
        medicins = new HashSet<>();
    }

    public AbstractMedicinsBuilder(Set<Medicin> medicins) {
        this.medicins = medicins;
    }

    public Set<Medicin> getMedicins() {
        return medicins;
    }

    abstract public void buildSetMedicins(String fileName) throws ProjectException;
}
