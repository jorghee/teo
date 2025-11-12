#include <iostream>
#include <string>

class ConexionBD {
private:
    static ConexionBD* instancia;  
    bool conectada;                 

    ConexionBD() : conectada(false) {}

public:

    static ConexionBD* getInstancia() {
        
    }

    void conectar() {
    }

    void desconectar() {
    }

    void estado(){}
    
};
