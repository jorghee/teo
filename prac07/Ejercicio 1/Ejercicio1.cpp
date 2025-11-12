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
