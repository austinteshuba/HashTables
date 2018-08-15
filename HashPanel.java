//HashPanel.java
//Austin Teshuba
//This is a JPanel created for HashAssign2.java that will allow the visual interface to work properly

import java.awt.*;//imports
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class HashPanel extends JPanel implements MouseListener {//this is a JPanel, which will be added to the JFrame. Mouse listener is implemented for click detection
	private HashImage map = null;//the map image begins as null
	private ArrayList<Circle> circles = new ArrayList<Circle>();//store all the circles
	
	private static HashTable<Creep> hash = new HashTable<Creep>();//this is a hashtable for Creep objects
	public HashPanel() {
		addMouseListener(this);//add mouse listener
		//HashImage map = null;
		try {
			map = new HashImage(ImageIO.read(new File("windsor.gif")), 0, 0);//load the map and resize it to fit screen
			map.resize(600, 600);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			Scanner getLines = new Scanner(new File("creeper.txt"));//read the file
			while(getLines.hasNextLine()) {//while there is another line
				String line = getLines.nextLine();
				String[] nums = line.split(" ");//get the line, split it into it's ints, and then add a creep object with these ints too the hashtable
				hash.add(new Creep(Integer.parseInt(nums[0]), Integer.parseInt(nums[1]), Integer.parseInt(nums[2]), Integer.parseInt(nums[3]), Integer.parseInt(nums[4])));
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	public void paintComponent(Graphics g) {//this paints to the screen
		g.drawImage(map.getImage(), map.getX(), map.getY(), this);//paint the map
		for (Circle cir:circles) {//draw all of the circles
			g.setColor(new Color(cir.red, cir.green, cir.blue));
			g.drawRect(cir.x, cir.y, cir.radius, cir.radius);
			//System.out.println("Sure");
		}
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {//if the mouse is released, we do computations to draw a circle
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				circles = new ArrayList<Circle>();
				int mx = e.getX();//get location of mouse
				int my = e.getY();
				
				
				int maxLeft = mx-10;//the min/max of left/right/up/down direction is +-10 pixels from mx/my
				int maxRight = mx+10;
				int maxUp = my-10;
				int maxDown = my+10;
				
				
				for (int x =maxLeft; x<maxRight; x++) {//iterate through the x-coordinates from maxLeft to maxRight
					for (int y = maxUp; y<maxDown; y++) {//iterate through the y-coordinates from maxUp to maxDown
						if ( Math.sqrt( Math.pow(Math.abs(x - mx),2) + Math.pow(Math.abs(y-my), 2))<=10 ) {//if the coordinates are in the circle
							//then it is in the circle.
							int key = new Point(x,y).hashCode();
							LinkedList<Creep> cre = hash.getList(new Point(x,y).hashCode());//the creep object is located at the hashcode of the x,y coordinate, as specified in the creep object
							//explanation: I got the entire list here because of the hashcode i used. It is possible to have
							//two cre objects with the same "hash code" that contain different colours because it is only based off x and y
							//while this made the cre objects very easy to locate, we must do a bit more work with the linked list to check for duplicates
							if (cre!=null) {
								int red =0;
								int blue = 0;
								int green = 0;
								int counter= 0;
								for (int t = 0; t<cre.size(); t++) {
									if (Math.abs(cre.get(t).hashCode())==Math.abs(key)) {//if the hashcode matches the hashcode of the x,y point
										
										counter+=1;//add one to the counter
										red+= Math.round((float)(cre.get(t).LH + 100.0)/200.0*255.0);//add a value from 0 to 255 to the RGB depending on the emotions
										blue += Math.round((float)(cre.get(t).EB + 100.0)/200.0*255.0);
										green += Math.round((float)(cre.get(t).HS + 100.0)/200.0*255.0);
									} 
								}
								if (counter>0) {
									//System.out.println("Here");
									red = Math.round(red/counter);//the true RGB values are the average of the colours. Get the average
									green = Math.round(green/counter);
									blue = Math.round(blue/counter);
									
									//add a circle to the array
									circles.add(new Circle(x,y,1,red,green,blue));
									System.out.println(circles.size());
									
								}
							}
						}	
							
					}		
				}
				
				repaint();//repaint will call paint component and draw the circle
				
	}

}
