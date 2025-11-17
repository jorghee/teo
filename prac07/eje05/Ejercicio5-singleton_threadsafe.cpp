#include <iostream>
#include <string>
#include <mutex>
#include <thread>
using namespace std;

class ConexionBD {
private:
    static ConexionBD* sgtn;
    static mutex mtx;       
    bool conectada;
    string servidor;
    int puerto;

    // Constructor privado
    ConexionBD() : conectada(false), servidor("193.54.21.1"), puerto(8080) {}

public:
    // Método Singleton con verificación doble
    static ConexionBD* getInstancia() {
        if (sgtn == nullptr) {             
            lock_guard<mutex> lock(mtx);   
            if (sgtn == nullptr) {         
                sgtn = new ConexionBD();
            }
        }
        return sgtn;
    }

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
};

// Inicialización de variables estáticas
ConexionBD* ConexionBD::sgtn = nullptr;
mutex ConexionBD::mtx;

// Función para probar desde varios hilos
void tareaConexion() {
    ConexionBD* bd = ConexionBD::getInstancia();
    bd->conectar();
    bd->estado();
    bd->desconectar();
}

int main() {
    // Crear varios hilos para probar el acceso simultáneo
    thread t1(tareaConexion);
    thread t2(tareaConexion);
    thread t3(tareaConexion);

    t1.join();
    t2.join();
    t3.join();

    return 0;
}
