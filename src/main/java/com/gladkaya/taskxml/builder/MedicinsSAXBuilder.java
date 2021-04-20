package com.gladkaya.taskxml.builder;

import com.gladkaya.taskxml.entity.Medicin;
import com.gladkaya.taskxml.exception.ProjectException;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class MedicinsSAXBuilder extends AbstractMedicinsBuilder {
    private MedicinHandler handler;
    private XMLReader reader;

    public MedicinsSAXBuilder() {
        medicins = new HashSet<>();
    }

    public MedicinsSAXBuilder(Set<Medicin> medicins) throws ProjectException {
        super(medicins);
        handler = new MedicinHandler();
        try {
            reader = XMLReaderFactory.createXMLReader();
            reader.setContentHandler(handler);
        } catch (SAXException e) {
            throw new ProjectException("Error of SAX parser: " + e);
        }
    }

    public Set<Medicin> getMedicins() {
        return medicins;
    }

    @Override
    public void buildSetMedicins(String fileName) throws ProjectException {
        try {
            reader.parse(fileName);
        } catch (SAXException e) {
            throw new ProjectException("Error of SAX parser: " + e);
        } catch (IOException e) {
            throw new ProjectException("Error of I/O: " + e);
        }
        medicins = handler.getMedicins();
    }
}
