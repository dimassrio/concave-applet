import java.awt.geom.Line2D;

public class LineExt extends Line2D.Double{
	private PointExt p1;
	private PointExt p2;
	private String name;

	public LineExt(String name, PointExt a, PointExt b){
		this.setName("L-"+name);
		this.setLine(a, b);
		p1 = a;
		p2 = b;
	}

	public LineExt(String name, double ax, double ay, double bx, double by){
		this.setName(name);
		this.setLine(ax, ay, bx, by);
	}

	public LineExt(String name){
		this.setName(name);
	}

	public void setName(String name){
		this.name = name;
	}

	public PointExt getPext1(){
		return this.p1;
	}

	public PointExt getPext2(){
		return this.p2;
	}
}