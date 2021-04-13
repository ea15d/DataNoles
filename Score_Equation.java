import java.util.*;
import java.math.BigDecimal;
import java.math.MathContext;
import java.lang.Math;
import java.math.RoundingMode;

class Score_Equation {
	//public static void main(String[] args) 
	public Score_Equation() {}
	public static BigDecimal SE(int n, int b, int q, int k, double c1, double c2) {
		//double c1=3.53;
    	//double c2=0.001;
		/*System.out.println("Enter values:");
    	Scanner sc = new Scanner(System.in);
    	double n= sc.nextInt();
    	double b= sc.nextInt();
    	double q= sc.nextInt();
    	double k = sc.nextInt();
    	sc.close();*/
		
    	BigDecimal p = BigDecimal.valueOf(n).divide(BigDecimal.valueOf(q), MathContext.DECIMAL128); 
    	//n/q;
    	
    	
    	BigDecimal Db = BigDecimal.valueOf(1).divide(BigDecimal.valueOf(b), MathContext.DECIMAL128);
    	Db = Db.multiply(p);
    	Db = Db.multiply(BigDecimal.valueOf(k));
    	Db = Db.setScale(0, RoundingMode.UP);
    	//Math.ceil((1/b)*p*k);
    	BigDecimal temp=BigDecimal.valueOf(c2).multiply(Db, MathContext.DECIMAL128);
        BigDecimal t=BigDecimal.valueOf(c1).add(temp,MathContext.DECIMAL128);
    	//System.out.printf("Score Equation Db is = " + Db + "\n");
    	//System.out.printf("Value of t_SE is = " + t + "\n");
    	return t;
	}
}