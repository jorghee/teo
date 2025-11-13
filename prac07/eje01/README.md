# Ejercicio 01 - Patrón Singleton en la Configuración del Sistema

## Descripción del Ejercicio

Este ejercicio implementa el **patrón Singleton** en C++ mediante la clase `Configuracion`, encargada de administrar los parámetros globales del sistema, como el **idioma** y la **zona horaria**.  
El patrón garantiza que solo exista **una única instancia** de la clase durante toda la ejecución del programa, evitando duplicaciones y asegurando un control centralizado de la configuración.

## Objetivos

- Aplicar el patrón Singleton en C++.  
- Asegurar la existencia de una única instancia global.  
- Permitir el acceso y modificación del estado desde diferentes punteros.  
- Demostrar la coherencia de los datos compartidos en una sola instancia.

## Implementación

### Clase `Configuracion` (Singleton)

La clase define atributos de configuración (`idioma` y `zonaHoraria`), una **instancia estática única**, y un **constructor privado** para evitar la creación directa de objetos.  
El método `getInstancia()` controla el acceso y crea la instancia solo la primera vez que se invoca.

```cpp
#include <iostream>
#include <string>
using namespace std;

// Clase Singleton
class Configuracion {
private:
    // Atributos de configuración
    string idioma;
    string zonaHoraria;

    // Instancia estática única
    static Configuracion* instancia;

    // Constructor privado para evitar múltiples instancias
    Configuracion() {
        idioma = "Español";
        zonaHoraria = "UTC-5";
    }

public:
    // Método estático que devuelve la instancia única
    static Configuracion* getInstancia() {
        if (instancia == nullptr) {
            instancia = new Configuracion();
        }
        return instancia;
    }

    // Métodos para modificar y mostrar configuraciones
    void setIdioma(const string& nuevoIdioma) {
        idioma = nuevoIdioma;
    }

    void setZonaHoraria(const string& nuevaZona) {
        zonaHoraria = nuevaZona;
    }

    void mostrar_configuracion() {
        cout << "Idioma: " << idioma << endl;
        cout << "Zona horaria: " << zonaHoraria << endl;
    }
};

// Inicialización de la variable estática
Configuracion* Configuracion::instancia = nullptr;

// === FUNCIÓN PRINCIPAL ===
int main() {
    // Obtener la única instancia
    Configuracion* c1 = Configuracion::getInstancia();
    c1->mostrar_configuracion();

    cout << "\n--- Cambiando configuración desde otro puntero ---\n";

    // Intentar crear otra "instancia" (apunta a la misma)
    Configuracion* c2 = Configuracion::getInstancia();
    c2->setIdioma("Inglés");
    c2->setZonaHoraria("UTC+1");

    // Mostrar desde el primer puntero (verás los mismos valores)
    c1->mostrar_configuracion();

    // Comprobamos que son el mismo objeto
    cout << "\nDirección de c1: " << c1 << endl;
    cout << "Dirección de c2: " << c2 << endl;

    return 0;
}
# Ejercicio 01 - Patrón Singleton en la Configuración del Sistema

## Descripción del Ejercicio
Este ejercicio implementa el **patrón Singleton** en C++ mediante la clase `Configuracion`, encargada de administrar los parámetros globales del sistema, como el **idioma** y la **zona horaria**.  
El Singleton garantiza que solo exista **una única instancia** de la clase durante toda la ejecución del programa, asegurando coherencia en el acceso y modificación de la configuración global.

## Objetivos
- Aplicar el patrón Singleton en C++.  
- Garantizar una instancia única para todo el sistema.  
- Permitir el acceso global a la configuración.  
- Demostrar la unicidad de la instancia mediante direcciones de memoria.

---

## Fragmentos del Código y Explicación

### 1. Definición de la Clase Singleton
La clase `Configuracion` define los atributos de idioma y zona horaria, además de una variable estática que guarda la única instancia del objeto.  
El constructor es privado para impedir que otras partes del código creen nuevas instancias.

```cpp
class Configuracion {
private:
    string idioma;
    string zonaHoraria;
    static Configuracion* instancia;

