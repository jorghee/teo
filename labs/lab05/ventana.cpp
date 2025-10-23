#include "ventana.h"

#include <QListWidget>
#include <QLabel>
#include <QVBoxLayout>

Ventana::Ventana(QWidget *parent)
  : QWidget(parent) {

  m_listaPaises = new QListWidget(this);
  m_infoLabel = new QLabel("Haga clic en un país para ver su información", this);
  m_infoLabel->setAlignment(Qt::AlignCenter);

  m_datosPaises["México"] = {"Ciudad de México", "Español"};
  m_datosPaises["Canadá"] = {"Ottawa", "Inglés y Francés"};
  m_datosPaises["Japón"] = {"Tokio", "Japonés"};
  m_datosPaises["Alemania"] = {"Berlín", "Alemán"};
  m_datosPaises["Brasil"] = {"Brasilia", "Portugués"};
  m_datosPaises["Italia"] = {"Roma", "Italiano"};

  for (const QString &pais : m_datosPaises.keys()) {
    m_listaPaises->addItem(pais);
  }

  auto *layout = new QVBoxLayout(this);
  layout->addWidget(m_listaPaises);
  layout->addWidget(m_infoLabel);
  setLayout(layout);

  // Conectar señal y slot
  connect(m_listaPaises, &QListWidget::itemClicked, this, &Ventana::onCountryClicked);

  setWindowTitle("Información de Países");
  resize(400, 300);
}

void Ventana::onCountryClicked(QListWidgetItem *item) {
  QString pais = item->text();
  if (m_datosPaises.contains(pais)) {
    const auto &datos = m_datosPaises.value(pais);
    QString info = QString("País: %1\nCapital: %2\nIdioma: %3")
                      .arg(pais)
                      .arg(datos.first)
                      .arg(datos.second);
    m_infoLabel->setText(info);
  }
}
