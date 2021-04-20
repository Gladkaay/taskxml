package com.gladkaya.taskxml.builder;

import com.gladkaya.taskxml.entity.*;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.time.YearMonth;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class MedicinHandler extends DefaultHandler {
    private Set<Medicin> medicins;
    private Medicin current;
    private MedicinXmlTag currentTag;
    private EnumSet<MedicinXmlTag> withText;
    private static final String UNDERSCORE = "_";
    private static final String HYPHEN = "-";
    private static final String DEFAULT_ANALOG="no analog";

    String medicament = MedicinXmlTag.MEDICAMENT.name();
    String cosmetic = MedicinXmlTag.COSMETIC.name();

    public MedicinHandler() {
        medicins = new HashSet<Medicin>();
        withText = EnumSet.range(MedicinXmlTag.NAME, MedicinXmlTag.AGE);
    }

    public Set<Medicin> getMedicins() {
       return medicins;
    }

    public void startElement(String uri, String localName, String qName, Attributes attrs){
        if (medicament.equals(qName) || cosmetic.equals(qName)) {
            current = medicament.equals(qName) ? new Medicament() : new Cosmetic();
            current.setMedicinId(attrs.getValue(0));
            if (attrs.getValue(1) != null) {
                current.setAnalog(attrs.getValue(1));
            } else {
                current.setAnalog(DEFAULT_ANALOG);
            }
        } else {
            MedicinXmlTag temp = MedicinXmlTag.valueOf(qName.toUpperCase().replace(HYPHEN, UNDERSCORE));
            if (withText.contains(temp)) {
                currentTag = temp;
            }
        }
    }
    public void endElement(String uri, String localName, String qName) {
        if (medicament.equals(qName) || cosmetic.equals(qName)) {
            medicins.add(current);
        }
    }
    public void characters(char[] ch, int start, int length) {
        String s = new String(ch, start, length);
        if (currentTag != null) {
            switch (currentTag) {
                case NAME:
                    current.setName(s);
                    break;
                case PHARM:
                    current.setPharm(s);
                    break;
                case COUNTRY:
                    current.setCountry(s);
                    break;
                case AGE:
                    current.setAge(s);
                    break;
                case PRICE:
                    current.setPrice(Integer.parseInt(s));
                    break;
                case BEST_BEFORE:
                    current.setBestBefore(YearMonth.parse(s));
                    break;
                case VERSION:
                    VersionMedicament version = VersionMedicament.valueOf(s);
                    ((Medicament) current).setVersion(version);
                    break;
                case AMOUNT:
                    ((Medicament) current).setAmount(Integer.parseInt(s));
                    break;
                case APPLICATION:
                    ApplicationCosmetic application = ApplicationCosmetic.valueOf(s);
                    ((Cosmetic) current).setApplication(application);
                    break;
                case VOLUME:
                    ((Cosmetic) current).setVolume(Integer.parseInt(s));
                default:
                    throw new EnumConstantNotPresentException(
                            currentTag.getDeclaringClass(),
                            currentTag.name());
            }
        }
        currentTag = null;
    }
}
