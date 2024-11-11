package graph;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for ConcreteEdgesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteEdgesGraph, as
 * well as tests for that particular implementation.
 */
public class ConcreteEdgesGraphTest extends GraphInstanceTest {
    
    @Override public Graph<String> emptyInstance() {
        return new ConcreteEdgesGraph();
    }
    
    // Test ConcreteEdgesGraph toString
    @Test
    public void testToString() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.add("B");
        graph.set("A", "B", 10);
        String expected = "Vertices: [A, B]\nEdges:\nA -> B : 10\n";
        assertEquals("expected formatted string", expected, graph.toString());
    }
    
    // Additional tests for ConcreteEdgesGraph could go here
    
    @Test
    public void testEdgeEquality() {
        ConcreteEdgesGraph.Edge edge1 = new ConcreteEdgesGraph.Edge("A", "B", 5);
        ConcreteEdgesGraph.Edge edge2 = new ConcreteEdgesGraph.Edge("A", "B", 5);
        assertEquals("expected edges to be equal", edge1, edge2);
    }
}