package com.leizhou.wr.web;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.leizhou.wr.DAO.Person;
import com.leizhou.wr.DAO.Points;
import com.leizhou.wr.DAO.Route;



public class RoutesXML {
	
	private String filePath;
	private String fileName;
	
	public RoutesXML(String filePath, String fileName) {
		this.filePath = filePath;
		this.fileName = fileName;
	}
	public Route readRouteByID(InputStream isr, int ID, String RouteModel) throws ParserConfigurationException, SAXException, IOException{
		DocumentBuilderFactory docFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		Document doc = docBuilder.parse(isr);
		doc.getDocumentElement().normalize();
		
		Route rs=new Route();
		/*//get root point
		Node root = doc.getElementsByTagName("routes").item(0);
		NodeList list = root.getChildNodes();
		
		for (int i = 0; i < list.getLength(); i++) {
			Node Nodechild = list.item(i);
			// NodeList listchild=Nodechild.getChildNodes();

			NamedNodeMap attr = Nodechild.getAttributes();
			Node nodeAttr = attr.getNamedItem("ID");
			//looking for child with same ID
			if (nodeAttr.getNodeValue().equals(String.valueOf(ID))) {
				//found, then;	
				
				//get departure GPS location
				int lat=Integer.valueOf(Nodechild.getFirstChild().getFirstChild().getTextContent());
				int lng=Integer.valueOf(Nodechild.getFirstChild().getLastChild().getTextContent());
				Points depa=new Points(lat,lng);
				
				//get destination GPS location
				lat=Integer.valueOf(Nodechild.getLastChild().getFirstChild().getTextContent());
				lng=Integer.valueOf(Nodechild.getLastChild().getLastChild().getTextContent());
				Points dest=new Points(lat,lng);
				if(RouteModel.equalsIgnoreCase("offer")){
					Person driver=new Person(String.valueOf(ID),depa,dest);
					
					rs=new Route(RouteModel,1);//only 1 seat for this test
					
					rs.addDrive(driver);
				}
				if(RouteModel.equalsIgnoreCase("request")){
					Person passenger=new Person(String.valueOf(ID),depa,dest);
					
					rs=new Route(RouteModel,0);//only 1 seat for this test
					
					//rs.addPassenger(passenger);
				}

			}
		}*/
		return rs;

	}

	public void AddRoute(InputStream isr, String ID, Points depa,
			Points dest) throws SAXException, IOException,
			ParserConfigurationException, TransformerFactoryConfigurationError,
			TransformerException {

		DocumentBuilderFactory docFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		Document doc = docBuilder.parse(isr);
		doc.getDocumentElement().normalize();
		
		//get root point
		Node root = doc.getElementsByTagName("routes").item(0);

		Element route = doc.createElement("route");
		// add as point
		root.appendChild(route);
		
		// set attribute to route element
		Attr attr = doc.createAttribute("ID");
		attr.setValue(ID);
		route.setAttributeNode(attr);
		
		Element depature= doc.createElement("depature");
		route.appendChild(depature);
		
		// point's lat lng value
		Element lat_start = doc.createElement("lat");
		lat_start.appendChild(doc.createTextNode(String.valueOf(depa.getLatE6())));
		depature.appendChild(lat_start);

		Element lng_start = doc.createElement("lng");
		lng_start.appendChild(doc.createTextNode(String.valueOf(depa.getLngE6())));
		depature.appendChild(lng_start);
		
		Element destenation= doc.createElement("destenation");
		route.appendChild(destenation);
		
		Element lat_end = doc.createElement("lat");
		lat_end.appendChild(doc.createTextNode(String.valueOf(dest.getLatE6())));
		destenation.appendChild(lat_end);

		Element lng_end = doc.createElement("lng");
		lng_end.appendChild(doc.createTextNode(String.valueOf(dest.getLngE6())));
		destenation.appendChild(lng_end);


		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory
				.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(
				new File(filePath + fileName));
		transformer.transform(source, result);
	}
	public void createXML() throws ParserConfigurationException,
			TransformerException {
		
		// root elements
		DocumentBuilderFactory docFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		Document doc = docBuilder.newDocument();

		// create root element
		Element rootPoints = doc.createElement("routes");
		doc.appendChild(rootPoints);

		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory
				.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(
				new File(filePath + fileName));
		transformer.transform(source, result);

	}
}
