/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package a4ud1_alumno;

import CLASESDATOS.Alumno;
import CLASESDATOS.Nombre;
import CLASESDATOS.NotaAlumno;
import CLASESDATOS.NotaModulo;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.Scanner;


public class A4UD1_Alumnos {

    public static void main(String[] args) {
        int opcion = 0;

        while (opcion != 7) {
            menu();
            Scanner sc = new Scanner(System.in);
            opcion = sc.nextInt();

            int numAlumno = 0;

            switch (opcion) {
                case 1:
                    listarAlumnos();
                    break;
                case 2:
                    listarNotas();
                    break;
                case 3:
                    anhadirAlumno();
                    break;
                case 4:
                    System.out.println("Introduce el número de alumno a consultar: ");
                    numAlumno = sc.nextInt();
                    consultarAlumno(numAlumno);
                    break;
                case 5:
                    System.out.println("Introduce el número de alumno a consultar: ");
                    numAlumno = sc.nextInt();
                    sc.nextLine(); // Consume caracter de salto de línea pendiente

                    System.out.println("Introduce el número de teléfono: ");
                    String numTelef = sc.nextLine();
                    alumnoTelefono(numAlumno, numTelef);
                    break;
                case 6:
                    listarAlumnosTXT();
                    break;
                default:
                    break;
            }

        }
    }

    public static void menu() {
        System.out.println("\n");
        System.out.println("Menú de opciones");
        System.out.println("----------------");
        System.out.println("1.-Listar los datos de todos los alumnos");
        System.out.println("2.-Listar las notas (modulo y nota) de todos los alumnos junto al número y nombre completo");
        System.out.println("3.-Añadir un nuevo alumno y sus notas que ha obtenido en los diferentes módulos.");
        System.out.println("4.-A partir de un número de alumno, visualice toda su información");
        System.out.println("5.-Dado un número de alumno y un teléfono permita añadir el teléfono a su lista si no existe o borrarlo si existe");
        System.out.println("6.-Obtener un listado de todos los alumnos en un fichero txt");
        System.out.println("7.-Salir");
    }

