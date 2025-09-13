#include <iostream>
#include <cmath>
#include <algorithm>

using namespace std;

/*
 * @param hours La cantidad total de horas estacionado. Puede contener fracciones.
 * @return double El cobro total por estacionamiento.
 */
double calculateParkingFee(double hours) {
  if (hours <= 0.0) {
    return 0.0;
  }

  // Redondeamos hacia arriba
  double roundedHours = ceil(hours);

  const double BASE_FEE = 3.00;
  const double HOURLY_RATE = 0.50;
  const double MAX_FEE = 12.00;

  double calculatedFee = BASE_FEE;

  if (roundedHours > 1) {
    calculatedFee += (roundedHours - 1) * HOURLY_RATE;
  }
  
  return min(calculatedFee, MAX_FEE);
}

int main() {
  cout << "Tarifa para 1.0 h: S/" << calculateParkingFee(1.0) << endl;   
  cout << "Tarifa para 3.5 h: S/" << calculateParkingFee(3.5) << endl;
  cout << "Tarida para 24.0 h: S/" << calculateParkingFee(24.0) << endl;

  return 0;
}
