import java.util.ArrayList;
import java.util.*;
import java.util.Iterator;

public class test{

	

public static void main(String[] args) {
	ArrayList data = new ArrayList();
	for(int i=1; i<=10;i++){
		data.add(i);
	}
	ListIterator test = data.listIterator();

	int i = 0;

	while(test.hasNext()){
		while (test.hasNext()) {
			int check = test.next();
			if(check)
		}
		while(test.hasPrevious()){

		}
	}
	
	

	/*
	Conccurent
	while(test.hasNext()) {
		while(testx.hasNext()){
			int check = (int)testx.next();
			if (check % 3 == 0) {
				testx.remove();
			}
			System.out.println("in : "+check);	
		}
		int out = (int)test.next();
		System.out.println("out : "+ out);
		
	}*/
}
	
}