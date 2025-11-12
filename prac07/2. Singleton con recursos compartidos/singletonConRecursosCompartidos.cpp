#include <iostream>
#include <fstream>
#include <string>
#include <ctime>
using namespace std;

class Logger {
private:
    static Logger* instancia;   // única instancia
    ofstream archivo;         

    Logger() {
        archivo.open("bitacora.log", ios::app);
    }

public:
    // metodo estático para obtener la instancia
    static Logger* getInstance() {
        if (instancia == nullptr)
            instancia = new Logger();
        return instancia;
    }

    // metodo para registrar mensajes con hora
    void log(const string& mensaje) {
        time_t ahora = time(nullptr);
        tm* tiempo = localtime(&ahora);

        char buffer[80];
        strftime(buffer, sizeof(buffer), "%Y-%m-%d %H:%M:%S", tiempo);

        archivo << "[" << buffer << "] " << mensaje << endl;
    }
};
