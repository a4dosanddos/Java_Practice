package com.example.xml.sax;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;

public class Example {
	public static void main(String[] args) {

		InputStream xmlStream = getStream(readFile("xml/test.xml"));

		try {
			XMLInputFactory xmlif = XMLInputFactory.newInstance();
			XMLStreamReader xmlr = xmlif.createXMLStreamReader(xmlStream);

			while (xmlr.hasNext()) {
				switch (xmlr.getEventType()) {
				case XMLStreamConstants.START_ELEMENT:
					System.out.println("element");
					System.out.println(xmlr.getLocalName());
				case XMLStreamConstants.ATTRIBUTE:
					System.out.println("attribute");
					System.out.println(xmlr.getAttributeLocalName(0));
				case XMLStreamConstants.CHARACTERS:
					System.out.println("characters");
					System.out.println(new String(xmlr.getTextCharacters(), xmlr.getTextStart(), xmlr.getTextLength()));
				case XMLStreamConstants.SPACE:
					System.out.println("space");
				default:
					System.out.println("default");
					break;
				}
				xmlr.next();
			}
		} catch (Exception e) {
			e.printStackTrace();
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
