package spike;

import java.util.ArrayList;
import java.util.List;

public class ListProva {

	public static void main(String[] args) {
		
		List<String> list = new ArrayList<String>();
		StringBuffer text = new StringBuffer();
		List<String> aList = new WrappedList<String>(list, new StatusJobExecutor(text, null));
		
		aList.add("1");
		aList.add("2");
		aList.add("3");
		
		List<String> list2 = new ArrayList<String>();
		list2.add("4");
		list2.add("5");
		list2.add("6");
		
		aList.addAll(list2);
		
		System.out.println("Voilà la tua lista: ");
		System.out.println(text.toString());
		
		
	}
	
}
