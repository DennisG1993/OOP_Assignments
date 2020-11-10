package ex0;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Graph_Algo implements graph_algorithms {
	private graph graph;
	
	@Override
	public void init(graph g) { this.graph = g; }

	@Override
	public graph copy() {
		graph deepCopiedGraph = new Graph_DS();
		try {
			for (node_data currentNode: this.graph.getV()) {
				int key = copyNodeToGraph(currentNode, deepCopiedGraph);
				for (node_data currentNeighbor: this.graph.getV(key)) {
					int neighbourKey = copyNodeToGraph(currentNeighbor, deepCopiedGraph);
					deepCopiedGraph.connect(key, neighbourKey);
				}
			}
		} catch (Exception e) {}
		return deepCopiedGraph;
	}
	
	private static int copyNodeToGraph(node_data node, graph g) {
		int key = node.getKey();
		node_data isNodePresentInGraph = g.getNode(key);
		if (isNodePresentInGraph == null) {
			String info = node.getInfo();
			int tag = node.getTag();
			node_data duplicateNode = new NodeData(key, info, tag);
			g.addNode(duplicateNode);
		}
		return key;
	}

	@Override
	public boolean isConnected() {
		if (this.graph.nodeSize() == 0) return true;
		boolean isConnected = true;
		node_data graphsFirstNode = this.graph.getV().stream().findFirst().get();
		BFS(graphsFirstNode.getKey());
		for(node_data node: this.graph.getV()) {
			if (node.getTag() == Integer.MIN_VALUE) {
				isConnected = false;
				break;
			}
		}
		resetAllTagsAndInfos();
		return isConnected;
	}
	
	@Override
	public int shortestPathDist(int src, int dest) {
		int path = -1;
		BFS(src);
		node_data destNode = this.graph.getNode(dest);
		if(destNode != null && destNode.getTag() != Integer.MIN_VALUE) {
			path = destNode.getTag();
		}
		resetAllTagsAndInfos();
		return path;

	}

	@Override
	public List<node_data> shortestPath(int src, int dest) {
		List<node_data> path = new ArrayList<>();
		BFS(src);
		node_data destNode = this.graph.getNode(dest);
		if(destNode != null && destNode.getInfo().length() > 0) {
			for(String key: destNode.getInfo().split("-")) {
				int intKey = Integer.parseInt(key);
				node_data node = this.graph.getNode(intKey);
				path.add(node);
			}
			resetAllTagsAndInfos();
			return path;
		}
		resetAllTagsAndInfos();
		return null;
	}
	
	private void BFS(int src) {
		LinkedList<node_data> q = new LinkedList<node_data>();
		node_data srcNode = this.graph.getNode(src);
		if (srcNode != null) {
			srcNode.setTag(0);
			srcNode.setInfo(Integer.toString(src));
			q.add(srcNode);
			while(!q.isEmpty()){
	            node_data currentNode = q.poll();
	            int distanceFromSrc = currentNode.getTag() + 1;
	            for (node_data neighborNode: currentNode.getNi()) {
	                if (neighborNode.getTag() == Integer.MIN_VALUE) {
	                	int neighborKey = neighborNode.getKey();
	                	neighborNode.setTag(distanceFromSrc);
	                	neighborNode.setInfo(currentNode.getInfo() + "-" + neighborKey);
	                	q.add(neighborNode);
	                }
	            }
	        }
		}
	}
	
	
	private void resetAllTagsAndInfos() {
		for (node_data node: this.graph.getV()) {
			node.setTag(Integer.MIN_VALUE);
			node.setInfo("");
		}
	}

}
