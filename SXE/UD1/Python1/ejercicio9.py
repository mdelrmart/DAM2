cantidadAInvertir = float(input())
interesAnual = float(input())
numAnhos = int(input())

total = cantidadAInvertir * (interesAnual/100 + 1) ** numAnhos

print(total)