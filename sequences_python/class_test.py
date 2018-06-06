class Test:

    def message(self):
        return "Hello"

    def print_msg(self):
        print(self.message())

    def main():
        print_msg(message)
    
    if __name__ == '__main__':
        main()