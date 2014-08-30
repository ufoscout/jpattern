package com.jpattern.core.validator;

import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.jpattern.core.util.DateUtil;
import com.jpattern.shared.result.IErrorMessage;

/**
 * se i validatori tornano false qualche cosa non va'
 * @author Claudio Quaresima - claudio.quaresima@gmail.com - 23/feb/09 19:14:55
 *
 * @version $Id: AValidator.java,v 1.0, 2009-05-13 21:48:04Z, Quaresima Claudio$
 */
public abstract class AValidator implements IValidator {

	public static final String MIN_MAX_LENGTH = "min_max_len";
    
    public static final String NULL = "null";
    public static final String NOT_VALID = "not_valid";    
    public static final String NUMBER_EXACTLY_LENGTH = "number_exactly";
    public static final String NUMBER_MIN_MAX_LENGTH = "number_min_max_len";
    public static final String LITERAL_EXACTLY_LENGTH = "literal_exactly";    
    public static final String LITERAL_MIN_MAX_LENGTH = "literal_min_max_len";   
    public static final String NUMBER = "number";
    public static final String PARTITA_IVA = "partita_iva";
    public static final String NOT_ADEQUATE = "not_adequate";
    public static final String NOT_MATCH = "not_match";
    public static final String NOT_CORRECT_DATE="date invalid";
    
    
    private List<IErrorMessage> _validateMessages;

    public AValidator(List<IErrorMessage> aValidateMessages) {
        _validateMessages = aValidateMessages;
    }
    
    public abstract void validate() throws Exception ;

    public static Integer toInteger(String aIntegerValue) {
        Integer returned = null;
        try {
            returned = new Integer(aIntegerValue);
        } catch(NumberFormatException n) {
            returned = Integer.valueOf(0);
        }
        
        return returned;
    }
    
    public static Long toLong(String aLongValue) {
        Long returned = null;
        try {
            returned = new Long(aLongValue);
        } catch(NumberFormatException n) {
            returned = Long.valueOf(0);
        }
        
        return returned;
    }
    

    
    protected void add(IErrorMessage aMessageInvalidate) {
        
        _validateMessages.add(aMessageInvalidate);
    }
    
    protected boolean validateNotNull(String aTestString) {
        return  !(aTestString == null || "".endsWith(aTestString.trim())) ;
    }
    
    protected boolean validateLengh(String aTestString, int min, int max) {
        String strPattern = ".{"+min+","+max+"}"; 
        Pattern pattern;
        Matcher matcher;
        boolean returned = true;
        try {
            pattern = Pattern.compile(strPattern); 
            matcher = pattern.matcher(aTestString);
            returned=  matcher.matches();
        } catch (Exception x) {
            
            returned = false;
        }
        
        return returned;
    }

    
    protected boolean validateLengh(String aTestString, int lenght) {
        String strPattern = ".{"+lenght+"}"; 
        Pattern pattern;
        Matcher matcher;
        boolean returned = true;
        try {
            pattern = Pattern.compile(strPattern); 
            matcher = pattern.matcher(aTestString);
            returned=  matcher.matches();
        } catch (Exception x) {
            
            returned = false;
        }
        
        return returned;
    }    

    protected boolean validateLiteralLengh(String aTestString, int min, int max) {
        String strPattern = "[a-zA-Z]{"+min+","+max+"}"; 
        Pattern pattern;
        Matcher matcher;
        boolean returned = true;
        try {
            pattern = Pattern.compile(strPattern); 
            matcher = pattern.matcher(aTestString);
            returned=  matcher.matches();
        } catch (Exception x) {
            
            returned = false;
        }
        
        return returned;
    }
    
    protected boolean validateLiteralLengh(String aTestString, int lenght) {
        String strPattern = "[a-zA-Z]{"+lenght+"}"; 
        Pattern pattern;
        Matcher matcher;
        boolean returned = true;
        try {
            pattern = Pattern.compile(strPattern); 
            matcher = pattern.matcher(aTestString);
            returned=  matcher.matches();
        } catch (Exception x) {
            
            returned = false;
        }
        
        return returned;
    }    

    
    
