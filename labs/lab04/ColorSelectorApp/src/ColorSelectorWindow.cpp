#include "ColorSelectorWindow.h"

#include <QListWidget>
#include <QLabel>
#include <QVBoxLayout>
#include <QStringList>

ColorSelectorWindow::ColorSelectorWindow(QWidget *parent)
  : QWidget(parent) {
  
  setWindowTitle("Selector de Colores");
  setMinimumSize(300, 250);

  colorListWidget = new QListWidget(this);
  selectedColorLabel = new QLabel("Ningún color seleccionado", this);
  selectedColorLabel->setAlignment(Qt::AlignCenter);

  QStringList colors = {
    "Rojo", "Verde", "Azul", "Amarillo", "Cian", 
    "Magenta", "Naranja", "Púrpura", "Negro", "Blanco"
  };
  colorListWidget->addItems(colors);

  QVBoxLayout *mainLayout = new QVBoxLayout(this);
  mainLayout->addWidget(colorListWidget);
  mainLayout->addWidget(selectedColorLabel);
  
  // Señal: QListWidget::itemClicked(QListWidgetItem*)
  // Slot: ColorSelectorWindow::onColorItemSelected(QListWidgetItem*)
  connect(colorListWidget, &QListWidget::itemClicked, this, &ColorSelectorWindow::onColorItemSelected);
}

void ColorSelectorWindow::onColorItemSelected(QListWidgetItem *item) {
  QString colorName = item->text();
  
  selectedColorLabel->setText(QString("Color seleccionado: %1").arg(colorName));
}
