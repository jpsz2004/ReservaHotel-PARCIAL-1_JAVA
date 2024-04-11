import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class LoginInterfaz extends JFrame {

    private JTextField txtCorreo;
    private JPasswordField txtContrasena;

    // Se crea una lista para almacenar los usuarios registrados
    private List<String[]> users = new ArrayList<>();

    // Variables de índice para los datos del usuario
    private static final int CORREO_INDEX = 0;
    private static final int CONTRASENA_INDEX = 1;

    public LoginInterfaz() {
        setTitle("My Hotel");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);

        // Crear paneles
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(3, 1, 10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JPanel correoPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblCorreo = new JLabel("Correo:");
        txtCorreo = new JTextField(20); // Ancho del campo de texto para correo
        correoPanel.add(lblCorreo);
        correoPanel.add(txtCorreo);

        JPanel contrasenaPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblContrasena = new JLabel("Contraseña:");
        txtContrasena = new JPasswordField(20); // Ancho del campo de texto para contraseña
        contrasenaPanel.add(lblContrasena);
        contrasenaPanel.add(txtContrasena);

        JPanel botonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton btnIniciarSesion = new JButton("Iniciar Sesión");
        JButton btnRegistrar = new JButton("Registrar");

        // Agregar acciones a los botones
        btnIniciarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarSesion();
            }
        });

        btnRegistrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarRegistro();
            }
        });

        botonPanel.add(btnIniciarSesion);
        botonPanel.add(btnRegistrar);

        // Agregar paneles al panel principal
        mainPanel.add(correoPanel);
        mainPanel.add(contrasenaPanel);
        mainPanel.add(botonPanel);

        // Agregar panel principal al JFrame
        add(mainPanel);

        pack(); // Ajustar el tamaño del JFrame
        setLocationRelativeTo(null); // Centrar la ventana en la pantalla
        setVisible(true);
    }

    private void iniciarSesion() {
        // Obtener el correo y la contraseña ingresados por el usuario
        String correo = txtCorreo.getText();
        String contrasena = new String(txtContrasena.getPassword());

        // Verificar si el usuario está registrado y las credenciales son correctas
        boolean usuarioEncontrado = false;
        for (String[] user : users) {
            if (user[CORREO_INDEX].equals(correo) && user[CONTRASENA_INDEX].equals(contrasena)) {
                usuarioEncontrado = true;
                break;
            }
        }

        // Mostrar mensaje según el resultado de la verificación
        if (usuarioEncontrado) {
            JOptionPane.showMessageDialog(this, "¡Bienvenido a My Hotel!", "Home", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Usuario incorrecto. Intente una vez mas ...", "Error de Inicio de Sesión", JOptionPane.ERROR_MESSAGE);
            txtContrasena.setText(""); // Limpiar el campo de contraseña
            txtCorreo.setText(""); // Limpiar el campo de correo
        }
    }

    private void mostrarRegistro() {
        // Crear una nueva instancia de la ventana de registro
        RegistroInterfaz registroInterfaz = new RegistroInterfaz(this);
        registroInterfaz.setVisible(true);
    }

    private class RegistroInterfaz extends JFrame {

        private JTextField txtTipoIdentificacion, txtDocumentoIdentificacion, txtNombre, txtApellido,
                txtCorreo, txtDireccionResidencia, txtCiudadResidencia, txtTelefono, txtContrasena,
                txtConfirmarContrasena;

        public RegistroInterfaz(JFrame parent) {
            setTitle("Registro de Usuario");
            setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            setResizable(false);

            // Crear paneles
            JPanel mainPanel = new JPanel();
            mainPanel.setLayout(new GridLayout(13, 1, 10, 10));
            mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

            // Crear campos de texto para el registro
            txtTipoIdentificacion = new JTextField(20);
            txtDocumentoIdentificacion = new JTextField(20);
            txtNombre = new JTextField(20);
            txtApellido = new JTextField(20);
            txtCorreo = new JTextField(20);
            txtDireccionResidencia = new JTextField(20);
            txtCiudadResidencia = new JTextField(20);
            txtTelefono = new JTextField(20);
            txtContrasena = new JPasswordField(20);
            txtConfirmarContrasena = new JPasswordField(20);

            // Agregar campos de texto al panel principal
            mainPanel.add(new JLabel("Tipo de Identificación:"));
            mainPanel.add(txtTipoIdentificacion);
            mainPanel.add(new JLabel("Documento de Identificación:"));
            mainPanel.add(txtDocumentoIdentificacion);
            mainPanel.add(new JLabel("Nombre:"));
            mainPanel.add(txtNombre);
            mainPanel.add(new JLabel("Apellido:"));
            mainPanel.add(txtApellido);
            mainPanel.add(new JLabel("Correo:"));
            mainPanel.add(txtCorreo);
            mainPanel.add(new JLabel("Dirección de Residencia:"));
            mainPanel.add(txtDireccionResidencia);
            mainPanel.add(new JLabel("Ciudad de Residencia:"));
            mainPanel.add(txtCiudadResidencia);
            mainPanel.add(new JLabel("Teléfono:"));
            mainPanel.add(txtTelefono);
            mainPanel.add(new JLabel("Contraseña:"));
            mainPanel.add(txtContrasena);
            mainPanel.add(new JLabel("Confirmar Contraseña:"));
            mainPanel.add(txtConfirmarContrasena);

            // Agregar botón de registro
            JButton btnRegistrarUsuario = new JButton("Registrar Usuario");
            btnRegistrarUsuario.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    registrarUsuario();
                }
            });
            mainPanel.add(btnRegistrarUsuario);

            // Agregar panel principal al JFrame
            add(mainPanel);

            pack(); // Ajustar el tamaño del JFrame
            setLocationRelativeTo(parent); // Centrar la ventana en la pantalla
        }

        private void registrarUsuario() {
            // Obtener los datos ingresados por el usuario
            String tipoIdentificacion = txtTipoIdentificacion.getText();
            String documentoIdentificacion = txtDocumentoIdentificacion.getText();
            String nombre = txtNombre.getText();
            String apellido = txtApellido.getText();
            String correo = txtCorreo.getText();
            String direccionResidencia = txtDireccionResidencia.getText();
            String ciudadResidencia = txtCiudadResidencia.getText();
            String telefono = txtTelefono.getText();
            String contrasena = txtContrasena.getText();
            String confirmarContrasena = txtConfirmarContrasena.getText();

            // Validar que las contraseñas coincidan
            if (!contrasena.equals(confirmarContrasena)) {
                JOptionPane.showMessageDialog(this, "Las contraseñas no coinciden", "Error de Registro", JOptionPane.ERROR_MESSAGE);
                return;
            }

            // Realizar el registro del usuario
            userRegistration(tipoIdentificacion, documentoIdentificacion, nombre, apellido, correo, direccionResidencia,
                    ciudadResidencia, telefono, contrasena, confirmarContrasena);

            // Cerrar la ventana de registro
            dispose();
        }

        // Método para registrar un nuevo usuario
        private void userRegistration(String tipoIdentificacion, String documentoIdentificacion, String nombre,
                                       String apellido, String correo, String direccionResidencia, String ciudadResidencia,
                                       String telefono, String contrasena, String confirmarContrasena) {
            // Agregar los datos del usuario a la lista de usuarios
            String[] user = {correo, contrasena};
            users.add(user);

            // Mostrar mensaje de registro exitoso
            JOptionPane.showMessageDialog(this, "¡Registro exitoso!", "Registro de Usuario", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new LoginInterfaz();
            }
        });
    }
}

