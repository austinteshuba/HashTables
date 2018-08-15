//HashTable.java
//Austin Teshuba
//This is a makeshift HashTable class used to store data and access it efficiently
import java.util.*;//import

public class HashTable<T>{
	//LinkedList<T>[] table=(LinkedList<T>[])(new Object[[10]);
	private ArrayList<LinkedList<T>>table;//start with an empty table
	private int maxSize = 70;//set the original max size percentage to 70%
	private int items=0;//we begin with 0 items
	
	public int getSize() {//return the size of the array list
		return table.size();
	}
	public int getItems() {//return the amount of items in list
		return items;
	}
	private void fill(int n){//fill the arraylist with null objects to the specified amounts
		table=new ArrayList<LinkedList<T>>();
		for(int i=0;i<n;i++){
			table.add(null);
		}
	}
	

	public HashTable(){//init a new hashtable
		fill(10);//start with size of 10
	}

	public void add(T val){//this function will add a value to the hashtable
		int hash=Math.abs(val.hashCode());//the hashcode is the absolute value of the value's built in hashcode
		int spot=hash%table.size();//the spot in the table is the remainder of the hash/the size of the table
		if(table.get(spot)==null){//if there is nothing at that spot, make an empty linked list
			table.set(spot,new LinkedList<T>());
		}
		//table.get(spot).add(val);
		//items++;

		LinkedList<T> lst=table.get(spot);
		if(lst.contains(val)==false){//if the list doesnt already have this value, add it
			lst.add(val);
			items++;

			if(items*100/table.size()>maxSize){//if we have exceeded the maxsize, resize everything
				resize();
			}
		}
	}
	public String toString(){//this will print a string version of the table with all of the contents
		String ans="";
		for(LinkedList<T>lst:table){
			if(lst!=null){
				for(T val:lst){//iterate through and add the value to the list
					ans+=val+"-+";
				}
			}
		}
		if(ans!=""){//if there is an answer, get rid of the last letter
			ans=ans.substring(0,ans.length()-1);
		}
		return "#"+ans+"#";//return the string
	}
	public void resize(){//resizes the table to meet the constraints of maxsize. this helps with efficiency
		items=0;//start with 0 items
		ArrayList<LinkedList<T>> tmp=table;//make a temporary table
		fill(table.size()*10);//fill the current table with nulls 10 times the current table's size
		for(LinkedList<T>lst:tmp){
			if(lst!=null){//iterate through the old table and add each object to the new hashtable
				for(T val:lst){
					add(val);
				}
			}
		}
	}
	private void resizeSize(float perc) {//this resizes the table to be a certain percent full
		ArrayList<LinkedList<T>> tmp=table;//make a temporary table with the contents of the current table
		int fillAmount = Math.round((float)items / perc);//calculate the amount of items we need 
		//System.out.println(perc);
		items = 0;
		fill(fillAmount);//fill with the fill amount
		for(LinkedList<T>lst:tmp){
			if(lst!=null){
				for(T val:lst){
					add(val);//add to the new list each object in old table
				}
			}
		}
	}
	
	public void remove(T val) {//remove a value from the table
		int hash = Math.abs(val.hashCode());//get the hash and the spot based off the hashcode and size
		int spot = hash % table.size();
		if (table.get(spot).contains(val)) {//
			//list actually has the object
			if (table.get(spot).size()==1) {//if thats the only thing in the list, just set the spot to null
				table.set(spot, null);
			} else {//otherwise, remove from linked list
				table.get(spot).remove(val);
			}
		}
	}
	public boolean has(T val) {//this simply checks if the hashtable has the object. used in Hashassign1
		int hash = Math.abs(val.hashCode());//get the hash and the spot based off hashcode and table size
		int spot = hash % table.size();
		if (table.get(spot)!=null) {
			if (table.get(spot).contains(val)) {//if the table contains the value return true
				return true;
			}
		}
		return false;//otherwise return false
	}
	
	public T get (int key) {//get the object based on the full hashcode
		LinkedList<T> l = table.get(Math.abs(key%table.size()));//get linked list at the spot in the table
		//T obj = l.getFirst();
		if (l!=null) {//if the list isnt null
			for (int t = 0; t<l.size(); t++) {
				if (l.get(t).hashCode()==key) {//if the object in the list is the one we want, return it
					return l.get(t);
				} 
			}
		}
		return null;//got nothin? return null
	}
	public LinkedList<T> getList(int key) {//use this for when there may be duplicate objects, and you would like to gather both of these objects. Used for HashAssign2
		LinkedList<T> l = table.get(Math.abs(key%table.size()));
		return l;
	}
	
	public float getLoad() {//this gets the amount the table is filled
		System.out.println(table.size());
		return items/(float) table.size();
	}
	public void setMaxLoad(float percent) {//this allows to set the max load of the object.
		if (percent >= 0.1 && percent <= 0.8) {//if it is withina reasonable percentage
			maxSize = Math.round(percent*100);//set the maxsize
			if (maxSize<(items/(float)table.size())*100) {//if we are currently above the new maxsize, resize
				System.out.println("Triggered");
				resizeSize(percent);
			}
		}
	}
	public void setLoad(float percent) {//set load to a specific percentage
		if (percent >=0.1 && percent <=0.8) {//if it is within bounds and below maxsize, resize
			if (maxSize>percent*100) {
				resizeSize(percent);
			}
			
		}
	}
	
	public ArrayList<T> toArray() {//this converts the hashtable to an arraylist
		ArrayList<T> newList = new ArrayList<T>();//create an empty arraylist
		for (LinkedList<T> lst:table) {//iterate through the table and add to the arraylist. 
			if (lst!=null) {
				for (T item:lst) {
					newList.add(item);
				}
			}
		}
		return newList;
	}
	
	
	
	
}