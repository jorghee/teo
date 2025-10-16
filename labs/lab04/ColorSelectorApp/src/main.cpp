#include <QApplication>
#include "ColorSelectorWindow.h"

int main(int argc, char *argv[]) {
  QApplication app(argc, argv);
  ColorSelectorWindow window;
  
  window.show();

  // Inicia el bucle de eventos de la aplicación.
  return app.exec();
}
