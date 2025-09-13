#include <iostream>

using namespace std;

/**
 * @param hours El número de horas.
 * @param minutes El número de minutos.
 * @param seconds El número de segundos.
 * @return long long El total equivalente en segundos.
 */
long long timeToSeconds(int hours, int minutes, int seconds) {
  const int SECONDS_PER_MINUTE = 60;
  const int SECONDS_PER_HOUR = 60 * 60;

  return static_cast<long long>(hours) * SECONDS_PER_HOUR +
         static_cast<long long>(minutes) * SECONDS_PER_MINUTE +
         seconds;
}

void printTimeInSeconds(int h, int m, int s, long long total) {
  cout << "Tiempo: " << h << "h " << m << "m " << s
      << "s son " << total << " segundos." << endl;
}

int main() {
  int h1 = 1, m1 = 20, s1 = 30;
  long long totalSeconds1 = timeToSeconds(h1, m1, s1);
  printTimeInSeconds(h1, m1, s1, totalSeconds1);

  int h2 = 0, m2 = 1, s2 = 40;
  long long totalSeconds2 = timeToSeconds(h2, m2, s2);
  printTimeInSeconds(h2, m2, s2, totalSeconds2);
  
  return 0;
}
