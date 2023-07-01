import sys

from handlers import *


if __name__ == '__main__':
    print("Script:")
    script = sys.stdin.read()

    chain = BashHandler(PythonHandler(JavaHandler(KotlinHandler(None))))
    chain.handle(script)
