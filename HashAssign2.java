//HashAssign2.java
//Austin Teshuba
//This is a program that reads from a creeper text file and displays the average emotion of an area via color

import java.awt.event.MouseEvent;//imports
import java.awt.event.MouseListener;
import java.io.*;
import java.util.*;
import javax.swing.*;

public class HashAssign2 extends JFrame {//this class extends JFrame, allowing for a visual interface
	
	private HashPanel panel = null;//the current panel starts as null
	public static void main (String[]args) {
		new HashAssign2();//init itself and start the process!
	}
	
	public HashAssign2() {//init for this class
		super("HashAssign2");//call the super's init. call the window HashAssign2
		//addMouseListener(this);//add mouse listener
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//exit when the window is closed
		setSize(600, 600);//set the screen width and height
		
		
		HashPanel panel = new HashPanel();//make a new panel and add it to screen
		add(panel);
		setVisible(true);//show the frame on the screen
		
		
	}

}