    Configuracion() {
        idioma = "Español";
        zonaHoraria = "UTC-5";
    }
```

Explicación:

Idioma y zonaHoraria almacenan la configuración del sistema. instancia es el puntero estático que contendrá la única instancia de la clase.  El constructor es privado, evitando el uso de new fuera de la clase.

### 2. Método de Acceso Global
El método getInstancia() controla la creación y acceso de la instancia única.

``` cpp
Copy code
public:
    static Configuracion* getInstancia() {
        if (instancia == nullptr) {
            instancia = new Configuracion();
        }
        return instancia;
    }
```
Explicación:

Si la instancia no existe (nullptr), se crea una nueva. Si ya existe, simplemente se devuelve la misma referencia. Este enfoque asegura que siempre se use la misma instancia.

### 3. Métodos de Modificación y Visualización
La clase permite cambiar y visualizar los parámetros de configuración mediante métodos públicos.

```cpp
Copy code
    void setIdioma(const string& nuevoIdioma) {
        idioma = nuevoIdioma;
    }

    void setZonaHoraria(const string& nuevaZona) {
        zonaHoraria = nuevaZona;
    }

    void mostrar_configuracion() {
        cout << "Idioma: " << idioma << endl;
        cout << "Zona horaria: " << zonaHoraria << endl;
    }
};
```

Los métodos setIdioma y setZonaHoraria actualizan los valores globales mostrar_configuracion() imprime los valores actuales en consola. Todos los punteros al Singleton comparten los mismos datos.

### 4. Inicialización de la Variable Estática
Antes de poder usar la clase, la variable estática debe inicializarse en nullptr.

``` cpp
Copy code
Configuracion* Configuracion::instancia = nullptr;
```
Explicación:
Esto marca que inicialmente no existe instancia creada.
La primera llamada a getInstancia() será la encargada de inicializarla.

### 5. Ejemplo de Uso
El siguiente código muestra cómo se accede a la configuración desde diferentes punteros que, en realidad, apuntan al mismo objeto.

```cpp
Copy code
int main() {
    Configuracion* c1 = Configuracion::getInstancia();
    c1->mostrar_configuracion();

    cout << "\n--- Cambiando configuración desde otro puntero ---\n";

    Configuracion* c2 = Configuracion::getInstancia();
    c2->setIdioma("Inglés");
    c2->setZonaHoraria("UTC+1");

    c1->mostrar_configuracion();

    cout << "\nDirección de c1: " << c1 << endl;
    cout << "Dirección de c2: " << c2 << endl;

    return 0;
}
```
### Explicación:

c1 obtiene la primera instancia del Singleton.
c2 intenta crear otra, pero recibe la misma dirección de memoria.
Los cambios hechos en c2 se reflejan en c1, confirmando la unicidad de la instancia.
Al imprimir las direcciones de c1 y c2, se comprueba que son iguales.

Ejemplo de Uso y Salida Esperada
Ejecución en Consola
Al compilar y ejecutar el programa, se obtiene una salida similar a la siguiente:


Idioma: Español
Zona horaria: UTC-5

--- Cambiando configuración desde otro puntero ---
Idioma: Inglés
Zona horaria: UTC+1

Dirección de c1: 0x55bcd0e4aeb0
Dirección de c2: 0x55bcd0e4aeb0
Interpretación:

Los valores iniciales provienen del constructor. Al modificar desde c2, los cambios se reflejan en toda la aplicación. Las direcciones son idénticas, demostrando que c1 y c2 apuntan al mismo objeto Singleton.

Conclusiones
El patrón Singleton centraliza la gestión de información compartida.
Garantiza la existencia de una única instancia accesible globalmente.
Facilita la sincronización de datos en módulos independientes.
Es ideal para manejar configuraciones, conexiones o registros globales.
Sin embargo, debe usarse con precaución para evitar dependencias excesivas en un estado global.
