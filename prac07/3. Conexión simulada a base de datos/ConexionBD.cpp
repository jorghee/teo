#include <iostream>
#include <string>
using namespace std;

class ConexionBD {
private:
    static ConexionBD* sgtn;
    bool conectada;
    string servidor;
    int puerto;

    ConexionBD() : conectada(false), servidor("193.54.21.1"), puerto(8080) {}

public:
    static ConexionBD* getInstancia() {
        if (sgtn == nullptr) {
            sgtn = new ConexionBD();
        }
        return sgtn;
    }

    void conectar() {
        if (!conectada) {
            conectada = true;
            std::cout << "Conectada a " << servidor << ":" << puerto << "\n";
        } else {
            std::cout << "Ya hay una conexion activa\n";
        }
    }

    void desconectar() {
        if (conectada) {
            conectada = false;
            std::cout << "Conexion cerrada\n";
        } else {
            std::cout << "No hay conexion activa\n";
        }
    }

    void estado() const {
        std::cout << (conectada ? "Conectado" : "Desconectado") << "\n";
    }

};

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