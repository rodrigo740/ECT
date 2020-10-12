#Rodrigo Lopes Martins 93264

a = [("a1","b1"),("a2","b2"),("a3","b3"),("a4","b4")]

def separar(l):
    return tuple(zip(*l))

print("Separaração de " + str(a) + ": " + str(separar(a)))
