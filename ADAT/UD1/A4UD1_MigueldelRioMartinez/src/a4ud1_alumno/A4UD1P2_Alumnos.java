package a4ud1_alumno;

import CLASESDATOS.*;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;

public class A4UD1P2_Alumnos {

    public static void main(String[] args) {
        int opcion = 0;

        while (opcion != 4) {
            menu();
            Scanner sc = new Scanner(System.in);
            opcion = sc.nextInt();

            switch (opcion) {
                case 1:
                    listarAlumnosOrdenadosFechas();
                    break;
                case 2:
                    listarNumAlumnosEdad();
                    break;
                case 3:
                    listarAlumnoAnhoNacimiento();
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
        System.out.println("1.-Listar la información de todos los alumnos ordenados por fechas de mayor a menor");
        System.out.println("2.-Listar el número de alumnos que hay por edad");
        System.out.println("3.-Listar la información de cada alumno por cada año de nacimiento");
        System.out.println("4.-Salir");
    }

    //1.-Listar la información de todos los alumnos ordenados por fechas de mayor a menor
    public static void listarAlumnosOrdenadosFechas() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try (RandomAccessFile archivoAlumnos = new RandomAccessFile("alumnos.dat", "r")) {

            ArrayList<AlumnoP2> alumnos = new ArrayList<>();

            for (int i = 0; i <= archivoAlumnos.length() / 100; i++) {
                archivoAlumnos.seek(i * 100);

                if (archivoAlumnos.readInt() != 0) {
                    //Volvemos a hacer el seek para volver a la posición inicial.
                    archivoAlumnos.seek(i * 100);

                    int numero = archivoAlumnos.readInt();
                    String nombre = archivoAlumnos.readUTF();
                    String apellido1 = archivoAlumnos.readUTF();
                    String apellido2 = archivoAlumnos.readUTF();

                    long fechaNac = archivoAlumnos.readLong();
                    String fechaNacString = sdf.format(fechaNac);
                    Date fechaNacimiento = null;
                    try {
                        fechaNacimiento = sdf.parse(fechaNacString);
                    } catch (ParseException e) {
                        System.out.println("La fecha de nacimiento no tiene el formato correcto.");
                        return;
                    }

                    int cantNumTelefonos = archivoAlumnos.readInt();

                    boolean borrado = false;

                    ArrayList<String> telefono = new ArrayList<>();

                    for (int j = 0; j < cantNumTelefonos; j++) {
                        telefono.add(archivoAlumnos.readUTF());
                    }

                    AlumnoP2 a = new AlumnoP2(numero, new Nombre(nombre, apellido1, apellido2), fechaNacimiento, telefono, borrado);
                    alumnos.add(a);

                }
            }

            Collections.sort(alumnos, (a1, a2) -> a2.getFechaNac().compareTo(a1.getFechaNac()));

            for (AlumnoP2 alumno : alumnos) {
                System.out.println("Número: " + alumno.getNumero());
                System.out.println("Nombre: " + alumno.getNombre().getNombre() + " " + alumno.getNombre().getApellido1() + " " + alumno.getNombre().getApellido2());
                System.out.println("Fecha Nacimiento: " + sdf.format(alumno.getFechaNac()));
                System.out.println("Teléfonos: " + alumno.getTelefono());
                //System.out.println(alumno.getTelefono().stream().count());
                System.out.println("\n");
            }

        } catch (EOFException e) {

        } catch (FileNotFoundException e) {
            System.out.println("El archivo no existe.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    //2.-Listar el número de alumnos que hay por edad
    public static void listarNumAlumnosEdad() {
        ArrayList<Integer> valoresEdad = new ArrayList<>();

        try (RandomAccessFile archivoAlumnos = new RandomAccessFile("alumnos.dat", "r")) {

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

            for (int i = 0; i < archivoAlumnos.length(); i += 100) {
                archivoAlumnos.seek(i);

                int numAlumno = archivoAlumnos.readInt();
                archivoAlumnos.readUTF();
                archivoAlumnos.readUTF();
                archivoAlumnos.readUTF();

                long fechaNacLong = archivoAlumnos.readLong();
                Date fechaNac = new Date(fechaNacLong);
                LocalDate fechaNacimiento = fechaNac.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                LocalDate fechaActual = LocalDate.now();

                int edad = Period.between(fechaNacimiento, fechaActual).getYears();

                int cantNumTelefonos = archivoAlumnos.readInt();

                for (int j = 0; j < cantNumTelefonos; j++) {
                    archivoAlumnos.readUTF();
                }

                valoresEdad.add(edad);
            }

            HashMap<Integer, Integer> mapaEdad = new HashMap<>();

            for(int num : valoresEdad) {
                if (mapaEdad.containsKey(num)) {
                    mapaEdad.put(num, mapaEdad.get(num) + 1);
                }
                else {
                    mapaEdad.put(num, 1);
                }
            }

            mapaEdad.forEach((edad, numVeces) -> {
                System.out.println("Hay " + numVeces + " persona(s) que tiene(n) " + edad + " años");
            });

        } catch (EOFException e) {
            // Fin de archivo
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //3.-Listar la información de cada alumno por cada año de nacimiento
    public static void listarAlumnoAnhoNacimiento() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try (RandomAccessFile archivoAlumnos = new RandomAccessFile("alumnos.dat", "r")) {
            Map<Integer, List<AlumnoP2>> alumnosAnho = new HashMap<>();

            for (int i = 0; i <= archivoAlumnos.length() / 100; i++) {
                archivoAlumnos.seek(i * 100);

                if (archivoAlumnos.readInt() != 0) {
                    //Volvemos a hacer el seek para volver a la posición inicial.
                    archivoAlumnos.seek(i * 100);

                    int numero = archivoAlumnos.readInt();
                    String nombre = archivoAlumnos.readUTF();
                    String apellido1 = archivoAlumnos.readUTF();
                    String apellido2 = archivoAlumnos.readUTF();
                    long fechaNacLong = archivoAlumnos.readLong();
                    Date fechaNacimiento = new Date(fechaNacLong);
                    int cantNumTelefonos = archivoAlumnos.readInt();

                    boolean borrado = false;

                    LocalDate fechaLocal = fechaNacimiento.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
                    int anhoNacimiento = fechaLocal.getYear();

                    ArrayList<String> telefonos = new ArrayList<>();
                    for (int j = 0; j < cantNumTelefonos; j++) {
                        telefonos.add(archivoAlumnos.readUTF());
                    }

                    AlumnoP2 a = new AlumnoP2(numero, new Nombre(nombre, apellido1, apellido2), fechaNacimiento, telefonos, borrado);
                    alumnosAnho.computeIfAbsent(anhoNacimiento, k -> new ArrayList<>()).add(a);
                }
            }

            for (Map.Entry<Integer, List<AlumnoP2>> entry : alumnosAnho.entrySet()) {
                int ano = entry.getKey();
                List<AlumnoP2> alumnos = entry.getValue();

                System.out.println("Año " + ano + ":");
                System.out.println("------------------------------------------------------------");

                for (AlumnoP2 alumno : alumnos) {
                    System.out.println("Alumno número: " + alumno.getNumero());
                    System.out.println("Nombre: " + alumno.getNombre().getNombre() + " " + alumno.getNombre().getApellido1() + " " + alumno.getNombre().getApellido2());
                    System.out.println("Fecha nacimiento: " + sdf.format(alumno.getFechaNac()));
                    System.out.println("Teléfonos: " + String.join(" ", alumno.getTelefono()));
                    System.out.println("------------------------------------------------------------");
                }
                System.out.println("\n");
            }
        } catch (FileNotFoundException e) {
            System.out.println("El archivo no existe.");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
