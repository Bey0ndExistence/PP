class Celula:
    def get_nume(self):
        pass


class FibraMusculara(Celula):
    nume = ""
    masa_musculara = 0

    def __init__(self, nume, masa_musculara):
        self.nume = nume
        self.masa_musculara = masa_musculara

    def get_nume(self):
        return self.nume

    def get_masa_musculara(self):
        return self.masa_musculara


class MuschiGenetic:
    fibre = []
    nume = ""
    masa_musculara = 0
    scop = ""

    def __init__(self, nume, fibre_musculare, scop):
        self.nume = nume
        self.fibre = fibre_musculare
        self.scop = scop
        for fibra in fibre_musculare:
            self.masa_musculara += fibra.get_masa_musculara()

    def get_nume(self):
        return self.nume

    def get_masa_musculara(self):
        return self.masa_musculara

    def get_scop(self):
        return self.scop

class FibraNervoasa(Celula):
    nume = ""
    lungime = 0

    def __init__(self, nume, lungime):
        self.nume = nume
        self.lungime = lungime

    def get_nume(self):
        return self.nume

    def get_lungime(self):
        return self.lungime


class TrunchiNervos:
    nervi = []
    nume = ""
    lungime = 0
    specializare = ""

    def __init__(self, nume, nervi, specializare):
        self.nume = nume
        self.nervi += nervi
        for nerv in nervi:
            self.lungime += nerv.get_lungime()
        self.specializare = specializare

    def get_nume(self):
        return self.nume

    def get_lungime(self):
        return self.lungime

    def get_specializare(self):
        return self.specializare

if __name__ == '__main__':
    # Crearea fibrelor musculare pentru bicepsul mâinii stângi și drepte

    fibre_stanga = [FibraMusculara("Fibra 1", 32),FibraMusculara("Fibra 2", 20)]

    fibre_dreapta = [FibraMusculara("Fibra 1", 12),FibraMusculara("Fibra 2", 11)]



    # Crearea mușchilor biceps pentru mâna stângă și dreaptă
    muschi_stanga = MuschiGenetic("Biceps mână stângă", fibre_stanga,
                                  {"locomotor", "încordare braț stâng"})
    muschi_dreapta = MuschiGenetic("Biceps mână dreaptă", fibre_dreapta,
                                   {"locomotor", "încordare braț drept"})

    # Crearea nervilor pentru brațul stâng și drept

    nervi_stanga= [FibraNervoasa("Nervoselu 1 stanga", 3), FibraNervoasa("Nervoselul 2 dreapta", 4)]

    nervi_dreapta = [FibraNervoasa("Nervoselu 1 stanga", 5), FibraNervoasa("Nervoselul 2 dreapta", 6)]



    # Crearea trunchiurilor nervoase pentru brațul stâng și drept
    trunchi_stanga = TrunchiNervos("Trunchi nervos braț stâng", nervi_stanga, "senzorial")
    trunchi_dreapta = TrunchiNervos("Trunchi nervos braț drept",  nervi_dreapta, "senzorial")

    lungime_totala = 0
    lungime_totala+=trunchi_stanga.get_lungime()
    lungime_totala+=trunchi_dreapta.get_lungime()

    print("lungime totala nervi: ", lungime_totala)
    # Afișarea informațiilor despre mușchii și nervii pentru brațul stâng și drept
    print("Mâna stângă:")
    print("Nume mușchi: ", muschi_stanga.get_nume())
    print("Masa musculară: ", muschi_stanga.get_masa_musculara())
    print("Scopuri: ", muschi_stanga.get_scop())


    print("\nMâna dreaptă:")
    print("Nume mușchi: ", muschi_dreapta.get_nume())
    print("Masa musculară: ", muschi_dreapta.get_masa_musculara())
    print("Scopuri: ", muschi_dreapta.get_scop())


    print("\nInformații despre trunchiurile nervoase:")
    print("Trunchi nervos braț stâng:")
    print("Nervi: ", [nerv.get_nume() for nerv in trunchi_stanga.nervi])
    print("Specializare: ", trunchi_stanga.get_specializare())


    print("\nTrunchi nervos braț drept:")
    print("Nervi: ", [nerv.get_nume() for nerv in trunchi_dreapta.nervi])
    print("Specializare: ", trunchi_dreapta.get_specializare())

    if "locomotor" in muschi_stanga.get_scop():
        print("provine din" ,muschi_stanga.get_nume())

    if "locomotor" in muschi_stanga.get_scop():
        print("provine din" ,muschi_stanga.get_nume())
