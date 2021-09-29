package wcscda.dojo;

import java.util.*;

/* NRO 2021-09-28 : Ecrire une fonction toArabic qui convertit
 *  des nombres romain (sous forme de string) en nombre (sous
 *  forme de int ou Integer) 
 * 
 * Voir exemple dans NumberTest
 */
public class Number {
    public static int toArabic(String strRoman) {
        Map<Character, Integer> mapRomanArabic = new HashMap<>();
        mapRomanArabic.put('I', 1);
        mapRomanArabic.put('V', 5);
        mapRomanArabic.put('X', 10);
        mapRomanArabic.put('L', 50);
        mapRomanArabic.put('C', 100);
        mapRomanArabic.put('D', 500);
        mapRomanArabic.put('M', 1000);
        int result = 0;
        // strRoman.
        for (int i = 0; i < strRoman.length() - 1; i++) {
            int arabic = mapRomanArabic.get(strRoman.charAt(i));
            if(mapRomanArabic.get(strRoman.charAt(i+1)) > arabic) {
                arabic *= -1;
            }
            result += arabic;
        }
        result += mapRomanArabic.get(strRoman.charAt(strRoman.length() - 1));

        return result;
    }

}
