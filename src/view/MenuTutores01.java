package view;

import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import model.Usuario;
import javax.swing.JOptionPane;
import model.Curso;
import model.Sede;

/**
 *
 * @author LENOVO
 */
public class MenuTutores01 extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(MenuTutores01.class.getName());
    
    DefaultTableModel modelo;
    ArrayList<Usuario> listaUsuario = new ArrayList<Usuario>();
    
    private DefaultTableModel modeloCursos;
    private ArrayList<Curso> listaCursos = new ArrayList<>();
    
    DefaultTableModel modeloSedes;
    ArrayList<Sede> listaSedes = new ArrayList<>();
    
    public MenuTutores01() {
        initComponents();
        modelo = new DefaultTableModel();
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        modelo.addColumn("Telefono");
        modelo.addColumn("ID");
        modelo.addColumn("Correo");
        modelo.addColumn("Curso");
        this.tabla.setModel(modelo);
        
        
        cargarDatosPredeterminados();
        
        refrescarTabla();
    
    tabla.addMouseListener(new java.awt.event.MouseAdapter() {
        public void mouseClicked(java.awt.event.MouseEvent evt) {
            tablaMouseClicked(evt);
        }
    });
    
       modeloCursos = new DefaultTableModel();
       modeloCursos.addColumn("Código");
       modeloCursos.addColumn("Nombre");
       modeloCursos.addColumn("Tutor");
       modeloCursos.addColumn("Horario");
       modeloCursos.addColumn("Sede");
       modeloCursos.addColumn("Cupos Max");
       modeloCursos.addColumn("Cupos Disp");

       if (tablaCursos != null) {
           this.tablaCursos.setModel(modeloCursos);
       }

        cargarCursosPredeterminados();
        refrescarTablaCursos();
        
        
        modeloSedes = new DefaultTableModel();
        modeloSedes.addColumn("Código");
        modeloSedes.addColumn("Nombre");
        modeloSedes.addColumn("Dirección");
        modeloSedes.addColumn("Teléfono");
        modeloSedes.addColumn("Ciudad");
        modeloSedes.addColumn("Jefe Sede");
        modeloSedes.addColumn("Capacidad");
        modeloSedes.addColumn("Cursos Activos");

        if (tablaSedes != null) {
            this.tablaSedes.setModel(modeloSedes);
        }

        cargarSedesPredeterminadas();

        actualizarComboBoxSedesCursos();

        actualizarContadorCursosSedes();

        refrescarTablaSedes();
    }
    
    
    private void actualizarContadorCursosSedes() {
    // Reiniciar contadores
    for (Sede sede : listaSedes) {
        sede.setCursosActivos(0);
    }
    
    // Contar cursos por sede
    for (Curso curso : listaCursos) {
        for (Sede sede : listaSedes) {
            if (sede.getNombre().equals(curso.getSede())) {
                sede.agregarCurso();
                break;
            }
        }
    }
}
    
    private void cargarSedesPredeterminadas() {
    
    listaSedes.clear();
   
    listaSedes.add(new Sede("SEDE001", "Cartagena - Sede Centro Histórico", "Calle de la Soledad #10-50", "6056421234", "Cartagena", "Lic. Marta Rodríguez", 25));
    listaSedes.add(new Sede("SEDE002", "Cartagena - Sede Bocagrande", "Avenida San Martín #5-100", "6056425678", "Cartagena", "Mg. Carlos Fuentes", 30));
    listaSedes.add(new Sede("SEDE003", "Cartagena - Sede Manga", "Carrera 2 #15-45", "6056429012", "Cartagena", "Ing. Laura Mendoza", 20));
    listaSedes.add(new Sede("SEDE004", "Turbaco - Sede Principal", "Carrera 8 #15-25", "6056890123", "Turbaco", "Esp. Roberto Silva", 18));
    listaSedes.add(new Sede("SEDE005", "Arjona - Sede Norte", "Calle Principal #20-30", "6056894567", "Arjona", "Dra. Patricia López", 15));
    listaSedes.add(new Sede("SEDE006", "Magangué - Sede Sur", "Avenida del Río #12-45", "6056856789", "Magangué", "Lic. Andrés Navarro", 22));
    listaSedes.add(new Sede("SEDE007", "Carmen de Bolívar - Sede Oriental", "Carrera 5 #8-15", "6056851234", "Carmen de Bolívar", "Mg. Sandra Castro", 16));
    listaSedes.add(new Sede("SEDE008", "San Juan Nepomuceno - Sede Montaña", "Calle Real #25-40", "6056855678", "San Juan Nepomuceno", "Ing. Jorge Herrera", 12));
    
    System.out.println("" + listaSedes.size() + " sedes cargadas en el ArrayList");
}
    
    private void cargarDatosPredeterminados() {
    listaUsuario.add(new Usuario(101234567L, "Ana", "García", "Taller de Programación Básica en Python", "ana.garcia@gmail.com", 3001234567L));
    listaUsuario.add(new Usuario(102234568L, "Carlos", "Rodríguez", "Curso de Fotografía Digital", "carlos.rodriguez@gmail.com", 3102345678L));
    listaUsuario.add(new Usuario(103234569L, "María", "López", "Taller de Emprendimiento y Modelos de Negocio", "maria.lopez@gmail.com", 3203456789L));
    listaUsuario.add(new Usuario(104234570L, "Juan", "Martínez", "Curso de Inglés Conversacional", "juan.martinez@gmail.com", 3154567890L));
}
    
    public void refrescarTablaSedes() {
        if (modeloSedes == null || tablaSedes == null) return;

        // Limpiar tabla
        while (modeloSedes.getRowCount() > 0) {
            modeloSedes.removeRow(0);
        }

        // Llenar con datos actualizados
        for (Sede sede : listaSedes) {
            Object[] fila = new Object[8];
            fila[0] = sede.getCodigo();
            fila[1] = sede.getNombre();
            fila[2] = sede.getDireccion();
            fila[3] = sede.getTelefono();
            fila[4] = sede.getCiudad();
            fila[5] = sede.getJefeSede();
            fila[6] = sede.getCapacidadMaxima();
            fila[7] = sede.getCursosActivos();

            modeloSedes.addRow(fila);
        }

        tablaSedes.setModel(modeloSedes);
    }

    // MÉTODO PARA LIMPIAR CAMPOS SEDES
    private void limpiarCamposSedes() {
        Tx_codigoSede.setText("");
        Tx_nombreSede.setText("");
        Tx_direccionSede.setText("");
        Tx_telefonoSede.setText("");
        Cb_ciudadSede.setSelectedIndex(0);
        Cb_jefeSede.setSelectedIndex(0);
        Sp_capacidadSede.setValue(20);
        tablaSedes.clearSelection();
    }

    // MÉTODO PARA ACTUALIZAR COMBOBOX DE SEDES EN CURSOS
    private void actualizarComboBoxSedesCursos() {
        if (Cb_sedeCurso == null) return;

        Cb_sedeCurso.removeAllItems();
        Cb_sedeCurso.addItem("Seleccionar Sede");

        for (Sede sede : listaSedes) {
            Cb_sedeCurso.addItem(sede.getNombre());
        }
    }

    // MÉTODO PARA ACTUALIZAR NOMBRE DE SEDE EN CURSOS
    private void actualizarNombreSedeEnCursos(String nombreViejo, String nombreNuevo) {
        int cursosActualizados = 0;

        for (Curso curso : listaCursos) {
            if (curso.getSede().equals(nombreViejo)) {
                curso.setSede(nombreNuevo);
                cursosActualizados++;
            }
        }

        if (cursosActualizados > 0) {
            refrescarTablaCursos();
            System.out.println("✅ Actualizados " + cursosActualizados + " cursos a la nueva sede");
        }
    }

    // LISTENER PARA TABLA SEDES
    private void tablaSedesMouseClicked(java.awt.event.MouseEvent evt) {                                      
        int fila = tablaSedes.getSelectedRow();
        if (fila >= 0 && fila < listaSedes.size()) {
            Sede sede = listaSedes.get(fila);

            // Llenar campos con los datos de la sede seleccionada
            Tx_codigoSede.setText(sede.getCodigo());
            Tx_nombreSede.setText(sede.getNombre());
            Tx_direccionSede.setText(sede.getDireccion());
            Tx_telefonoSede.setText(sede.getTelefono());

            // Seleccionar en ComboBoxes
            seleccionarEnComboBox(Cb_ciudadSede, sede.getCiudad());
            seleccionarEnComboBox(Cb_jefeSede, sede.getJefeSede());

            // Establecer valor en Spinner
            Sp_capacidadSede.setValue(sede.getCapacidadMaxima());

            System.out.println("✅ Sede seleccionada: " + sede.getNombre());
        }
    }

    private int contarEstudiantesEnCurso(String nombreCurso) {
        int contador = 0;
        for (Usuario estudiante : listaUsuario) {
            if (estudiante.getCurso().equals(nombreCurso)) {
                contador++;
            }
        }
        return contador;
        }

    private boolean validarCorreo(String correo) {
            String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
            return correo.matches(regex);
        }

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {                                
        int fila = tabla.getSelectedRow();
        if (fila >= 0) {
            Usuario estudiante = listaUsuario.get(fila);
            Tx_nombre.setText(estudiante.getNombre());
            Tx_apellido.setText(estudiante.getApellido());
            Tx_telefono.setText(String.valueOf(estudiante.getTelefono()));
            Tx_id.setText(String.valueOf(estudiante.getId()));
            Tx_email.setText(estudiante.getCorreo());

            // Seleccionar el curso en el ComboBox
            for (int i = 0; i < Cb_curso.getItemCount(); i++) {
                if (Cb_curso.getItemAt(i).equals(estudiante.getCurso())) {
                    Cb_curso.setSelectedIndex(i);
                    break;
                }
            }
        }

        }
   
    public void refrescarTabla(){
        while(modelo.getRowCount()>0){
            modelo.removeRow(0);
        }
        
        for (Usuario estudiante : listaUsuario) {
            Object x []= new Object[6];
            x [0] = estudiante.getNombre();
            x [1] = estudiante.getApellido();
            x [2] = estudiante.getTelefono();
            x [3] = estudiante.getId();
            x [4] = estudiante.getCorreo();
            x [5] = estudiante.getCurso();
            
            modelo.addRow(x);
            
        }
        tabla.setModel(modelo);
        
    }
    
    public void refrescarTablaCursos() {
    // Limpiar tabla
    while (modeloCursos.getRowCount() > 0) {
        modeloCursos.removeRow(0);
    }
    
    // Llenar con datos actualizados
    for (Curso curso : listaCursos) {
        Object[] fila = new Object[7];
        fila[0] = curso.getCodigo();
        fila[1] = curso.getNombre();
        fila[2] = curso.getTutor();
        fila[3] = curso.getHorario();
        fila[4] = curso.getSede();
        fila[5] = curso.getCuposMaximos();
        fila[6] = curso.getCuposDisponibles();
        
        modeloCursos.addRow(fila);
    }
    
    
    tablaCursos.setModel(modeloCursos);
}
    
    private void cargarCursosPredeterminados() {
    listaCursos.add(new Curso("PROG01", "Taller de Programación Básica en Python", 
        "Dr. Carlos Martínez Herrera", "Lunes y Miércoles 14:00-16:00", 
        "Cartagena - Sede Centro Histórico", 25));
    
    listaCursos.add(new Curso("FOTO01", "Curso de Fotografía Digital", 
        "Lic. Ana Rodríguez Pérez", "Martes y Jueves 16:00-18:00", 
        "Cartagena - Sede Bocagrande", 20));
}
    
    private void limpiarCamposCursos() {
    Tx_codigoCurso.setText("");
    Tx_nombreCurso.setText("");
    Cb_tutorCurso.setSelectedIndex(0);
    Cb_horarioCurso.setSelectedIndex(0);
    Cb_sedeCurso.setSelectedIndex(0);
    Sp_cuposCurso.setValue(20); // Valor por defecto
    
    // Deseleccionar cualquier fila en la tabla
    tablaCursos.clearSelection();
    }
    
    
    private void tablaCursosMouseClicked(java.awt.event.MouseEvent evt) {                                     
        int fila = tablaCursos.getSelectedRow();
        if (fila >= 0 && fila < listaCursos.size()) {
            Curso curso = listaCursos.get(fila);

            // Llenar campos con los datos del curso seleccionado
            Tx_codigoCurso.setText(curso.getCodigo());
            Tx_nombreCurso.setText(curso.getNombre());

            // Seleccionar en ComboBoxes
            seleccionarEnComboBox(Cb_tutorCurso, curso.getTutor());
            seleccionarEnComboBox(Cb_horarioCurso, curso.getHorario());
            seleccionarEnComboBox(Cb_sedeCurso, curso.getSede());

            // Establecer valor en Spinner
            Sp_cuposCurso.setValue(curso.getCuposMaximos());

            System.out.println("✅ Curso seleccionado: " + curso.getNombre());
        }
    }
    
    private void seleccionarEnComboBox(JComboBox<String> comboBox, String valor) {
    for (int i = 0; i < comboBox.getItemCount(); i++) {
        if (comboBox.getItemAt(i).equals(valor)) {
            comboBox.setSelectedIndex(i);
            return;
        }
    }
    // Si no encuentra el valor, selecciona el primero
    comboBox.setSelectedIndex(0);
}
    
    private void actualizarNombreCursoEnEstudiantes(String nombreViejo, String nombreNuevo) {
        int estudiantesActualizados = 0;

        for (Usuario estudiante : listaUsuario) {
            if (estudiante.getCurso().equals(nombreViejo)) {
                estudiante.setCurso(nombreNuevo);
                estudiantesActualizados++;
            }
        }

        if (estudiantesActualizados > 0) {
            refrescarTabla(); // Actualizar tabla de estudiantes
            System.out.println("✅ Actualizados " + estudiantesActualizados + " estudiantes al nuevo nombre del curso");
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel8 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        Tx_nombre = new javax.swing.JTextField();
        Tx_apellido = new javax.swing.JTextField();
        Tx_telefono = new javax.swing.JTextField();
        Tx_id = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        Bt_agregar = new javax.swing.JButton();
        Bt_eliminar = new javax.swing.JButton();
        Bt_modificar = new javax.swing.JButton();
        Bt_buscar = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        Cb_curso = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        Tx_email = new javax.swing.JTextField();
        jPanel9 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        Tx_codigoCurso = new javax.swing.JTextField();
        Tx_nombreCurso = new javax.swing.JTextField();
        Cb_tutorCurso = new javax.swing.JComboBox<>();
        Cb_horarioCurso = new javax.swing.JComboBox<>();
        Cb_sedeCurso = new javax.swing.JComboBox<>();
        Jb_agregarCurso = new javax.swing.JButton();
        Jb_eliminarCurso = new javax.swing.JButton();
        Jb_modificarCurso = new javax.swing.JButton();
        Jb_buscarCurso = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaCursos = new javax.swing.JTable();
        Sp_cuposCurso = new javax.swing.JSpinner();
        jPanel10 = new javax.swing.JPanel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        Tx_codigoSede = new javax.swing.JTextField();
        Tx_nombreSede = new javax.swing.JTextField();
        Tx_direccionSede = new javax.swing.JTextField();
        Tx_telefonoSede = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        Cb_jefeSede = new javax.swing.JComboBox<>();
        Cb_ciudadSede = new javax.swing.JComboBox<>();
        jLabel25 = new javax.swing.JLabel();
        Sp_capacidadSede = new javax.swing.JSpinner();
        Bt_agregarSede = new javax.swing.JToggleButton();
        Bt_eliminarSede = new javax.swing.JToggleButton();
        Bt_modifcarSede = new javax.swing.JToggleButton();
        Bt_buscarSede = new javax.swing.JToggleButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tablaSedes = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 153, 153));

        jLabel6.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        jLabel6.setText("MENU TUTORES");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(334, Short.MAX_VALUE)
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(244, 244, 244)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 114, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabel6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1040, 120));

        jPanel3.setBackground(new java.awt.Color(0, 204, 204));

        jPanel4.setBackground(new java.awt.Color(0, 204, 204));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel4MouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel2.setText("ESTUDIANTES");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(jLabel2)
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addGap(24, 24, 24))
        );

        jPanel5.setBackground(new java.awt.Color(0, 204, 204));
        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel5MouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel3.setText("CURSOS");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(27, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addGap(23, 23, 23))
        );

        jPanel6.setBackground(new java.awt.Color(0, 204, 204));
        jPanel6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel6MouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 3, 18)); // NOI18N
        jLabel4.setText("SEDES");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(56, 56, 56))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap(26, Short.MAX_VALUE)
                .addComponent(jLabel4)
                .addGap(21, 21, 21))
        );

        jPanel7.setBackground(new java.awt.Color(0, 204, 204));
        jPanel7.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel5.setFont(new java.awt.Font("Segoe UI", 3, 24)); // NOI18N
        jLabel5.setText("SALIR");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel7Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(jLabel5)
                .addGap(11, 11, 11))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 117, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 210, 510));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel7.setText("Nombre");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel8.setText("Apellido");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel9.setText("Telefono");

        jLabel10.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel10.setText("ID");

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        ));
        jScrollPane1.setViewportView(tabla);

        Bt_agregar.setBackground(new java.awt.Color(0, 255, 51));
        Bt_agregar.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        Bt_agregar.setText("AGREGAR");
        Bt_agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bt_agregarActionPerformed(evt);
            }
        });

        Bt_eliminar.setBackground(new java.awt.Color(204, 0, 0));
        Bt_eliminar.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        Bt_eliminar.setText("ELIMINAR");
        Bt_eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bt_eliminarActionPerformed(evt);
            }
        });

        Bt_modificar.setBackground(new java.awt.Color(51, 51, 255));
        Bt_modificar.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        Bt_modificar.setText("MODIFICAR");
        Bt_modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bt_modificarActionPerformed(evt);
            }
        });

        Bt_buscar.setBackground(new java.awt.Color(255, 255, 51));
        Bt_buscar.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        Bt_buscar.setText("BUSCAR");
        Bt_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bt_buscarActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel14.setText("Curso");

        Cb_curso.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Taller de Programación Básica en Python", "Curso de Fotografía Digital", "Taller de Emprendimiento y Modelos de Negocio", "Curso de Inglés Conversacional", "Taller de Cocina Internacional", "Curso de Diseño Gráfico con Canva", "Taller de Electricidad Residencial Básica", "Curso de Primeros Auxilios" }));
        Cb_curso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cb_cursoActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel15.setText("Correo");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(Bt_agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addGap(164, 164, 164)
                                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(Cb_curso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(jPanel8Layout.createSequentialGroup()
                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(Tx_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(Tx_id, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(25, 25, 25)
                                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel8Layout.createSequentialGroup()
                                                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(Tx_email, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                            .addGroup(jPanel8Layout.createSequentialGroup()
                                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(Tx_apellido, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
                                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(Tx_telefono, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                                .addGap(54, 54, 54))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(127, 127, 127)
                                .addComponent(Bt_eliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(139, 139, 139)
                                .addComponent(Bt_modificar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(Bt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31))))))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Tx_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Tx_apellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel9)
                    .addComponent(Tx_telefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(Tx_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Tx_email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGap(29, 29, 29)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Cb_curso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Bt_agregar)
                    .addComponent(Bt_eliminar)
                    .addComponent(Bt_modificar)
                    .addComponent(Bt_buscar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 277, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("ESTUDIANTES", jPanel8);

        jLabel11.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel11.setText("Codigo Curso");

        jLabel12.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel12.setText("Nombre Curso");

        jLabel13.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel13.setText("Tutor");

        jLabel16.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel16.setText("Horarios");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel17.setText("Sedes");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel18.setText("Cupos Maximos");

        Tx_codigoCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Tx_codigoCursoActionPerformed(evt);
            }
        });

        Cb_tutorCurso.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar Tutor", "Dr. Carlos Martínez", "Lic. Ana Rodríguez", "Mg. Luis González", "Prof. María López", "Ing. Jorge Silva", "Dra. Patricia Castro", "Lic. Roberto Mendoza", "Mg. Sandra Rojas" }));

        Cb_horarioCurso.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar Horario", "Lunes y Miércoles 08:00-10:00", "Lunes y Miércoles 10:00-12:00", "Lunes y Miércoles 14:00-16:00", "Lunes y Miércoles 16:00-18:00", "Lunes y Miércoles 18:00-20:00", "Martes y Jueves 08:00-10:00", "Martes y Jueves 10:00-12:00", "Martes y Jueves 14:00-16:00", "Martes y Jueves 16:00-18:00", "Martes y Jueves 18:00-20:00", "Viernes 14:00-18:00", "Viernes 18:00-22:00", "Sábados 08:00-12:00", "Sábados 14:00-18:00", "Domingos 08:00-12:00" }));

        Cb_sedeCurso.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar Sede", "Cartagena - Sede Centro Histórico", "Cartagena - Sede Bocagrande", "Cartagena - Sede Manga", "Turbaco - Sede Principal", "Arjona - Sede Norte", "Magangué - Sede Sur", "Carmen de Bolívar - Sede Oriental", "San Juan Nepomuceno - Sede Montaña", "Santa Rosa de Lima - Sede Costera", "María La Baja - Sede Rural" }));
        Cb_sedeCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Cb_sedeCursoActionPerformed(evt);
            }
        });

        Jb_agregarCurso.setBackground(new java.awt.Color(0, 255, 0));
        Jb_agregarCurso.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        Jb_agregarCurso.setText("AGREGAR");
        Jb_agregarCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jb_agregarCursoActionPerformed(evt);
            }
        });

        Jb_eliminarCurso.setBackground(new java.awt.Color(255, 0, 0));
        Jb_eliminarCurso.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        Jb_eliminarCurso.setText("ELIMINAR");
        Jb_eliminarCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jb_eliminarCursoActionPerformed(evt);
            }
        });

        Jb_modificarCurso.setBackground(new java.awt.Color(51, 51, 255));
        Jb_modificarCurso.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        Jb_modificarCurso.setText("MODIFICAR");
        Jb_modificarCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jb_modificarCursoActionPerformed(evt);
            }
        });

        Jb_buscarCurso.setBackground(new java.awt.Color(255, 255, 51));
        Jb_buscarCurso.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        Jb_buscarCurso.setText("BUSCAR");
        Jb_buscarCurso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Jb_buscarCursoActionPerformed(evt);
            }
        });

        tablaCursos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        ));
        jScrollPane2.setViewportView(tablaCursos);

        Sp_cuposCurso.setModel(new javax.swing.SpinnerNumberModel(10, 10, 35, 1));

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(Jb_agregarCurso)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel9Layout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(10, 10, 10)))
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(Tx_codigoCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(Tx_nombreCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Cb_horarioCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(Cb_tutorCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)
                                .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(96, 96, 96)
                        .addComponent(Jb_eliminarCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Jb_modificarCurso)
                        .addGap(20, 20, 20)))
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Cb_sedeCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Sp_cuposCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(199, 199, 199))
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(99, 99, 99)
                        .addComponent(Jb_buscarCurso, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 825, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel13)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Tx_codigoCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Cb_tutorCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Cb_sedeCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jLabel16)
                    .addComponent(jLabel18)
                    .addComponent(Tx_nombreCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Cb_horarioCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Sp_cuposCurso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Jb_agregarCurso)
                    .addComponent(Jb_eliminarCurso)
                    .addComponent(Jb_modificarCurso)
                    .addComponent(Jb_buscarCurso))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33))
        );

        jTabbedPane1.addTab("CURSOS", jPanel9);

        jLabel19.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel19.setText("Codigo Sede");

        jLabel20.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel20.setText("Nombre Sede");

        jLabel21.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel21.setText("Direccion Sede");

        jLabel22.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel22.setText("Telefono Sede");

        jLabel23.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel23.setText("Jefe Sede");

        jLabel24.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel24.setText("Ciudad Sede");

        Cb_jefeSede.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar Jefe", "Dr. Carlos Martínez Herrera", "Lic. Ana Rodríguez Pérez", "Mg. Luis González Díaz", "Prof. María López Santos", "Ing. Jorge Silva Mendoza", "Dra. Patricia Castro Rojas", "Lic. Roberto Navarro Vega", "Mg. Sandra Morales Gil", "Esp. Juan Torres Fuentes", "Lic. Carolina Pájaro Silva", "Mg. Rafael Martínez Pérez" }));

        Cb_ciudadSede.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar Ciudad", "Cartagena", "Turbaco", "Arjona", "Magangué", "Carmen de Bolívar", "San Juan Nepomuceno", "Santa Rosa de Lima", "María La Baja" }));

        jLabel25.setFont(new java.awt.Font("Segoe UI", 3, 14)); // NOI18N
        jLabel25.setText("Capicidad Sede");

        Sp_capacidadSede.setModel(new javax.swing.SpinnerNumberModel(10, 10, 50, 5));

        Bt_agregarSede.setBackground(new java.awt.Color(0, 255, 0));
        Bt_agregarSede.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        Bt_agregarSede.setText("AGREGAR");
        Bt_agregarSede.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bt_agregarSedeActionPerformed(evt);
            }
        });

        Bt_eliminarSede.setBackground(new java.awt.Color(255, 0, 0));
        Bt_eliminarSede.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        Bt_eliminarSede.setText("ELIMINAR");
        Bt_eliminarSede.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bt_eliminarSedeActionPerformed(evt);
            }
        });

        Bt_modifcarSede.setBackground(new java.awt.Color(51, 51, 255));
        Bt_modifcarSede.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        Bt_modifcarSede.setText("MODIFICAR");
        Bt_modifcarSede.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bt_modifcarSedeActionPerformed(evt);
            }
        });

        Bt_buscarSede.setBackground(new java.awt.Color(255, 255, 51));
        Bt_buscarSede.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        Bt_buscarSede.setText("BUSCAR");
        Bt_buscarSede.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Bt_buscarSedeActionPerformed(evt);
            }
        });

        tablaSedes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7"
            }
        ));
        jScrollPane3.setViewportView(tablaSedes);

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(Tx_codigoSede, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(Tx_nombreSede, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Sp_capacidadSede, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel10Layout.createSequentialGroup()
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(Tx_direccionSede)
                                    .addComponent(Tx_telefonoSede, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE))
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addGap(51, 51, 51)
                                        .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addGap(39, 39, 39)
                                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(jPanel10Layout.createSequentialGroup()
                                        .addComponent(Cb_ciudadSede, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(8, 8, 8))
                                    .addComponent(Cb_jefeSede, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(44, 44, 44))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(Bt_agregarSede, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(109, 109, 109)
                        .addComponent(Bt_eliminarSede, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(Bt_modifcarSede, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(122, 122, 122)
                        .addComponent(Bt_buscarSede, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(32, 32, 32))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 809, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 23, Short.MAX_VALUE))))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jLabel21)
                    .addComponent(Tx_codigoSede, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Tx_direccionSede, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23)
                    .addComponent(Cb_jefeSede, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(jLabel22)
                    .addComponent(Tx_nombreSede, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Tx_telefonoSede, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24)
                    .addComponent(Cb_ciudadSede, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(Sp_capacidadSede, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Bt_agregarSede)
                    .addComponent(Bt_eliminarSede)
                    .addComponent(Bt_modifcarSede)
                    .addComponent(Bt_buscarSede))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("SEDES", jPanel10);

        jPanel1.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 120, 830, 510));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1036, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 644, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel4MouseClicked
        jTabbedPane1.setSelectedIndex(0);
    }//GEN-LAST:event_jPanel4MouseClicked

    private void jPanel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel5MouseClicked
        jTabbedPane1.setSelectedIndex(1);
    }//GEN-LAST:event_jPanel5MouseClicked

    private void jPanel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel6MouseClicked
        jTabbedPane1.setSelectedIndex(2);
    }//GEN-LAST:event_jPanel6MouseClicked

    private void Bt_agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bt_agregarActionPerformed

    if (Tx_nombre.getText().trim().isEmpty() || 
        Tx_apellido.getText().trim().isEmpty() || 
        Tx_telefono.getText().trim().isEmpty() || 
        Tx_id.getText().trim().isEmpty() || 
        Tx_email.getText().trim().isEmpty()) {
        
        JOptionPane.showMessageDialog(this, 
            "Todos los campos son obligatorios", 
            "Campos incompletos", 
            JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    String correo = Tx_email.getText().trim();
    if (!validarCorreo(correo)) {
        JOptionPane.showMessageDialog(this, 
            "El formato del correo electrónico no es válido\n\n" +
            "Ejemplos válidos:\n" +
            "• usuario@dominio.com\n" +
            "• nombre.apellido@empresa.co\n" +
            "• juan123@universidad.edu", 
            "Correo inválido", 
            JOptionPane.ERROR_MESSAGE);
        return;
    }
    
    
    if (Cb_curso.getSelectedIndex() == 0) {
        JOptionPane.showMessageDialog(this, 
            "Por favor seleccione un curso válido", 
            "Curso no seleccionado", 
            JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    try {
        Usuario estudiante = new Usuario();
       
        estudiante.setNombre(Tx_nombre.getText().trim());
        estudiante.setApellido(Tx_apellido.getText().trim());
        estudiante.setTelefono(Long.parseLong(Tx_telefono.getText().trim())); 
        estudiante.setId(Long.parseLong(Tx_id.getText().trim()));
        estudiante.setCorreo(Tx_email.getText().trim());
        estudiante.setCurso(Cb_curso.getSelectedItem().toString());
        
        listaUsuario.add(estudiante);
        refrescarTabla();
        
        
        limpiarCampos();
        
        javax.swing.JOptionPane.showMessageDialog(this, 
            "Estudiante agregado correctamente", 
            "Éxito", 
            JOptionPane.INFORMATION_MESSAGE);
        
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, 
            "Error: El ID y Teléfono deben contener solo números\n\n" +
            "• ID: " + Tx_id.getText() + "\n" +
            "• Teléfono: " + Tx_telefono.getText(), 
            "Error de formato numérico", 
            JOptionPane.ERROR_MESSAGE);
    }
}
    
    private void limpiarCampos() {
    Tx_nombre.setText("");
    Tx_apellido.setText("");
    Tx_telefono.setText("");
    Tx_id.setText("");
    Tx_email.setText("");
    Cb_curso.setSelectedIndex(0); 
    
    }//GEN-LAST:event_Bt_agregarActionPerformed

    private void Cb_cursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cb_cursoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Cb_cursoActionPerformed

    private void Bt_eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bt_eliminarActionPerformed
        int fila = tabla.getSelectedRow();

     if (fila == -1) {
         JOptionPane.showMessageDialog(this, 
             "Por favor seleccione un estudiante de la tabla para eliminar", 
             "Ninguna fila seleccionada", 
             JOptionPane.WARNING_MESSAGE);
         return;
     }

     int confirmacion = JOptionPane.showConfirmDialog(this,
         "¿Está seguro que desea eliminar este estudiante?\n" +
         "Nombre: " + tabla.getValueAt(fila, 0) + " " + tabla.getValueAt(fila, 1),
         "Confirmar eliminación",
         JOptionPane.YES_NO_OPTION);

     if (confirmacion == JOptionPane.YES_OPTION) {
         listaUsuario.remove(fila);
         refrescarTabla();
         JOptionPane.showMessageDialog(this, 
             "Estudiante eliminado correctamente", 
             "Éxito", 
             JOptionPane.INFORMATION_MESSAGE);
     }
    }//GEN-LAST:event_Bt_eliminarActionPerformed

    private void Bt_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bt_buscarActionPerformed
         String idBuscar = Tx_id.getText().trim();

       if (idBuscar.isEmpty()) {
           JOptionPane.showMessageDialog(this, 
               "Ingrese un ID para buscar", 
               "Campo vacío", 
               JOptionPane.WARNING_MESSAGE);
           return;
       }

       try {
           long idBuscado = Long.parseLong(idBuscar);
           boolean encontrado = false;

           for (int i = 0; i < listaUsuario.size(); i++) {
               Usuario estudiante = listaUsuario.get(i);
               if (estudiante.getId() == idBuscado) {
                   // Llenar los campos con los datos encontrados
                   Tx_nombre.setText(estudiante.getNombre());
                   Tx_apellido.setText(estudiante.getApellido());
                   Tx_telefono.setText(String.valueOf(estudiante.getTelefono()));
                   Tx_email.setText(estudiante.getCorreo());

                   // Seleccionar el curso en el ComboBox
                   for (int j = 0; j < Cb_curso.getItemCount(); j++) {
                       if (Cb_curso.getItemAt(j).equals(estudiante.getCurso())) {
                           Cb_curso.setSelectedIndex(j);
                           break;
                       }
                   }

                   // Seleccionar la fila en la tabla
                   tabla.setRowSelectionInterval(i, i);
                   tabla.scrollRectToVisible(tabla.getCellRect(i, 0, true));

                   encontrado = true;
                   JOptionPane.showMessageDialog(this, 
                       "Estudiante encontrado", 
                       "Búsqueda exitosa", 
                       JOptionPane.INFORMATION_MESSAGE);
                   break;
               }
           }

           if (!encontrado) {
               JOptionPane.showMessageDialog(this, 
                   "No se encontró ningún estudiante con ID: " + idBuscar, 
                   "No encontrado", 
                   JOptionPane.WARNING_MESSAGE);
           }

       } catch (NumberFormatException e) {
           JOptionPane.showMessageDialog(this, 
               "El ID debe ser un número válido", 
               "Error de formato", 
               JOptionPane.ERROR_MESSAGE);
       }
    }//GEN-LAST:event_Bt_buscarActionPerformed

    private void Bt_modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bt_modificarActionPerformed
        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada == -1) {
        JOptionPane.showMessageDialog(this, 
            "Seleccione un estudiante de la tabla para modificar", 
            "Ninguna fila seleccionada", 
            JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    
    if (Tx_nombre.getText().trim().isEmpty() || 
        Tx_apellido.getText().trim().isEmpty() || 
        Tx_telefono.getText().trim().isEmpty() || 
        Tx_id.getText().trim().isEmpty() || 
        Tx_email.getText().trim().isEmpty()) {
        
        JOptionPane.showMessageDialog(this, 
            "Todos los campos son obligatorios", 
            "Campos incompletos", 
            JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    String correo = Tx_email.getText().trim();
    if (!validarCorreo(correo)) {
        JOptionPane.showMessageDialog(this, 
            "El formato del correo electrónico no es válido\n\n" +
            "Ejemplos válidos:\n" +
            "• usuario@dominio.com\n" +
            "• nombre.apellido@empresa.co\n" +
            "• juan123@universidad.edu", 
            "Correo inválido", 
            JOptionPane.ERROR_MESSAGE);
        return;
    }
    try {
        Usuario estudiante = listaUsuario.get(filaSeleccionada);
        
        // Confirmar modificación
        int confirmacion = JOptionPane.showConfirmDialog(this,
            "¿Está seguro que desea modificar este estudiante?\n" +
            "ID: " + estudiante.getId() + "\n" +
            "Nombre: " + estudiante.getNombre() + " " + estudiante.getApellido(),
            "Confirmar modificación",
            JOptionPane.YES_NO_OPTION);
        
        if (confirmacion == JOptionPane.YES_OPTION) {
            // Actualizar datos
            estudiante.setNombre(Tx_nombre.getText().trim());
            estudiante.setApellido(Tx_apellido.getText().trim());
            estudiante.setTelefono(Long.parseLong(Tx_telefono.getText().trim()));
            estudiante.setId(Long.parseLong(Tx_id.getText().trim()));
            estudiante.setCorreo(Tx_email.getText().trim());
            estudiante.setCurso(Cb_curso.getSelectedItem().toString());
            
            refrescarTabla();
            limpiarCampos();
            
            JOptionPane.showMessageDialog(this, 
                "Estudiante modificado correctamente", 
                "Modificación exitosa", 
                JOptionPane.INFORMATION_MESSAGE);
        }
        
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, 
            "Error: El ID y Teléfono deben contener solo números", 
            "Error de formato numérico", 
            JOptionPane.ERROR_MESSAGE);
      }
    }//GEN-LAST:event_Bt_modificarActionPerformed

    private void Tx_codigoCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Tx_codigoCursoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Tx_codigoCursoActionPerformed

    private void Jb_agregarCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jb_agregarCursoActionPerformed
        if (Tx_codigoCurso.getText().trim().isEmpty() || 
        Tx_nombreCurso.getText().trim().isEmpty()) {
        
        JOptionPane.showMessageDialog(this, 
            "Los campos Código y Nombre son obligatorios", 
            "Campos incompletos", 
            JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    // 2. VALIDAR COMBOBOXES
    if (Cb_tutorCurso.getSelectedIndex() == 0) {
        JOptionPane.showMessageDialog(this, 
            "Por favor seleccione un tutor", 
            "Tutor no seleccionado", 
            JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    if (Cb_horarioCurso.getSelectedIndex() == 0) {
        JOptionPane.showMessageDialog(this, 
            "Por favor seleccione un horario", 
            "Horario no seleccionado", 
            JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    if (Cb_sedeCurso.getSelectedIndex() == 0) {
        JOptionPane.showMessageDialog(this, 
            "Por favor seleccione una sede", 
            "Sede no seleccionada", 
            JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    // 3. VALIDAR SPINNER (OBTENER Y VERIFICAR VALOR)
    int cupos = (Integer) Sp_cuposCurso.getValue();
    if (cupos < 10 || cupos > 35) {
        JOptionPane.showMessageDialog(this, 
            "Los cupos deben estar entre 10 y 35\n" +
            "Valor actual: " + cupos, 
            "Cupos fuera de rango", 
            JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    //VALIDAR QUE EL CÓDIGO NO EXISTA
    String codigo = Tx_codigoCurso.getText().trim().toUpperCase();
    for (Curso cursoExistente : listaCursos) {
        if (cursoExistente.getCodigo().equalsIgnoreCase(codigo)) {
            JOptionPane.showMessageDialog(this, 
                "Ya existe un curso con el código: " + codigo + "\n" +
                "Curso existente: " + cursoExistente.getNombre(), 
                "Código duplicado", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
    }
    
    try {
        // 5. CREAR NUEVO CURSO
        Curso nuevoCurso = new Curso(
            codigo, // Convertido a mayúsculas
            Tx_nombreCurso.getText().trim(),
            Cb_tutorCurso.getSelectedItem().toString(),
            Cb_horarioCurso.getSelectedItem().toString(),
            Cb_sedeCurso.getSelectedItem().toString(),
            cupos // Usamos la variable ya validada
        );
        
        // AGREGAR A LA LISTA
        listaCursos.add(nuevoCurso);
        
        // ACTUALIZAR TABLA
        refrescarTablaCursos();
        
        // LIMPIAR CAMPOS
        limpiarCamposCursos();
        
        // MENSAJE DE CONFIRMACIÓN
        JOptionPane.showMessageDialog(this, 
            "Curso agregado correctamente\n" +
            "• Código: " + codigo + "\n" +
            "• Nombre: " + nuevoCurso.getNombre() + "\n" +
            "• Tutor: " + nuevoCurso.getTutor() + "\n" +
            "• Cupos: " + nuevoCurso.getCuposMaximos(),
            "Curso registrado", 
            JOptionPane.INFORMATION_MESSAGE);
        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, 
            "Error al agregar el curso: " + e.getMessage(), 
            "Error inesperado", 
            JOptionPane.ERROR_MESSAGE);
        e.printStackTrace(); // Para debug
    }
    }//GEN-LAST:event_Jb_agregarCursoActionPerformed

    private void Jb_eliminarCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jb_eliminarCursoActionPerformed
        int filaSeleccionada = tablaCursos.getSelectedRow();
    
    if (filaSeleccionada == -1) {
        JOptionPane.showMessageDialog(this, 
            "Por favor seleccione un curso de la tabla para eliminar", 
            "Ningún curso seleccionado", 
            JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    // 2. OBTENER EL CURSO SELECCIONADO
    Curso cursoAEliminar = listaCursos.get(filaSeleccionada);
    
    // 3. VERIFICAR SI HAY ESTUDIANTES INSCRITOS EN ESTE CURSO
    int estudiantesInscritos = contarEstudiantesEnCurso(cursoAEliminar.getNombre());
    
    if (estudiantesInscritos > 0) {
        JOptionPane.showMessageDialog(this, 
            "No se puede eliminar el curso\n\n" +
            "• Curso: " + cursoAEliminar.getNombre() + "\n" +
            "• Código: " + cursoAEliminar.getCodigo() + "\n" +
            "• Estudiantes inscritos: " + estudiantesInscritos + "\n\n" +
            "Primero debe reasignar o eliminar los estudiantes de este curso.", 
            "Curso con estudiantes inscritos", 
            JOptionPane.ERROR_MESSAGE);
        return;
    }
    
    // 4. CONFIRMAR ELIMINACIÓN
    int confirmacion = JOptionPane.showConfirmDialog(this,
        "¿Está seguro que desea eliminar este curso?\n\n" +
        "Información del curso:\n" +
        "• Código: " + cursoAEliminar.getCodigo() + "\n" +
        "• Nombre: " + cursoAEliminar.getNombre() + "\n" +
        "• Tutor: " + cursoAEliminar.getTutor() + "\n" +
        "• Sede: " + cursoAEliminar.getSede() + "\n\n" +
        "⚠️ Esta acción no se puede deshacer",
        "Confirmar eliminación de curso",
        JOptionPane.YES_NO_OPTION,
        JOptionPane.WARNING_MESSAGE);
    
    if (confirmacion == JOptionPane.YES_OPTION) {
        // 5. ELIMINAR EL CURSO
        listaCursos.remove(filaSeleccionada);
        
        // 6. ACTUALIZAR LA TABLA
        refrescarTablaCursos();
        
        // 7. LIMPIAR CAMPOS SI EL CURSO ELIMINADO ESTABA CARGADO
        limpiarCamposCursos();
        
        // 8. MENSAJE DE CONFIRMACIÓN
        JOptionPane.showMessageDialog(this, 
            "Curso eliminado correctamente\n\n" +
            "• Código: " + cursoAEliminar.getCodigo() + "\n" +
            "• Nombre: " + cursoAEliminar.getNombre(), 
            "Curso eliminado", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    }//GEN-LAST:event_Jb_eliminarCursoActionPerformed

    private void Jb_modificarCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jb_modificarCursoActionPerformed
            int filaSeleccionada = tablaCursos.getSelectedRow();

       if (filaSeleccionada == -1) {
           JOptionPane.showMessageDialog(this, 
               "Por favor seleccione un curso de la tabla para modificar", 
               "Ningún curso seleccionado", 
               JOptionPane.WARNING_MESSAGE);
           return;
       }

       // 2. VALIDAR CAMPOS OBLIGATORIOS
       if (Tx_codigoCurso.getText().trim().isEmpty() || 
           Tx_nombreCurso.getText().trim().isEmpty()) {

           JOptionPane.showMessageDialog(this, 
               "Los campos Código y Nombre son obligatorios", 
               "Campos incompletos", 
               JOptionPane.WARNING_MESSAGE);
           return;
       }

       // 3. VALIDAR COMBOBOXES
       if (Cb_tutorCurso.getSelectedIndex() == 0) {
           JOptionPane.showMessageDialog(this, 
               "Por favor seleccione un tutor", 
               "Tutor no seleccionado", 
               JOptionPane.WARNING_MESSAGE);
           return;
       }

       if (Cb_horarioCurso.getSelectedIndex() == 0) {
           JOptionPane.showMessageDialog(this, 
               "Por favor seleccione un horario", 
               "Horario no seleccionado", 
               JOptionPane.WARNING_MESSAGE);
           return;
       }

       if (Cb_sedeCurso.getSelectedIndex() == 0) {
           JOptionPane.showMessageDialog(this, 
               "Por favor seleccione una sede", 
               "Sede no seleccionada", 
               JOptionPane.WARNING_MESSAGE);
           return;
       }

       // 4. VALIDAR SPINNER
       int nuevosCupos = (Integer) Sp_cuposCurso.getValue();
       if (nuevosCupos < 10 || nuevosCupos > 35) {
           JOptionPane.showMessageDialog(this, 
               "Los cupos deben estar entre 10 y 35\n" +
               "Valor actual: " + nuevosCupos, 
               "Cupos fuera de rango", 
               JOptionPane.WARNING_MESSAGE);
           return;
       }

       try {
           // 5. OBTENER EL CURSO ORIGINAL
           Curso cursoOriginal = listaCursos.get(filaSeleccionada);
           String codigoOriginal = cursoOriginal.getCodigo();
           String codigoNuevo = Tx_codigoCurso.getText().trim().toUpperCase();

           // 6. VERIFICAR SI SE CAMBIÓ EL CÓDIGO Y SI EL NUEVO CÓDIGO YA EXISTE
           if (!codigoOriginal.equals(codigoNuevo)) {
               for (Curso cursoExistente : listaCursos) {
                   if (cursoExistente.getCodigo().equalsIgnoreCase(codigoNuevo) && 
                       !cursoExistente.getCodigo().equals(codigoOriginal)) {

                       JOptionPane.showMessageDialog(this, 
                           "Ya existe otro curso con el código: " + codigoNuevo + "\n" +
                           "Curso existente: " + cursoExistente.getNombre(), 
                           "Código duplicado", 
                           JOptionPane.ERROR_MESSAGE);
                       return;
                   }
               }
           }

           // 7. VERIFICAR REDUCCIÓN DE CUPOS (si hay estudiantes inscritos)
           int estudiantesInscritos = contarEstudiantesEnCurso(cursoOriginal.getNombre());
           if (nuevosCupos < cursoOriginal.getCuposMaximos() && estudiantesInscritos > nuevosCupos) {
               JOptionPane.showMessageDialog(this, 
                   "No se pueden reducir los cupos\n\n" +
                   "• Cupos actuales: " + cursoOriginal.getCuposMaximos() + "\n" +
                   "• Cupos nuevos: " + nuevosCupos + "\n" +
                   "• Estudiantes inscritos: " + estudiantesInscritos + "\n\n" +
                   "Los nuevos cupos no pueden ser menores que la cantidad\nde estudiantes ya inscritos en el curso.", 
                   "Reducción de cupos no permitida", 
                   JOptionPane.ERROR_MESSAGE);
               return;
           }

           // 8. CONFIRMAR MODIFICACIÓN
           int confirmacion = JOptionPane.showConfirmDialog(this,
               "¿Está seguro que desea modificar este curso?\n\n" +
               "Cambios a realizar:\n" +
               "• Código: " + cursoOriginal.getCodigo() + " → " + codigoNuevo + "\n" +
               "• Nombre: " + cursoOriginal.getNombre() + " → " + Tx_nombreCurso.getText().trim() + "\n" +
               "• Tutor: " + cursoOriginal.getTutor() + " → " + Cb_tutorCurso.getSelectedItem().toString() + "\n" +
               "• Horario: " + cursoOriginal.getHorario() + " → " + Cb_horarioCurso.getSelectedItem().toString() + "\n" +
               "• Sede: " + cursoOriginal.getSede() + " → " + Cb_sedeCurso.getSelectedItem().toString() + "\n" +
               "• Cupos: " + cursoOriginal.getCuposMaximos() + " → " + nuevosCupos + "\n\n" +
               "⚠️ Los cambios afectarán a todos los estudiantes inscritos",
               "Confirmar modificación de curso",
               JOptionPane.YES_NO_OPTION,
               JOptionPane.WARNING_MESSAGE);

           if (confirmacion == JOptionPane.YES_OPTION) {
               // 9. ACTUALIZAR EL CURSO
               cursoOriginal.setCodigo(codigoNuevo);
               cursoOriginal.setNombre(Tx_nombreCurso.getText().trim());
               cursoOriginal.setTutor(Cb_tutorCurso.getSelectedItem().toString());
               cursoOriginal.setHorario(Cb_horarioCurso.getSelectedItem().toString());
               cursoOriginal.setSede(Cb_sedeCurso.getSelectedItem().toString());
               cursoOriginal.setCuposMaximos(nuevosCupos);

               // 10. ACTUALIZAR EL NOMBRE DEL CURSO EN LOS ESTUDIANTES INSCRITOS
               if (!cursoOriginal.getNombre().equals(Tx_nombreCurso.getText().trim())) {
                   actualizarNombreCursoEnEstudiantes(cursoOriginal.getNombre(), Tx_nombreCurso.getText().trim());
               }

               // 11. ACTUALIZAR LA TABLA
               refrescarTablaCursos();

               // 12. LIMPIAR CAMPOS
               limpiarCamposCursos();

               // 13. MENSAJE DE CONFIRMACIÓN
               JOptionPane.showMessageDialog(this, 
                   "Curso modificado correctamente\n\n" +
                   "• Código: " + codigoNuevo + "\n" +
                   "• Nombre: " + cursoOriginal.getNombre() + "\n" +
                   "• Estudiantes actualizados: " + estudiantesInscritos, 
                   "Curso actualizado", 
                   JOptionPane.INFORMATION_MESSAGE);
           }

       } catch (Exception e) {
           JOptionPane.showMessageDialog(this, 
               "Error al modificar el curso: " + e.getMessage(), 
               "Error inesperado", 
               JOptionPane.ERROR_MESSAGE);
           e.printStackTrace();
       }
    }//GEN-LAST:event_Jb_modificarCursoActionPerformed

    private void Jb_buscarCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Jb_buscarCursoActionPerformed
        String terminoBusqueda = Tx_codigoCurso.getText().trim();
    
    if (terminoBusqueda.isEmpty()) {
        JOptionPane.showMessageDialog(this, 
            "Ingrese un código de curso para buscar", 
            "Campo de búsqueda vacío", 
            JOptionPane.WARNING_MESSAGE);
        return;
    }
    
    // 2. BUSCAR EL CURSO
    boolean encontrado = false;
    terminoBusqueda = terminoBusqueda.toUpperCase(); // Búsqueda case-insensitive
    
    for (int i = 0; i < listaCursos.size(); i++) {
        Curso curso = listaCursos.get(i);
        
        if (curso.getCodigo().equalsIgnoreCase(terminoBusqueda)) {
            // 3. CARGAR DATOS EN LOS CAMPOS
            Tx_codigoCurso.setText(curso.getCodigo());
            Tx_nombreCurso.setText(curso.getNombre());
            
            // Seleccionar en ComboBoxes
            seleccionarEnComboBox(Cb_tutorCurso, curso.getTutor());
            seleccionarEnComboBox(Cb_horarioCurso, curso.getHorario());
            seleccionarEnComboBox(Cb_sedeCurso, curso.getSede());
            
            // Establecer valor en Spinner
            Sp_cuposCurso.setValue(curso.getCuposMaximos());
            
            // 4. SELECCIONAR Y RESALTAR EN LA TABLA
            tablaCursos.setRowSelectionInterval(i, i);
            tablaCursos.scrollRectToVisible(tablaCursos.getCellRect(i, 0, true));
            
            // 5. MENSAJE DE ÉXITO
            JOptionPane.showMessageDialog(this, 
                "Curso encontrado\n\n" +
                "• Código: " + curso.getCodigo() + "\n" +
                "• Nombre: " + curso.getNombre() + "\n" +
                "• Tutor: " + curso.getTutor() + "\n" +
                "• Estudiantes inscritos: " + (curso.getCuposMaximos() - curso.getCuposDisponibles()), 
                "Búsqueda exitosa", 
                JOptionPane.INFORMATION_MESSAGE);
            
            encontrado = true;
            break;
        }
    }
    
    // 6. MENSAJE SI NO SE ENCUENTRA
    if (!encontrado) {
        JOptionPane.showMessageDialog(this, 
            "No se encontró ningún curso con el código: " + terminoBusqueda + "\n\n" +
            "Sugerencias:\n" +
            "• Verifique que el código sea correcto\n" +
            "• Los códigos son sensibles a mayúsculas\n" +
            "• Ejemplo: PROG01, FOTO01", 
            "Curso no encontrado", 
            JOptionPane.WARNING_MESSAGE);
    }
    }//GEN-LAST:event_Jb_buscarCursoActionPerformed

    private void Cb_sedeCursoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Cb_sedeCursoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_Cb_sedeCursoActionPerformed

    private void Bt_agregarSedeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bt_agregarSedeActionPerformed
            if (Tx_codigoSede.getText().trim().isEmpty() || 
            Tx_nombreSede.getText().trim().isEmpty() ||
            Tx_direccionSede.getText().trim().isEmpty() ||
            Tx_telefonoSede.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(this, 
                "Los campos Código, Nombre, Dirección y Teléfono son obligatorios", 
                "Campos incompletos", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 2. VALIDAR COMBOBOXES
        if (Cb_ciudadSede.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, 
                "Por favor seleccione una ciudad", 
                "Ciudad no seleccionada", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (Cb_jefeSede.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, 
                "Por favor seleccione un jefe de sede", 
                "Jefe de sede no seleccionado", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 3. VALIDAR SPINNER
        int capacidad = (Integer) Sp_capacidadSede.getValue();
        if (capacidad < 5 || capacidad > 50) {
            JOptionPane.showMessageDialog(this, 
                "La capacidad debe estar entre 5 y 50 cursos", 
                "Capacidad fuera de rango", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 4. VALIDAR TELÉFONO (solo números)
        if (!Tx_telefonoSede.getText().trim().matches("\\d+")) {
            JOptionPane.showMessageDialog(this, 
                "El teléfono debe contener solo números", 
                "Teléfono inválido", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 5. VALIDAR QUE EL CÓDIGO NO EXISTA
        String codigo = Tx_codigoSede.getText().trim().toUpperCase();
        for (Sede sedeExistente : listaSedes) {
            if (sedeExistente.getCodigo().equalsIgnoreCase(codigo)) {
                JOptionPane.showMessageDialog(this, 
                    "Ya existe una sede con el código: " + codigo + "\n" +
                    "Sede existente: " + sedeExistente.getNombre(), 
                    "Código duplicado", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
        }

        try {
            // 6. CREAR NUEVA SEDE
            Sede nuevaSede = new Sede(
                codigo,
                Tx_nombreSede.getText().trim(),
                Tx_direccionSede.getText().trim(),
                Tx_telefonoSede.getText().trim(),
                Cb_ciudadSede.getSelectedItem().toString(),
                Cb_jefeSede.getSelectedItem().toString(),
                capacidad
            );

            // 7. AGREGAR A LA LISTA
            listaSedes.add(nuevaSede);

            // 8. ACTUALIZAR COMBOBOX DE CURSOS
            actualizarComboBoxSedesCursos();

            // 9. ACTUALIZAR TABLA
            refrescarTablaSedes();

            // 10. LIMPIAR CAMPOS
            limpiarCamposSedes();

            // 11. MENSAJE DE CONFIRMACIÓN
            JOptionPane.showMessageDialog(this, 
                "Sede agregada correctamente\n\n" +
                "• Código: " + codigo + "\n" +
                "• Nombre: " + nuevaSede.getNombre() + "\n" +
                "• Ciudad: " + nuevaSede.getCiudad() + "\n" +
                "• Capacidad: " + nuevaSede.getCapacidadMaxima() + " cursos", 
                "Sede registrada", 
                JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error al agregar la sede: " + e.getMessage(), 
                "Error inesperado", 
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_Bt_agregarSedeActionPerformed

    private void Bt_eliminarSedeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bt_eliminarSedeActionPerformed
        int filaSeleccionada = tablaSedes.getSelectedRow();
        
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, 
                "❌ Por favor seleccione una sede de la tabla para eliminar", 
                "Ninguna sede seleccionada", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 2. OBTENER LA SEDE SELECCIONADA
        Sede sedeAEliminar = listaSedes.get(filaSeleccionada);

        // 3. VERIFICAR SI HAY CURSOS ACTIVOS EN ESTA SEDE
        if (sedeAEliminar.getCursosActivos() > 0) {
            JOptionPane.showMessageDialog(this, 
                "No se puede eliminar la sede\n\n" +
                "• Sede: " + sedeAEliminar.getNombre() + "\n" +
                "• Código: " + sedeAEliminar.getCodigo() + "\n" +
                "• Cursos activos: " + sedeAEliminar.getCursosActivos() + "\n\n" +
                "Primero debe eliminar o reubicar los cursos de esta sede.", 
                "Sede con cursos activos", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 4. CONFIRMAR ELIMINACIÓN
        int confirmacion = JOptionPane.showConfirmDialog(this,
            "¿Está seguro que desea eliminar esta sede?\n\n" +
            "Información de la sede:\n" +
            "• Código: " + sedeAEliminar.getCodigo() + "\n" +
            "• Nombre: " + sedeAEliminar.getNombre() + "\n" +
            "• Dirección: " + sedeAEliminar.getDireccion() + "\n" +
            "• Ciudad: " + sedeAEliminar.getCiudad() + "\n\n" +
            "Esta acción no se puede deshacer",
            "Confirmar eliminación de sede",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE);

        if (confirmacion == JOptionPane.YES_OPTION) {
            // 5. ELIMINAR LA SEDE
            listaSedes.remove(filaSeleccionada);

            // 6. ACTUALIZAR COMBOBOX DE CURSOS
            actualizarComboBoxSedesCursos();

            // 7. ACTUALIZAR LA TABLA
            refrescarTablaSedes();

            // 8. LIMPIAR CAMPOS SI LA SEDE ELIMINADA ESTABA CARGADA
            limpiarCamposSedes();

            // 9. MENSAJE DE CONFIRMACIÓN
            JOptionPane.showMessageDialog(this, 
                "Sede eliminada correctamente\n\n" +
                "• Código: " + sedeAEliminar.getCodigo() + "\n" +
                "• Nombre: " + sedeAEliminar.getNombre(), 
                "Sede eliminada", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_Bt_eliminarSedeActionPerformed

    private void Bt_modifcarSedeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bt_modifcarSedeActionPerformed
        // 1. VERIFICAR SI HAY UNA FILA SELECCIONADA
        int filaSeleccionada = tablaSedes.getSelectedRow();

        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, 
                "Por favor seleccione una sede de la tabla para modificar", 
                "Ninguna sede seleccionada", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 2. VALIDAR CAMPOS OBLIGATORIOS
        if (Tx_codigoSede.getText().trim().isEmpty() || 
            Tx_nombreSede.getText().trim().isEmpty() ||
            Tx_direccionSede.getText().trim().isEmpty() ||
            Tx_telefonoSede.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(this, 
                "Los campos Código, Nombre, Dirección y Teléfono son obligatorios", 
                "Campos incompletos", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 3. VALIDAR COMBOBOXES
        if (Cb_ciudadSede.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, 
                "Por favor seleccione una ciudad", 
                "Ciudad no seleccionada", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (Cb_jefeSede.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, 
                "Por favor seleccione un jefe de sede", 
                "Jefe de sede no seleccionado", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 4. VALIDAR SPINNER
        int nuevaCapacidad = (Integer) Sp_capacidadSede.getValue();
        if (nuevaCapacidad < 5 || nuevaCapacidad > 50) {
            JOptionPane.showMessageDialog(this, 
                "La capacidad debe estar entre 5 y 50 cursos", 
                "Capacidad fuera de rango", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 5. VALIDAR TELÉFONO
        if (!Tx_telefonoSede.getText().trim().matches("\\d+")) {
            JOptionPane.showMessageDialog(this, 
                "El teléfono debe contener solo números", 
                "Teléfono inválido", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // 6. OBTENER LA SEDE ORIGINAL
            Sede sedeOriginal = listaSedes.get(filaSeleccionada);
            String codigoOriginal = sedeOriginal.getCodigo();
            String codigoNuevo = Tx_codigoSede.getText().trim().toUpperCase();

            // 7. VERIFICAR SI SE CAMBIÓ EL CÓDIGO Y SI EL NUEVO CÓDIGO YA EXISTE
            if (!codigoOriginal.equals(codigoNuevo)) {
                for (Sede sedeExistente : listaSedes) {
                    if (sedeExistente.getCodigo().equalsIgnoreCase(codigoNuevo) && 
                        !sedeExistente.getCodigo().equals(codigoOriginal)) {

                        JOptionPane.showMessageDialog(this, 
                            "Ya existe otra sede con el código: " + codigoNuevo + "\n" +
                            "Sede existente: " + sedeExistente.getNombre(), 
                            "Código duplicado", 
                            JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
            }

            // 8. VERIFICAR REDUCCIÓN DE CAPACIDAD (si hay cursos activos)
            if (nuevaCapacidad < sedeOriginal.getCapacidadMaxima() && 
                sedeOriginal.getCursosActivos() > nuevaCapacidad) {

                JOptionPane.showMessageDialog(this, 
                    "No se puede reducir la capacidad\n\n" +
                    "• Capacidad actual: " + sedeOriginal.getCapacidadMaxima() + "\n" +
                    "• Capacidad nueva: " + nuevaCapacidad + "\n" +
                    "• Cursos activos: " + sedeOriginal.getCursosActivos() + "\n\n" +
                    "La nueva capacidad no puede ser menor que la cantidad\nde cursos activos en la sede.", 
                    "Reducción de capacidad no permitida", 
                    JOptionPane.ERROR_MESSAGE);
                return;
            }

            // 9. CONFIRMAR MODIFICACIÓN
            int confirmacion = JOptionPane.showConfirmDialog(this,
                "¿Está seguro que desea modificar esta sede?\n\n" +
                "Cambios a realizar:\n" +
                "• Código: " + sedeOriginal.getCodigo() + " → " + codigoNuevo + "\n" +
                "• Nombre: " + sedeOriginal.getNombre() + " → " + Tx_nombreSede.getText().trim() + "\n" +
                "• Dirección: " + sedeOriginal.getDireccion() + " → " + Tx_direccionSede.getText().trim() + "\n" +
                "• Teléfono: " + sedeOriginal.getTelefono() + " → " + Tx_telefonoSede.getText().trim() + "\n" +
                "• Ciudad: " + sedeOriginal.getCiudad() + " → " + Cb_ciudadSede.getSelectedItem().toString() + "\n" +
                "• Jefe: " + sedeOriginal.getJefeSede() + " → " + Cb_jefeSede.getSelectedItem().toString() + "\n" +
                "• Capacidad: " + sedeOriginal.getCapacidadMaxima() + " → " + nuevaCapacidad + "\n\n" +
                "⚠️ Los cambios afectarán a todos los cursos de esta sede",
                "Confirmar modificación de sede",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);

            if (confirmacion == JOptionPane.YES_OPTION) {
                // 10. GUARDAR NOMBRE ORIGINAL PARA ACTUALIZAR CURSOS
                String nombreOriginal = sedeOriginal.getNombre();

                // 11. ACTUALIZAR LA SEDE
                sedeOriginal.setCodigo(codigoNuevo);
                sedeOriginal.setNombre(Tx_nombreSede.getText().trim());
                sedeOriginal.setDireccion(Tx_direccionSede.getText().trim());
                sedeOriginal.setTelefono(Tx_telefonoSede.getText().trim());
                sedeOriginal.setCiudad(Cb_ciudadSede.getSelectedItem().toString());
                sedeOriginal.setJefeSede(Cb_jefeSede.getSelectedItem().toString());
                sedeOriginal.setCapacidadMaxima(nuevaCapacidad);

                // 12. ACTUALIZAR EL NOMBRE DE LA SEDE EN LOS CURSOS
                if (!nombreOriginal.equals(Tx_nombreSede.getText().trim())) {
                    actualizarNombreSedeEnCursos(nombreOriginal, Tx_nombreSede.getText().trim());
                }

                // 13. ACTUALIZAR COMBOBOX DE CURSOS
                actualizarComboBoxSedesCursos();

                // 14. ACTUALIZAR LA TABLA
                refrescarTablaSedes();

                // 15. LIMPIAR CAMPOS
                limpiarCamposSedes();

                // 16. MENSAJE DE CONFIRMACIÓN
                JOptionPane.showMessageDialog(this, 
                    "Sede modificada correctamente\n\n" +
                    "• Código: " + codigoNuevo + "\n" +
                    "• Nombre: " + sedeOriginal.getNombre() + "\n" +
                    "• Cursos actualizados: " + sedeOriginal.getCursosActivos(), 
                    "Sede actualizada", 
                    JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, 
                "Error al modificar la sede: " + e.getMessage(), 
                "Error inesperado", 
                JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }//GEN-LAST:event_Bt_modifcarSedeActionPerformed

    private void Bt_buscarSedeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Bt_buscarSedeActionPerformed
        String terminoBusqueda = Tx_codigoSede.getText().trim();
    
        if (terminoBusqueda.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Ingrese un código de sede para buscar", 
                "Campo de búsqueda vacío", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }

        // 2. BUSCAR LA SEDE
        boolean encontrado = false;
        terminoBusqueda = terminoBusqueda.toUpperCase();

        for (int i = 0; i < listaSedes.size(); i++) {
            Sede sede = listaSedes.get(i);

            if (sede.getCodigo().equalsIgnoreCase(terminoBusqueda)) {
                // 3. CARGAR DATOS EN LOS CAMPOS
                Tx_codigoSede.setText(sede.getCodigo());
                Tx_nombreSede.setText(sede.getNombre());
                Tx_direccionSede.setText(sede.getDireccion());
                Tx_telefonoSede.setText(sede.getTelefono());

                // Seleccionar en ComboBoxes
                seleccionarEnComboBox(Cb_ciudadSede, sede.getCiudad());
                seleccionarEnComboBox(Cb_jefeSede, sede.getJefeSede());

                // Establecer valor en Spinner
                Sp_capacidadSede.setValue(sede.getCapacidadMaxima());

                // 4. SELECCIONAR Y RESALTAR EN LA TABLA
                tablaSedes.setRowSelectionInterval(i, i);
                tablaSedes.scrollRectToVisible(tablaSedes.getCellRect(i, 0, true));

                // 5. MENSAJE DE ÉXITO
                JOptionPane.showMessageDialog(this, 
                    "Sede encontrada\n\n" +
                    "• Código: " + sede.getCodigo() + "\n" +
                    "• Nombre: " + sede.getNombre() + "\n" +
                    "• Ciudad: " + sede.getCiudad() + "\n" +
                    "• Cursos activos: " + sede.getCursosActivos() + "/" + sede.getCapacidadMaxima(), 
                    "Búsqueda exitosa", 
                    JOptionPane.INFORMATION_MESSAGE);

                encontrado = true;
                break;
            }
        }

        // 6. MENSAJE SI NO SE ENCUENTRA
        if (!encontrado) {
            JOptionPane.showMessageDialog(this, 
                "No se encontró ninguna sede con el código: " + terminoBusqueda + "\n\n" +
                "Sugerencias:\n" +
                "• Verifique que el código sea correcto\n" +
                "• Los códigos son sensibles a mayúsculas\n" +
                "• Ejemplo: SEDE001, SEDE002", 
                "Sede no encontrada", 
                JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_Bt_buscarSedeActionPerformed

    
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new MenuTutores01().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Bt_agregar;
    private javax.swing.JToggleButton Bt_agregarSede;
    private javax.swing.JButton Bt_buscar;
    private javax.swing.JToggleButton Bt_buscarSede;
    private javax.swing.JButton Bt_eliminar;
    private javax.swing.JToggleButton Bt_eliminarSede;
    private javax.swing.JToggleButton Bt_modifcarSede;
    private javax.swing.JButton Bt_modificar;
    private javax.swing.JComboBox<String> Cb_ciudadSede;
    private javax.swing.JComboBox<String> Cb_curso;
    private javax.swing.JComboBox<String> Cb_horarioCurso;
    private javax.swing.JComboBox<String> Cb_jefeSede;
    private javax.swing.JComboBox<String> Cb_sedeCurso;
    private javax.swing.JComboBox<String> Cb_tutorCurso;
    private javax.swing.JButton Jb_agregarCurso;
    private javax.swing.JButton Jb_buscarCurso;
    private javax.swing.JButton Jb_eliminarCurso;
    private javax.swing.JButton Jb_modificarCurso;
    private javax.swing.JSpinner Sp_capacidadSede;
    private javax.swing.JSpinner Sp_cuposCurso;
    private javax.swing.JTextField Tx_apellido;
    private javax.swing.JTextField Tx_codigoCurso;
    private javax.swing.JTextField Tx_codigoSede;
    private javax.swing.JTextField Tx_direccionSede;
    private javax.swing.JTextField Tx_email;
    private javax.swing.JTextField Tx_id;
    private javax.swing.JTextField Tx_nombre;
    private javax.swing.JTextField Tx_nombreCurso;
    private javax.swing.JTextField Tx_nombreSede;
    private javax.swing.JTextField Tx_telefono;
    private javax.swing.JTextField Tx_telefonoSede;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tabla;
    private javax.swing.JTable tablaCursos;
    private javax.swing.JTable tablaSedes;
    // End of variables declaration//GEN-END:variables
}
