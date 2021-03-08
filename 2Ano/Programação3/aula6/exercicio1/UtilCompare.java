package aula6.exercicio1;

public class UtilCompare {
	
	@SuppressWarnings("unchecked")
	public static Comparable findMax(Comparable[] arrayObj) {
		int max = 0;
		for (int i = 0; i < arrayObj.length; i++) {
			if (arrayObj[i] != null && arrayObj[max].compareTo(arrayObj[i]) == -1) {
				max = i;
			}
		}
		return arrayObj[max];
	}
	
	
	@SuppressWarnings("unchecked")
	public static void sortArray(Comparable[] arrayObj) {
		Comparable temp;
		for (int i = 0; i < arrayObj.length; i++) {
			for (int j = 0; j < arrayObj.length-1; j++) {
				if (arrayObj[j].compareTo(arrayObj[j+1]) == 1) {
					temp = arrayObj[j];
					arrayObj[j] = arrayObj[j+1];
					arrayObj[j+1] = temp;
				}
			}
		}
	}

}
