import os

def filter_files(paths):

    for path in paths:
        if os.path.isfile(path) and path.endswith('.txt'):
            yield path

def count_lines(paths):

    for path in paths:
        try:
            with open(path, 'r') as file:
                lines = file.readlines()
                line_count = len(lines)
                yield (path, line_count)
        except Exception as e:
            print(f"Error processing file {path}: {str(e)}")

def display_results(results):

    for path, line_count in results:
        print(f"{path}: {line_count} lines")

# Exemplu de utilizare

# Lista de path-uri
file_paths = [
                '/home/student/Desktop/lab9/P2/fisier1.txt',
                '/home/student/Desktop/lab9/P2/fisier2.txt',
                '/home/student/Desktop/lab9/P2/fisier3.jpg',
                '/home/student/Desktop/lab9/P2/fisier4.txt'
            ]

# Pipeline-ul de generatoare
numbered_lines = count_lines(filter_files(file_paths))

# Afișarea rezultatelor
display_results(numbered_lines)