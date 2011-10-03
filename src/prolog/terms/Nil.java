package prolog.terms;

/**
  Special constant terminating a list
*/
public class Nil extends Const {
  public Nil(String s){
    super(s);
  }
  
  public Nil(){
    this("[]");
  }
}
