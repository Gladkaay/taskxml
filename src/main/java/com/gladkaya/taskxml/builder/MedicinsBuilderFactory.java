package com.gladkaya.taskxml.builder;

import com.gladkaya.taskxml.exception.ProjectException;

public class MedicinsBuilderFactory {
    public enum TypeParser {
        SAX, STAX, DOM
    }

    public static AbstractMedicinsBuilder createMedicinBuilder(String typeParser) throws ProjectException {
        TypeParser type = TypeParser.valueOf(typeParser.toUpperCase());
        switch (type) {
            case DOM:
                return new MedicinsDOMBuilder();
            case SAX:
                return new MedicinsSAXBuilder();
            case STAX:
                return new MedicinsStAXBuilder();
            default:
                throw new ProjectException("Parser " + typeParser + " doesn't exist");
        }
    }
}
