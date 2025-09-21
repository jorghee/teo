#include <iostream>
#include <vector>

int countChange(int money, const std::vector<int>& coins);

/**
 * @brief Función auxiliar recursiva que resuelve el problema.
 *
 * Utiliza un índice para rastrear qué monedas estamos considerando,
 * evitando así la creación ineficiente de nuevos vectores en cada llamada.
 *
 * @param money La cantidad restante de dinero a cambiar.
 * @param coins El vector constante de denominaciones de monedas.
 * @param coinIndex El índice de la moneda que estamos considerando actualmente.
 * @return El número de maneras de dar cambio.
 */
int countChangeRecursive(int money, const std::vector<int>& coins, int coinIndex) {
  if (money == 0) {
    return 1;
  }

  if (money < 0 || coinIndex < 0) {
    return 0;
  }

  int waysWithCurrentCoin = countChangeRecursive(money - coins[coinIndex], coins, coinIndex);

  int waysWithoutCurrentCoin = countChangeRecursive(money, coins, coinIndex - 1);

  return waysWithCurrentCoin + waysWithoutCurrentCoin;
}

/**
 * @brief Función pública que inicia el proceso recursivo.
 *
 * @param money La cantidad total de dinero a cambiar.
 * @param coins El vector de denominaciones de monedas disponibles.
 * @return El número de maneras diferentes de dar cambio.
 */
int countChange(int money, const std::vector<int>& coins) {
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
