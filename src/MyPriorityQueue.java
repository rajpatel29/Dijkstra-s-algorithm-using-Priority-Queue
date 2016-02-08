import java.util.ArrayList;
import java.util.HashMap;


public class MyPriorityQueue
{
	ArrayList<Node> list  = new ArrayList<Node>();
	
	HashMap<String ,  Integer> hm = new HashMap<>();
	
	
	
	
	public void showList()
	{
		for (int i = 0; i < list.size(); i++) 
		{
			Node x = (Node) list.get(i);
			System.out.println(x.node_No  + "   " + x.priority);
		}
		System.out.println();
		System.out.println();
		System.out.println(hm);
	}
	
	public  Node poll()
	{
		Node out = null;
		if(list.size() == 1)
		{
			hm.remove(list.get(0).node_No);   ////////
			
			out = list.get(0);
			list.remove(0);
			return out; 
		}
		
		int lastIndex = list.size();
		Node last  = list.get(lastIndex - 1);
		list.remove(lastIndex - 1);
		
		hm.remove(list.get(0).node_No);     /////
		out = list.get(0);
		list.remove(0);
		hm.remove(out.node_No);
		
		list.add(0, last);
		hm.put(last.node_No, 0);
		
		int parentIndex = 0;
		
		while(true)
		{
			Node smallest , parent , child_1 , child_2;
			int child_1_Index, child_2_Index , smallestIndex ;
			
			
			child_1_Index = parentIndex * 2 + 1; 
			child_2_Index = parentIndex * 2 + 2;
			
			if(child_1_Index > list.size() - 1)
			{
				break;
			}
			
			parent = list.get(parentIndex);
			child_1 = list.get(child_1_Index);
			
			if(child_2_Index > list.size() - 1)
			{
				smallest = child_1;
				smallestIndex = child_1_Index ;
			}
			else
			{
				child_2 = list.get(child_2_Index);
				
				if(child_1.priority < child_2.priority)
				{
					smallest = child_1;
					smallestIndex = child_1_Index ;
				}
				else
				{
					smallest = child_2;
					smallestIndex = child_2_Index ;
				}
			}
			

			if(smallest.priority < parent.priority)
			{
				list.remove(parentIndex);
				list.add(parentIndex , smallest);
				hm.put(smallest.node_No, parentIndex);
				
				list.remove(smallestIndex);
				list.add(smallestIndex , parent);
				hm.put(parent.node_No, smallestIndex);
				
			}
			else
			{
				break;
			}
			
			parentIndex = smallestIndex;
		}
		return out;
	}
	
	public void offer(Node n)
	{
		list.add(n);
		hm.put(n.node_No, list.size() - 1);
		int childIndex = list.size() - 1;
		
		
		while(childIndex != 0)
		{
			int parentIndex = (childIndex  - 1 )/2;
			Node parent  =  list.get(parentIndex);
			Node child =  list.get(childIndex);
			
			if(child.priority < parent.priority)
			{
				list.remove(parentIndex);
				list.add(parentIndex, child);
				hm.put(child.node_No, parentIndex);
				
				list.remove(childIndex );
				list.add(childIndex  , parent);
				hm.put(parent.node_No, childIndex);
			}
			else
			{
				break;
			}
			
			childIndex = parentIndex; 
		}
	}

	public void Update(Node n)
	{
		int index = hm.get(n.node_No);
		list.remove(index);
		list.add(index, n);
		
		int childIndex = index;
		
		while(childIndex != 0)
		{
			int parentIndex = (childIndex  - 1 )/2;
			Node parent  =  list.get(parentIndex);
			Node child =  list.get(childIndex);
			
			if(child.priority < parent.priority)
			{
				list.remove(parentIndex);
				list.add(parentIndex, child);
				hm.put(child.node_No, parentIndex);
				
				list.remove(childIndex );
				list.add(childIndex  , parent);
				hm.put(parent.node_No, childIndex);
			}
			else
			{
				break;
			}
			
			childIndex = parentIndex; 
		}
	}

	public int getPriority(String x)
	{
		int index = hm.get(x);
		Node node = list.get(index);
		return node.priority;
	}
	public void changePath(String parent, String child)
	{
		int childIndex = hm.get(child);
		Node childNode = list.get(childIndex);
		childNode.path = parent + "-" + child;		
	}
}
