contrasenha = "abc123."

print("Introduce una contraseña:")

contrasenhaIntroducida = str(input().lower())

if contrasenhaIntroducida.__eq__(contrasenha):
    print("La contraseña es correcta")
else:
    print("La contraseña no es correcta")