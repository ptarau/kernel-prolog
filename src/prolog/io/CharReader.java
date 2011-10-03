package prolog.io;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import prolog.terms.Const;
import prolog.terms.Int;
import prolog.terms.Prog;
import prolog.terms.Source;
import prolog.terms.Term;

/**
  Builds  Fluents from Java
  Streams
*/
public class CharReader extends Source {
  public Reader reader;
  
  public CharReader(Reader reader,Prog p){
    super(p);
    this.reader=reader;
  }
  
  public CharReader(String f,Prog p){
    super(p);
    makeReader(f);
  }
  
  public CharReader(Term t,Prog p){
    super(p);
    this.reader=new StringReader(t.toUnquoted());
  }
  
  public CharReader(Prog p){
    this(IO.input,p);
  }
  
  protected void makeReader(String f) {
    this.reader=IO.url_or_file(f);
  }
  
  public Term getElement() {
    if(IO.input==reader) {
      String s=IO.promptln(">:");
      if(null==s||s.length()==0)
        return null;
      return new Const(s);
    }
    
    if(null==reader)
      return null;
    int c=-1;
    try {
      c=reader.read();
    } catch(IOException e) {
    }
    if(-1==c) {
      stop();
      return null;
    } else
      return new Int(c);
  }
  
  public void stop() {
    if(null!=reader&&IO.input!=reader) {
      try {
        reader.close();
      } catch(IOException e) {
      }
      reader=null;
    }
  }
}
