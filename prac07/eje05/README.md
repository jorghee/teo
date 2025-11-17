# Ejercicio 05: Singleton con Subprocesos (Thread-Safe)

## Descripción del Ejercicio

Este ejercicio es una **modificación del Ejercicio 03** (Conexión a Base de Datos) para hacerlo **seguro en entornos multihilo**.  
Se implementa el **patrón Singleton thread-safe** en C++ utilizando **mutex** y **lock_guard** para garantizar la seguridad en entornos concurrentes con múltiples subprocesos.  
La clase `ConexionBD` representa una conexión a base de datos que debe ser única y accesible de forma segura desde múltiples hilos simultáneamente, evitando condiciones de carrera.

## Objetivos

- Modificar el Singleton del Ejercicio 03 para hacerlo thread-safe.
- Implementar bloqueo con mutex para proteger la creación de la instancia única.
- Aplicar el patrón de **verificación doble (Double-Checked Locking)** para evitar que se creen múltiples instancias en hilos distintos.
- Demostrar la seguridad del Singleton en un entorno multihilo con subprocesos concurrentes.
- Proteger todas las operaciones críticas (conectar, desconectar) con lock_guard.

---

## Implementación del Patrón Singleton Thread-Safe

### 1. Definición de la Clase con Mutex

La clase `ConexionBD` define un mutex estático que controla el acceso concurrente a la instancia y a las operaciones críticas.

```cpp
class ConexionBD {
private:
    static ConexionBD* sgtn;
    static mutex mtx;       
    bool conectada;
    string servidor;
    int puerto;

    ConexionBD() : conectada(false), servidor("193.54.21.1"), puerto(8080) {}
```

**Explicación:**

- `static ConexionBD* sgtn` almacena la única instancia del Singleton.
- `static mutex mtx` es un mecanismo de sincronización que previene condiciones de carrera.
- El constructor es privado para evitar la creación directa de instancias.
- Los atributos `conectada`, `servidor` y `puerto` gestionan el estado de la conexión.

### 2. Método getInstancia() con Double-Checked Locking

El método implementa **verificación doble (Double-Checked Locking)** para evitar que múltiples hilos creen instancias diferentes simultáneamente, optimizando el rendimiento en entornos multihilo.

```cpp
public:
    static ConexionBD* getInstancia() {
        if (sgtn == nullptr) {             
            lock_guard<mutex> lock(mtx);   
            if (sgtn == nullptr) {         
                sgtn = new ConexionBD();
            }
        }
        return sgtn;
    }
```

**Explicación:**

- **Primera verificación** (`if (sgtn == nullptr)`): Evita bloquear el mutex si la instancia ya existe, mejorando el rendimiento.
- **lock_guard**: Bloquea el mutex automáticamente y lo libera al salir del scope.
- **Segunda verificación** (dentro del lock): Garantiza que solo un hilo cree la instancia, incluso si varios hilos pasaron la primera verificación simultáneamente.
- Este patrón se conoce como **Double-Checked Locking** y es esencial para Singletons thread-safe eficientes.

### 3. Métodos Protegidos con Mutex

Todas las operaciones que modifican el estado deben protegerse con mutex.

```cpp
void conectar() {
    lock_guard<mutex> lock(mtx);
    if (!conectada) {
        conectada = true;
        cout << "Conectada a " << servidor << ":" << puerto << "\n";
    } else {
        cout << "Ya hay una conexion activa\n";
    }
}

void desconectar() {
    lock_guard<mutex> lock(mtx);
    if (conectada) {
        conectada = false;
        cout << "Conexion cerrada\n";
    } else {
        cout << "No hay conexion activa\n";
    }
}

void estado() const {
    cout << (conectada ? "Conectado" : "Desconectado") << "\n";
}
```

**Explicación:**

- Cada método crítico utiliza `lock_guard<mutex>` para adquirir el bloqueo al inicio.
- El bloqueo se libera automáticamente cuando el objeto `lock` se destruye al salir del método.
- Esto evita que múltiples hilos modifiquen el estado simultáneamente, previniendo condiciones de carrera.
- El método `estado()` es de solo lectura y no modifica variables compartidas.

### 4. Inicialización de Variables Estáticas

Las variables estáticas deben inicializarse fuera de la clase.

```cpp
ConexionBD* ConexionBD::sgtn = nullptr;
mutex ConexionBD::mtx;
```

**Explicación:**

- La instancia se inicializa en `nullptr` indicando que aún no ha sido creada.
- El mutex se construye por defecto y está listo para usarse.

### 5. Prueba con Múltiples Subprocesos (Threads)

