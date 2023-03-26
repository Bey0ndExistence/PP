import sys
import random
import sqlite3
from datetime import datetime
from PyQt5.QtWidgets import QApplication, QWidget, QLabel, QVBoxLayout, QHBoxLayout, QTextEdit, QPushButton, QFileDialog


class JurnalApp(QWidget):
    def __init__(self):
        super().__init__()

        self.current_entry_id = None
        self.initUI()

    def initUI(self):
        self.setWindowTitle('JurnalApp')

        # Adaugam label-ul pentru afisarea citatului
        self.quoteLabel = QLabel(self.getRandomQuote())
        self.quoteLabel.setWordWrap(True)

        # Adaugam layout-ul vertical pentru a afisa label-ul
        vbox = QVBoxLayout()
        vbox.addWidget(self.quoteLabel)

        # Adaugam text editabil si butoanele Load si Save
        self.textEdit = QTextEdit()
        vbox.addWidget(self.textEdit)

        # Adaugam butoanele Load si Save
        hbox = QHBoxLayout()
        loadButton = QPushButton('Load')
        loadButton.clicked.connect(self.loadText)
        hbox.addWidget(loadButton)
        saveButton = QPushButton('Save')
        saveButton.clicked.connect(self.saveText)
        hbox.addWidget(saveButton)
        vbox.addLayout(hbox)

        self.setLayout(vbox)

    def getRandomQuote(self):
        # Conectam la baza de date si citim un citat la intamplare
        conn = sqlite3.connect('quotes.db')
        cursor = conn.cursor()
        cursor.execute('SELECT quote FROM quotes ORDER BY RANDOM() LIMIT 1')
        quote_row = cursor.fetchone()
        conn.close()
        if quote_row:
            return quote_row[0]
        else:
            return ""

    def loadText(self):
        # Alegem o intrare aleatorie din jurnal
        conn = sqlite3.connect('jurnal.db')
        cursor = conn.cursor()
        cursor.execute('SELECT id, text FROM entries ORDER BY RANDOM() LIMIT 1')
        id, text = cursor.fetchone()
        conn.close()

        # Setam textul si id-ul curent
        self.textEdit.setPlainText(text)
        self.current_entry_id = id

    def saveText(self):
        # Daca avem un id curent, actualizam intrarea existenta
        if self.current_entry_id:
            conn = sqlite3.connect('jurnal.db')
            cursor = conn.cursor()
            cursor.execute('UPDATE entries SET text = ?, timestamp = ? WHERE id = ?', (self.textEdit.toPlainText(), datetime.now(), self.current_entry_id))
            conn.commit()
            conn.close()
        else:
            # Daca nu avem un id curent, inseram o noua intrare
            conn = sqlite3.connect('jurnal.db')
            cursor = conn.cursor()
            cursor.execute('INSERT INTO entries (text, timestamp) VALUES (?, ?)', (self.textEdit.toPlainText(), datetime.now()))
            conn.commit()
            # Setam id-ul curent cu id-ul nou inserat
            self.current_entry_id = cursor.lastrowid
            conn.close()


if __name__ == '__main__':
    app = QApplication(sys.argv)
    jurnal = JurnalApp()
    jurnal.show()
    sys.exit(app.exec_())