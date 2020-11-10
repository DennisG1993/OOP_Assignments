# OOP ex0

### In this exercise we've been asked to implement the interfaces:
## node_data which has the following attributes and methods:
**Atributes:**
* private int key - unique id for each node
* private static int numberOfCreatedInstances - an int used to create unique keys, incremented whenever we create an instance of node_data
* private HashMap<Integer, node_data> neighbors - hashMap containing the connected nodes. the reason it is a hash map is so we could insert and get neighbours in O(1)
* private String info - same as tag a string which hold data for various uses
* private int tag - temp data saved to the node for various uses
* 
**methods:**
* getKey - a simple function used to return the node key(unique id) whcih simply returns
* getNi - returns a collection neighbour nodes
* addNi - add a neighbor node by key
* removeNode - if given node is a neighbor it removes it from neighbors else does nothing
* getInfo - returns node's info. inital value is an empty string
* setInfo - sets a node info
* getTag - returns node's tag. inital tag is Integer.MIN_VALUE
* setTag - sets a node tag

## graph(unweighted and undirected) which has the following attributes and methods:
**Atributes:**
* private HashMap<Integer, node_data> nodes - contains the graph's vertices of type node_data(as well as with node_data the hashMap was used for the fast insertion and search)
* private int numberOfEdges - number of the edges the graph has
* private int numberOfChanges - counts the number of changes has been made to the graph from the moment of initialization

**methods:**
* getNode - recives in key and returns the node corresponding to that key or null if no node with such key is found
* hasEdge - returns true or false wether there is a connection between nodes of two given keys (uses node_data.hasNi)
* addNode - simply adds gives node to the graph
* connect - recives two keys and connects the corrisponding nodes if they exist and arent connected (uses node_data.addNi)
* getV - returns a collection of all the node's in the graph
* getV(int node_id) - returns a collection of all the neighbous of a node corrisponding to given key or null if no such node exists in the graph
* removeNode - removes the node from the graph and cleans all the connections(edges) of given node, returns the removed node or null if it isn't in the graph
* removeEdge - removes the connection between two nodes if it exists else does nothing
* nodeSize - returns the number of nodes in the graph
* edgeSize - return the number of edges in the graph
* getMC - returns the number of changes were made since the initialization of the graph

## graph_algorithms which has the following attributes and methods:
**Atributes:**
* private graph graph

**methods:**
* init - recives a graph and initialazies the class attribute graph to the given graph
* copy - returns deep copy of the class attribute graph. if the graph hasn't been initialized yet simply returns a new graph.
it works by creating a new graph and then looping through existing graps nodes and creating new nodes from them with same attribues then adding them to the new graph and adding the right connections.
* isConnected - returns true or false wether the graph is connected which means that there is a path between any 2 nodes in the graph. the implementation uses sort of BFS to loop throguh all the nodes of the graph from the first node by going to its neighbors and then their neighbors ....  and then we check wether all the nodes were visited or not by checking their tag which was changed if they were looped through.
* shortestPathDist - recives 2 keys and returns the shortest path between corrisponding nodes. implemented using same BFS as isConnected but now we check the tag not only if it has changed from inital Integer.MIN_VALUE but also the data it has since it contains the number of moves required to get to it from given starting node. if the tag wasn't changed meaning we didn't visit that node then no path exists and we return -1
* shortestPath - same as shortestPathDist but now we check the info we contaisn the previously visited nodes before we get to the destination (info contains all the keys of previous nodes) if info of dest node isnt empty we get the nodes by key and return a collection of them else no path exists and we return null
