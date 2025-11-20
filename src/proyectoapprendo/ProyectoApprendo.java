package proyectoapprendo;

<<<<<<< HEAD
=======
import model.GestorUsuarios;

/**
 *
 * @author ISABELLA
 */
>>>>>>> feature/login
public class ProyectoApprendo {

    public static void main(String[] args) {
        GestorUsuarios.inicializarUsuariosBase();
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true); 
            }
        });
    }
    
}
