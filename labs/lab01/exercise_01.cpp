#include <iostream>
#include <vector>
#include <numeric>

int sumArray(const std::vector<int>& numbers) {
  return std::accumulate(numbers.begin(), numbers.end(), 0);
}

int main() {
  std::vector<int> numbers = {10, 20, 30, 40};
  int total = sumArray(numbers);
  std::cout << "La suma es: " << total << std::endl;

  return 0;
}
