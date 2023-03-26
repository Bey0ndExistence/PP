import os
import sys
from PyQt5.QtWidgets import QWidget, QApplication, QFileDialog
from PyQt5.uic import loadUi
from PyQt5 import QtCore
import html
import sysv_ipc

def debug_trace(ui=None):
    from pdb import set_trace
    QtCore.pyqtRemoveInputHook()
    set_trace()
    # QtCore.pyqtRestoreInputHook()


class HTMLConverter(QWidget):
    ROOT_DIR = os.path.dirname(os.path.abspath(__file__))

    def __init__(self):
        super(HTMLConverter, self).__init__()
        ui_path = os.path.join(self.ROOT_DIR, 'html_converter1.ui')
        loadUi(ui_path, self)
        self.browse_btn.clicked.connect(self.browse)
        self.file_path = None
        self.convertHTML_btn.clicked.connect(self.handle_convertHTML_btn)
        self.sendToC_btn.clicked.connect(self.sendToC)

    def handle_convertHTML_btn(self):
        self.convertHTML()
        self.HTMLconvert()

    def sendToC(self):
        mq = sysv_ipc.MessageQueue(-1)
        mq.send(self.html_content)

    def browse(self):
        options = QFileDialog.Options()
        options |= QFileDialog.DontUseNativeDialog
        file, _ = QFileDialog.getOpenFileName(self,
                                              caption='Select file',
                                              directory='',
                                              filter="Text Files (*.txt)",
                                              options=options)
        if file:
            self.file_path = file
            self.path_line_edit.setText(file)
            print(file)


    def convertHTML(self):
        self.text = ""
        if self.file_path:
            with open(self.file_path, 'r') as file:
                line = file.readline()
                while line:
                    self.text += line
                    line = file.readline()
                self.plainTextEdit.setPlainText(self.text)
        else:
            self.plainTextEdit.setPlainText('Invalid path')


    def HTMLconvert(self):

            # Imparte continutul in titlu si paragrafe
            lines =self.text.strip().split("\n")
            title = lines[0]
            paragraphs = lines[2:]
            # Converteste continutul in HTML
            html_title = "\n\t\t<title>{}</title>\n".format(html.escape(title.strip()))
            html_paragraphs = ""
            for paragraph in paragraphs:
                html_paragraphs += "\n\t\t<p>{}</p>".format(html.escape(paragraph.strip()))

            # Creeaza continutul HTML
            self.html_content = "<html>\n\t<head>{}\t</head>\n\t<body>{}\n\t</body>\n</html>\n".format(html_title, html_paragraphs)

            # Scrie continutul HTML in fisierul de iesire
            self.plainTextEdit.setPlainText(self.html_content)

if __name__ == '__main__':
    app = QApplication(sys.argv)
    window = HTMLConverter()
    window.show()
    sys.exit(app.exec_())
