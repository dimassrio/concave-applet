import java.util.ArrayList;

public class Data extends ArrayList<PointExt> {

	public PointExt getLeft(){
		PointExt temp = this.get(0);
		if(this.size()>1){
			for(int i = 1; i< this.size(); i++){
				if(this.get(i).getX()<temp.getX()){
					temp = this.get(i);
				}
			}
			
		}
		return temp;
	}

	public void find(){
		PointExt[] arr = this.toArray(new PointExt[0]);
		PointExt init = getLeft();

		if(init == arr[0]){
			PointExt temp == arr[1];
		}else{
			PointExt temp == arr[0];
		}
		
		for(int i=0; i<arr.length;i++){
			
		}
		//for(int i=0; i<ext.size();i++){}
	}

	public void sort(){

		if(this.size()>1){
		int length = this.size();
		PointExt temp;
		for(int i = 0; i < length; i++){
			for(int j=0; j<length-1; j++ ){
				if(this.get(j).getX()>this.get(j+1).getX()){
					temp = this.get(j);
					this.set(j, this.get(j+1));
					this.set(j+1, temp);
				}

			}
		
		}

		}
	}

	public void printData(){
		for (PointExt temp : this) {
			System.out.println(temp.getName()+" [x="+temp.getX()+", y="+temp.getY()+"]");
		}
	}

	public DataLine coupling(){
		PointExt init = this.get(0);
		PointExt temp = this.get(1);
		Data sorted = new Data();
		sorted.add(init);

		int length = this.size();
		for(int i = 0; i < length; i++){
			for(int j=this.indexOf(temp); j<length-1; j++ ){
				System.out.println(j);
				if(this.get(j)==temp){

				}else{
					if(init.distance(this.get(j+1))<init.distance(temp)){
						temp = this.get(j);
					}	
				}
			}
			System.out.println("init :"+init.getName());
			System.out.println("temp :"+temp.getName());
			sorted.add(temp);
			init = temp;
			temp = this.get(this.indexOf(temp)+1);
		}

		DataLine lineContainer = new DataLine();

		for(int i = 0; i<sorted.size()-1; i++){
			LineExt tempLine = new LineExt(getNext26(sorted.size()), sorted.get(i), sorted.get(i+1));
			lineContainer.add(tempLine);
		}

		return lineContainer;
	}

	public static String getNext26(int number){
		number++;
		number = Math.abs(number);
		String result = toBase26(number);
		return result;
	}

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

}