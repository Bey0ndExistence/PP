import os
import tkinter as tk

class Parser:
    ROOT_DIR = os.path.dirname(os.path.abspath(__file__))

    def __init__(self, gui):
        self.gui = gui
        self.gui.title('Exemplul 1 cu Tkinter')

        self.gui.geometry("650x230")

        self.integer_list_lbl = tk.Label(master=self.gui, text="List of integers:")

        self.add_list_btn = tk.Button(master=self.gui,text="Add list",command=self.add_list)

        self.filter_odd_btn = tk.Button(master=self.gui, text="Filter Odds",command=self.filter_odds)
        self.filter_prime_btn = tk.Button(master=self.gui,text="Filter Primes",command=self.filter_primes)
        self.sum_list_btn = tk.Button(master=self.gui,text="Sum List",command=self.sum_list)

        self.integer_list_text = tk.Text(self.gui, width=50, height=1)
        self.integer_list_text.insert("1.0", str(list(range(1, 16)))[1:-1])

        self.result_text = tk.Text(self.gui, width=50, height=10)

        self.integer_list_lbl.grid(row=0, column=0)
        self.integer_list_text.grid(row=0, column=1)
        self.add_list_btn.grid(row=0, column=2)
        self.filter_odd_btn.grid(row=1, column=2)
        self.filter_prime_btn.grid(row=2, column=2)
        self.sum_list_btn.grid(row=3, column=2)
        self.result_text.grid(row=1, column=1, rowspan=3)

        self.gui.mainloop()

    def add_list(self):
        result = self.integer_list_text.get("1.0", tk.END)
        result = result.strip().replace(' ', '')
        result = [int(item) for item in result.split(',')]
        self.integer_list = result # queue
        self.result_text.delete('1.0', tk.END)
        self.result_text.insert(tk.END, str(result))

    def filter_odds(self):
        odds = [x for x in self.integer_list if x % 2 != 0]
        self.result_text.delete('1.0', tk.END)
        self.result_text.insert(tk.END, str(odds))

    def filter_primes(self):
        primes = []
        for num in self.integer_list:
            if num > 1:
                for i in range(2, num):
                    if num % i == 0:
                        break
                else:
                    primes.append(num)
        self.result_text.delete('1.0', tk.END)
        self.result_text.insert(tk.END, str(primes))

    def sum_list(self):
        total = sum(self.integer_list)
        self.result_text.delete('1.0', tk.END)
        self.result_text.insert(tk.END, f"The sum is {total}")


if __name__ == '__main__':
    root = tk.Tk()
    root.title('Exemplul 1 cu Tkinter')
    app = Parser(root)
    root.mainloop()