La función `tareaConexion()` simula operaciones realizadas por diferentes subprocesos que intentan acceder simultáneamente a la conexión.

```cpp
void tareaConexion() {
    ConexionBD* bd = ConexionBD::getInstancia();
    bd->conectar();
    bd->estado();
    bd->desconectar();
}

int main() {
    thread t1(tareaConexion);
    thread t2(tareaConexion);
    thread t3(tareaConexion);

    t1.join();
    t2.join();
    t3.join();

    return 0;
}
```

**Explicación:**

- Se crean tres subprocesos (`t1`, `t2`, `t3`) que ejecutan simultáneamente `tareaConexion()`.
- Cada subproceso intenta obtener la instancia, conectarse, consultar el estado y desconectarse.
- El uso de mutex garantiza que estas operaciones no generen conflictos ni condiciones de carrera.
- `join()` espera a que cada subproceso termine antes de finalizar el programa.
- Esto demuestra que aunque múltiples hilos intenten acceder, todos utilizan la misma instancia única.

---

## Ejemplo de Salida Esperada

Al compilar y ejecutar el programa, se obtiene una salida similar a:

```
Conectada a 193.54.21.1:8080
Conectado
Conexion cerrada
Ya hay una conexion activa
Conectado
Conexion cerrada
Ya hay una conexion activa
Conectado
Conexion cerrada
```

**Interpretación:**

- El primer subproceso que llega se conecta exitosamente.
- Los subprocesos siguientes intentan conectarse, pero encuentran que ya hay una conexión activa.
- Gracias al mutex, no hay condiciones de carrera ni comportamiento indefinido.
- Todos los subprocesos operan sobre la misma instancia de `ConexionBD`.
- El orden de ejecución puede variar entre diferentes ejecuciones debido a la naturaleza concurrente.

---

## Diferencias con el Ejercicio 03

| Aspecto | Ejercicio 03 | Ejercicio 05 |
|---------|--------------|--------------|
| **Thread-Safety** | No es thread-safe | Thread-safe con mutex |
| **Multithreading** | No utiliza hilos | Usa múltiples hilos |
| **Double-Checked Locking** | No implementado | Implementado para eficiencia |
| **Protección de métodos** | Sin protección | Todos los métodos críticos protegidos |
| **Contexto** | Aplicaciones mono-hilo | Aplicaciones concurrentes |

---

## Ventajas del Singleton Thread-Safe

1. **Seguridad en concurrencia**: Múltiples subprocesos pueden acceder sin generar condiciones de carrera.
2. **Eficiencia**: Double-Checked Locking minimiza el uso del mutex después de la inicialización, mejorando el rendimiento.
3. **Instancia única garantizada**: Incluso con acceso simultáneo de múltiples hilos, solo se crea una instancia.
4. **Protección de estado**: Las operaciones críticas están protegidas contra modificaciones concurrentes mediante bloqueo.
5. **Escalabilidad**: Apropiado para aplicaciones de alto rendimiento con múltiples subprocesos.
6. **Prevención de errores**: Elimina comportamientos indefinidos causados por accesos concurrentes no protegidos.

---

## Conceptos Clave

### Mutex (Mutual Exclusion)
Mecanismo de sincronización que permite que solo un hilo acceda a una sección crítica a la vez.

### Lock Guard
Wrapper RAII (Resource Acquisition Is Initialization) que adquiere el mutex en su construcción y lo libera automáticamente en su destrucción.

### Double-Checked Locking
Patrón de optimización que reduce la sobrecarga del mutex verificando la condición antes y después de adquirir el bloqueo. Evita que múltiples hilos creen instancias diferentes simultáneamente.

### Condiciones de Carrera
Situaciones donde múltiples hilos acceden y modifican datos compartidos simultáneamente, causando resultados impredecibles.

---

## Conclusión

El patrón Singleton thread-safe es fundamental en aplicaciones modernas que utilizan concurrencia y múltiples subprocesos. La implementación con **bloqueo mediante mutex** y **verificación doble (Double-Checked Locking)** garantiza tanto la seguridad como la eficiencia en entornos multihilo.

Este ejercicio demuestra cómo modificar un Singleton básico (Ejercicio 03) para hacerlo seguro en entornos con subprocesos concurrentes, protegiendo recursos compartidos y evitando que se creen múltiples instancias en hilos distintos. Es una técnica esencial para gestionar conexiones a bases de datos, configuraciones globales, pools de recursos y otros componentes críticos en aplicaciones paralelas donde múltiples hilos requieren acceso simultáneo a un recurso único.
