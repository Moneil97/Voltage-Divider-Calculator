package pakge;

public class Resistor {

	public String name = "";
	public double value;

	public Resistor(String name) {
		this.name = name;
		value = getNumericValue(name);
	}

	private Double getNumericValue(String resistor) {

		if (resistor.endsWith("K")) {
			return Double.parseDouble(resistor.substring(0,
					resistor.length() - 1)) * 1000;
		} else if (resistor.endsWith("M")) {
			return Double.parseDouble(resistor.substring(0,
					resistor.length() - 1)) * 1000000;
		} else {
			return Double.parseDouble(resistor);
		}
	}

}