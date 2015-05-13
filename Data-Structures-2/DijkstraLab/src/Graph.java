import java.util.List;

public class Graph {
	  private final List<Vertex> vertices;
	  private final List<Edge> edges;

	  public Graph(List<Vertex> vertexes, List<Edge> edges) {
	    this.vertices = vertexes;
	    this.edges = edges;
	  }

	  public List<Vertex> getVertexes() {
	    return vertices;
	  }

	  public List<Edge> getEdges() {
	    return edges;
	  }	  
	} 