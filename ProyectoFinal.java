import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class ProyectoFinal {
    //Se hace la declaración de la variables que se usarán en toda la clase
    public static Scanner readData = new Scanner(System.in);

    public static final byte TIPO_IDENTIFICACION = 0;
    public static final byte DOCUMENTO_IDENTIFICACION = 1;
    public static final byte NOMBRE = 2;
    public static final byte APELLIDO = 3;
    public static final byte CORREO = 4;
    public static final byte DIRECCION_RESIDENCIA = 5;
    public static final byte CIUDAD_RESIDENCIA = 6;
    public static final byte TELEFONO = 7;
    public static final byte CONTRASENA = 8;
    public static final byte CONFIRMAR_CONTRASENA = 9;

    
    //Se crea una lista para almacenar los usuarios registrados
    public static List<String[]> users = new ArrayList<String[]>();

    
    //Se realiza la validación de que en el registro de usuario las contraseñas coincidan
    public static boolean previousPasswordValidation(String contrasena, String confirmarContrasena){
        if (contrasena.equals(confirmarContrasena)){
            return true;
        }
        else{
            return false;
        }
    }

    //Este método permite realizar una pausa, cuano se entrege un mensaje por pantalla
    public static void wait(int time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    //Se solicitan los datos de registro de usaurio
    public static void requestRegistrationData(){
        String tipoIdentificacion, documentoIdentificacion, 
                nombre, apellido, correo, direccionResidencia, 
                ciudadResidencia, telefono, contrasena, 
                confirmarContrasena;

        System.out.println("Ingrese el tipo de identificación");
        tipoIdentificacion = readData.nextLine();
        System.out.println("Ingrese el documento de identificación");
        documentoIdentificacion = readData.nextLine();
        System.out.println("Ingrese el nombre");
        nombre = readData.nextLine();
        System.out.println("Ingrese el apellido");
        apellido = readData.nextLine();
        System.out.println("Ingrese el correo");
        correo = readData.nextLine();
        System.out.println("Ingrese la dirección de residencia");
        direccionResidencia = readData.nextLine();
        System.out.println("Ingrese la ciudad de residencia");
        ciudadResidencia = readData.nextLine();
        System.out.println("Ingrese el teléfono");
        telefono = readData.nextLine();
        System.out.println("Ingrese la contraseña");
        contrasena = readData.nextLine();
        System.out.println("Confirme la contraseña");
        confirmarContrasena = readData.nextLine();

        //Se realiza la validación de que las contraseñas coincidan
        if (!previousPasswordValidation(contrasena, confirmarContrasena)){
            while(!previousPasswordValidation(contrasena, confirmarContrasena)){
                System.out.println("Las contraseñas no coinciden\n\n");
                wait(1000);
                System.out.println("Ingrese la contraseña");
                contrasena = readData.nextLine();
                System.out.println("Confirme la contraseña");
                confirmarContrasena = readData.nextLine();
            }
        }
        System.out.println("Registro exitoso");
            wait(1500);
            userRegistration(tipoIdentificacion, documentoIdentificacion, 
                            nombre, apellido, correo, direccionResidencia, 
                            ciudadResidencia, telefono, contrasena, 
                            confirmarContrasena);
    }


    //En este método se agregan los datos del usuario al array user que luego será almacenado en la lista users
    public static void userRegistration(String tipoIdentificacion, String documentoIdentificacion, 
                                        String nombre, String apellido, 
                                        String correo, String direccionResidencia, 
                                        String ciudadResidencia, 
                                        String telefono, String contrasena, 
                                        String confirmarContrasena){

        String[] user = new String[10];
        user[TIPO_IDENTIFICACION] = tipoIdentificacion;
        user[DOCUMENTO_IDENTIFICACION] = documentoIdentificacion;
        user[NOMBRE] = nombre;
        user[APELLIDO] = apellido;
        user[CORREO] = correo;
        user[DIRECCION_RESIDENCIA] = direccionResidencia;
        user[CIUDAD_RESIDENCIA] = ciudadResidencia;
        user[TELEFONO] = telefono;
        user[CONTRASENA] = contrasena;
        user[CONFIRMAR_CONTRASENA] = confirmarContrasena;

        //Se almacena el usuario en la lista users
        users.add(user);
        
    }

    //Se realiza la validación de que el usuario exista en la lista users y sus credenciales sean correctas
    public static boolean signIn(String correo, String contrasena){
        for (String[] user : users){
            if (user[CORREO].equals(correo) && user[CONTRASENA].equals(contrasena)){
                return true; // Retorna verdadero solo si encuentra una coincidencia.
            }
        }
        return false;
    }

    //Se solicitan los datos de inicio de sesión
    public static void requestSignInData(){
        String correo, contrasena;
        byte attempts = 1;

        //Este ciclo permite que el usuario ingrese a la primera si sus datos son correctos, o que tenga máximo 3 intentos
        while(attempts <= 3){
            System.out.println("Ingrese el correo");
            correo = readData.nextLine();
            System.out.println("Ingrese la contraseña");
            contrasena = readData.nextLine();

            if (signIn(correo, contrasena)){
                System.out.println("Usuario logueado correctamente");
                wait(1000);
                break;
            }
            else{
                if(attempts == 3){
                    break;
                }else{
                    System.out.println("Usuario incorrecto, intente una vez más...");
                    wait(1000);
                    attempts++;
                }
            }
        }
    }

    //Este método entrega el mensaje de salida con una espera de un segundo
    public static void exit(){
        System.out.println("Hasta Pronto!");
        wait(1000);
    }
    

    //Se muestra el menú de inicio de sesión y registro
    public static void showMenuLoginRegistration() {
        boolean showMenu = true;

        while(showMenu){
            System.out.println("\n\nBienvenido a My Hotel...\n"+ "Mas que un lugar para descansar.");
            System.out.println("-----------------------------------------------------------\n\n");
            System.out.println("Ingrese la opción deseada");
            System.out.println("1. Registrarse");
            System.out.println("2. Iniciar Sesión");
            System.out.println("3. Salir\n\n");

            byte option = readData.nextByte();
            readData.nextLine();

            switch(option){
                case 1:
                    requestRegistrationData();
                    break;
                case 2:
                    requestSignInData();
                    break;
                case 3:
                    showMenu = false;
                    exit();
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        }
    }

 
    //Método principal
    public static void main(String[] args) {
        showMenuLoginRegistration();
        readData.close();
    }
    
}
