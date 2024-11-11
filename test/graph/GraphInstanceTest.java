package graph;

import static org.junit.Assert.*;

import java.util.Collections;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

/**
 * Tests for instance methods of Graph.
 * 
 * <p>PS2 instructions: you MUST NOT add constructors, fields, or non-@Test
 * methods to this class, or change the spec of {@link #emptyInstance()}.
 * Your tests MUST only obtain Graph instances by calling emptyInstance().
 * Your tests MUST NOT refer to specific concrete implementations.
 */
public abstract class GraphInstanceTest {
    
    /**
     * Overridden by implementation-specific test classes.
     * 
     * @return a new empty graph of the particular implementation being tested
     */
    public abstract Graph<String> emptyInstance();
    
    // Test that assertions are enabled
    @Test(expected=AssertionError.class)
    public void testAssertionsEnabled() {
        assert false; // make sure assertions are enabled with VM argument: -ea
    }
    
    // Test a new graph has no vertices
    @Test
    public void testInitialVerticesEmpty() {
        assertEquals("expected new graph to have no vertices",
                Collections.emptySet(), emptyInstance().vertices());
    }
    
    // Test adding a single vertex
    @Test
    public void testAddVertex() {
        Graph<String> graph = emptyInstance();
        assertTrue("expected true for adding a new vertex", graph.add("A"));
        assertTrue("expected graph to contain vertex A", graph.vertices().contains("A"));
    }
    
    // Test adding a duplicate vertex
    @Test
    public void testAddDuplicateVertex() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        assertFalse("expected false for adding duplicate vertex", graph.add("A"));
    }
    
    // Test setting edges
    @Test
    public void testSetEdge() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.add("B");
        assertEquals("expected 0 as no previous edge existed", 0, graph.set("A", "B", 5));
        assertEquals("expected weight 5 from A to B", 5, graph.sources("B").get("A").intValue());
        assertEquals("expected weight 5 from A to B", 5, graph.targets("A").get("B").intValue());
    }
    
    // Test removing a vertex
    @Test
    public void testRemoveVertex() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.add("B");
        graph.set("A", "B", 5);
        assertTrue("expected true on removing existing vertex", graph.remove("A"));
        assertFalse("expected vertex A removed", graph.vertices().contains("A"));
        assertFalse("expected edge from A to B removed", graph.sources("B").containsKey("A"));
    }
    
    // Test sources and targets of vertices
    @Test
    public void testSourcesAndTargets() {
        Graph<String> graph = emptyInstance();
        graph.add("A");
        graph.add("B");
        graph.set("A", "B", 3);
        
        Map<String, Integer> sources = graph.sources("B");
        Map<String, Integer> targets = graph.targets("A");
        
        assertEquals("expected one source for B", 1, sources.size());
        assertEquals("expected one target for A", 1, targets.size());
        assertEquals("expected source A for B", Integer.valueOf(3), sources.get("A"));
        assertEquals("expected target B for A", Integer.valueOf(3), targets.get("B"));
    }
}