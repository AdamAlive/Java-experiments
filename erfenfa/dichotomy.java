package erfenfa;

public class dichotomy {
	private double x_low = -10.0;
	private double x_high = 5.0;
	private double x_c;
	private final double delta = 0.001;
	

	public double cal(double x){
		return (x*x*x - 10*x +23);
	}
	

	public double getAnswer() {
		while(cal(x_c) != 0 && x_high - x_low >= delta)
		{
			x_c = (x_low + x_high) / 2;
			if (cal(x_c) * cal(x_low) <0 ){
				x_high = x_c;
			}else{
				x_low = x_c;
			}
		}
		return x_c;
	}
}
