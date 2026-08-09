import java.util.*;
// Exercises 1–16. Compile with: javac Basics.java. Run a numbered class, e.g. java Calculator.
class HelloWorld { public static void main(String[] a) { System.out.println("Hello, World!"); } }
class Calculator { public static void main(String[] a) { Scanner s=new Scanner(System.in); System.out.print("Two numbers and + - * /: "); double x=s.nextDouble(),y=s.nextDouble(); String op=s.next(); switch(op){case "+"->System.out.println(x+y);case "-"->System.out.println(x-y);case "*"->System.out.println(x*y);case "/"->System.out.println(y==0?"Cannot divide by zero":x/y);default->System.out.println("Unknown operation");} } }
class EvenOdd { public static void main(String[] a) { int n=new Scanner(System.in).nextInt(); System.out.println(n%2==0?"Even":"Odd"); } }
class LeapYear { public static void main(String[] a) { int y=new Scanner(System.in).nextInt(); System.out.println((y%400==0||y%4==0&&y%100!=0)?"Leap year":"Not a leap year"); } }
class MultiplicationTable { public static void main(String[] a) { int n=new Scanner(System.in).nextInt(); for(int i=1;i<=10;i++)System.out.printf("%d x %d = %d%n",n,i,n*i); } }
class DataTypes { public static void main(String[] a) { int i=42; float f=3.14f; double d=9.81; char c='J'; boolean b=true; System.out.println(i+" "+f+" "+d+" "+c+" "+b); } }
class Casting { public static void main(String[] a) { double d=12.75; int i=(int)d; int n=7; double asDouble=n; System.out.println(i+" "+asDouble); } }
class Precedence { public static void main(String[] a) { int r=10+5*2; System.out.println(r+" (multiplication happens before addition)"); } }
class GradeCalculator { public static void main(String[] a) { int m=new Scanner(System.in).nextInt(); char g=m>=90?'A':m>=80?'B':m>=70?'C':m>=60?'D':'F'; System.out.println(g); } }
class GuessingGame { public static void main(String[] a) { int target=new Random().nextInt(100)+1,guess; Scanner s=new Scanner(System.in); do { guess=s.nextInt(); System.out.println(guess<target?"Too low":guess>target?"Too high":"Correct!"); } while(guess!=target); } }
class Factorial { public static void main(String[] a) { int n=new Scanner(System.in).nextInt(); if(n<0)throw new IllegalArgumentException("non-negative only"); long f=1; for(int i=2;i<=n;i++)f*=i; System.out.println(f); } }
class Overloading { static int add(int a,int b){return a+b;} static double add(double a,double b){return a+b;} static int add(int a,int b,int c){return a+b+c;} public static void main(String[] a){System.out.println(add(1,2));System.out.println(add(1.5,2.5));System.out.println(add(1,2,3));} }
class Fibonacci { static long fibonacci(int n){return n<=1?n:fibonacci(n-1)+fibonacci(n-2);} public static void main(String[] a){System.out.println(fibonacci(new Scanner(System.in).nextInt()));} }
class ArrayStatistics { public static void main(String[] a){Scanner s=new Scanner(System.in);int n=s.nextInt(),sum=0;for(int i=0;i<n;i++)sum+=s.nextInt();System.out.printf("sum=%d average=%.2f%n",sum,(double)sum/n);} }
class ReverseString { public static void main(String[] a){Scanner s=new Scanner(System.in);System.out.println(new StringBuilder(s.nextLine()).reverse());} }
class Palindrome { public static void main(String[] a){String x=new Scanner(System.in).nextLine().replaceAll("[^A-Za-z0-9]","").toLowerCase();System.out.println(x.contentEquals(new StringBuilder(x).reverse())?"Palindrome":"Not a palindrome");} }
