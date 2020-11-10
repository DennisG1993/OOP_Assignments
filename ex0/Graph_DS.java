
package ex0;

import java.util.Collection;
import java.util.HashMap;


public class Graph_DS implements graph  {
	private HashMap<Integer, node_data> nodes;
	private int numberOfEdges = 0;
	private int numberOfChanges = 0;
	
	public Graph_DS() {
		this.nodes = new HashMap<Integer, node_data>();
	}

	@Override
	public node_data getNode(int key) { return this.nodes.get(key); }

	@Override
	public boolean hasEdge(int node1, int node2) {
		node_data firstNode = this.getNode(node1);
		if (firstNode != null) return firstNode.hasNi(node2);
		return false;
	}

	@Override
	public void addNode(node_data n) {
		int key = n.getKey();
		node_data isNodePresentInGraph = this.nodes.putIfAbsent(key, n);
		if (isNodePresentInGraph == null) {
			this.numberOfChanges++;
		}
	}

	@Override
	public void connect(int node1, int node2) {
		node_data firstNodeFromGraph = this.getNode(node1);
		node_data secondNodeFromGraph = this.getNode(node2);
		if (firstNodeFromGraph != null && secondNodeFromGraph != null) {
			boolean isAlreadyConnected = this.hasEdge(firstNodeFromGraph.getKey(), secondNodeFromGraph.getKey());
			boolean isSameNode = node1 == node2;
			if (!isAlreadyConnected && !isSameNode) {
				firstNodeFromGraph.addNi(secondNodeFromGraph);
				this.numberOfChanges++;
				this.numberOfEdges++;
			}
		}
	}

	@Override
	public Collection<node_data> getV() { return this.nodes.values(); }

	@Override
	public Collection<node_data> getV(int node_id) {
		node_data nodeFromGraph = this.getNode(node_id);
		if (nodeFromGraph != null) {
			return nodeFromGraph.getNi();
		}
		return null;
	}

	@Override
	public node_data removeNode(int key) {
		node_data nodeFromGraph = this.getNode(key);
		if (nodeFromGraph != null) {
			Collection<node_data> neighbours = nodeFromGraph.getNi();
			while(!neighbours.isEmpty()) {
				try {
					node_data currentNeighbor = neighbours.stream().findFirst().get();
					int key2 = currentNeighbor.getKey();
					this.removeEdge(key2, key);
				} catch(Exception e) {
					
				}
			}
			this.nodes.remove(key);
			this.numberOfChanges++;
		}
		return nodeFromGraph;
	}

	@Override
	public void removeEdge(int node1, int node2) {
		if (hasEdge(node1, node2)) {
			node_data firstNodeFromGraph = this.getNode(node1);
			node_data secondNodeFromGraph = this.getNode(node2);
			firstNodeFromGraph.removeNode(secondNodeFromGraph);
			this.numberOfChanges++;
			this.numberOfEdges--;
		}
	}

	@Override
	public int nodeSize() { return this.nodes.size(); }

	@Override
	public int edgeSize() { return this.numberOfEdges; }

	@Override
	public int getMC() { return this.numberOfChanges; }

}
