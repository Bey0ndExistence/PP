import copy
import json

class JSONFile:
    def __init__(self, title, author, paragraphs):
        self.title = title
        self.author = author
        self.paragraphs = paragraphs

    def generate_json(self):
        data = {
            "title": self.title,
            "author": self.author,
            "paragraphs": self.paragraphs
        }
        return json.dumps(data)

class HTMLFile:
    def __init__(self, title, author, paragraphs):
        self.title = title
        self.author = author
        self.paragraphs = paragraphs

    def generate_html(self):
        html = f"<h1>{self.title}</h1>\n"
        html += f"<p>Author: {self.author}</p>\n"
        for paragraph in self.paragraphs:
            html += f"<p>{paragraph}</p>\n"
        return html


class TextFile:
    def __init__(self, title, author, paragraphs):
        self.title = title
        self.author = author
        self.paragraphs = paragraphs

    def generate_text(self):
        text = f"Title: {self.title}\n"
        text += f"Author: {self.author}\n\n"
        for paragraph in self.paragraphs:
            text += f"{paragraph}\n"
        return text

    def clone(self):
        return copy.copy(self)

class Article:
    def __init__(self, title, author, paragraphs):
        self.title = title
        self.author = author
        self.paragraphs = paragraphs

class Blog:
    def __init__(self, title, author, paragraphs):
        self.title = title
        self.author = author
        self.paragraphs = paragraphs


nr_paragrafe = int(input("Nr Paragrafe: "))
title = input("Titlul: ")
author = input("Autorul: ")


paragraphs = []
for i in range(nr_paragrafe):
    paragraph = input(f"Paragraful {i+1}: ")
    paragraphs.append(paragraph)


tip_file = input("HTML sau JSON sau Text ?:  ")

if  tip_file== "HTML":
    file = HTMLFile(title, author, paragraphs)
    generated_file = file.generate_html()
elif tip_file == "JSON":
    file = JSONFile(title, author, paragraphs)
    generated_file = file.generate_json()
elif tip_file == "Text":
    file = TextFile(title, author, paragraphs)
    generated_file = file.generate_text()

print(generated_file)