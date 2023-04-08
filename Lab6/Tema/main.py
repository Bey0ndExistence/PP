import os

class GenericFIle:
    def get_path(self):
        pass
    def get_freq(self):
        pass

class TextASCII(GenericFIle):

    def __init__(self, path, frecvente):
        self.path_absolut = path
        self.frecvente = frecvente

    def get_path(self):
        return self.path_absolut

    def get_freq(self):
        return self.frecvente

class TextUNICODE(GenericFIle):
    def __init__(self, path, frecvente):
        self.path_absolut = path
        self.frecvente = frecvente

    def get_path(self):
        return self.path_absolut

    def get_freq(self):
        return self.frecvente

class Binary(GenericFIle):
    def __init__(self, path, frecvente):
        self.path_absolut = path
        self.frecvente = frecvente

    def get_path(self):
        return self.path_absolut

    def get_freq(self):
        return self.frecvente


class XMLFile(TextASCII):
    def __init__(self, path, frecvente, first_tag):
        super().__init__(path,frecvente)
        self.first_tag= first_tag
    def get_first_tag(self):
        return self.first_tag

class BMP(Binary):
    def __init__(self, path, frecvente,width,height,bpp):
        super().__init__(path, frecvente)
        self.width = width
        self.height = height
        self.bpp = bpp

    def show_info(self):
        print("Path: " + self.path_absolut)
        print( "Frecvente: ",  self.width)
        print(" Width: " ,self.width)
        print(" BPP: " , self.bpp)

def checkFormat(content, file_path):
    pAscii = sum(1 for c in content if c==9 or c==10 or c==13 or (32<=c<=127))/ len(content)
    qAscii = 1-pAscii
    if(pAscii>0.8):
        return TextASCII(file_path, pAscii)

    unicode= sum(1 for c in content if c==0)/ len(content)
    if(unicode >= 0.3):
        return TextUNICODE(file_path, unicode)

    dict = {}
    for c in content:
      if c in dict:
          dict[c] +=1
      else:
          dict[c] = 1

    frecv = []
    for c in content:
        frecv.append(dict[c])

    frecv = [frecv[i]/len(content) for i in frecv]
    ok=1
    for p in frecv:
        if p > 0.3:
            ok =0

    if (ok==1):
        return BMP(file_path,frecv,640,426,32)



if __name__ == '__main__':

    asciiList = []
    unicodeList = []
    binaryList = []
    director = "/home/student/Desktop/PP/Lab6/Tema/director/"

    ROOT_DIR = os.path.dirname(os.path.abspath(director))
    for root, subdirs, files in os.walk(director):
        for file in os.listdir(root):
            file_path = os.path.join(root, file)

            if os.path.isfile(file_path):
                # deschide fișierul spre acces binar
                f = open(file_path, 'rb')
                try:
                    # în content se va depune o listă de octeți
                    content = f.read()
                    res = checkFormat(content,file_path)

                    if(isinstance(res,TextASCII)):
                        asciiList.append(res)
                    elif(isinstance(res,TextUNICODE)):
                        unicodeList.append(res)
                    elif(isinstance(res,BMP)):
                        binaryList.append(res)
                # TODO
                finally:
                    f.close()


    for ascii in asciiList:
        print(ascii.get_path())
    for unicode in unicodeList:
        print(unicode.get_path())
    for binary in binaryList:
        binary.show_info()


