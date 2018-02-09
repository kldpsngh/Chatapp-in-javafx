package application;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class MyNumber {
	
	private DoubleProperty number;
	
	public final double getNumber()
	{
		if(number!=null)
			return number.get();
		return 0;
	}
	
	public final DoubleProperty numberproperty()
	{
		if(number==null)
		{
			number=new SimpleDoubleProperty(0);
		}
		return number;
	}
	public final void setNumber(double number)
	{
		this.numberproperty().set(number);
	}

}
