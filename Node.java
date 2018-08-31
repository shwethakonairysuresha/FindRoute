//Represent the nodes in the path 
public class Node{

    public final String state;		//indicates the value of the node
    public double pathCost;			//cumulative cost 
    public Edge[] adjacencies;	//successors of state(source)
    public Node parent;				//parent of source

    public Node(String val){

    	state = val;

    }

    public String toString(){
        return state;
    }

}
