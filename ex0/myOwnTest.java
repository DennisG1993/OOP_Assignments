package ex0;

import java.util.Date;

//simple graph tests just to check functionality and exceptions

public class myOwnTest {
	private static int numberOfTests = 0, numberOfTestsPassed = 0, numberOfExceptions = 0;

	public static void test0() {
		//simple graph creation
		graph g = new Graph_DS();
		String classNameOf_g = g.getClass().getName().substring(4);
		String testName = "test0";
		simpleAssert(testName, classNameOf_g, "Graph_DS");
	}
	
	public static void test1() {
		//simple graph creation
		graph g = new Graph_DS();
		int Random = (int)(Math.random()*1000);
		for (int i = 0; i < Random; i++) {
			g.addNode(new NodeData());
		}
		String testName = "test1";
		simpleAssert(testName,Integer.toString(g.nodeSize()) , Integer.toString(Random));
	}
	
	public static void test2() {
		//simple graph creation
		graph g = new Graph_DS();
		node_data firstNode = new NodeData();
		int firstNodeKey = firstNode.getKey();
		g.addNode(firstNode);
		int Random = (int)(Math.random()*1000);
		for (int i = 0; i < Random; i++) {
			node_data temp = new NodeData();
			g.addNode(temp);
			int key = temp.getKey();
			g.connect(firstNodeKey, key);
		}
		String testName = "test2";
		simpleAssert(testName,Integer.toString(g.edgeSize()) , Integer.toString(Random));
	}
	

	private static void simpleAssert(String testName, String result, String expectation) {
		boolean resultMeetsExpectation = result.equals(expectation);
		String testResult = resultMeetsExpectation ? "passed" : "failed";
		System.out.println("the test: " + testName + " - " + testResult);
		if (resultMeetsExpectation) numberOfTestsPassed++;
		else System.out.println("Expected: " + expectation +" but got: " + result);
		}
	

	public static void main(String[] args) {
		long start = new Date().getTime();

		try{ 
			numberOfTests++;
			test0();
		}catch (Exception e) {e.printStackTrace(); numberOfExceptions++;}
		try{ 
			numberOfTests++;
			test1();
		}catch (Exception e) {e.printStackTrace(); numberOfExceptions++;}
		try{ 
			numberOfTests++;
			test2();
		}catch (Exception e) {e.printStackTrace(); numberOfExceptions++;}

		long end = new Date().getTime();
		double runTimeInSeconds = (end-start)/1000.0;
		System.out.println("The tests ran in: " + runTimeInSeconds + "seconds.");
		System.out.println("Tests passed: " + numberOfTestsPassed + "/" + numberOfTests);
		System.out.println("Number Of thrown exceptions is: " + numberOfExceptions);
	}
}
