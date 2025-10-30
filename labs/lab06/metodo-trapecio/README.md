# Implementación del Método del Trapecio en C++

## 1. Lógica del Método del Trapecio

El método del trapecio es una técnica de integración numérica que 
aproxima el valor de una integral definida.

$$ \int_{a}^{b} f(x) \,dx $$

La idea fundamental es dividir el intervalo `[a, b]` en `n`
subintervalos de igual ancho, `h`, y aproximar el área bajo la curva
en cada subintervalo mediante un trapecio.

El ancho de cada subintervalo es:

$$ h = \frac{b - a}{n} $$

La integral completa se aproxima como la suma de las áreas de todos
los trapecios:

$$ \int_{a}^{b} f(x) \,dx \approx \sum_{i=0}^{n-1} A_i = \frac{h}{2} \sum_{i=0}^{n-1} (f(x_i) + f(x_{i+1})) $$

## 2. Estructura del Proyecto

El código está organizado en una estructura modular para facilitar su
mantenimiento y escalabilidad.

```sh
metodo-trapecio-cpp/
├── include/
│ └── numerical_integration/
│ ├── IIntegrable.hpp
│ └── TrapezoidalIntegrator.hpp
├── src/
│ └── numerical_integration/
│ └── TrapezoidalIntegrator.cpp
├── main.cpp
└── README.md
```

- **`include/`**: Contiene los archivos de cabecera (`.hpp`). La clase
principal `TrapezoidalIntegrator` define la interfaz pública del
integrador.
- **`src/`**: Contiene los archivos de implementación (`.cpp`), donde
se desarrolla la lógica de los métodos declarados en las cabeceras.
- **`main.cpp`**: Es el punto de entrada del programa. Su propósito es
demostrar el uso de la clase `TrapezoidalIntegrator` con funciones de
ejemplo.

## 3. Aplicación de Conceptos de C++

### Funciones en línea (`inline`)

La función `trapezoidArea`, que calcula el área de un solo trapecio,
se llama repetidamente dentro de un bucle. Para optimizar el
rendimiento, se declara como `inline`. Esto sugiere al compilador que
reemplace la llamada a la función por su código, evitando la
sobrecarga de una llamada a función.

*Fragmento de `TrapezoidalIntegrator.hpp`*:
```cpp
private:
  /**
   * @brief Calcula el área de un único trapecio.
   * 
   * Declarada como 'inline' para sugerir al compilador que inserte el 
   * código directamente en el lugar de la llamada.
   */
  inline double trapezoidArea(double y1, double y2, double h) const;
```


### Funciones amigas (`friend`)

Para permitir que una función externa acceda a los miembros privados
de la clase `TrapezoidalIntegrator`, se utiliza una función amiga.
En este caso, se sobrecarga el operador `<<` para que pueda imprimir
directamente el estado de un objeto `TrapezoidalIntegrator`.

*Fragmento de `TrapezoidalIntegrator.hpp`*:
```cpp
  friend std::ostream& operator<<(
    std::ostream& os, 
    const TrapezoidalIntegrator& integrator
  );```
*Uso en `main.cpp`*:
```cpp
const TrapezoidalIntegrator integrator(10000);
std::cout << integrator << std::endl; // Imprime el número de intervalos
```

### Constantes (`const`)

El calificador `const` se utiliza ampliamente para garantizar la
seguridad y la corrección del código:
- **Métodos `const`**: Métodos como `operator()` y `getNumIntervals`
se declaran `const` porque no modifican el estado del objeto. Esto
permite llamarlos desde instancias constantes.
- **Parámetros `const`**: Los parámetros pasados por referencia, como
el objeto `std::function`, se declaran `const` para asegurar que no
serán modificados dentro de la función.
- **Variables `const`**: Se utilizan para definir valores que no
deben cambiar después de su inicialización, como el ancho `h` de los
trapecios.

*Fragmento de `TrapezoidalIntegrator.cpp`*:
```cpp
double TrapezoidalIntegrator::operator()(...) const { // Método const
  const double h = (b - a) / num_intervals_; // Variable const
  // ...
}
```

### Funciones Lambda

Proporcionan una forma concisa de definir funciones anónimas en el
lugar donde se necesitan. En `main.cpp`, se utilizan para definir las
funciones matemáticas que se van a integrar, evitando la necesidad de
declarar funciones globales o locales separadas.

*Fragmento de `main.cpp`*:
```cpp
// Se define la función f(x) = (x+1)^2 usando una expresión lambda.
auto poly_func = [](double x) -> double {
  return x * x + 2 * x + 1;
};

// Se pasa la lambda al integrador.
double result = integrator(0.0, 2.0, poly_func);
```

### Sobrecarga de Operadores

Permite que los objetos de una clase se comporten de manera similar a
los tipos de datos fundamentales. En este proyecto, se sobrecarga:
- **`operator()`**: Permite que una instancia de
`TrapezoidalIntegrator` sea "invocable" como si fuera una función.
Esto crea una sintaxis muy intuitiva para realizar la integración.
- **`operator<<`**: Como se mencionó, permite una fácil impresión del
estado del objeto en un `std::ostream`.

*Fragmento de `main.cpp`*:
```cpp
TrapezoidalIntegrator integrator;
// Sintaxis similar a una función gracias a operator()
double result = integrator(0.0, 2.0, my_function); 
```

### Manejo de Excepciones

Para hacer el código más robusto, se utiliza un bloque `try-catch`
para gestionar errores en tiempo de ejecución. El método de
integración valida sus entradas y lanza una excepción
`std::invalid_argument` si, por ejemplo, el límite inferior de
integración es mayor que el superior.

*Fragmento de `TrapezoidalIntegrator.cpp`*:
```cpp
if (a > b) {
  throw std::invalid_argument(
    "El límite inferior 'a' no puede ser mayor que el límite superior 'b'."
  );
}
```
*Fragmento de `main.cpp`*:
```cpp
try {
  integrator(3.0, 1.0, some_func); // Esto lanzará una excepción
} catch (const std::invalid_argument& e) {
  std::cerr << "Excepción capturada: " << e.what() << std::endl;
}
```

## 4. Compilación y Ejecución

Navegamos hasta el directorio raíz (`metodo-trapecio-cpp/`) y
ejecutamos el siguiente comando:

```sh
g++ -std=c++17 -Iinclude/ src/numerical_integration/TrapezoidalIntegrator.cpp main.cpp -o main
```

### Ejecución

Una vez compilado, podemos ejecutar el programa de la siguiente
manera:

```sh
./main
```

