import java.util.ArrayList;

public class Data extends ArrayList<PointExt> {

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

}