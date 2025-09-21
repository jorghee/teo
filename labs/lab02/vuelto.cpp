
#include <iostream>
#include <vector>

int countChange(int money, const std::vector<int>& coins);

std::vector<std::vector<int>> memo;

int countChangeRecursive(int money, const std::vector<int>& coins, int coinIndex) {
  if (money == 0) return 1;
  if (money < 0 || coinIndex < 0) return 0;

  if (memo[coinIndex][money] != -1) {
    return memo[coinIndex][money];
  }

  int waysWithCurrentCoin = countChangeRecursive(money - coins[coinIndex], coins, coinIndex);
  int waysWithoutCurrentCoin = countChangeRecursive(money, coins, coinIndex - 1);

  return memo[coinIndex][money] = waysWithCurrentCoin + waysWithoutCurrentCoin;
}

int countChange(int money, const std::vector<int>& coins) {
  memo.assign(coins.size(), std::vector<int>(money + 1, -1));
  return countChangeRecursive(money, coins, coins.size() - 1);
}

int main() {
  int amount = 4;
  std::vector<int> denominations = {1, 2};

  std::cout << "Calculando el numero de maneras de dar cambio para la cantidad: " << amount << std::endl;
  std::cout << "Usando las monedas: { ";
  for (size_t i = 0; i < denominations.size(); ++i) {
    std::cout << denominations[i] << (i == denominations.size() - 1 ? "" : ", ");
  }
  std::cout << " }" << std::endl;

  int result = countChange(amount, denominations);

  std::cout << "\nResultado esperado: 3" << std::endl;
  std::cout << "Resultado obtenido: " << result << std::endl;

  return 0;
}
