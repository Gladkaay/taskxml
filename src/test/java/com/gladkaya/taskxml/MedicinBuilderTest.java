package com.gladkaya.taskxml;

import com.gladkaya.taskxml.builder.AbstractMedicinsBuilder;
import com.gladkaya.taskxml.builder.MedicinHandler;
import com.gladkaya.taskxml.builder.MedicinsDOMBuilder;
import com.gladkaya.taskxml.entity.Medicin;
import com.gladkaya.taskxml.exception.ProjectException;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.IOException;
import java.util.Set;

public class MedicinBuilderTest {
   /* private static final String fileName = "src/main/resources/data/medicins.xml";
    private Set<Medicin> medicin;

    @Test
    public void MedicinsDomBuilderTest() throws ProjectException {
        MedicinsDOMBuilder domBuilder = new MedicinsDOMBuilder();
        domBuilder.buildSetMedicins(fileName);
        System.out.println(domBuilder.getMedicins());
    //    Assert.assertEquals(set, expectedSet);
    }*/

   // @Test
   /* public void MedicinsSaxBuilderTest() throws ProjectException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        try {
            SAXParser parser = factory.newSAXParser();
            MedicinHandler handler = new MedicinHandler();
            parser.parse(fileName, handler);
            Set<Medicin> medicin = MedicinHandler.medicins;
            for (Medicin medicins: medicin) {
                System.out.println(medicins);
            }
        } catch (ParserConfigurationException e) {
            throw new ProjectException(e);
        } catch (SAXException e) {
            throw new ProjectException(e);
        } catch (IOException e) {
            throw new ProjectException(e);
        }

    }*/
}
