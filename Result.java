package pakge;

public class Result {

	public double voltage, targetVoltage, resultVoltage, distanceFromTarget, heat;
	public double maxVoltage = Double.MAX_VALUE, minVoltage = 0, maxHeat=Double.MAX_VALUE, minHeat=0;
	public Resistor resistor1, resistor2;
	public boolean valid = false;
	
	public Result(Resistor r1, Resistor r2, double in, double out){
		resistor1 = r1;
		resistor2 = r2;
		voltage = in;
		targetVoltage = out;
	}
	
	public void calculate(){
		resultVoltage = voltage * (resistor2.value / (resistor1.value + resistor2.value));

		distanceFromTarget = Math.abs(targetVoltage - resultVoltage);
		
		//Check Filters
		if (checkDistanceFilter()){
			heat = Math.pow(voltage, 2)/(resistor1.value + resistor2.value);
			valid = checkHeatFilter();
		}
	}
	
	private boolean checkHeatFilter() {
		return (heat > minHeat && heat < maxHeat);
	}

	public boolean checkDistanceFilter(){
		return (resultVoltage > minVoltage && resultVoltage < maxVoltage);
	}
	
	public String toString(){
		return (resistor1.name + " + " + resistor2.name +" = " + String.format("%.2fv", resultVoltage) + String.format(" (%.2fv)",targetVoltage - resultVoltage) + String.format(" (Heat: %.2f watts)", heat));
	}
}
