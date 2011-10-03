package prolog.terms;


/**
  Part of the Term hierarchy, implementing double float
  point numbers.
  @see Term
  @see Nonvar
*/
public class Real extends Num {
  public Real(double i) {
    val=i;
  }

  double val;

  public String name() {
    return ""+val;
  }

  boolean bind_to(Term that,Trail trail) {
     return super.bind_to(that,trail) 
		 && val==((Real)that).val;
  }

  public final int getArity() {
    return Term.REAL;
  }

  public final double getValue() {
	  return val;
  }
}

