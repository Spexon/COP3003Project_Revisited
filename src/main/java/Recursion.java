public class Recursion {


  public void recursion() {

    String str = "Hello";
    System.out.println("Final: " + reverseString(str)); // olleH
  }

  public static String reverseString(String str) {
    System.out.println(str);
    if (str.length() == 1) { // Base case (recursion always needs one of these)
      return str;
    }
    return reverseString(str.substring(1, str.length())) + str.charAt(0);
    //                                     ello                H

  }

}
