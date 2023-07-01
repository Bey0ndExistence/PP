import sys
from abc import ABC, abstractmethod
from typing import Optional


class Command(ABC):
    @abstractmethod
    def execute(self, program: str):
        pass


class BashCommand(Command):
    def execute(self, program: str):
        print("Executing Bash command:", program)


class PythonCommand(Command):
    def execute(self, program: str):
        print("Executing Python command:", program)


class KotlinCommand(Command):
    def execute(self, program: str):
        print("Executing Kotlin command:", program)


class JavaCommand(Command):
    def execute(self, program: str):
        print("Executing Java command:", program)





