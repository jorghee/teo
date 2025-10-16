#ifndef COLOR_SELECTOR_WINDOW_H
#define COLOR_SELECTOR_WINDOW_H

#include <QWidget>

class QListWidget;
class QLabel;
class QListWidgetItem;

/**
 * @class ColorSelectorWindow
 * @brief Ventana principal de la aplicación que permite al usuario
 *        seleccionar un color de una lista y ver su selección.
 *
 * Hereda de QWidget y utiliza el sistema de señales y slots de Qt.
 */
class ColorSelectorWindow : public QWidget {
  Q_OBJECT

public:
  explicit ColorSelectorWindow(QWidget *parent = nullptr);

private slots:
  /**
   * @brief Slot que se activa cuando un elemento de la lista es seleccionado.
   * @param item Puntero al elemento de la lista que fue clickeado.
   */
  void onColorItemSelected(QListWidgetItem *item);

private:
  // Punteros a los widgets que componen la interfaz.
  QListWidget *colorListWidget;
  QLabel *selectedColorLabel;
};

#endif
