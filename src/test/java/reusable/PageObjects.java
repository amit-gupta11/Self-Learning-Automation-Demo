package reusable;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;


public class PageObjects {

    public static String getXpath(String elementName) {
        String identifier = null;
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

            Document doc = factory.newDocumentBuilder().parse("src/test/resources/pageObjects.xml");

            // Create an XPath object to query the XML
            XPath xpath = XPathFactory.newInstance().newXPath();

            // Select all elements with the name "element"
            String expression = "//element";
            NodeList elements = (NodeList) xpath.evaluate(expression, doc, XPathConstants.NODESET);

            // Loop over the elements and get the identifier value for the specified name
            for (int i = 0; i < elements.getLength(); i++) {
                Element element = (Element) elements.item(i);
                String xmlName = element.getAttribute("name");
                if (elementName.equals(xmlName)) {
                    identifier = element.getAttribute("identifier");
                    System.out.println("Identifier value for " + elementName + ": " + identifier);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return identifier;
    }


}


