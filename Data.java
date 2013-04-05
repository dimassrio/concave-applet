import java.util.ArrayList;
import java.util.ListIterator;

public class Data extends ArrayList<PointExt> {

	public PointExt getLeft(){
		PointExt temp = this.get(0);
		int x = 0;
		if(this.size()>1){
			for(int i = 1; i< this.size(); i++){
				if(this.get(i).getX()<temp.getX()){
					temp = this.get(i);
					x = i;
				}
			}
			this.remove(x);
		}
		return temp;
	}

	public PointExt getRight(){
		PointExt temp = this.get(0);
		if(this.size()>1){
			for (int i = 1; i<this.size() ; i++) {
				if(this.get(i).getX()>temp.getX()){
					temp = this.get(i);
				}
			}
		}
		return temp;
	}

	public DataLine getSorted(){
		Data result = new Data();
		PointExt init = getLeft();
		PointExt temp = this.get(0);
		int flag = 0;
		Data ori  = (Data) this.clone();
		result.add(init);
		for(int i = this.size()-1; i>=0; i--){
			for(int j = 0; j<this.size(); j++){
				if(init==getRight()){
					flag = 1;
				}

				if(!result.contains(this.get(j))){
					if(flag==0){
						if((this.get(j).getX()>init.getX())&&(init.distance(this.get(j))<init.distance(temp))){
							temp = this.get(j);
						}
					}else{
						if((this.get(j).getX()<init.getX())&&(init.distance(this.get(j))<init.distance(temp))){
							temp = this.get(j);
						}
					}
				}
			}
			result.add(temp);
			init = temp;
			for(int k = 0; k<this.size();k++){
				if(!result.contains(this.get(k))){
					temp = this.get(k);
				}
			}
			
		}
		result.add(result.get(1));

		DataLine resultLine = new DataLine();

		for(int i=0; i < result.size()-1; i++){
			LineExt tempLine = new LineExt(getNext26(resultLine.size()), result.get(i), result.get(i+1));
			resultLine.add(tempLine);
			System.out.println(tempLine.getPext1().getName()+","+tempLine.getPext2().getName());
		}

		LineExt finalLine = new LineExt(getNext26(resultLine.size()), result.get(result.size()-1), result.get(0));
		resultLine.add(finalLine);
		return resultLine;

		/*for(int i = 0; i<this.size(); i++){
			for(int j = 0; j<this.size(); j++){
				if(init == getRight()){
					flag=1;
				}
				if(init != temp){
					System.out.println(init.getName()+"!="+temp.getName());
					if((flag==0)){
					/*if((this.get(j).getX()>init.getX())&&(init.distance(this.get(j))<init.distance(temp))&&(result.contains(this.get(j)))){
						temp = this.get(j);
					}*/

			/*		if(this.get(j).getX()>init.getX()){
						if(result.contains(this.get(j))==false&&ori.contains(this.get(j))==true) {
							if(init.distance(this.get(j))<init.distance(temp)){
								if(init.equals(temp)==false){
									temp = this.get(j);
								}
							}
						}
					}

				}else{
					/*if((this.get(j).getX()<init.getX())&&(init.distance(this.get(j))<init.distance(temp))&&(result.contains(this.get(j)))){
						temp = this.get(j);
					}*/

			/*		if(this.get(j).getX()<init.getX()){
						if(result.contains(this.get(j))==false&&ori.contains(this.get(j))==true) {
							if(init.distance(this.get(j))<init.distance(temp)){
								if(init.equals(temp)==false){
									temp = this.get(j);
								}
							}
						}
					}
				}
				}
			}*/
		//for (PointExt print : result ) {
		//	System.out.println(print.hashCode());
		//}
	}

	public void find(){
		PointExt init = getLeft();
		Data tempOri = (Data)this.clone();
		Data tempData = new Data();
		tempData.add(init);
		PointExt temp = tempOri.get(0);
		ListIterator<PointExt> it = tempOri.listIterator();
		int flag = 0;
		int idx = 0;
		while(it.hasNext()){
			while(it.hasNext()){
				PointExt check = it.next();
				if(check == getRight()){
					flag = 1;
				}

				if(flag == 0){
					if((check.getX()>init.getX())&&(init.distance(check)<init.distance(temp))&&!(tempData.contains(check))){
						temp = check;
						idx = it.nextIndex();
					}
				}else{
					if((check.getX()<init.getX())&&(init.distance(check)<init.distance(temp))&&!(tempData.contains(check))){
						temp = check;
						idx = it.nextIndex();
					}					
				}
			}
			tempData.add(temp);
			System.out.println(temp.getName());
			tempOri.remove(temp);
			it = tempOri.listIterator();
			while(it.hasPrevious()){
				PointExt check = it.next();
				if(it.next() == getRight()){
					flag = 1;
				}

				if(flag == 0){
					if((check.getX()>init.getX())&&(init.distance(check)<init.distance(temp))&&!(tempData.contains(check))){
						temp = check;
						idx = it.nextIndex();
					}
				}else{
					if((check.getX()<init.getX())&&(init.distance(check)<init.distance(temp))&&!(tempData.contains(check))){
						temp = check;
						idx = it.nextIndex();
					}					
				}
			}
			tempData.add(temp);
			System.out.println(temp.getName());
			tempOri.remove(temp);
			it = tempOri.listIterator();
		}

		/*for (PointExt iterate : tempData ) {
			System.out.println(iterate.getName());
		}*/
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