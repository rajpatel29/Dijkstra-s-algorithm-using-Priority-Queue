
public class Node 
{
	public String node_No;
	public int priority;
	public String path;
	
	public Node(String node , int  weight ) 
	{
		node_No = node;
		priority = weight;
	}
	
	public Node(String node , int  weight , String str ) 
	{
		node_No = node;
		priority = weight;
		path = str;
	}
	
	
	public String getNode_No() 
	{
		return node_No;
	}
	
	public void setNode_No(String node_No) 
	{
		this.node_No = node_No;
	}
	
	public int getpriority() 
	{
		return priority;
	}
	
	public void setpriority(int priority) 
	{
		this.priority = priority;
	}

	
	
}
