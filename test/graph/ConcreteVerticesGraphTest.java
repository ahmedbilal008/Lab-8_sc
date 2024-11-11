package graph;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for ConcreteVerticesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteVerticesGraph, as
 * well as tests for that particular implementation.
 */
public class ConcreteVerticesGraphTest extends GraphInstanceTest {
    
    @Override public Graph<String> emptyInstance() {
        return new ConcreteVerticesGraph();
    }
    
    // Test ConcreteVerticesGraph toString
    @Test
    public void testToString() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.add("B");
        graph.set("A", "B", 5);
        String expected = "Vertices: [A, B]\nEdges:\nA -> B : 5\n";
        assertEquals("expected formatted string", expected, graph.toString());
    }
    
    // Additional tests for ConcreteVerticesGraph could go here
}