#include <iostream>
#include <vector>
#include <random>
#include <iomanip>

using namespace std;

/**
 * @param totalRolls El número total de veces que se debe lanzar el dado.
 */
void simulateDiceRolls(int totalRolls) {
  random_device rd;
  mt19937 gen(rd());
  uniform_int_distribution<> distrib(1, 6);

  // Un vector de tamaño 6 inicializados con el valor de 0
  vector<int> frequencies(6, 0);

  for (int i = 0; i < totalRolls; ++i) {
    int roll = distrib(gen);
    frequencies[roll - 1]++;
  }

  cout << "Simulación de " << totalRolls << " lanzamientos de dados:" << endl;
  cout << "------------------------------------" << endl;
  for (int i = 0; i < frequencies.size(); ++i) {
    int face = i + 1;
    int count = frequencies[i];
    double percentage = (static_cast<double>(count) / totalRolls) * 100.0;
    
    cout << "Cara " << face << ": " << count << " veces ("
              << fixed << setprecision(2) << percentage << "%)" << endl;
  }
  cout << "------------------------------------" << endl;
}

int main() {
  const int SIMULATION_COUNT = 20000;
  simulateDiceRolls(SIMULATION_COUNT);
  return 0;
}