    protected boolean validateExpRegular(String aTestString, String aExpression) {
        String strPattern = aExpression;
        Pattern pattern;
        Matcher matcher;
        boolean returned = true;
        try {
            pattern = Pattern.compile(strPattern);
            matcher = pattern.matcher(aTestString);
            returned=  matcher.matches();
        } catch (Exception x) {
            returned = false;
        }
        
        return returned;
    }    
    //  [a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?
    protected boolean validateExpEmail(String aEmail) {
        String pattern = "[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";
        return validateExpRegular(aEmail, pattern);
    }
    
    // ([^/]+)(/.*)?(/.*)
    protected boolean validateExpWeb(String aWeb) {
        String pattern = "([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?";
        return validateExpRegular(aWeb, pattern);
    }
    
    protected boolean validateDate(String aDate) {
        try {
            DateUtil.toDate(aDate);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
    
    protected boolean validateNumber(String aNumber){
        return validateExpRegular(aNumber, "[0-9]*$");
    }
    
    protected boolean validateNumber(String aNumber, int lenght){
        return validateExpRegular(aNumber, "[0-9]{"+lenght+"}");
    }    
    
    protected boolean validateNumber(String aNumber, int min, int max){
        return validateExpRegular(aNumber, "[0-9]{"+min+","+max+"}");
    }        
    
    protected boolean validateEuro(String aNumber){
        //String strPattern = "^(0|((\\d{1,3})(\\.\\d{3})*))(,\\d{1,2})?$";
        String strPattern = "^(0|((\\d{1,3})(\\.\\d{3})*))(,\\d{2})$";
        
        
        return validateExpRegular(aNumber, strPattern);
    }
    
    
    protected Date toDate(String aDate) {
        try {
         return  DateUtil.toDate(aDate);
        }
        catch (Exception e) {
            return new Date();
        }
    }
    
    /**
     *  Stringa in formato euro trasformata in double object
     *  
     * @param aEuro stringa in formato valido euro 
     *                  ( es. 1.111,11 to 1111.11 )
     * @return Double object
     */
    protected  Double euroToDouble(String aEuro) {
        String euro = aEuro.trim();
        euro = euro.replaceAll("\\.","");
        euro = euro.replaceAll(",", ".");
        
        Double euroDouble = null;
        try {
            euroDouble =    Double.valueOf(euro);
        }
        catch (NumberFormatException e) {
        }
        return euroDouble;
    }
    
 
    protected boolean validateFloatingPointNumber(String aNumber, int decimali){
        String strPattern = "([-+]?[0-9]+)";
        if(decimali > 0){
            strPattern = strPattern + "(,\\d{1,"+decimali+"})?$";
        }
        return validateExpRegular(aNumber, strPattern);
    }
    
    protected  Double floatingPointToDouble(String aNumber) {
        String number = aNumber.trim();
        number = number.replaceAll(",", ".");
        Double numberDouble = null;
        try {
            numberDouble = Double.valueOf(number);
        }
        catch (NumberFormatException e) {
        }
        return numberDouble;
    }
    
    protected boolean validateIntValue(Integer i,int min, int max){
    	int n = i.intValue();
    	return ((min<n||min==n)&&(n<max||n==max));
    }
    
    protected boolean validateListaPercentuali(Integer[] lista){
    	int len = lista.length;
    	int somma = 0;
    	for(int i=0;i<len;i++){
    		int val = lista[i].intValue();
    		if(val<0||val>100) return false;
    		somma = somma + val;
    	}
    	return (somma<100||somma==100);
    }
    
    protected boolean validatePartitaIva(String aTestString) {
    	return validateExpRegular(aTestString, "[0-9]{11}");
    }
    
    protected boolean validateExpPassword(String aPassword, int min, int max) {
        
        String pattern  ="[a-zA-Z0-9\\_\\*\\-\\+\\!\\?\\,\\:\\;\\.\\xE0\\xE8\\xE9\\xF9\\xF2\\xEC\\x27]{"+min+","+max+"}";
        return validateExpRegular(aPassword, pattern);        
    }
    

    
}
