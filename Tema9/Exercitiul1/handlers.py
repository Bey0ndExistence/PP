from commands import *
from abc import ABC, abstractmethod
from typing import Optional


class Handler(ABC):
    command: Command

    def __init__(self, command: Command):
        self.command = command

    @abstractmethod
    def handle(self, file: str):
        pass


class BashHandler(Handler):
    next: Optional[Handler]

    def __init__(self, next: Optional[Handler] = None):
        super().__init__(BashCommand())
        self.next = next

    def handle(self, file: str):
        if self.looks_like_bash(file):
            self.command.execute(file)
        elif self.next is not None:
            self.next.handle(file)

    @staticmethod
    def looks_like_bash(file: str) -> bool:
        if file.startswith("#!/usr/bin/bash") or file.startswith("#!/usr/bin/env bash") or file.startswith("/bin/bash"):
            return True
        return False


class PythonHandler(Handler):
    next: Optional[Handler]

    def __init__(self, next: Optional[Handler] = None):
        super().__init__(PythonCommand())
        self.next = next

    def handle(self, file: str):
        if self.looks_like_python(file):
            self.command.execute(file)
        elif self.next is not None:
            self.next.handle(file)

    @staticmethod
    def looks_like_python(file: str) -> bool:
        return file.startswith("print(")


class KotlinHandler(Handler):
    next: Optional[Handler]

    def __init__(self, next: Optional[Handler] = None):
        super().__init__(KotlinCommand())
        self.next = next

    def handle(self, file: str):
        if self.looks_like_kotlin(file):
            self.command.execute(file)
        elif self.next is not None:
            self.next.handle(file)

    @staticmethod
    def looks_like_kotlin(file: str) -> bool:
        return ";" in file


class JavaHandler(Handler):
    next: Optional[Handler]

    def __init__(self, next: Optional[Handler] = None):
        self.next = next
        super().__init__(JavaCommand())

    def handle(self, file: str):
        if self.looks_like_java(file):
            self.command.execute(file)
        elif self.next is not None:
            self.next.handle(file)

    @staticmethod
    def looks_like_java(file: str) -> bool:
        return ";" in file

