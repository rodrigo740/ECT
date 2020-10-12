#Rodrigo Lopes Martins 93264

a = [1, 2, 3, 4, 5]
b = [6, 7, 8, 9, 10]
c = [1, 2, 3, 8, 3, 2, 1]
d = [[1,2,3],[10,11,12],[9],[8]]
e = [1, 1, 3, 5, 4, 1, 1]
t1 = [1, 2, 11, 12]
t2 = [3, 4, 7, 8, 9, 10, 13]

def listLength(l):
    
    if l:
        return 1 + listLength(l[1:])
    else:
        return 0

def listSum(l):
    if l:
        return l[0] + listSum(l[1:])
    else:
        return 0

def checkElem(l, elem):
    if l:
        if elem == l[0]:
            return True
        else:
            return checkElem(l[1:],elem)
    else:
        return False
    
def concatLists(l1, l2):
    if l2:
        l1.append(l2[0])
        return concatLists(l1, l2[1:])
    else:
        return l1
        
def invertList(l):
    if l:
        return [l[-1]] + invertList(l[:-1])
    else:
        return l

def capicua(l):
    if l:
        if l[0] == l[-1]:
            return capicua(l[1:-1])
        else:
            return False
    else:
        return True

def concatListOfLists(l):
    if l:
        return l[0] + concatListOfLists(l[1:])
    else:
        return l

def swapElems(l, x, y):
    if l:
        if l[0] == x:
            l[0] = y
            return [l[0]] + swapElems(l[1:], x, y)
        else:
            return [l[0]] + swapElems(l[1:], x, y)
    else:
        return l

def listsUnion(l1, l2):
    if l2:
        if l1:
            if l2[0] < l1[0]:
                return [l2[0]] + listsUnion(l1, l2[1:])
            else:
                return [l1[0]] + listsUnion(l1[1:], l2)
        else:
            return l2
    else:
        return l1

print("Tamanho da lista: " + str(listLength(a)))
print("Soma da lista: " + str(listSum(a)))

if checkElem(a, 4):
    print("Encontrado!")
else:
    print("Não encontrado!")
    
print("Concatenação: " + str(concatLists(a,b)))
print("Inversão " + str(invertList(a)))

if capicua(c):
    print("É uma capicua!")
else:
    print("Não é uma capicua!")

print("Concatenação de lista de listas: " + str(concatListOfLists(d)))
print("Troca de 1 por 4: " + str(swapElems(e, 1, 4)))
print("União ordenada de listas: " + str(listsUnion(t1,t2)))
