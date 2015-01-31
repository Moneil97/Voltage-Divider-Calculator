package pakge;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class Sorter {
	
	public enum Sorts{
		closest, heat, r1, r2, result
	}
	
	public enum Ements{
		incrementing, decrementing,
	}
	
	 HashMap<Sorts, Field> map = new HashMap<Sorts, Field>();
	 public Sorts currentSort;
	 public Ements currentEment;

	public Sorter() {
		try {
			//System.out.println(Arrays.toString(Result.class.getDeclaredFields()));
			map.put(Sorts.closest, Result.class.getDeclaredField("distanceFromTarget"));
			map.put(Sorts.heat, Result.class.getDeclaredField("heat"));
			map.put(Sorts.r1, Result.class.getDeclaredField("resistor1"));
			map.put(Sorts.r2, Result.class.getDeclaredField("resistor2"));
			map.put(Sorts.result, Result.class.getDeclaredField("resultVoltage"));
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
	}
	
	public  void sortBy(ArrayList<Result> results, Sorts e, Ements ement){
		
		Collections.sort(results, new Comparator<Result>() {
			
			@Override
			public int compare(Result r1, Result r2) {
				Field f = map.get(e);
				currentSort = e;
				currentEment = ement;
				
				try {
					if (f.get(r1) instanceof Double){
						if (f.getDouble(r1) > f.getDouble(r2))
							return (ement == Ements.incrementing ? 1 : -1);
						else if (f.getDouble(r1) < f.getDouble(r2))
							return (ement == Ements.incrementing ? -1 : 1);
						else
							return 0;
					}
					else if (f.get(r1) instanceof Resistor){
						if (((Resistor) f.get(r1)).value > ((Resistor) f.get(r2)).value)
							return (ement == Ements.incrementing ? 1 : -1);
						else if (((Resistor) f.get(r1)).value < ((Resistor) f.get(r2)).value)
							return (ement == Ements.incrementing ? -1 : 1);
						else
							return 0;
					}
				} catch (IllegalArgumentException | IllegalAccessException e) {
					e.printStackTrace();
				}
				return 0;
			}
		});
	}
}