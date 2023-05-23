import java.util.Arrays;
import java.util.Scanner;
import java.io.*;
import java.util.*;

public class SSeguraFloydAglorithm {

	public static int arraySize;
	public static int W[][]; 
	public static int D[][]; 
	public static int P[][];
	public static ArrayList<Integer> path = new ArrayList<Integer>();
		
	public static void main(String[] args)
	{
		int v1;
		int v2;
		
		Scanner scan = new Scanner(System.in);
		System.out.print("Enter number: ");
		
		
		int next = scan.nextInt();
		arraySize = next; System.out.println();
		
		W = arrayGenerator(arraySize);
		P = zeroArray(arraySize);
		
		floyd2(arraySize, W, D, P);

		
		System.out.println();
		
		try {
			System.out.print("You can find path between vertices, would you like to proceed? [y/n] >> ");
			String response = scan.next(); System.out.println();
			
			while(response.equals("y"))
			{
				System.out.print("Enter starting node: ");
				next = scan.nextInt();
				v1 = next; System.out.println();
				
				System.out.print("Enter destination node: ");
				next = scan.nextInt();
				v2 = next; System.out.println();
				
				printPath(v1, v2);
				path.clear();
				
				System.out.println();
				System.out.print("Try again? [y/n] >> ");
				response = scan.next();
				System.out.println("-------------------------------------------------");
			}
		}
		catch (ArrayIndexOutOfBoundsException e)
		{
			System.out.println("Selected a vertex that is not in graph, attempt terminated.");
		}
		catch (Exception e)
		{
			System.out.println("Invalid response, attempt terminated.");
		}
		
	}
	
	public static int[][] arrayGenerator(int size)
	{
		int [][] graph = new int[size][size];
		int value;
		
		for(int i = 0; i < size; i++)
		{
			for(int j = 0; j < size; j++)
			{
				if(i == j)
					graph[i][j] = 0;
				else
				{
					value = (int)(Math.random()*100);
					graph[i][j] = value;
				}
			}
		}
		return graph;
	}
	
	//Finished
	public static void floyd2(int n, int W[][], int D[][], int P[][])
	{
		int i, j, k;
		
		System.out.println("W graph:");
		printGraph(arraySize, W); System.out.println();
		
		for(i = 0; i < n; i++)
		{
			for(j = 0; j < n; j++)
			{
				P[i][j] = 0;
			}
		}
		
		D = W;
		
		for(k = 0; k < n; k++)
		{
			for(i = 0; i < n; i++)
			{
				for(j = 0; j < n; j++)
				{
					if(D[i][k] + D[k][j] < D[i][j])
					{
						P[i][j] = k;
						D[i][j] = D[i][k] + D[k][j];
					}
				}
			}
		}
		
		System.out.println("D graph:");
		printGraph(arraySize, D); System.out.println();
		
		System.out.println("P graph:");
		printGraph(arraySize, P); System.out.println();
	}
	
	public static int[][] zeroArray(int size)
	{
		int arr[][] = new int[size][size];
		
		for(int i = 0; i < size; i++)
		{
			for(int j = 0; j < size; j++)
			{
				arr[i][j] = 0; 
			}
		}
		
		return arr;
	}
	
	public static void printPath(int q, int r)
	{
		path.add(q);
		
		findPath(q,r);
		
		path.add(r);
		
		System.out.println("The shortest path from v" + q + " to v" + r + ": " + path);
	}
	
	public static void findPath(int q, int r)
	{
		if(P[q][r] != 0 && P[q][r] != r)
		{
			findPath(q, P[q][r]);
			path.add(P[q][r]);
			findPath(P[q][r], r);
		}
	}
	
	//Finished
	public static void printGraph(int size, int[][] a)
	{
		for(int i = 0; i < size; i++)
		{
			System.out.print("[");
			for(int j = 0; j < size; j++)
			{
				if(j == (size-1))
				{
					System.out.print(a[i][j]);
				}
				else
					System.out.print(a[i][j] + " ");
			}
			System.out.print("]");
			System.out.println();
		}
	}
	
}
