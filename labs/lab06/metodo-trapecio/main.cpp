#include <iostream>
#include <cmath>
#include <iomanip>
#include "numerical_integration/TrapezoidalIntegrator.hpp"

void demonstrate_integration(
  const TrapezoidalIntegrator& integrator,
  double a, 
  double b, 
  const std::function<double(double)>& func,
  const std::string& func_name
);

int main() {
  std::cout << std::fixed << std::setprecision(8);

  // 'integrator' es un objeto constante. Sus métodos deben ser 'const'.
  const TrapezoidalIntegrator integrator(10000); 
  
  // Se define la función a integrar usando una expresión lambda.
  // Esto permite declarar funciones anónimas y locales de forma concisa.
  auto poly_func = [](double x) -> double {
    return x * x + 2 * x + 1; // f(x) = (x+1)^2
  };
  
  // Otra función lambda para demostrar con una función trigonométrica.
  auto sin_func = [](const double x) { return std::sin(x); };

  std::cout << ">> Método del Trapecio" << std::endl;
  
  // 2. Sobrecarga de Operador () y Función Amiga (<<)
  // Se imprime el estado del integrador usando el operador '<<' sobrecargado.
  std::cout << integrator << std::endl << std::endl;

  // Se invoca el integrador como si fuera una función gracias a 'operator()'.
  demonstrate_integration(integrator, 0.0, 2.0, poly_func, "f(x) = x^2 + 2x + 1");
  demonstrate_integration(integrator, 0.0, M_PI, sin_func, "f(x) = sin(x)");

  std::cout << "\n>> Manejo de Excepciones" << std::endl;
  try {
    std::cout << "Intervalo inválido (a > b)..." << std::endl;
    // Esto lanzará una excepción std::invalid_argument.
    integrator(3.0, 1.0, poly_func); 
  } catch (const std::invalid_argument& e) {
    std::cerr << "Error: " << e.what() << std::endl;
  }

  return 0;
}

/**
 * @brief Función auxiliar para ejecutar y mostrar los resultados de la 
 * integración.
 */
void demonstrate_integration(
  const TrapezoidalIntegrator& integrator,
  double a, 
  double b, 
  const std::function<double(double)>& func,
  const std::string& func_name
) {
  std::cout << "Integrando " << func_name 
            << " desde " << a << " hasta " << b << "..." << std::endl;
  
  double result = integrator(a, b, func); // Uso de operator()
  
  std::cout << "  > Resultado aproximado: " << result << std::endl << std::endl;
}
