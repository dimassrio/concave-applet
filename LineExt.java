import java.awt.geom.Line2D;

public class LineExt extends Line2D.Double{
	private String name;

	public LineExt(String name, PointExt a, PointExt b){
		this.setName(name);
		this.setLine(a, b);

	}

	public LineExt(String name, double ax, double ay, double bx, double by){
		this.setName(name);
		this.setLine(ax, ay, bx, by);
	}

	public void setName(String name){
		this.name = name;
	}
	
}