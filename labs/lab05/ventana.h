#pragma once

#include <QWidget>
#include <QMap>
#include <QPair>

class QListWidget;
class QLabel;
class QListWidgetItem;

class Ventana : public QWidget {
  Q_OBJECT

public:
  explicit Ventana(QWidget *parent = nullptr);
  ~Ventana() = default;

private slots:
  void onCountryClicked(QListWidgetItem *item);

private:
  QListWidget *m_listaPaises;
  QLabel *m_infoLabel;
  QMap<QString, QPair<QString, QString>> m_datosPaises;
};
