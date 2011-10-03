package prolog.terms;

public class Conj extends Cons {
  public Conj(Term x0,Term x1){
    super(",",x0,x1);
  }
  
  public String conjToString() {
    Term h=args[0].ref();
    Term t=args[1].ref();
    StringBuffer s=new StringBuffer(watchNull(h));
    for(;;) {
      if(!(t instanceof Conj)) {
        s.append(","+t);
        break;
      } else {
        h=((Conj)t).args[0].ref();
        t=((Conj)t).args[1].ref();
        s.append(","+watchNull(h));
      }
    }
    return s.toString();
  }
  
  public String toString() {
    return funToString();
  }
  
  static public final Term getHead(Term T) {
    T=T.ref();
    return (T instanceof Conj)?((Conj)T).getArg(0):T;
  }
  
  static public final Term getTail(Term T) {
    T=T.ref();
    return (T instanceof Conj)?((Conj)T).getArg(1):Const.aTrue;
  }
}
