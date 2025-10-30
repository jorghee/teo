#ifndef IINTEGRABLE_HPP
#define IINTEGRABLE_HPP

/**
 * @interface IIntegrable
 * @brief Define la interfaz para una función matemática de una variable que 
 * puede ser evaluada en un punto.
 *
 * Esta clase abstracta pura sirve como un contrato para cualquier función 
 * que se desee integrar numéricamente.
 */
class IIntegrable {
public:
  /**
   * @brief Destructor virtual para asegurar una correcta destrucción en 
   * clases derivadas.
   */
  virtual ~IIntegrable() = default;

  /**
   * @brief Operador de llamada a función que permite evaluar la función en 
   * un punto x.
   * @param x El punto en el cual evaluar la función.
   * @return El valor de la función en el punto x.
   */
  virtual double operator()(double x) const = 0;
};

#endif
