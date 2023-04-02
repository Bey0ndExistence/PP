import os
class AudioFile:
    def __init__(self, filename):
        if not filename.endswith(self.ext):
            raise Exception("")
        self.filename = filename


class MP3File(AudioFile):
    ext = "mp3"

    def play(self):
        print("se canta {} un mp3".format(self.filename))


class WavFile(AudioFile):
    ext = "wav"

    def play(self):
        print("se canta {} un wav".format(self.filename))


class OggFile(AudioFile):
    ext = "ogg"

    def play(self):
        print("se canta {} un ogg".format(self.filename))


class FlacFile:
    def __init__(self, filename):
        if not filename.endswith(".flac"):
            raise Exception("Format necunoscut")
        self.filename = filename

    def play(self):
        print("se canta {} un flac".format(self.filename))

def checkPathFormat():
    path = input("Introduceti path-ul")
    if os.path.isfile(path) == 1:
        print("bine ma e fisier")



if __name__ == '__main__':
    mp3 = MP3File
    wav = WavFile
    ogg = OggFile
    flac = FlacFile
    path = input("path fisier: ")
    if os.path.isfile(path):
        file1 = os.path.basename(path)
        try:
            test1 = MP3File(file1)
            test1.play()
        except Exception as e:
            print(f"An error occurred: {e}")

        file2 = os.path.basename(path)
        try:
            test2 = WavFile(file2)
            test2.play()
        except Exception as e:
            print(f"An error occurred: {e}")

        file3 = os.path.basename(path)
        try:
            test3 = OggFile(file3)
            test3.play()
        except Exception as e:
            print(f"An error occurred: {e}", end='')

        file4 = os.path.basename(path)
        try:
            test4 = FlacFile(file4)
            test4.play()
        except Exception as e:
            print(f"An error occurred: {e}", end='')