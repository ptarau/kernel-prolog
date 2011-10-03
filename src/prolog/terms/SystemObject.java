package prolog.terms;

/**
 * A SystemObject is a  Nonvar with system assigned name
 * 
 */
public class SystemObject extends Nonvar {
  
  static long ctr=0;
  
  private long ordinal;
  
  public SystemObject(){
    ordinal=++ctr;
  }
  
  public String name() {
    return "{"+getClass().getName()+"."+ordinal+"}";
  }
  
  boolean bind_to(Term that,Trail trail) {
    return super.bind_to(that,trail)&&ordinal==((SystemObject)that).ordinal;
  }
  
  public String toString() {
    return name();
  }
  
  public final int getArity() {
    return Term.JAVA;
  }
}
