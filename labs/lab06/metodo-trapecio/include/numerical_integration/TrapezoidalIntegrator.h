#ifndef TRAPEZOIDAL_INTEGRATOR_HPP
#define TRAPEZOIDAL_INTEGRATOR_HPP

#include <functional>
#include <iostream>

/**
 * @class TrapezoidalIntegrator
 * @brief Implementa el método del trapecio para la integración numérica.
 *
 * Esta clase encapsula la lógica para aproximar la integral definida de
 * una función utilizando una suma de áreas de trapecios.
 */
class TrapezoidalIntegrator {
public:
  /**
   * @brief Constructor que inicializa el integrador con un número 
   * predeterminado de subintervalos.
   * @param n El número de trapecios a utilizar para la aproximación.
   */
  explicit TrapezoidalIntegrator(int n = 1000);

  /**
   * @brief Sobrecarga del operador () para realizar la integración.
   * 
   * Permite que una instancia de la clase se comporte como una función,
   * facilitando una sintaxis más limpia y expresiva.
   *
   * @param a Límite inferior de integración.
   * @param b Límite superior de integración.
   * @param func Una función lambda o cualquier objeto invocable que 
   * representa el integrando.
   * @return La aproximación numérica de la integral definida.
   * @throw std::invalid_argument si el número de subintervalos es no 
   * positivo o si a > b.
   */
  double operator()(
    double a, 
    double b, 
    const std::function<double(double)>& func
  ) const;

  /**
   * @brief Establece el número de subintervalos para la integración.
   * @param n El nuevo número de trapecios.
   */
  void setNumIntervals(int n);

  /**
   * @brief Obtiene el número actual de subintervalos.
   * @return El número de trapecios.
   */
  int getNumIntervals() const;

  /**
   * @brief Función amiga para sobrecargar el operador de inserción de flujo.
   * 
   * Permite imprimir el estado del integrador (el número de intervalos) 
   * de forma directa en un stream de salida.
   *
   * @param os El stream de salida (e.g., std::cout).
   * @param integrator El objeto TrapezoidalIntegrator a imprimir.
   * @return Una referencia al stream de salida.
   */
  friend std::ostream& operator<<(
    std::ostream& os, 
    const TrapezoidalIntegrator& integrator
  );

private:
  int num_intervals_;

  /**
   * @brief Calcula el área de un único trapecio.
   * 
   * Declarada como 'inline' para sugerir al compilador que inserte el 
   * código directamente en el lugar de la llamada, optimizando así el 
   * rendimiento en bucles de cálculo intensivo.
   *
   * @param y1 Altura del lado izquierdo del trapecio.
   * @param y2 Altura del lado derecho del trapecio.
   * @param h Ancho del trapecio.
   * @return El área del trapecio.
   */
  inline double trapezoidArea(double y1, double y2, double h) const;
};

#endif
