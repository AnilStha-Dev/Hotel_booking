/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Regular_Expression;

/**
 *
 * @author info_
 */

public class Test1 {
    public static void main(String[] args) {
        RegularExpression re = new RegularExpression();
        
        String str1 = "12-3";
        System.out.println(re.isDate(str1)+ " date");
        
        str1 = "123.4567891011012";
        System.out.println(re.isFloat(str1));
        
        str1 = "Kathmandu Nepal";
        System.out.println("string " + re.isAlphabets(str1));
                
        str1 = "krishna.aryal@gmail.com.np";
        System.out.println(re.isEmail(str1));
        str1 = "krishna.aryal@gmail.com.np";
        System.out.println(re.isEmail(str1));
        
    }
}
