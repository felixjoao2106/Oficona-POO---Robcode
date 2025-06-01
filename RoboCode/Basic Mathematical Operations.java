/*

Autor: João Vitor Felix Aizza da Silva
Data: 13/05/2025
Atividade: 04
Kata: Basic Mathematical Operations
 
*/
 
public class BasicOperations 
{
  public static Integer basicMath(String op, int v1, int v2)
  {
    switch (op) {
      case "+":
        return v1 + v2;
      case "-":
        return v1 - v2;
      case "*":
        return v1 * v2;
      case "/":
        if (v2 == 0) {
          throw new ArithmeticException("Divisão por zero não é permitida.");
        }
        return v1 / v2;
      default:
        throw new IllegalArgumentException("Operação inválida: " + op);
    }
  }
 
  public static void main(String[] args) {
    System.out.println(basicMath("+", 4, 7));
    System.out.println(basicMath("-", 10, 3));
    System.out.println(basicMath("*", 6, 6)); 
    System.out.println(basicMath("/", 12, 4)); 
  }
}