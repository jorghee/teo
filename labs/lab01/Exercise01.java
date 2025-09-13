import java.util.Arrays;

public class Exercise01 {

  /**
   * @param numbers El arreglo de enteros.
   * @return La suma de los elementos del arreglo
   */
  public static int sumArray(int[] numbers) {
    return Arrays.stream(numbers).sum();
  }

  public static void main(String[] args) {
    int[] numbers = {10, 20, 30, 40};
    int total = sumArray(numbers);
    System.out.println("La suma es: " + total);
  }
}
