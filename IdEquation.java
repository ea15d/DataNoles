import java.util.*;
import org.apache.commons.math3.util.CombinatoricsUtils;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

class IdEquation {
 
    // Coefficient C(n, r)
	// using source: https://programming-idioms.org/idiom/67/binomial-coefficient-n-choose-k/281/java

	static BigInteger binom(int N, int K) {
	    BigInteger ret = BigInteger.ONE;
	    for (int k = 0; k < K; k++) {
	        ret = ret.multiply(BigInteger.valueOf(N-k))
	                 .divide(BigInteger.valueOf(k+1));
	    }
	    return ret;
	}
	// source end

 
    //public static void main(String[] args)
    public IdEquation() {}
    static BigDecimal IE(int n, int b, int q, double c1, double c2) {
    	/*System.out.println("Enter values:");
    	Scanner sc = new Scanner(System.in);
    	int n= sc.nextInt();
    	int b= sc.nextInt();
    	int q= sc.nextInt();
    	sc.close();*/
        //System.out.println(binomialCoeff(n-b,q));
        //System.out.println(binomialCoeff(n,q));
    	
    	BigInteger num = binom(n-b, q);
    	BigInteger denom = binom(n, q);
    	//double c1=3.53;
    	//double c2=0.001;

        
    	/*System.out.printf("Numerator=" + num+"\n");
        System.out.printf("Denominator=" + denom+"\n");*/

        BigDecimal num2 = new BigDecimal(num);
        BigDecimal denom2 = new BigDecimal(denom);
        BigDecimal Pb_inv = num2.divide(denom2, MathContext.DECIMAL128);
        
        //System.out.println(Pb_inv);

        BigDecimal Pb = BigDecimal.valueOf(1).subtract(Pb_inv);
        
        //System.out.printf("Pb=" + Pb+"\n");
        
        BigDecimal Db = BigDecimal.valueOf(n).divide(BigDecimal.valueOf(b), MathContext.DECIMAL128);
        Db = Db.multiply(Pb);
        BigDecimal temp=BigDecimal.valueOf(c2).multiply(Db, MathContext.DECIMAL128);
        BigDecimal t=BigDecimal.valueOf(c1).add(temp,MathContext.DECIMAL128);
        //System.out.printf("Id Equation Db is = " + Db + "\n");
        //System.out.printf("Value of t_IE is = " + t + "\n");
        
        return t;
    }
}
