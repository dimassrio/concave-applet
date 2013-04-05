import java.awt.geom.Point2D.Double;
import java.awt.geom.*;

public class PointExt extends Point2D.Double {
	public String name;
	public PointExt next;
	public static void main(String[] args) {
			
	}
	
	public PointExt(int x, int y){
		this.setLocation(x, y);
	}

	public PointExt(double x, double y){
		this.setLocation(x, y);
	}

	public PointExt(String name){
		this.setName(name);
	}

	public PointExt(String name, int x, int y){
		this.setName(name);
		this.setLocation(x,y);
	}

	public PointExt(String name, double x, double y){
		this.setName(name);
		this.setLocation(x,y);
	}

	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	public void printData(){
		System.out.println(getName()+"("+getX()+","+getY()+")");
	}

}