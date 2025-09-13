#include <iostream>
// #include <algorithm>

using namespace std;

double customMin(double x, double y) {
  return (x < y) ? x : y;
}

/**
 * @param a El primer número.
 * @param b El segundo número.
 * @param c El tercer número.
 * @return double El valor más pequeño entre los tres parámetros.
 */
double getSmallestNumber(double a, double b, double c) {
  // return min({a, b, c});
  return customMin(customMin(a, b), c);
}

int main() {
  cout << "Menor de 10.5, 3.2, 7.8 es: " << endl
    << getSmallestNumber(10.5, 3.2, 7.8) << endl;
  cout << "Menor de 100.0, 100.0, 100.1 es: " << endl
    << getSmallestNumber(100.0, 100.0, 100.1) << endl;
  return 0;
}
