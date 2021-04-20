package com.gladkaya.taskxml.builder;

import com.gladkaya.taskxml.entity.*;
import com.gladkaya.taskxml.exception.ProjectException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.YearMonth;
import java.util.HashSet;
import java.util.Set;

public class MedicinsStAXBuilder extends AbstractMedicinsBuilder {
    private final static Logger logger = LogManager.getLogger();
    private Set<Medicin> medicins;
    private XMLInputFactory inputFactory;
    private static final String DEFAULT_ANALOG="no analog";

    public MedicinsStAXBuilder() {
        medicins = new HashSet<>();
    }
    public MedicinsStAXBuilder(Set<Medicin> medicins) {
        super(medicins);
        inputFactory = XMLInputFactory.newInstance();
    }
    public Set<Medicin> getMedicins() {
        return medicins;
    }
    @Override
    public void buildSetMedicins(String fileName) {
        FileInputStream inputStream = null;
        XMLStreamReader reader = null;
        String name;
        try {
            inputStream = new FileInputStream(new File(fileName));
            reader = inputFactory.createXMLStreamReader(inputStream);
            while (reader.hasNext()) {
                int type = reader.next();
                if (type == XMLStreamConstants.START_ELEMENT) {
                    name = reader.getLocalName();
                    if (MedicinXmlTag.valueOf(name.toUpperCase()) == MedicinXmlTag.MEDICAMENT) {
                        Medicin medicin = new Medicament();
                        buildMedicin(medicin, reader);
                        medicins.add(medicin);
                    }
                    if (MedicinXmlTag.valueOf(name.toUpperCase()) == MedicinXmlTag.COSMETIC) {
                        Medicin medicin = new Cosmetic();
                        buildMedicin(medicin, reader);
                        medicins.add(medicin);
                    }
                }

            }
        } catch (FileNotFoundException e) {
            logger.log(Level.INFO, "File "  + fileName + " not found! " + e.getMessage());
        } catch (XMLStreamException e) {
            logger.log(Level.INFO, "StAX parsing error! "  + e.getMessage() );
        } catch (ProjectException e) {
            logger.log(Level.INFO, "Exception: " + e.getMessage());
        } finally {
            try {
                if (inputStream != null){
                    inputStream.close();
                }
            } catch (IOException e) {
                logger.log(Level.INFO,"Impossible close file "  + fileName + " : " + e);
            }
        }
    }
    private Medicin buildMedicin(Medicin med, XMLStreamReader reader) throws ProjectException, XMLStreamException {
        Medicin medicin = new Medicin();
        medicin.setMedicinId(reader.getAttributeValue(null, MedicinXmlTag.ID.name()));
        String analog = reader.getAttributeValue(null, MedicinXmlTag.ANALOG.name());
        if(analog != null) {
            medicin.setAnalog(analog);
        } else {
            medicin.setAnalog(DEFAULT_ANALOG);
        }
        String name;
        while (reader.hasNext()) {
            int type = reader.next();
            switch (type) {
                case XMLStreamConstants.START_ELEMENT:
                    name = reader.getLocalName();
                    Medicament medicament = (Medicament) medicin;
                    Cosmetic cosmetic = (Cosmetic) medicin;
                    switch (MedicinXmlTag.valueOf(name.toUpperCase())){
                        case NAME:
                            medicin.setName(getXMLText(reader));
                            break;
                        case PHARM:
                            medicin.setPharm(getXMLText(reader));
                            break;
                        case COUNTRY:
                            medicin.setCountry(getXMLText(reader));
                            break;
                        case AGE:
                            medicin.setAge(getXMLText(reader));
                            break;
                        case PRICE:
                            medicin.setPrice(Integer.parseInt(getXMLText(reader)));
                            break;
                        case BEST_BEFORE:
                            medicin.setBestBefore(YearMonth.parse(getXMLText(reader)));
                            break;
                        case VERSION:
                            VersionMedicament version = VersionMedicament.valueOf(getXMLText(reader));
                            medicament.setVersion(version);
                            break;
                        case AMOUNT:
                            medicament.setAmount(Integer.parseInt(getXMLText(reader)));
                            break;
                        case APPLICATION:
                            ApplicationCosmetic application = ApplicationCosmetic.valueOf(getXMLText(reader));
                            cosmetic.setApplication(application);
                            break;
                        case VOLUME:
                            cosmetic.setVolume(Integer.parseInt(getXMLText(reader)));
                    }
                    break;
                case XMLStreamConstants.END_ELEMENT:
                    name = reader.getLocalName();
                    if (MedicinXmlTag.valueOf(name.toUpperCase()) == MedicinXmlTag.MEDICAMENT || MedicinXmlTag.valueOf(name.toUpperCase()) == MedicinXmlTag.COSMETIC);{
                        return medicin;
                }
            }
        }
        throw new ProjectException("Unknown element in tag Medicin");
    }
    private String getXMLText (XMLStreamReader reader ) throws ProjectException {
        String text = null;
        try {
            if (reader.hasNext()){
                reader.next();
                text = reader.getText();
            }
        } catch (XMLStreamException e) {
            throw new ProjectException(e);
        }
        return text;
    }
}
