package graph;

import java.util.*;

/**
 * An implementation of Graph with vertices and directed edges with weights.
 * 
 * This class is mutable and represents a graph where edges can be modified by updating their weights.
 */
public class ConcreteEdgesGraph implements Graph<String> {
    
    private final Set<String> vertices = new HashSet<>();
    private final List<Edge> edges = new ArrayList<>();
    
    // Constructor
    public ConcreteEdgesGraph() {
        checkRep();
    }
    
    // Representation invariant check
    private void checkRep() {
        for (Edge edge : edges) {
            assert edge.getWeight() >= 0 : "Edge weight cannot be negative";
            assert vertices.contains(edge.getSource()) && vertices.contains(edge.getTarget())
                    : "Vertices of each edge must be in the graph";
        }
    }
    
    @Override
    public boolean add(String vertex) {
        boolean added = vertices.add(vertex);
        checkRep();
        return added;
    }
    
    @Override
    public int set(String source, String target, int weight) {
        if (!vertices.contains(source)) vertices.add(source);
        if (!vertices.contains(target)) vertices.add(target);

        for (Edge edge : edges) {
            if (edge.getSource().equals(source) && edge.getTarget().equals(target)) {
                int previousWeight = edge.getWeight();
                if (weight == 0) {
                    edges.remove(edge);
                } else {
                    edge.setWeight(weight);
                }
                checkRep();
                return previousWeight;
            }
        }
        
        if (weight > 0) {
            edges.add(new Edge(source, target, weight));
        }
        checkRep();
        return 0;
    }
    
    @Override
    public boolean remove(String vertex) {
        boolean removed = vertices.remove(vertex);
        if (removed) {
            edges.removeIf(edge -> edge.getSource().equals(vertex) || edge.getTarget().equals(vertex));
        }
        checkRep();
        return removed;
    }
    
    @Override
    public Set<String> vertices() {
        return Collections.unmodifiableSet(new HashSet<>(vertices));
    }
    
    @Override
    public Map<String, Integer> sources(String target) {
        Map<String, Integer> sources = new HashMap<>();
        for (Edge edge : edges) {
            if (edge.getTarget().equals(target)) {
                sources.put(edge.getSource(), edge.getWeight());
            }
        }
        return Collections.unmodifiableMap(sources);
    }
   
    @Override
    public Map<String, Integer> targets(String source) {
        Map<String, Integer> targets = new HashMap<>();
        for (Edge edge : edges) {
            if (edge.getSource().equals(source)) {
                targets.put(edge.getTarget(), edge.getWeight());
            }
        }
        return Collections.unmodifiableMap(targets);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Vertices: ").append(vertices).append("\nEdges:\n");
        for (Edge edge : edges) {
            sb.append(edge).append("\n");
        }
        return sb.toString();
    }

    /**
     * Represents a directed edge with a weight in a graph.
     * Immutable and internal to the ConcreteEdgesGraph class.
     */
    public static class Edge {
        
        private final String source;
        private final String target;
        private int weight;
        
        // Constructor
        public Edge(String source, String target, int weight) {
            this.source = source;
            this.target = target;
            this.weight = weight;
            checkRep();
        }
        
        // Representation invariant check
        private void checkRep() {
            assert weight >= 0 : "Edge weight cannot be negative";
        }
        
        public String getSource() {
            return source;
        }
        
        public String getTarget() {
            return target;
        }
        
        public int getWeight() {
            return weight;
        }
        
        public void setWeight(int weight) {
            this.weight = weight;
            checkRep();
        }
        
        @Override
        public String toString() {
            return source + " -> " + target + " : " + weight;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Edge edge = (Edge) obj;
            return source.equals(edge.source) && target.equals(edge.target) && weight == edge.weight;
        }

        @Override
        public int hashCode() {
            return Objects.hash(source, target, weight);
        }
    }
}
