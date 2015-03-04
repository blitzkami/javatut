import java.util.*;
import java.io.*;
public class Store{
    private List <Item> myStore;

    public Store(String fName)
    {
        myStore = new ArrayList <Item>();
        loadFile(fName);
    }

    public Store()
    {
        myStore=new ArrayList <Item> ();
        loadFile("file50.txt");
    }

    private void loadFile(String inFileName){
        int id;
        int inv;
        Scanner inFile;
        try{ 
            inFile = new Scanner(new File(inFileName)); 
            while(inFile.hasNextInt())
            { 
                id = inFile.nextInt(); 
                inv = inFile.nextInt(); 
                inFile.nextLine(); 
                myStore.add(new Item(id, inv));
            } 
        }
        catch(IOException e)
        {
            System.out.println("Error: " + e.getMessage());
        }  

    }

    public void displayStore() // Print contents of ArrayList of Items
    {    

        String display = toString();
        System.out.println(display);
        //Start searching part of program.
        System.out.println("-------------------------------------------------");

    }

    public String toString()
    {
        String storeDisplay = "";
        storeDisplay += "\n\n+-----------------+\n";
        storeDisplay += "| Store Inventory |\n";
        storeDisplay += "+-----------------+\n\n";
        storeDisplay += String.format("%19s%14s", "ID Number", "# of Items");
        storeDisplay += "\n";
        for (int ii = 0; ii < myStore.size(); ii++)
        {
            Item item = myStore.get(ii);
            if (ii % 10 == 0) // Print in groups of 5 items.
            {
                storeDisplay += "\n";
            }
            storeDisplay += String.format("%6d%24s", ii+1, item);
            storeDisplay += "\n";
        }
        storeDisplay += "\n\n";
        return storeDisplay;
    } 

    public void sort()
    {
        mergeSort(myStore, 0, myStore.size()-1);
    }	//to get recursive sort going (call mergeSort)
    private void merge(List <Item> list, int first, int mid, int last)
    {
        int index1 = first; 
        int index2 = mid+1; 
        List<Item> temp = new ArrayList<Item>(); 
        
        for (int i = first; i <= last; i++)
        { 
            if(index1 > mid) 
            { 
                temp.add(list.get(index2)); 
                index2++; 
            } 
            else if (index2 > last)
            { 
                temp.add(list.get(index1)); 
                index1++; 
            } 
            else if (list.get(index1).compareTo(list.get(index2)) < 0) 
            { 
                temp.add(list.get(index1)); 
                index1++; 
            } 
            else
            { 
                temp.add(list.get(index2)); 
                index2++; 
            } 
        }
        for (int i = 0; i < temp.size(); i++) 
        {
            list.set(first + i, temp.get(i)); 
        } 
    }

    private void mergeSort(List <Item> list, int first, int last)
    {
        if (first == last)
        {
        }
        else if (last-first==1)
        {
            if(list.get(first).compareTo(list.get(last))>0)
            {
                Item temp = list.get(first);
                list.set(first,(list.get(last)));
                list.set(last,temp);

            }
        }
        else
        {
            // recursion, divide list into two halves
            int mid = (first+last)/2;
            mergeSort(list, first, mid);
            mergeSort(list, mid+1, last);
            merge(list,first,mid,last);
        } 
    }

}
____________________________________________________________________________

public class Driver
{
    public static void main(String[] args)
    {     
       Store n00b = new Store();
       n00b.sort();
       n00b.displayStore();
    }
}
}
