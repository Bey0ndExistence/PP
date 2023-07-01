from abc import ABC, abstractmethod
from interfaces import State, Observable


class ChoiceObserver(ABC):
    @abstractmethod
    def update(self):
        pass


class SelectProductsSTM(Observable):
    current_state: State

    def __init__(self):
        super().__init__()

        self.select_products_state = SelectProduct()
        self.select_products_state.state_machine = self



        self.Whiskey_state = Whiskey()
        self.Whiskey_state.state_machine = self

        self.coca_cola_state = CocaCola()
        self.coca_cola_state.state_machine = self

        self.Baton_state = Baton()
        self.Baton_state.state_machine = self

        self.current_state = self.select_products_state

    def choose_another_product(self):
        self.current_state = self.select_products_state
        self.current_state.choose()
        self.notify_all()


class SelectProduct(State):
    state_machine: SelectProductsSTM
    price: float = 0.0

    def choose(self):
        print('Alegeti o optiune: ')
        print(f'\t1) Coca-Cola ({self.state_machine.coca_cola_state.price} RON)')
        print(f'\t2) Whiskey ({self.state_machine.Whiskey_state.price}) RON')
        print(f'\t3) Baton proteic 30g ({self.state_machine.Baton_state.price}) RON')

        choice = int(input('Alegerea dvs.: '))

        if choice == 1:
            self.state_machine.current_state = self.state_machine.coca_cola_state
        elif choice == 2:
            self.state_machine.current_state = self.state_machine.Whiskey_state

        elif choice == 3:
            self.state_machine.current_state = self.state_machine.Baton_state


class CocaCola(State):
    state_machine: SelectProductsSTM
    price: float = 4


class Whiskey(State):
    state_machine: SelectProductsSTM
    price: float = 50.32


class Baton(State):
    state_machine: SelectProductsSTM
    price: float = 6