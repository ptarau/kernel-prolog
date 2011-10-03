package prolog.terms;

import java.util.Stack;

/**
  Basic toplevel Prolog Engine. Loads and executes Prolog
  programs and can be extended to spawn threads executing on new Prolog Engine
  objects as well as networking threads and 
  synced local and remote Linda transactions
*/
public class Prog extends Source implements Runnable {
  // CONSTRUCTORS
  
  /**
    Creates a Prog starting execution with argument "goal" 
  */
  public Prog(Clause goal,Prog parent){
    super(parent);
    this.parent=parent;
    goal=goal.ccopy();
    this.trail=new Trail();
    this.orStack=new Stack();
    if(null!=goal)
      orStack.push(new Unfolder(goal,this));
    
  }
  
  // INSTANCE FIELDS
  
  private Trail trail;
  
  private Stack orStack;
  
  private Prog parent;
  
  public final Trail getTrail() {
    return trail;
  }
  
  public final Prog getParent() {
    return parent;
  }
  
  // CLASS FIELDS
  
  public static int tracing=1;
  
  // INSTANCE METHODS
  
  public Term getElement() {
    if(null==orStack)
      return null;
    Clause answer=null;
    while(!orStack.isEmpty()) {
      Unfolder I=(Unfolder)orStack.pop();
      answer=I.getAnswer();
      if(null!=answer)
        break;
      Clause goal=(Clause)I.getElement();
      if(null!=goal) {
        if(I.notLastClause())
          orStack.push(I);
        else
          I.stop();
        if(null==answer)
          orStack.push(new Unfolder(goal,this));
      }
    }
    Term head;
    if(null==answer) {
      head=null;
      stop();
    } else
      head=answer.getHead();
    return head;
  }
  
  public void stop() {
    if(null!=trail) {
      trail.unwind(0);
      trail=null;
    }
    orStack=null;
  }
  
  /** 
    Computes a copy of the first solution X of Goal G.
  */
  
  static public Term firstSolution(Term X,Term G) {
    Prog p=new_engine(X,G);
    Term A=ask_engine(p);
    if(A!=null) {
      A=new Fun("the",A);
      p.stop();
    } else
      A=Const.aNo;
    return A;
  }
  
  static public Prog new_engine(Term X,Term G) {
    Clause C=new Clause(X,G);
    Prog p=new Prog(C,null);
    return p;
  }
  
  static public Term ask_engine(Prog p) {
    return p.getElement();
  }
  
  public void run() {
    for(;;) {
      Term Answer=getElement();
      if(null==Answer)
        break;
    }
  }
}