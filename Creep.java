//Austin Teshuba
//Creep.java
//This is a class for creep objects, that contain the x and y coordinates as well as the emotion values for each entry in creeper.txt

import java.awt.*;
public class Creep {
	public int x, y, LH, HS, EB;//create 5 public fields for coordinates and emotions
	public Creep(int x, int y, int LH, int HS, int EB) {//init the fields based on entered parameters
		this.x = x;
		this.y = y;
		this.LH = LH;
		this.HS = HS;
		this.EB = EB;
	}
	@Override
	public int hashCode() {//this creates a hashcode ONLY based off x and y
		return new Point(x,y).hashCode();//get the hashcode of the Point made from x,y
	}
	
}