    //1.-Listar los datos de todos los alumnos
    public static void listarAlumnos() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try (RandomAccessFile archivoAlumnos = new RandomAccessFile("alumnos.dat", "r")) {
            for (int i = 0; i <= archivoAlumnos.length() / 100; i++) {
                archivoAlumnos.seek(i * 100);

                if (archivoAlumnos.readInt() != 0) {
                    //Volvemos a hacer el seek para volver a la posición inicial.
                    archivoAlumnos.seek(i * 100);
                    System.out.println("Número: " + archivoAlumnos.readInt());
                    System.out.println("Nombre: " + archivoAlumnos.readUTF() + " " + archivoAlumnos.readUTF() + " " + archivoAlumnos.readUTF());
                    System.out.println("Fecha Nacimiento: " + sdf.format(archivoAlumnos.readLong()));
                    int cantNumTelefonos = archivoAlumnos.readInt();
                    System.out.println("Cant. números de teléfono: " + cantNumTelefonos);
                    System.out.println("Teléfonos:");

                    for (int j = 0; j < cantNumTelefonos; j++) {
                        System.out.println(archivoAlumnos.readUTF());
                    }

                    System.out.println("\n");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("El archivo no existe.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    //2.-Listar las notas (modulo y nota) de todos los alumnos junto al número y nombre completo
    public static void listarNotas() {
        try (RandomAccessFile archivoAlumnos = new RandomAccessFile("alumnos.dat", "r");
             ObjectInputStream archivoNotas = new ObjectInputStream(new FileInputStream("NotasAlumnos.dat"))) {

            NotaAlumno notasAlumno;
            for (int i = 0; i < archivoAlumnos.length(); i += 100) {
                archivoAlumnos.seek(i);

                System.out.println("Número: " + archivoAlumnos.readInt());
                System.out.println("Nombre: " + archivoAlumnos.readUTF() + " " + archivoAlumnos.readUTF() + " " + archivoAlumnos.readUTF());

                if ((notasAlumno = (NotaAlumno) archivoNotas.readObject()) != null) {
                    for (NotaModulo notaModulo : notasAlumno.getNotas()) {
                        System.out.println("Módulo: " + notaModulo.getAsignatura() + " - Nota: " + notaModulo.getNota());
                    }
                    System.out.println("\n");
                }
            }

        } catch (EOFException e) {
            // Fin de archivo
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //3.-Añadir un nuevo alumno y sus notas que ha obtenido en los diferentes módulos.
    public static void anhadirAlumno() {

        try (RandomAccessFile archivoAlumnos = new RandomAccessFile("alumnos.dat", "rw");
             FileOutputStream fosNotas = new FileOutputStream("NotasAlumnos.dat", true);
             ObjectOutputStream archivoNotas = (new File("NotasAlumnos.dat").length() == 0)
                     ? new ObjectOutputStream(fosNotas)
                     : new MiObjectOutputStream(fosNotas)) {

            int ultimoNumero = 0;

            for (int i = 0; i <= archivoAlumnos.length() / 100; i++) {
                archivoAlumnos.seek(i * 100);

                if (archivoAlumnos.readInt() != 0) {
                    //Volvemos a hacer el seek para volver a la posición inicial.
                    archivoAlumnos.seek(i * 100);
                    ultimoNumero = archivoAlumnos.readInt();
                }
            }

            Scanner sc = new Scanner(System.in);

            System.out.println("Nombre del alumno:");
            String nombre = sc.nextLine();

            System.out.println("Primer apellido del alumno:");
            String apellido1 = sc.nextLine();

            System.out.println("Segundo apellido del alumno:");
            String apellido2 = sc.nextLine();

            Nombre n = new Nombre(nombre, apellido1, apellido2);

            System.out.println("Fecha de nacimiento del alumno:");
            String fechaTexto = sc.next();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date fechaNacimiento = null;
            try {
                fechaNacimiento = sdf.parse(fechaTexto);
            } catch (ParseException e) {
                System.out.println("La fecha de nacimiento no tiene el formato correcto.");
                return;
            }

            System.out.println("Cantidad de números de teléfono del alumno:");
            int cantNumTelef = sc.nextInt();
            sc.nextLine(); // Consume caracter de salto de línea pendiente

            ArrayList<String> telefonos = new ArrayList<>();

            for (int i = 0; i < cantNumTelef; i++) {
                System.out.println("Introduce el número de teléfono " + (i + 1) + ":");
                telefonos.add(sc.nextLine());
            }

            boolean borrado = false;
            Alumno a = new Alumno(n, fechaNacimiento, telefonos, borrado);

            int nuevoNumero = ultimoNumero + 1;

            archivoAlumnos.seek(ultimoNumero * 100);
            archivoAlumnos.writeInt(nuevoNumero);
            archivoAlumnos.writeUTF(a.getNombre().getNombre());
            archivoAlumnos.writeUTF(a.getNombre().getApellido1());
            archivoAlumnos.writeUTF(a.getNombre().getApellido2());
            archivoAlumnos.writeLong(a.getFechaNac().getTime());
            archivoAlumnos.writeInt(cantNumTelef);

            for (String telefono : telefonos) {
                archivoAlumnos.writeUTF(telefono);
            }

            String modulo = "";

            ArrayList<NotaModulo> notas = new ArrayList<>();

            while (!modulo.equals("*")) {
                System.out.println("Nombre del módulo:");
                modulo = sc.nextLine();

                if (modulo.equals("*")) {
                    break;
                }

                System.out.println("Nota del módulo:");
                double nota = sc.nextDouble();

                notas.add(new NotaModulo(modulo, nota));
                sc.nextLine(); // Consume caracter de salto de línea pendiente
            }

            NotaAlumno notaAlumno = new NotaAlumno(nuevoNumero, notas);
            archivoNotas.writeObject(notaAlumno);

        } catch (FileNotFoundException e) {
            System.out.println("El archivo no existe.");
        } catch (IOException e) {
            System.out.println("Error I/O");
        }

    }

    //4.-A partir de un número de alumno, visualice toda su información
    public static void consultarAlumno(int numAlumno) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try (RandomAccessFile archivoAlumnos = new RandomAccessFile("alumnos.dat", "r");
             ObjectInputStream archivoNotas = new ObjectInputStream(new FileInputStream("NotasAlumnos.dat"))) {

            if (archivoAlumnos.readInt() != 0) {
                //Volvemos a hacer el seek para volver a la posición inicial.
                archivoAlumnos.seek((numAlumno - 1) * 100);
                System.out.println("Número: " + archivoAlumnos.readInt());
                System.out.println("Nombre: " + archivoAlumnos.readUTF() + " " + archivoAlumnos.readUTF() + " " + archivoAlumnos.readUTF());
                System.out.println("Fecha Nacimiento: " + sdf.format(archivoAlumnos.readLong()));
                int cantNumTelefonos = archivoAlumnos.readInt();
                System.out.println("Cant. números de teléfono: " + cantNumTelefonos);
                System.out.println("Teléfonos:");

                for (int j = 0; j < cantNumTelefonos; j++) {
                    System.out.println(archivoAlumnos.readUTF());
                }

                while (true) {
                    try {
                        NotaAlumno na = (NotaAlumno) archivoNotas.readObject();
                        if (Objects.equals(na.getNumero(), numAlumno)) {
                            for (NotaModulo notaModulo : na.getNotas()) {
                                System.out.println("Módulo: " + notaModulo.getAsignatura() + " - Nota: " + notaModulo.getNota());
                            }
                            System.out.println("\n");
                        }
                    } catch (EOFException e) {
                        // Fin del fichero
                        break;
                    }
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("El archivo no existe.");
        } catch (IOException e) {
            System.out.println("El número introducido no es correcto.");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    //5.-Dado un número de alumno y un teléfono permita añadir el teléfono a su lista si no existe o borrarlo si existe
    public static void alumnoTelefono(int numAlumno, String numTelef) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Scanner sc = new Scanner(System.in);
        String confirmacion = "";

        try (RandomAccessFile archivoAlumnos = new RandomAccessFile("alumnos.dat", "rw")) {
            if (archivoAlumnos.readInt() != 0) {
                //Volvemos a hacer el seek para volver a la posición inicial.
                long posicionAlumno = (numAlumno - 1) * 100;
                archivoAlumnos.seek(posicionAlumno);

                int numeroAlumno = archivoAlumnos.readInt();
                String nombre = archivoAlumnos.readUTF();
                String apellido1 = archivoAlumnos.readUTF();
                String apellido2 = archivoAlumnos.readUTF();
                long fechaNacLong = archivoAlumnos.readLong();
                int cantNumTelefonos = archivoAlumnos.readInt();

                System.out.println("Número: " + numeroAlumno);
                System.out.println("Nombre: " + nombre + " " + apellido1 + " " + apellido2);
                System.out.println("Fecha Nacimiento: " + sdf.format(fechaNacLong));
                System.out.println("Cant. números de teléfono: " + cantNumTelefonos);
                System.out.println("Teléfonos:");

                ArrayList<String> telefonos = new ArrayList<>();
                boolean telefonoExiste = false;

                for (int j = 0; j < cantNumTelefonos; j++) {
                    String telefono = archivoAlumnos.readUTF();
                    System.out.println(telefono);
                    telefonos.add(telefono);

                    if (telefono.equals(numTelef)) {
                        telefonoExiste = true;
                    }
                }


                if (telefonoExiste) {
                    System.out.println("El teléfono " + numTelef + " EXISTE, ¿Quieres borrarlo? S para borrar, cualquier letra para cancelar: ");
                    confirmacion = sc.nextLine();

                    if (confirmacion.equalsIgnoreCase("S")) {
                        telefonos.remove(numTelef);
                        cantNumTelefonos--;
                    }
                } else {
                    System.out.println("El teléfono " + numTelef + " NO EXISTE, ¿Quieres añadirlo? S para añadir, cualquier letra para cancelar: ");
                    confirmacion = sc.nextLine();

                    if (confirmacion.equalsIgnoreCase("S")) {
                        telefonos.add(numTelef);
                        cantNumTelefonos++;
                    }
                }

                // Reescribimos toda la información del alumno, es necesario por la forma que está estructurado.
                archivoAlumnos.seek(posicionAlumno);
                archivoAlumnos.writeInt(numAlumno);
                archivoAlumnos.writeUTF(nombre);
                archivoAlumnos.writeUTF(apellido1);
                archivoAlumnos.writeUTF(apellido2);
                archivoAlumnos.writeLong(fechaNacLong);
                archivoAlumnos.writeInt(cantNumTelefonos);

                for (String telefono : telefonos) {
                    archivoAlumnos.writeUTF(telefono);
                }

            }

        } catch (FileNotFoundException e) {
            System.out.println("El archivo no existe.");
        } catch (IOException e) {
            System.out.println("Problema I/O");
        }
    }

    //6.-Obtener un listado de todos los alumnos en un fichero txt
    public static void listarAlumnosTXT() {
        try (RandomAccessFile archivoAlumnos = new RandomAccessFile("alumnos.dat", "r");
             ObjectInputStream archivoNotas = new ObjectInputStream(new FileInputStream("NotasAlumnos.dat"));
             PrintStream printStream = new PrintStream(".\\Datos.txt")) {

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            NotaAlumno notasAlumno;

            int contador = 0;

            printStream.print("----------------------DATOS ALUMNOS ----------------------------------------" + "\n");

            for (int i = 0; i < archivoAlumnos.length(); i += 100) {
                contador++;
                archivoAlumnos.seek(i);

                printStream.print("ALUMNO NÚMERO: " + archivoAlumnos.readInt() + "\n");
                printStream.print("NOMBRE: " + archivoAlumnos.readUTF() + " " + archivoAlumnos.readUTF() + " " + archivoAlumnos.readUTF() + "\n");

                long fechaNacLong = archivoAlumnos.readLong();
                Date fechaNac = new Date(fechaNacLong);
                LocalDate fechaNacimiento = fechaNac.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate fechaActual = LocalDate.now();

                int edad = Period.between(fechaNacimiento, fechaActual).getYears();

                printStream.print("FECHA NACIMIENTO: " + sdf.format(fechaNac) + " EDAD: " + edad + "\n");
                int cantNumTelefonos = archivoAlumnos.readInt();
                printStream.print("TELEFONO (S): ");

                for (int j = 0; j < cantNumTelefonos; j++) {
                    printStream.print(archivoAlumnos.readUTF() + " ");
                }

                printStream.print("\n\n");

                printStream.print("MODULO                             NOTA" + "\n");
                printStream.print("---------------------------------------------------------------------------" + "\n");

                int contadorNotas = 0;
                double sumaNotas = 0;

                if ((notasAlumno = (NotaAlumno) archivoNotas.readObject()) != null) {
                    for (NotaModulo notaModulo : notasAlumno.getNotas()) {
                        printStream.print(notaModulo.getAsignatura() + "                             " + notaModulo.getNota() + "\n");
                        contadorNotas++;
                        sumaNotas += notaModulo.getNota();
                    }
                    printStream.print("\n");
                    printStream.print("NOTA MEDIA " + (sumaNotas / contadorNotas) + "\n");
                }
                printStream.print("---------------------------------------------------------------------------" + "\n");
            }
            printStream.print("TOTAL DE ALUMNOS........................." + contador);

        } catch (EOFException e) {
            // Fin de archivo
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
