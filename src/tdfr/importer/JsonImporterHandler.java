package tdfr.importer;

import java.io.IOException;

import org.json.simple.parser.ContentHandler;
import org.json.simple.parser.ParseException;

import tdfr.Node;
import tdfr.graph.Graph;

public class JsonImporterHandler implements ContentHandler {

	/** The graph to which nodes are added. */
	private Graph graph;
	/** The node currently being built. */
	private Node node;
	/** Key of the current object entry. */
	private String key;
	
	JsonImporterHandler(Graph graph) {
		this.graph = graph;
	}
	
	@Override
	public void startJSON() throws ParseException, IOException {
	}

	@Override
	public void endJSON() throws ParseException, IOException {
	}

	@Override
	public boolean startArray() throws ParseException, IOException {
		return true;
	}

	@Override
	public boolean endArray() throws ParseException, IOException {
		return true;
	}

	@Override
	public boolean startObject() throws ParseException, IOException {
		node = new Node();
		return true;
	}

	@Override
	public boolean endObject() throws ParseException, IOException {
		graph.addVertex(node);
		return true;
	}

	@Override
	public boolean startObjectEntry(String key) throws ParseException,
			IOException {
		this.key = key;
		return true;
	}

	@Override
	public boolean endObjectEntry() throws ParseException, IOException {
		return true;
	}

	@Override
	public boolean primitive(Object obj) throws ParseException, IOException {
		// todo read in attributes here
		return true;
	}
}
