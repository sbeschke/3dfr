package tdfr.importer;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;
import java.util.Set;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import tdfr.Node;
import tdfr.graph.Graph;

public class JsonImporter {
	public Graph loadFile(String fileName) {
		Graph graph = new Graph();
		JSONParser parser = new JSONParser();
		JsonImporterHandler handler = new JsonImporterHandler(graph);
		FileReader reader;
		try {
			reader = new FileReader(fileName);
			parser.parse(reader, handler);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Random rand = new Random();
		Set<Node> nodes = graph.vertexSet();
		
		System.out.println(nodes.size());
		
		for(Node n : nodes) {
			n.setX(rand.nextFloat()*400);
			n.setY(rand.nextFloat()*400);
			n.setZ(rand.nextFloat()*400);
		}

		Object[] nodesA = nodes.toArray();
		for (int i = 0; i < nodes.size(); i++) {
			int size = nodes.size();
			graph.addEdge(
					(Node) nodesA[rand.nextInt(size)],
					(Node) nodesA[rand.nextInt(size)]);
		}
		
		return graph;
	}
}
