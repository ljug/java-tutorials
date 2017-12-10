/**
 * Titre : <p>NAOP</p>
 * Description : The NAOP interpreter<p>
 * Copyright : Copyright (c) Pascal Farès<p>
 * <p>ISAE</p>
 * @author Pascal Farès
 * @version $*
 */
package ljug.langage;
import java.io.*;
import static ljug.langage.URCTokenizer.Token;
/**
 * The special Tokenizer for the NAOP Language
 * @see java.io.StreamTokenizer
 */
public class URCTokenizer extends StreamTokenizer implements Serializable {
    /** the set of keywords
     */
    public static enum Token {
        /**
         * (
         */
        PO,
        /**
         * )
         */
        PF,
        /**
         * +
         */
        PLUS,
        /**
         * -
         */
        MOINS,
        /**
         * *
         */
        MULT,
        /**
         * /
         */
        DIV,
        /**
         * a valid java symbol
         */
        SYMBOL, //une variable (symbole)
        /**
         * A double
         */
        CONST,
        /**
         * end instruction ;
         */
        FININST, //séparateur ;
        /** Quiter
         */
        QUIT,
        /**
         * =
         */
        AFFECT,
        /**
         * !
         */
        EVAL,
        /**
         * show
         */
        SHOW,     
       
        ASSOC,
        
        LISTU,
        LISTR,

        ADDUSER, //pour valider une expression selon la note éliminatoire entre 0 et 4
        
        ADDROLE, //pour valider une expression selon la note éliminatoire entre 0 et 5

         /**
         * nop
         */
        NOP
                
    }
    
    Token  tok=Token.NOP;
    /**
     * Tokenizer from a String
     * @param is The input String
     */
    public URCTokenizer(String is) {
        super(new BufferedReader(new StringReader(is)));
        this.slashStarComments(true);
        this.ordinaryChar('"');
    }
    /**
     * Tokenizer from an InputStream
     * @param is The input Stream
     */
    public URCTokenizer(InputStream is) {
        super(new BufferedReader(new InputStreamReader(is)));
        this.slashStarComments(true);
        this.eolIsSignificant(true);
        this.ordinaryChar('"');
    }
    
    /**
     * The nextToken for NAOP language
     * @return the token type. Token is enum
     */
    public Token nToken() {
        tok=Token.NOP;
        
        try {
            switch (super.nextToken()) {
                case TT_WORD :
                    if (sval.equals("quit")) return (tok=Token.QUIT);
                else if(sval.equals("show")) return (tok=Token.SHOW);
                else if(sval.equals("assoc")) return (tok=Token.ASSOC);
                else if(sval.equals("listu")) return (tok=Token.LISTU);
                else if(sval.equals("listr")) return (tok=Token.LISTR);
                else if(sval.equals("adduser")) return (tok=Token.ADDUSER); 
                else if(sval.equals("addrole")) return (tok=Token.ADDROLE); 
                else return (tok=Token.SYMBOL);
                case TT_NUMBER : return (tok=Token.CONST) ;
                case TT_EOL: return (tok=Token.FININST);
                default :
                    if (ttype == '+') return (tok=Token.PLUS);
                    else if (ttype=='-') return (tok=Token.MOINS);
                    else if (ttype=='*') return (tok=Token.MULT);
                    else if (ttype==':') return (tok=Token.DIV);
                    else if (ttype=='(') return (tok=Token.PO);
                    else if (ttype==')') return (tok=Token.PF);
                 
                    else if (ttype==';') return (tok=Token.FININST);
                    else if (ttype=='=') return (tok=Token.AFFECT);
                    else if (ttype=='!') return (tok=Token.EVAL);
                  
                    else return tok=Token.NOP;
            }
        } catch (IOException e) {System.out.println(e); }
        return (tok);
    }
    /**
     * The last Token
     * @return the token type
     */
    public Token getTok() {return tok;}
    /**
     * Pretty print the current token
     * @return the string pretty print
     */
    @Override
    public String toString() {
        switch (tok) {
            case PO:    return ("PO"); //parenth�se ouvrante
            case PF:    return ("PF"); //parenth�se fermante
            case PLUS:  return("PLUS");
            case MOINS: return ("MOINS");
            case MULT:  return ("MULT");
            case DIV:   return ("DIV");
            case SYMBOL:   return ("VAR:"+sval); //une variable (simbole)
            case CONST: return("CONST:"+nval);
            case FININST:return("SEP"); //s�parateur
            case QUIT:  return("QUIT");
            case NOP:   return("NOP?");
            case AFFECT:return("AFFECT");
            case EVAL:  return("EVAL");
            case ADDUSER: return("ADDUSER");
            case ADDROLE: return("ADDROLE");
            default: return("Big problème! is "+tok);
        }
    }
    
    
}
