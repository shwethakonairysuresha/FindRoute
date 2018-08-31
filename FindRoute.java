import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;



public class FindRoute {
	public static void main(String[] args)throws Exception
	  {
		int s = 0, duplicate = 0;
		String stArr[] = null;
		ArrayList<String> sourceCity = new ArrayList<String>(); 	
        ArrayList<String> destinationCity = new ArrayList<String>();
        ArrayList<Double> distance = new ArrayList<Double>();
        ArrayList<String> nodes = new ArrayList<String>();
        ArrayList<Node> nodeList = new ArrayList<Node>();
        
	  // To read txt file from command prompt
	  String fName = args[0];
	  String source = args[1];
	  String destination = args[2];
	  
	  //System.out.println("Filename="+fName+" Source="+source+" Destination="+destination);
	 
	  BufferedReader br = new BufferedReader(new FileReader(fName));
	 
	  String st;
	  while (!(st = br.readLine()).equalsIgnoreCase("END OF INPUT")) 
	  {
		  stArr = st.split(" ");
		  sourceCity.add(stArr[0]);
		  destinationCity.add(stArr[1]);
		  distance.add(Double.parseDouble(stArr[2]));
	  }
	  
	  //To get unique nodes from sourcecity and destinationCity
		 for(s=0;s<sourceCity.size();s++)
		 {
			 for(int x=0;x<nodes.size();x++)
			 {
				 if(nodes.get(x).contains(sourceCity.get(s)))
				 {
					 duplicate = 1;
					 //System.out.println(sourceCity);
					 break;
				 }
			 }
			 
			 if(duplicate == 0)
			 {
				 nodes.add(sourceCity.get(s));
				 //System.out.println(sourceCity);
			 }
			 duplicate = 0;
		 }
		 
		 for(s=0;s<destinationCity.size();s++)
		 {
			 for(int x=0;x<nodes.size();x++)
			 {
				 if(nodes.get(x).contains(destinationCity.get(s)))
				 {
					 duplicate = 1;
					 //System.out.println(destinationCity);
					 break;
				 }
			 }
			 
			 if(duplicate == 0)
			 {
				 nodes.add(destinationCity.get(s));
				 //System.out.println(destinationCity);
			 }
			 duplicate = 0;
		 }
		 
		 for(s=0;s<nodes.size();s++)
		 {
			 nodeList.add(new Node(nodes.get(s)));
		 }
		 
		 /*n = 0;
	        while (n < nodes.size())
	        {
	            nodeList.add(new Node(nodes.get(n)));
	            n++;
	        }*/
	        
	        s = 0;
	        int i = 0;
	        int j = 0;
	        
	        //To create edges between the nodes
	        ArrayList<Edge> Edges = new ArrayList<Edge>();
	        for(s=0;s<nodeList.size();s++)
	        {
	        	for(i = 0;i<sourceCity.size() && i<destinationCity.size();i++)
	        	{
	        		if(nodeList.get(s).state.contains(sourceCity.get(i)))
	        		{
	        			for(j=0;j<nodeList.size();j++)
	        			{
	        				if(nodeList.get(j).state.contains(destinationCity.get(i)))
	        				{
	        					break;
	        				}
	        			}
	        			Edges.add(new Edge(nodeList.get(j), distance.get(i)));
	        		}
	        		if(nodeList.get(s).state.contains(destinationCity.get(i)))
	        		{
	        			for(j=0;j<nodes.size();j++)
	        			{
	        				if(nodeList.get(j).state.contains(sourceCity.get(i)))
	        				{
	        					break;
	        				}
	        			}
	        			Edges.add(new Edge(nodeList.get(j), distance.get(i)));
	        		}
	        	}
	        	
	        	i=0;
	        	nodeList.get(s).adjacencies = Edges.toArray(new Edge[Edges.size()]);
	        	Edges.clear();
	        }
	        
	        //To get the index of the source node and destination node
	        //int i=0, s = 0;
	        for(s=0; s<nodeList.size();s++)
	        {
	        	//System.out.println(nodeList.get(s));
	        	if(nodeList.get(s).state.contains(source))
	        	{
	        		//System.out.println(nodeList.get(s));
	        		break;
	        	}
	        }
	        for(i=0; i<nodeList.size();i++)
	        {
	        	if(nodeList.get(i).state.contains(destination))
	        	{
	        		//System.out.println(i);
	        		break;
	        	}
	        }
		 
	        //Call to uniform cost search
	        //System.out.println("Hi");
	        UniformCostSearch(nodeList.get(s),nodeList.get(i));
	        //System.out.println("Hello");
	        
	        //Call to Print the path
	        List<Node> sdpath = path(nodeList.get(i));
	        
	        //No connection between nodes
	        if(1 == sdpath.size())
	        {
	        	System.out.println("Distance: infinity\r\n"+"route:\r\n"+ "none");
	        }
	        else
	        {
	        	System.out.println("shortest route:");
	        	for(int x1=0;x1<sdpath.size()-1;x1++)
	        	{
	        		for(int y1=0;y1<sourceCity.size() && y1<destinationCity.size();y1++)
	        		{
	        			if(((sdpath.get(x1).state.equalsIgnoreCase(sourceCity.get(y1))) && (sdpath.get(x1+1).state.equalsIgnoreCase(destinationCity.get(y1))))||((sdpath.get(x1).state.equalsIgnoreCase(destinationCity.get(y1))) && (sdpath.get(x1+1).state.equalsIgnoreCase(sourceCity.get(y1)))))
	        			{
	        				System.out.println(sdpath.get(x1).state+" to "+sdpath.get(x1+1).state+" "+distance.get(y1)+"km");
	        				break;
	        			}
	        		}
	        	}System.out.println("Distance: "+nodeList.get(i).pathCost+"km");
	        }
	  }
	        