/**************************** IDEA PARA VERIFICAR LOS TRES INTENTOS *****************/

// public static boolean signIn(String correo, String contrasena){
//     for (String[] user : users){
//         if (user[CORREO_INDEX].equals(correo) && user[CONTRASENA_INDEX].equals(contrasena)){
//             return true; // Retorna verdadero solo si encuentra una coincidencia.
//         }
//     }
//     return false;
// }

// public void iniciarSesion() {
//     String correo, contrasena;
//     byte intentos = 1;

//     // Máximo tres intentos
//     while (intentos <= 3) {
//         correo = txtCorreo.getText();
//         contrasena = new String(txtContrasena.getPassword());

//         if (signIn(correo, contrasena)) {
//             JOptionPane.showMessageDialog(this, "¡Bienvenido a My Hotel!", "Inicio de Sesión Exitoso", JOptionPane.INFORMATION_MESSAGE);
//             txtCorreo.setText(""); 
//             txtContrasena.setText(""); 
//             return; 
//         } else {
//             if (intentos == 3) {
//                 JOptionPane.showMessageDialog(this, "Ha excedido el número máximo de intentos. Reiniciando...", "Error de Inicio de Sesión", JOptionPane.ERROR_MESSAGE);
//                 txtCorreo.setText(""); 
//                 txtContrasena.setText(""); 
//                 return; // Salir del método si se excede el límite de intentos
//             } else {
//                 JOptionPane.showMessageDialog(this, "Usuario incorrecto. Intente una vez más ...", "Error de Inicio de Sesión", JOptionPane.ERROR_MESSAGE);
//                 txtCorreo.setText("");
//                 txtContrasena.setText(""); 
//                 intentos++; 
//             }
//         }
//     }
// }
