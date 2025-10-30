#include "numerical_integration/TrapezoidalIntegrator.h"
#include <stdexcept>
#include <numeric>
#include <vector>

TrapezoidalIntegrator::TrapezoidalIntegrator(int n) : num_intervals_(n) {}

void TrapezoidalIntegrator::setNumIntervals(int n) {
  num_intervals_ = n;
}

int TrapezoidalIntegrator::getNumIntervals() const {
  return num_intervals_;
}

inline double TrapezoidalIntegrator::trapezoidArea(
  double y1, double y2, double h
) const {
  return (y1 + y2) * h / 2.0;
}

double TrapezoidalIntegrator::operator()(
  double a, 
  double b, 
  const std::function<double(double)>& func
) const {
  // Manejo de Excepciones
  // Se valida que los parámetros de entrada sean lógicos.
  if (num_intervals_ <= 0) {
    throw std::invalid_argument(
      "El número de intervalos debe ser positivo."
    );
  }
  if (a > b) {
    throw std::invalid_argument(
      "El límite inferior 'a' no puede ser mayor que el límite superior 'b'."
    );
  }
  if (a == b) {
    return 0.0;
  }

  // Método del Trapecio
  const double h = (b - a) / num_intervals_;
  double integral_sum = 0.0;

  for (int i = 0; i < num_intervals_; ++i) {
    const double x1 = a + i * h;
    const double x2 = a + (i + 1) * h;
    integral_sum += trapezoidArea(func(x1), func(x2), h);
  }

  return integral_sum;
}

// Función friend
std::ostream& operator<<(
  std::ostream& os, 
  const TrapezoidalIntegrator& integrator
) {
  os << "Integrador Numérico (Método del Trapecio)\n"
     << "  - Número de intervalos: " << integrator.num_intervals_;
  return os;
}
