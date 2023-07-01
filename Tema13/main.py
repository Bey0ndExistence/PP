from collections import OrderedDict
from datetime import date
from functional import seq
from more_itertools import map_reduce


### EX1 ###


#A se instala pachetul functional si more_itertools

class Person:
    def __init__(self, first_name: str, last_name: str, date_of_birth: date, email_address: str):
        self.first_name = first_name
        self.last_name = last_name
        self.date_of_birth = date_of_birth
        self.email_address = email_address

    def __repr__(self):
        return f'{self.first_name} {self.last_name} {self.date_of_birth.isoformat()} {self.email_address}'

def yearsago(years, from_date):
    try:
        return from_date.replace(year=from_date.year - years)
    except ValueError:
        # Must be 2/29!
        return from_date.replace(month=2, day=28, year=from_date.year-years)


def num_years(begin, end):
    num_years = int((end - begin).days / 365.2425)
    if begin > yearsago(num_years, end):
        return num_years - 1
    else:
        return num_years


def ex1():
    people = seq(
        Person("John", "Doe", date(1960, 11, 3), "jdoe@example.com"),
        Person("Ellen", "Smith", date(1992, 5, 13), "ellensmith@example.com"),
        Person("Jane", "White", date(1986, 2, 1), "janewhite@example.com"),
        Person("Bill", "Jackson", date(1999, 11, 6), "bjackson@example.com"),
        Person("John", "Smith", date(1975, 7, 14), "johnsmith@example.com"),
        Person("Jack", "Williams", date(2005, 5, 28), "")
    )

    youngest = people.sorted(lambda p: p.date_of_birth, reverse=True).first()
    oldest = people.sorted(lambda p: p.date_of_birth).first()
    print(f'Youngest person is {youngest}')
    print(f'Oldest person is {oldest}')

    underage = people.filter(lambda p: num_years(p.date_of_birth, date.today()) < 18)
    print(f'Underage people {underage}')

    emails = people.map(lambda p: p.email_address)
    print(f'emails {emails}')

    emails = people.map(lambda p: (f'{p.first_name} {p.last_name}', p.email_address)).dict()
    print(f'emails {emails}')

    emails = people.map(lambda p: (p.email_address, p)).dict()
    print(emails)

    grouped = people.group_by(lambda p: p.date_of_birth.month)
    print(f'birthdays each month {grouped}')

    partitioned = people.partition(lambda p: p.date_of_birth.year <= 1980)
    print(f'people born before/after 1980 {partitioned}')

    distinct_fn = people.map(lambda p: p.first_name).distinct()
    print(f'first names: {distinct_fn}')

    average_age = people.map(lambda p: num_years(p.date_of_birth, date.today())).average()
    print(f'average age: {average_age}')

    count = people.filter(lambda p: p.last_name).count(lambda _: True)
    print(f'number of people named Smith: {count}')

    optional = people.filter(lambda p: p.first_name == 'John').last_option()
    if optional is None:
        print('No person named John :(')
    else:
        print(f'He exists: {optional}')

    search_result = people.filter(lambda p: p.first_name == 'Thomas').map(lambda p: p.last_name)

    if search_result is None:
        print('No person named Thomas found :(')
    else:
        print(f'There he is: {search_result}')


### Exercitiul 2 ###
def ex2():
    # Definirea textului de intrare
    text = 'Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.'

    # Implementarea funcției map_reduce
    sorted_words_dict = map_reduce(text.split(), keyfunc=lambda x: x[0], reducefunc=lambda lst: sorted(lst))

    # Sortarea cheilor în ordine alfabetică
    sorted_keys = sorted(sorted_words_dict)

    # Crearea unei liste pentru a stoca cuvintele sortate
    sorted_words = []

    # Iterarea peste cheile sortate
    for key in sorted_keys:
        # Adăugarea cuvintelor sortate corespunzătoare fiecărei chei în lista
        sorted_words.extend(sorted_words_dict[key])

    # Verificarea că lista sorted_words se potrivește cu textul sortat
    assert sorted_words == sorted(text.split())

    # Afișarea cuvintelor sortate
    print(sorted_words)

### Exercitiul 3 ###

def ex3():
    # Definirea listei de intrare
    lst = seq(1, 21, 75, 39, 7, 2, 35, 3, 31, 7, 8)

    # Filtrarea numerelor mai mici decât 5, gruparea numerelor în perechi, înmulțirea perechilor și calcularea sumei
    result = lst.filter(lambda x: x >= 5).grouped(2).map(lambda l: l[0] * l[1]).sum()

    # Afișarea listei originale
    print(f'Processed: {lst}')

    # Afișarea rezultatului final
    print(f'\t=>{result}')


if __name__ == "__main__":

    print("\n     --EXERCITIUL 1--   \n")
    ex1()
    print("\n     --EXERCITIUL 2--   \n")
    ex2()
    print("\n     --EXERCITIUL 3-- \n")
    ex3()