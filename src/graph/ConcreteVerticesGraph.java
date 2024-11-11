package graph;

import java.util.*;

public class ConcreteVerticesGraph implements Graph<String> {

    private final Set<String> vertices = new HashSet<>();
    private final Map<String, Map<String, Integer>> edges = new HashMap<>();

    private void checkRep() {
        for (Map.Entry<String, Map<String, Integer>> entry : edges.entrySet()) {
            for (int weight : entry.getValue().values()) {
                assert weight >= 0 : "Edge weight cannot be negative";
            }
        }
    }
    
    @Override
    public boolean add(String vertex) {
        if (vertices.add(vertex)) {
            edges.put(vertex, new HashMap<>());
            checkRep();
            return true;
        }
        return false;
    }

    @Override
    public int set(String source, String target, int weight) {
        if (weight < 0) throw new IllegalArgumentException("Weight cannot be negative");
        add(source);
        add(target);

        Integer previous = edges.get(source).put(target, weight);
        if (weight == 0) edges.get(source).remove(target);

        checkRep();
        return previous == null ? 0 : previous;
    }

    @Override
    public boolean remove(String vertex) {
        if (vertices.remove(vertex)) {
            edges.remove(vertex);
            edges.values().forEach(map -> map.remove(vertex));
            checkRep();
            return true;
        }
        return false;
    }

    @Override
    public Set<String> vertices() {
        return Collections.unmodifiableSet(vertices);
    }

    @Override
    public Map<String, Integer> sources(String target) {
        Map<String, Integer> sources = new HashMap<>();
        for (String vertex : vertices) {
            edges.get(vertex).forEach((t, weight) -> {
                if (t.equals(target)) sources.put(vertex, weight);
            });
        }
        return Collections.unmodifiableMap(sources);
    }

    @Override
    public Map<String, Integer> targets(String source) {
        return Collections.unmodifiableMap(edges.getOrDefault(source, new HashMap<>()));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Vertices: ").append(vertices).append("\nEdges:\n");
        edges.forEach((src, targets) -> targets.forEach((target, weight) -> 
            sb.append(src).append(" -> ").append(target).append(" : ").append(weight).append("\n")));
        return sb.toString();
    }

}
