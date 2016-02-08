import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.GenericArrayType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

public class MainClass 
{
	public static void main(String[] args) throws Exception
	{
			Random rand = new Random();
			Scanner sc = new Scanner(System.in);
		
			int noOfNodes;
			
			HashMap<String, ArrayList<String>> hm = new HashMap<>();
			HashMap<String, Integer> hm_weight = new HashMap<>();
			Set<String> set = new HashSet<>();
			ArrayList<String> nodeList = new ArrayList<>(); 
			
			ArrayList<Integer> list = new ArrayList<Integer>();
			
			BufferedReader br = new BufferedReader(new FileReader("Input"));
			noOfNodes = Integer.parseInt(  br.readLine());

			
			String tmp;
			while((tmp = br.readLine() )  != null)
			{

				StringTokenizer st = new StringTokenizer(tmp);
				String key = st.nextToken();
				String elementOfList = st.nextToken();
				int weight = Integer.parseInt(st.nextToken());
				
				String tm =  "" + key + elementOfList;
				hm_weight.put(tm, weight);
				
				set.add(key);
				set.add(elementOfList);
				
				if(hm.containsKey(key))
				{
					hm.get(key).add(elementOfList);
				}
				else
				{
					hm.put(key, new ArrayList<>());
					hm.get(key).add(elementOfList);
				}
			}
			
			br.close();
			
			HashMap<String, String> output = new HashMap<>();
			
			for (String s : set)
			{
			   nodeList.add(s);
			}
			
			
			MyPriorityQueue pQueue = new MyPriorityQueue();
			
			for (int i = 0; i < nodeList.size()   ; i++) 
			{
				String temp = nodeList.get(i);
				if( temp.equals("0"))
				{
					pQueue.offer(new Node(temp , 0 , "0"  ));
				}
				else
				{
					pQueue.offer(new Node(temp, 1000000));
				}
			}
			
			
			System.out.println("begin\tend\t\tweight\t\tpath");
			long startTime = System.currentTimeMillis();
			while(!pQueue.list.isEmpty())
			{
				Node smallest = pQueue.poll();
				output.put(smallest.node_No, smallest.path);
				
				System.out.println("0\t" + smallest.node_No+"\t\t" + smallest.priority + "\t\t" + smallest.path );
//				System.out.println("node number: " + smallest.node_No + " weight: " + smallest.priority + " path: " + smallest.path );
				
				String pa = smallest.path;
				
				if(hm.containsKey(smallest.node_No))
				{
					for (int i = 0; i < hm.get(smallest.node_No).size(); i++)
					{
						String edge = hm.get(smallest.node_No).get(i);
						
						if(output.containsKey(edge))
						{
							continue;
						}
						
						String tempo = smallest.node_No + edge; 
						
						
						int currentPriority = pQueue.getPriority(edge);
						
						int newPriority = smallest.priority + hm_weight.get(tempo);
						if(newPriority < currentPriority)
						{
							pQueue.Update(new Node(edge, newPriority ));
							pQueue.changePath(pa, edge );
						}
					}
				}
			}
			long endTime = System.currentTimeMillis();
			System.out.println(endTime - startTime);
	}
}


//
//private static void removeFirst(ArrayList<Integer> list) 
//{
//	if(list.size() == 1)
//	{
//		list.remove(0);
//		return; 
//	}
//	
//	int lastIndex = list.size();
//	int last = list.get(lastIndex - 1);
//	list.remove(lastIndex - 1);
//	list.remove(0);
//	list.add(0, last);
//	
//	int parentIndex =  0;
//
//	while(true)
//	{
//		int smallest , smallestIndex , parent;
//		int child_1_Index, child_2_Index;
//		int child_1 , child_2;
//		
//		child_1_Index = parentIndex * 2 + 1; 
//		child_2_Index = parentIndex * 2 + 2;
//		
//		if(child_1_Index > list.size() - 1)
//		{
//			break;
//		}
//		
//		parent = list.get(parentIndex);
//		child_1 = list.get(child_1_Index);
//		
//		if(child_2_Index > list.size() - 1)
//		{
//			smallest = child_1;
//			smallestIndex = child_1_Index ;
//		}
//		else
//		{
//			child_2 = list.get(child_2_Index);
//			
//			if(child_1 < child_2)
//			{
//				smallest = child_1;
//				smallestIndex = child_1_Index ;
//			}
//			else
//			{
//				smallest = child_2;
//				smallestIndex = child_2_Index ;
//			}
//		}
//		
//		
//		if(smallest < parent)
//		{
//			list.remove(parentIndex);
//			list.add(parentIndex , smallest);
//			
//			list.remove(smallestIndex);
//			list.add(smallestIndex , parent);
//		}
//		else
//		{
//			break;
//		}
//		
//		parentIndex = smallestIndex;
//	}
//	
//}
//
//
//private static void insertElement(ArrayList<Integer> list) 
//{
//	int childIndex = list.size() - 1;
//	
//	while(childIndex != 0)
//	{
//		int parentIndex = (childIndex  - 1 )/2;
//		int parent  = (int) list.get(parentIndex);
//		int child = (int) list.get(childIndex);
//		
//		if(child < parent)
//		{
//			list.remove(parentIndex);
//			list.add(parentIndex, child);
//			list.remove(childIndex );
//			list.add(childIndex  , parent);
//		}
//		else
//		{
//			break;
//		}
//		
//		childIndex = parentIndex; 
//	}
//}




//
//ArrayList<Integer> lis = new ArrayList<>();
//
//for (int i = 0; i < 7; i++)
//{
//	int tm = rand.nextInt(100) ;
//	lis.add(tm);
//	insertElement(lis);
//	pQueue.offer(new Node ( "" + i,tm));
//}
//
//pQueue.showList();
//
//System.out.println();
//
//System.out.println(lis);
//System.out.println();
//System.out.println();
//System.out.println();
//
//System.out.println("After deletion");
//
//
//System.out.println(pQueue.poll().node_No);
//pQueue.showList();
//removeFirst(lis);
//System.out.println(lis);
//
//for (int i = 0; i < 2; i++) 
//{
//	System.out.println("Enter the node number: ");
//	String node = sc.nextLine();
//	
//	System.out.println("Enter new value: ");
//	int value = sc.nextInt();
//	
//	pQueue.Update(new Node(node , value)); 
//	pQueue.showList();
//}


