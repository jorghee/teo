#include <iostream>
#include <string>

class ConexionBD {
private:
    static ConexionBD* sgtn;  // singleton
    bool conectada;                 

    ConexionBD() : conectada(false) {}

public:
    static ConexionBD* getInstancia() {
        if (sgtn == nullptr) {
            sgtn = new ConexionBD();
            std::cout << "Se creo la conexion\n";
        }
        return sgtn;
    }

    void conectar() {
        if (!conectada) {
            conectada = true;
            std::cout << "Conectada\n";
        } else {
            std::cout << "Ya hay una conexion activa\n";
        }
    }

    void desconectar() {
        if (conectada) {
            conectada = false;
            std::cout << "Conexión cerrada\n";
        } else {
            std::cout << "No hay ninguna conexion activa \n";
        }
    }

    void estado() const {
        if (conectada)
            std::cout << "- Conectado\n";
        else
            std::cout << "- Desconectado\n";
    }
};

ConexionBD* ConexionBD::sgtn = nullptr;



