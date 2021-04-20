package com.gladkaya.taskxml.builder;

import com.gladkaya.taskxml.entity.*;
import com.gladkaya.taskxml.exception.ProjectException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.time.YearMonth;
import java.util.HashSet;
import java.util.Set;

public class MedicinsDOMBuilder extends AbstractMedicinsBuilder{
    private DocumentBuilder documentBuilder;
    private static final String DEFAULT_ANALOG="no analog";

    public MedicinsDOMBuilder() {
        medicins = new HashSet<>();
    }

    public MedicinsDOMBuilder(Set<Medicin> medicins) throws ProjectException {
        super(medicins);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            documentBuilder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new ProjectException("Error of parser configuration: " + e);
        }
    }
    public Set<Medicin> getMedicins() {
        return medicins;
    }

    @Override
    public void buildSetMedicins(String fileName) throws ProjectException {
        Document doc = null;
        try {
            doc = documentBuilder.parse(fileName);
            Element root = doc.getDocumentElement();
            NodeList medicamentList = root.getElementsByTagName("medicin");
            NodeList cosmeticList = root.getElementsByTagName("medicin");
            for (int i = 0; i < medicamentList.getLength(); i++) {
                Element element = (Element) medicamentList.item(i);
                Medicin medicin = new Medicament();
                buildMedicin(element, medicin);
                medicins.add(medicin);
            }
            for (int i = 0; i < cosmeticList.getLength(); i++) {
                Element element = (Element) cosmeticList.item(i);
                Medicin medicin = new Cosmetic();
                buildMedicin (element, medicin);
                medicins.add(medicin);
            }
        } catch (SAXException e) {
            throw new ProjectException("Parsing failure: " + e);
        } catch (IOException e) {
            throw new ProjectException("File error or I/O error: " + e);
        }
    }

    private void buildMedicin(Element medicinElement, Medicin med) {
        Medicin medicin = new Medicin();
        medicin.setMedicinId(medicinElement.getAttribute("medicinId"));
        if (medicinElement.getAttribute("analog") != null) {
            medicin.setAnalog(medicinElement.getAttribute("analog"));
        } else {
            medicin.setAnalog(DEFAULT_ANALOG);
        }
        medicin.setName(getElementTextContent(medicinElement, "name"));
        medicin.setPharm(getElementTextContent(medicinElement, "pharm"));
        medicin.setCountry(getElementTextContent(medicinElement, "country"));
        medicin.setAge(getElementTextContent(medicinElement, "age"));
        medicin.setPrice(Integer.parseInt(getElementTextContent(medicinElement, "price")));
        medicin.setBestBefore(YearMonth.parse(getElementTextContent(medicinElement, "best-before")));

        if (medicin.getClass() == Medicament.class) {
            Medicament medicament = (Medicament) medicin;
            VersionMedicament version = VersionMedicament.valueOf(getElementTextContent(medicinElement, "version"));
            medicament.setVersion(version);
            medicament.setAmount(Integer.parseInt(getElementTextContent(medicinElement, "amount")));
        } else {
            Cosmetic cosmetic = (Cosmetic) medicin;
            ApplicationCosmetic application = ApplicationCosmetic.valueOf(getElementTextContent(medicinElement, "application"));
            cosmetic.setApplication(application);
            cosmetic.setVolume(Integer.parseInt(getElementTextContent(medicinElement, "volume")));
        }
    }
    private static String getElementTextContent(Element element, String elementName){
        NodeList nodeList = element.getElementsByTagName(elementName);
        Node node = nodeList.item(0);
        String text = node.getTextContent();
        return text;
    }
}
