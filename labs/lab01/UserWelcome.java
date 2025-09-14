import java.util.Scanner;

public class UserWelcome {

  @FunctionalInterface
  interface Welcome {
    void greet(String userName);
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    System.out.print("Ingresa tu nombre: ");
    String userName = sc.nextLine();

    Welcome greetUser = System.out::println;

    greetUser.greet("Bienvenido a Java, " + userName);

    sc.close();
  }
}
