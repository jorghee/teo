#include <iostream>
#include <vector>
#include <random>
#include <algorithm>

int main() {
  std::random_device rd;
  std::mt19937 gen(rd());
  std::uniform_int_distribution<> distrib(1, 100);

  std::vector<int> numeros;
  const int totalNumeros = 30;
  numeros.reserve(totalNumeros);
  for (int i = 0; i < totalNumeros; ++i) {
    numeros.push_back(distrib(gen));
  }

  // La lambda define la condición: ser impar Y menor que 20.
  long long contador = std::count_if(numeros.begin(), numeros.end(), [](int n) {
    return (n % 2 != 0) && (n < 20);
  });

  std::cout << "Vector generado:" << std::endl;
  for (size_t i = 0; i < numeros.size(); ++i) {
    std::cout << numeros[i] << ((i == numeros.size() - 1) ? "" : ", ");
  }
  std::cout << "\n\n";

  std::cout << "Result: Se encontraron " << contador 
            << " números impares menores de 20." << std::endl;

  return 0;
}
