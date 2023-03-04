import re

def letters(input):
    return ''.join([c for c in input if c.isalpha() or c==" "])

def main():
    file = open("citirefisier.txt", "r") #deschidere fisier

    string=file.read()  #conversie textIO -> string

    new_str=string;


    new_str = re.sub('\.', '', new_str) #stergere .
    new_str = re.sub(',', '', new_str) #stergere ,
    new_str = re.sub('!', '', new_str) #stergere !
    new_str = re.sub('\?', '', new_str) #stergere ?

    print("Select:\n" "1-Delete multiple spaces\n" "2-LOWER CASE\n""3-UPPER CASE\n""4-Filter Words by number of letters\n""5-Filter by numbers\n")

    r=0
    while(r!=2):
        x=int(input("Introducere optiune:"))
        r=r+1
        if(x==1):
            new_str = re.sub(' +', ' ', new_str)  # stergere spatii multiple
        elif(x==2):
            new_str = new_str.lower()
        elif(x==3):
            new_str = new_str.upper()
        elif(x==4):
            n=int(input("Cate litere sa aiba cuvintele?"))
            temp=new_str.split()
            new_str=list(filter(lambda ele: len(ele) != n,temp))
            new_str=' '.join(new_str)
        elif(x==5):
            new_str=letters(new_str)

    print(string)
    print(new_str)
main()