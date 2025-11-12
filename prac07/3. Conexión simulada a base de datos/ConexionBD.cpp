#include <iostream>
#include <string>

class ConexionBD {
private:
    static ConexionBD* sgtn;  
    bool conectado;                 

    ConexionBD() : conectado(false) {}

public:

    static ConexionBD* getInstancia() {
        
    }

    void conectar() {
    }

    void desconectar() {
    }

    void estado(){}
    
};
