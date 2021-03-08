package aula6.exercicio2;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ListsProcess {
	
	public static <T> List<T> filter(List<T> lista, Predicate<T> tester) {
		
		List<T> tmp = new ArrayList<T>();
		for (T t : lista)
			if (tester.test(t))
				tmp.add(t);
		return tmp;
		
	}

}
