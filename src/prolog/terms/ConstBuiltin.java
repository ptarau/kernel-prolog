package prolog.terms;


/**
* Template for builtins of arity 0
*/

abstract public class ConstBuiltin extends Const {
  
  public ConstBuiltin(String s){
    super(s);
  }
  
  abstract public int exec(Prog p);
  
  public boolean isBuiltin() {
    return true;
  }
}
