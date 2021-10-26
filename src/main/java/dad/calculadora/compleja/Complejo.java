package dad.calculadora.compleja;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class Complejo {
	
	private DoubleProperty real= new SimpleDoubleProperty();
	private DoubleProperty imaginario= new SimpleDoubleProperty();
	public final DoubleProperty realProperty() {
		return this.real;
	}
	
	public double getReal() {
		return this.realProperty().get();
	}
	
	public void setReal(double real) {
		this.realProperty().set(real);
	}
	
	public DoubleProperty imaginarioProperty() {
		return this.imaginario;
	}
	
	public double getImaginario() {
		return this.imaginarioProperty().get();
	}
	
	public void setImaginario(double imaginario) {
		this.imaginarioProperty().set(imaginario);
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "("+real.get()+", "+imaginario.get()+")";
	}
	
	
	
	
	
	
	
	
}
