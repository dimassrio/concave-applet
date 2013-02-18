import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.BufferedReader;

public class Master extends JApplet implements Runnable, MouseListener, ActionListener, MouseMotionListener {
	private static String windowTitle = "Concave Check";
	private static boolean debug      = false;
	private static int windowWidth    = 1024;
	private static int windowHeight   = 700;
	private static JFrame mainWindow;
	
	private MasterPanel masterPanel           = new MasterPanel(this);
	private Component currentSwitch           = null;
	public  DefaultComboBoxModel<String> test = new DefaultComboBoxModel();
	public  JComboBox<String> opsiInit        = new JComboBox(test);
	private JButton clearButton               = new JButton("New");
	private JButton addButton                 = new JButton("Open");
	private JButton startButton               = new JButton("START");
	public JLabel status                      = new JLabel();
	public JLabel pointerPosition             = new JLabel();
	private JFileChooser chooseFile           = new JFileChooser();
	
	public String columnName[]                = new String[3];
	
	public DefaultTableModel dataTable;
	private JTable pointTable;

	public static void main(String[] args) {
		Master applet = new Master();
		applet.init();
		JFrame dWindow = new JFrame();           // Create window
		dWindow.setSize(windowWidth, windowHeight);               // Set window size
		dWindow.setTitle(windowTitle);           // Set window title
		dWindow.setLayout(new BorderLayout());   // Specify layout manager
		dWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		dWindow.add(applet, "Center");           // Place applet into window
		dWindow.setVisible(true); 
		mainWindow = dWindow;
	}

	public void init(){
		try{
			SwingUtilities.invokeAndWait(this);
		}
		catch(Exception e){
			System.err.println("Initialization failure ");
		}
	}

	public void run(){
		setLayout(new BorderLayout());
		masterPanel.setBackground(Color.white);
		this.add(masterPanel, "Center");
		masterPanel.addMouseListener(this);
		masterPanel.addMouseMotionListener(this);

		JPanel buttonPanel = new JPanel();
		startButton.setForeground(Color.red);
		buttonPanel.add(clearButton);
		buttonPanel.add(addButton);
		buttonPanel.add(opsiInit);
		buttonPanel.add(startButton);
		this.add(buttonPanel, "North");
		clearButton.addActionListener(this);
		addButton.addActionListener(this);

		JPanel statusPanel = new JPanel();
		pointerPosition.setText("(0,0)");
		statusPanel.add(pointerPosition, "West");
		status.setText("Ready");
		statusPanel.add(status, "East");
		this.add(statusPanel, "South");
		
		Object[] columnName = {"Point Name", "X", "Y"};
		dataTable.setRowCount(1);
		dataTable.setColumnIdentifiers(columnName);
		pointTable = new JTable(dataTable);
		JPanel tablePanel = new JPanel();
		tablePanel.add(pointTable);
		this.add(tablePanel, "East");
	}

	public void mouseExited(MouseEvent e){

	}

	public void mouseEntered(MouseEvent e){

	}

	public void mousePressed(MouseEvent e){
		if(e.getSource()!=masterPanel){
			return;
		}else{
			masterPanel.addPoint(e.getX(), e.getY());
			masterPanel.repaint();
			String[] pc = masterPanel.getPointList();
			int last = pc.length;

			opsiInit.addItem(pc[last-1]);
			opsiInit.setSelectedIndex(opsiInit.getItemCount()-1);
			Object[] tempData = {opsiInit.getItemCount()-1, e.getX(), e.getY()};
			dataTable.setRowCount(1);
			

			masterPanel.repaint();
		}
	}

	public void mouseReleased(MouseEvent e){

	}

	public void mouseClicked(MouseEvent e){

	}

	public void mouseMoved(MouseEvent e){
		if(e.getSource()!=masterPanel){
			return;
		}else{
			pointerPosition.setText("("+e.getX()+","+e.getY()+")");
		}
	}

	public void mouseDragged(MouseEvent e){

	}

	public void actionPerformed(ActionEvent e){
		if(e.getSource()==clearButton){
			masterPanel.clearPanel();
			masterPanel.repaint();
		}else if(e.getSource()==addButton){
			int returnVal = chooseFile.showOpenDialog(masterPanel);
			if (returnVal == JFileChooser.APPROVE_OPTION) {
					masterPanel.openFile(chooseFile);
					masterPanel.repaint();
			}
		}
	}
}

class MasterPanel extends JPanel{
	private static int pointRadius = 3;

