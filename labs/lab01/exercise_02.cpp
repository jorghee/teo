#include <iostream>
#include <vector>
#include <limits>

using namespace std;

/**
 * @param count El número de enteros ingresados por el usuario.
 * @return vector<int> Un vector validado que contiene los números ordenados
 */
vector<int> getStrictlyIncreasingNumbers(int count) {
  if (count <= 0) {
    return {};
  }

  vector<int> numbers;
  numbers.reserve(count);

  cout << "Ingresar " << count << " enteros, cada uno mayor que el anterior." << endl;

  for (int i = 0; i < count; ++i) {
    int currentNumber;
    while (true) {
      cout << "Ingresar el número " << i + 1 << ": ";
      cin >> currentNumber;

      // Validar el input
      if (cin.fail()) {
        cout << "Error: tipo inválido. Ingresa un entero." << endl;
        cin.clear();
        cin.ignore(numeric_limits<streamsize>::max(), '\n');
        continue;
      }

      // Validar los números
      if (numbers.empty() || currentNumber > numbers.back()) {
        numbers.push_back(currentNumber);
        break;
      } else {
        cout << "Error: El número debe ser mayor que " << numbers.back()
          << ". Try again." << endl;
      }
    }
  }

  return numbers;
}

int main() {
  const int NUMBERS_TO_GET = 10;
  vector<int> validatedNumbers = getStrictlyIncreasingNumbers(NUMBERS_TO_GET);

  cout << "\nLos números ingresados son:" << endl;
  for (int number : validatedNumbers) {
    cout << number << " ";
  }
  cout << endl;

  return 0;
}
