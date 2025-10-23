#include <QApplication>
#include "ventana.h"

int main(int argc, char *argv[]) {
  QApplication app(argc, argv);

  Ventana ventanaPrincipal;
  ventanaPrincipal.show();
  
  return app.exec();
}
