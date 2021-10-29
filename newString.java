import java.lang.String;

public class newString {
    public static void main(String args[]) {
        String s = "Sachin";
        
        //toUpperCase - To uppercase the entire string.
        //toLowerCase - To lowercase the entire string.
        System.out.println(s.toUpperCase()); //SACHIN
        System.out.println(s.toLowerCase()); //sachin
        System.out.println(s); //Sachin(No changes.)


        //Trim - To remove unwanted space from both side.
        //No specific trim syntax for left or right side.
        String s1 = " Sachin ";
        System.out.println(s1); // _Sachin_
        System.out.println(s1.trim()); //Sachin
        

        //startsWith - Boolean form. To verify if the string starts with that alphabet or not.
        //endsWith - Boolean form. To verify if the string ends with that alphabet or not.
        System.out.println(s.startsWith("S")); //true
        System.out.println(s.endsWith("n")); //true
        System.out.println(s.endsWith("g")); //False

        
        //charAt - To print alphabet based on INdex number.
        System.out.println(s.charAt(0));
        System.out.println(s.charAt(5));

        
        //lenght - lenght of string.
        System.out.println(s.length()); //6

        
        //valueOf - 
        int a = 10;
        String s3 = String.valueOf(a);
        System.out.println(s3+10);

        
        //replace - what to replace from what. 
        String s2 = "Java is programming language. Java is platform independent.";
        String replaceString = s2.replace("Java", "Kava"); //Replace all occurence of "JAVA" into "KAVA".
        System.out.println(replaceString);

        
        //subString - 
    }
}
