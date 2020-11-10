package ex0;

import java.util.Collection;
import java.util.HashMap;

public class NodeData implements node_data {
	private int key;
	private static int numberOfCreatedInstances = 0;
    private HashMap<Integer, node_data> neighbors;
    private String info = "";
    private int tag = Integer.MIN_VALUE;
	
    public NodeData() {
    	this.key = numberOfCreatedInstances++;
    	this.neighbors = new HashMap<Integer, node_data>();
 
    }
    
    public NodeData(int key, String info, int tag) {
    	this.key = key;
    	this.info = info;
    	this.tag = tag;
    	this.neighbors = new HashMap<Integer, node_data>();

    }
	
	@Override
	public int getKey() { return this.key; }

	@Override
	public Collection<node_data> getNi() { return this.neighbors.values(); }

	@Override
	public boolean hasNi(int key) { return this.neighbors.containsKey(key); }

	@Override
	public void addNi(node_data t) {
		int key = t.getKey();
		if (!this.hasNi(key)) {
		this.neighbors.put(key, t);
		t.addNi(this);
		}
	}

	@Override
	public void removeNode(node_data node) {
		int key = node.getKey();
		if (this.hasNi(key)) {
			this.neighbors.remove(key);
			node.removeNode(this);
			
		}
	}

	@Override
	public String getInfo() { return this.info; }

	@Override
	public void setInfo(String s) { this.info = s; }

	@Override
	public int getTag() { return this.tag; }

	@Override
	public void setTag(int t) { this.tag = t; }
	
	@Override
	public String toString() { return "{\nkey: " + this.getKey() +"\ninfo: " + this.getInfo() + "\ntag: " + this.getTag() +"\n}"; }
}