	        public static void UniformCostSearch(Node start, Node end)
	        {
	        	start.pathCost = 0;
	        	//Implementing minimum priority queue
	        	PriorityQueue<Node> priorityqueue = new PriorityQueue<Node>(20, new Comparator<Node>() {
	        		
	        		public int compare(Node i, Node j)
	        		{
	        			if(i.pathCost>j.pathCost)
	        			{
	        				return 1;
	        			}
	        			else if(i.pathCost<j.pathCost)
	        			{
	        				return -1;
	        			}
	        			else
	        			{
	        				return 0;
	        			}
	        		}
	        	}
	        			);
	        	priorityqueue.add(start);
	        	
	        	Set<Node> exploredSet = new HashSet<Node>();
	        	boolean located = false;
	        	
	        	//Frontrier is not empty expand
	        	double cost = 0;
	        	do {
	        		Node currentnode = priorityqueue.poll();
	        		exploredSet.add(currentnode);        //for visited node
	        		
	        		//If the current node is destination node
	        		if(currentnode.state.equals(end.state)) 
	        		{
	        			located=true;
	        		}
	        		
	        		//If the current node can be expanded
	        		for(Edge e: currentnode.adjacencies)
	        		{
	        			Node child = e.target;
	        			cost = e.cost;
	        			
	        			//Add the successor of the current node to the queue if it is not explored
	        			if(!exploredSet.contains(child) && !priorityqueue.contains(child))
	        			{
	        				child.pathCost=currentnode.pathCost+cost;
	        				child.parent=currentnode;
	        				priorityqueue.add(child);
	        			}
	        			
	        			else if((priorityqueue.contains(child) && (child.pathCost>(currentnode.pathCost+cost))))
	        			{
	        				child.parent=currentnode;
	        				child.pathCost=currentnode.pathCost+cost;
	        				priorityqueue.remove(child);
	        				priorityqueue.add(child);
	        			}
	        		}
	        	}while(!priorityqueue.isEmpty() && (located==false));
	        }
		 
	        	//To print the shortest path between source and destination
	        	public static List<Node> path(Node target)
	        	{
	        		List<Node> sdpath = new ArrayList<Node>();
	        		for(Node d = target; d!=null; d=d.parent)
	        		{
	        			sdpath.add(d);
	        		}
	        		Collections.reverse(sdpath);
	        		return sdpath;
	        	} 
	  }