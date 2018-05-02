package com.example.xml.sax;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;

public class Example {
	public static void main(String[] args) {

		InputStream xmlStream = getStream(readFile("xml/test.xml"));
		XMLStreamReader xmlr = null;
		try {
			XMLInputFactory xmlif = XMLInputFactory.newInstance();
			xmlr = xmlif.createXMLStreamReader(xmlStream);

			while (xmlr.hasNext()) {
				switch (xmlr.getEventType()) {
				case XMLStreamConstants.START_ELEMENT:
					String localName = xmlr.getLocalName();
					List<String> attrLocalNames = new ArrayList<>();
					List<String> attrValues = new ArrayList<>();
					for(int i = 0; i < xmlr.getAttributeCount(); i++) {
						attrLocalNames.add(xmlr.getAttributeLocalName(i));
						attrValues.add(xmlr.getAttributeValue(i));
					}
					System.out.println("=====");
					System.out.println(localName);
					System.out.println(attrLocalNames);
					System.out.println(attrValues);
					break;
				case XMLStreamConstants.CHARACTERS:
					System.out.println(new String(xmlr.getTextCharacters(), xmlr.getTextStart(), xmlr.getTextLength()));
					break;
				case XMLStreamConstants.SPACE:
					System.out.println("SPACE");
					break;
				case XMLStreamConstants.COMMENT:
					System.out.println(xmlr.getText());
					break;
				default:
					break;
				}
				xmlr.next();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				xmlr.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	private static String readFile(String file) {
		StringBuffer buffer = new StringBuffer();
		try {
			Files.lines(Paths.get(file)).forEach(s -> buffer.append(s));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}

	private static InputStream getStream(String xml) {
		return new ByteArrayInputStream(xml.getBytes());
	}
}