	private Graphics2D g;
	private Master controller;
	private PointExt temp = new PointExt("A", 0, 0);
	public ArrayList<PointExt> pointContainer = new ArrayList();
	public MasterPanel(Master controller){
		this.controller = controller;
	}
	/**
		Insert new Point at Panel
		the point will be inserted at pointContainer.
	**/
	public void addPoint(int x, int y) {
		PointExt point;
		point = new PointExt(getNext26(pointContainer.size()-1),x, y);
		temp = point;
		pointContainer.add(temp);
		controller.status.setText("Point Created: "+temp.getName()+" ("+x+","+y+")");
	}
	/**
		Draw new Point at the Panel
		the name and location of the point
		will viewed beside the point
	**/
	public void draw(PointExt point){
		double x = point.x;
		double y = point.y;
		int r = pointRadius;
		Ellipse2D.Double tempPoint = new Ellipse2D.Double(x-r,y-r, r+r, r+r);
		g.setColor(Color.red);
		g.draw(tempPoint);
		g.fill(tempPoint);


	}
	/**
		Paint every component in the panel,
		point from pointContainer.
	**/
	public void paintComponent(Graphics g){
		Graphics2D g2 = (Graphics2D) g;
		super.paintComponent(g);
		this.g=g2;

		if(pointContainer==null){
			return;
		}else{
			for(PointExt tempPoint : pointContainer){
				g2.setColor(Color.black);
				draw(tempPoint);
				float tempX = (float) tempPoint.getX();
				float tempY = (float) tempPoint.getY();
				g2.setColor(Color.gray);
				g2.drawString(tempPoint.getName()+"("+tempX+","+tempY+")", tempX, tempY);
			}
		}

	}
	/**
		Clear everything inside pointContainer
		and repaint the panel
	**/
	public void clearPanel(){
		if (pointContainer == null) {
			return;
		}else{
			pointContainer.clear();
			controller.status.setText("===== Point Cleared =====");
		}
	}
	/**
		Open structured file, the data will be converted into pointContainer
		file structure
		Name(x, y)
	**/
	public void openFile(JFileChooser cf){
		File fileData;
		fileData = cf.getSelectedFile();
		controller.status.setText("Load data from : "+fileData.getAbsolutePath());
		try{
			pointContainer.clear();
			pointContainer = fileHandler(fileData.getAbsolutePath());
			String[] pc = getPointList();
			int last = pc.length;

			controller.opsiInit.addItem(pc[last-1]);
			controller.opsiInit.setSelectedIndex(controller.opsiInit.getItemCount()-1);
		}catch(FileNotFoundException ex){
			controller.status.setText("File Not Found");
		}
	}

	public ArrayList<PointExt> fileHandler(String file) throws FileNotFoundException{
		Scanner s = null;
		ArrayList<PointExt> resExt = new ArrayList();
		String[] temp;
		String[] temp2;
		PointExt tempPointExt;

		try{
			s = new Scanner(new BufferedReader(new FileReader(file)));
			while(s.hasNext()){
				temp = s.nextLine().split("\\(");
				temp2 = temp[1].split(",");
				temp2[1] = temp2[1].substring(0,temp2[1].length()-1);
				//System.out.println(temp[0]+"("+temp2[0]+","+temp2[1]+")");
			
				double check = Double.parseDouble(temp2[0]);
				int tex = (int) check;
				check = Double.parseDouble(temp2[1]);
				int tey = (int) check;
				//tempPoint = new Point(tex, tey);
				tempPointExt = new PointExt(temp[0], tex, tey);
				//res.add(tempPoint);
				resExt.add(tempPointExt);
			}
		}
		finally{
			if(s!=null){
				s.close();
			}
		}
		
		return resExt;
	}
	/** 
	Naming Method
	get the latest alphabet for naming the Point.
	@param number reference of the latest alphabet.
	**/
	public static String toBase26(int number){
		number = Math.abs(number);
		String converted = "";
		// Repeatedly divide the number by 26 and convert the
		// remainder into the appropriate letter.
		do
		{
			int remainder = number % 26;
			converted = (char)(remainder + 'A') + converted;
			number = (number - remainder) / 26;
		} while (number > 0);
 
		return converted;
	}
	 
	public static int fromBase26(String number) {
		int s = 0;
		if (number != null && number.length() > 0) {
			s = (number.charAt(0) - 'A');
			for (int i = 1; i < number.length(); i++) {
				s *= 26;
				s += (number.charAt(i) - 'A');
			}
		}
		return s;
	}
	
	public static String getNext26(int number){
		  number++;
		  number = Math.abs(number);
		  String result = toBase26(number);
		  return result;
	}

	/**
		Return name for every point inside pointContainer.	
	**/
	public String[] getPointList(){
		String[] result = new String[pointContainer.size()];
		int i=0;
		for(PointExt temp:pointContainer){
			result[i] = temp.getName();
			i++;
		}
		return result;
	}


}