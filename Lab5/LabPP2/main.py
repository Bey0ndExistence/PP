import sys
import random
from time import time
from PyQt5.QtWidgets import QApplication, QWidget, QLabel, QVBoxLayout, QTextEdit, QPushButton, QHBoxLayout, QFileDialog

class Jurnal(QWidget):
    def __init__(self):
        super().__init__()

        self.initUI()

    def initUI(self):
        self.setWindowTitle('Jurnal')

        # Citim un citat la intamplare din fisierul text
        with open('citate.txt', 'r') as file:
            quotes = file.readlines()
        self.quote = random.choice(quotes)

        # Adaugam label-ul pentru afisarea citatului
        self.quoteLabel = QLabel(self.quote)
        self.quoteLabel.setStyleSheet("background-color: red; font-weight: bold;")



        # Adaugam text editabil si butoanele Load si Save
        self.textEdit = QTextEdit()
        self.textEdit.setStyleSheet("background-color: blue;font-weight: bold;")

        # Adaugam butoanele Load si Save
        buttonBox = QHBoxLayout()
        loadButton = QPushButton('Load')
        loadButton.setStyleSheet("background-color: yellow;font-weight: bold;")
        loadButton.clicked.connect(self.loadText)
        buttonBox.addWidget(loadButton)
        saveButton = QPushButton('Save')
        saveButton.setStyleSheet("background-color: yellow;font-weight: bold;")
        saveButton.clicked.connect(self.saveText)
        buttonBox.addWidget(saveButton)

        # Adaugam totul intr-un layout vertical
        mainBox = QVBoxLayout()
        mainBox.addWidget(self.quoteLabel)
        mainBox.addLayout(buttonBox)
        mainBox.addWidget(self.textEdit)

        self.setLayout(mainBox)
        self.setGeometry(500, 500, 350, 350)
        self.show()

    def loadText(self):
        filename, _ = QFileDialog.getOpenFileName(self, 'Deschide fisier')
        if filename:
            with open(filename, 'r') as file:
                self.textEdit.setText(file.read())



    def saveText(self):
        filename, _ = QFileDialog.getSaveFileName(self, 'Save File', f'jurnal_{time()}.txt')
        if filename:
            with open(filename, 'w') as file:
                file.write(self.textEdit.toPlainText())

if __name__ == '__main__':
    app = QApplication(sys.argv)
    jurnal = Jurnal()
    sys.exit(app.exec_())