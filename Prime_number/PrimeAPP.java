package Prime_number;

public class PrimeAPP{
	private int prime;
	
	public PrimeAPP( int prime ){
		this.prime = prime;
	}
	
	public void FindPrimes(){
		int m,n;
		System.out.println(prime + " ���ڵ����������У� ");
		Cal: for( n = 2 ; n <= prime ; n++){
			for( m = 2; m < n/2 ; m++){
			if (n % m ==0 )
				continue Cal;
			}
			System.out.print(n + " " + "\t");
			}
				
	} 
	

}
