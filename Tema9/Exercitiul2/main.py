from selectproducts import SelectProductsSTM, ChoiceObserver
from takemoney import TakeMoneySTM


class VendingMachineSTM(ChoiceObserver):
    take_money_stm: TakeMoneySTM
    select_products_stm: SelectProductsSTM

    def __init__(self):
        self.take_money_stm = TakeMoneySTM()
        self.select_products_stm = SelectProductsSTM()
        self.select_products_stm.attach(self)

    def run(self):
        quit_program = False
        while not quit_program:
            choice = input('Do you want to use the Vending Machine? [y/n/quit] ')
            self.take_money_stm.wait_state.client_arrived()

            if choice == 'y':
                process_money = True
                while process_money:
                    choice = input('How much do you want to insert? [0.10/0.50/1/5/10/done] ')
                    if choice == '0.10':
                        self.take_money_stm.insert_money_state.insert_10bani()
                    elif choice == '0.50':
                        self.take_money_stm.insert_money_state.insert_50bani()
                    elif choice == '1':
                        self.take_money_stm.insert_money_state.insert_1leu()
                    elif choice == '5':
                        self.take_money_stm.insert_money_state.insert_5lei()
                    elif choice == '10':
                        self.take_money_stm.insert_money_state.insert_10lei()
                    elif choice == 'done':
                        process_money = False
                self.select_products_stm.choose_another_product()
            elif choice == 'n':
                pass
            elif choice == 'quit':
                quit_program = True

    def proceed_to_checkout(self):
        price_in_lei = int(self.select_products_stm.current_state.price * 100)
        if self.take_money_stm.money >= price_in_lei:
            self.take_money_stm.add_money(-price_in_lei)
            product_name = self.select_products_stm.current_state.__class__.__name__
            print(f'You received a {product_name}')
            choice = input('Do you want to select another product? [y/n] ')

            if choice.lower() == 'y':
                self.select_products_stm.choose_another_product()
            elif choice.lower() == 'n':
                rest_amount = self.take_money_stm.money / 100
                print(f'Your change is {rest_amount}')
                self.take_money_stm.update_amount_of_money(0)
                self.take_money_stm.current_state = self.take_money_stm.wait_state
        else:
            print(f'You haven\'t inserted enough money. Available: {self.take_money_stm.money / 100}, required: {price_in_lei}')

    def update(self):
        self.proceed_to_checkout()


if __name__ == '__main__':
    vending_machine = VendingMachineSTM()
    vending_machine.run()
