# Ejercicio 03 - Patrón Singleton en la Conexión a Base de Datos

## Descripción del Ejercicio
Este ejercicio implementa el **patrón de diseño Singleton** en la clase `ConexionBD`, la cual representa una conexión simulada a una base de datos.  
El propósito es asegurar que solo exista una instancia activa de conexión durante la ejecución del programa, evitando duplicidad de objetos que gestionen recursos compartidos.

## Objetivos
- Aplicar el patrón Singleton en C++ para el control de conexiones.  
- Evitar múltiples instancias que alteren la integridad del sistema.  
- Centralizar el manejo de conexión, desconexión y estado mediante una única clase.  
- Comprender cómo se comparte una misma instancia entre distintos punteros.

---

## Implementación del Patrón Singleton

### Definición de la Clase y Constructor Privado

```cpp
class ConexionBD {
private:
    static ConexionBD* sgtn;
    bool conectada;
    string servidor;
    int puerto;

    ConexionBD() : conectada(false), servidor("193.54.21.1"), puerto(8080) {}
```

### Método Estático de Acceso Único
```cpp
public:
    static ConexionBD* getInstancia() {
        if (sgtn == nullptr) {
            sgtn = new ConexionBD();
        }
        return sgtn;
    }
```
Este método actúa como un punto de acceso global a la única instancia.
Cuando se llama por primera vez, crea el objeto; en las siguientes llamadas, devuelve la misma referencia. Así se garantiza que todos los punteros apunten al mismo espacio en memoria, cumpliendo con la premisa del patrón Singleton.


### Métodos de Conexión y Estado
```cpp
void conectar() {
    if (!conectada) {
        conectada = true;
        cout << "Conectada a " << servidor << ":" << puerto << "\n";
    } else {
        cout << "Ya hay una conexion activa\n";
    }
}

void desconectar() {
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

Estos métodos controlan el ciclo de vida de la conexión.
conectar() establece una conexión si no hay una activa; si ya existe, avisa que no puede abrir otra. desconectar() cierra la conexión si estaba activa.
Finalmente, estado() muestra si el sistema se encuentra conectado o desconectado.

### Instancia Única Global y Uso en el main
```cpp
ConexionBD* ConexionBD::sgtn = nullptr;

int main() {

    ConexionBD* bd1 = ConexionBD::getInstancia();
    bd1->estado();
    bd1->conectar();
    
    ConexionBD* bd2 = ConexionBD::getInstancia();
    bd2->conectar();
    bd2->estado();
    
    bd1->desconectar();
    bd2->estado();

    return 0;
}
```

En el main, los punteros bd1 y bd2 solicitan la instancia única mediante getInstancia().
Aunque se crean dos punteros, ambos apuntan al mismo objeto Singleton, lo que se evidencia al ejecutar el programa: las operaciones realizadas por uno afectan el estado del otro, ya que comparten la misma instancia en memoria.

### Ejemplo de Uso y Salida Esperada
``` cpp
Desconectado
Conectada a 193.54.21.1:8080
Ya hay una conexion activa
Conectado
Conexion cerrada
Desconectado
```
La salida confirma que solo existe una conexión real: cuando bd1 se conecta, bd2 detecta que la conexión ya está activa.
Posteriormente, al desconectarse desde bd1, bd2 refleja el mismo estado, demostrando el correcto funcionamiento del patrón Singleton.

---

### Conclusión
El patrón Singleton demuestra su utilidad en escenarios donde se requiere una sola instancia controlada de un recurso compartido, como una conexión a base de datos, una configuración global o un registro de logs.
En este ejercicio, se evidenció que cualquier intento de crear múltiples conexiones termina apuntando a la misma instancia, garantizando un manejo centralizado del estado y evitando inconsistencias.
Este enfoque promueve la eficiencia y la coherencia dentro del sistema, especialmente en contextos donde el acceso concurrente o los recursos limitados son críticos.