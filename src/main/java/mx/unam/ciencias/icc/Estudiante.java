package mx.unam.ciencias.icc;

/**
 * Clase para representar estudiantes. Un estudiante tiene nombre, número de
 * cuenta, promedio y edad. La clase implementa {@link Registro}, por lo que
 * puede seriarse en una línea de texto y deseriarse de una línea de
 * texto; además de determinar si sus campos casan valores arbitrarios y
 * actualizarse con los valores de otro estudiante.
 */
public class Estudiante implements Registro<Estudiante, CampoEstudiante> {

    /* Nombre del estudiante. */
    private String nombre;
    /* Número de cuenta. */
    private int cuenta;
    /* Pormedio del estudiante. */
    private double promedio;
    /* Edad del estudiante.*/
    private int edad;

    /**
     * Define el estado inicial de un estudiante.
     * @param nombre el nombre del estudiante.
     * @param cuenta el número de cuenta del estudiante.
     * @param promedio el promedio del estudiante.
     * @param edad la edad del estudiante.
     */
    public Estudiante(String nombre,
                      int    cuenta,
                      double promedio,
                      int    edad) {
        // Aquí va su código.
	this.nombre = nombre;
	this.cuenta = cuenta;
	this.promedio = promedio;
	this.edad = edad;
    }

    /**
     * Regresa el nombre del estudiante.
     * @return el nombre del estudiante.
     */
    public String getNombre() {
        // Aquí va su código.
	return this.nombre;
    }

    /**
     * Define el nombre del estudiante.
     * @param nombre el nuevo nombre del estudiante.
     */
    public void setNombre(String nombre) {
        // Aquí va su código.
	this.nombre = nombre;
    }

    /**
     * Regresa el número de cuenta del estudiante.
     * @return el número de cuenta del estudiante.
     */
    public int getCuenta() {
        // Aquí va su código.
	return this.cuenta;
    }

    /**
     * Define el número cuenta del estudiante.
     * @param cuenta el nuevo número de cuenta del estudiante.
     */
    public void setCuenta(int cuenta) {
        // Aquí va su código.
	this.cuenta = cuenta;
    }

    /**
     * Regresa el promedio del estudiante.
     * @return el promedio del estudiante.
     */
    public double getPromedio() {
        // Aquí va su código.
	return promedio;
    }

    /**
     * Define el promedio del estudiante.
     * @param promedio el nuevo promedio del estudiante.
     */
    public void setPromedio(double promedio) {
        // Aquí va su código.
	this.promedio = promedio;
    }

    /**
     * Regresa la edad del estudiante.
     * @return la edad del estudiante.
     */
    public int getEdad() {
        // Aquí va su código.
	return this.edad;
    }

    /**
     * Define la edad del estudiante.
     * @param edad la nueva edad del estudiante.
     */
    public void setEdad(int edad) {
        // Aquí va su código.
	this.edad = edad;
    }

    /**
     * Regresa una representación en cadena del estudiante.
     * @return una representación en cadena del estudiante.
     */
    @Override public String toString() {
        // Aquí va su código.
	return String.format("Nombre   : %s\n" +
                     "Cuenta   : %09d\n" +
                     "Promedio : %2.2f\n" +
                     "Edad     : %d",
			     nombre, cuenta, promedio, edad);
    }

    /**
     * Nos dice si el objeto recibido es un estudiante igual al que manda llamar
     * el método.
     * @param objeto el objeto con el que el estudiante se comparará.
     * @return <code>true</code> si el objeto recibido es un estudiante con las
     *         mismas propiedades que el objeto que manda llamar al método,
     *         <code>false</code> en otro caso.
     */
    @Override public boolean equals(Object objeto) {
        if (!(objeto instanceof Estudiante))
            return false;
        Estudiante estudiante = (Estudiante)objeto;
        // Aquí va su código.
	 return nombre.equals(estudiante.nombre) && cuenta == estudiante.cuenta && promedio == estudiante.promedio && edad == estudiante.edad;
    }

    /**
     * Regresa el estudiante seriado en una línea de texto. La línea de
     * texto que este método regresa debe ser aceptada por el método {@link
     * Estudiante#deseria}.
     * @return la seriación del estudiante en una línea de texto.
     */
    @Override public String seria() {
        // Aquí va su código.
	return String.format("%s\t%d\t%2.2f\t%d\n", nombre, cuenta, promedio, edad);
    }

    /**
     * Deseria una línea de texto en las propiedades del estudiante. La
     * seriación producida por el método {@link Estudiante#seria} debe
     * ser aceptada por este método.
     * @param linea la línea a deseriar.
     * @throws ExcepcionLineaInvalida si la línea recibida es nula, vacía o no
     *         es una seriación válida de un estudiante.
     */
    @Override public void deseria(String linea) {
        // Aquí va su código.
	 String lineaSinEspacios = linea.trim();

        if(lineaSinEspacios == null || lineaSinEspacios.equals(""))
            throw new ExcepcionLineaInvalida("Linea invalida");
        
        String[] porDeseriar = lineaSinEspacios.split("\t", 4);
        //int palabrasEnLinea = linea.split("\t").length;
        if(porDeseriar.length != 4)
            throw new ExcepcionLineaInvalida("Linea invalida");
        try {
        this.nombre = porDeseriar[0];
        this.cuenta = Integer.parseInt(porDeseriar[1]);
        this.promedio =  Double.parseDouble(porDeseriar[2]);
        this.edad =  Integer.parseInt(porDeseriar[3]);

        } catch (NumberFormatException e) {
            throw new ExcepcionLineaInvalida("Linea invalida");
        }
    }

    /**
     * Actualiza los valores del estudiante con los del estudiante recibido.
     * @param estudiante el estudiante con el cual actualizar los valores.
     * @throws IllegalArgumentException si el estudiante es <code>null</code>.
     */
    @Override public void actualiza(Estudiante estudiante) {
        // Aquí va su código.
	        if (estudiante == null) {
            throw new IllegalArgumentException();
        } else {
            this.nombre = estudiante.nombre;
            this.cuenta = estudiante.cuenta;
            this.promedio = estudiante.promedio;
            this.edad = estudiante.edad;
        }
    }

    
    /**
     * Nos dice si el estudiante casa el valor dado en el campo especificado.
     * @param campo el campo que hay que casar.
     * @param valor el valor con el que debe casar el campo del registro.
     * @return <code>true</code> si:
     *         <ul>
     *           <li><code>campo</code> es {@link CampoEstudiante#NOMBRE} y
     *              <code>valor</code> es instancia de {@link String} y es una
     *              subcadena del nombre del estudiante.</li>
     *           <li><code>campo</code> es {@link CampoEstudiante#CUENTA} y
     *              <code>valor</code> es instancia de {@link Integer} y su
     *              valor entero es menor o igual a la cuenta del
     *              estudiante.</li>
     *           <li><code>campo</code> es {@link CampoEstudiante#PROMEDIO} y
     *              <code>valor</code> es instancia de {@link Double} y su
     *              valor doble es menor o igual al promedio del
     *              estudiante.</li>
     *           <li><code>campo</code> es {@link CampoEstudiante#EDAD} y
     *              <code>valor</code> es instancia de {@link Integer} y su
     *              valor entero es menor o igual a la edad del
     *              estudiante.</li>
     *         </ul>
     *         <code>false</code> en otro caso.
     * @throws IllegalArgumentException si el campo es <code>null</code>.
     */
    @Override public boolean casa(CampoEstudiante campo, Object valor) {
        // Aquí va su código.
	if(campo == null)
            throw new IllegalArgumentException();

        if(!(campo instanceof CampoEstudiante))
                throw new IllegalArgumentException();

        CampoEstudiante camp = (CampoEstudiante)campo;

        switch(camp){
            case NOMBRE:
            if(valor instanceof String){
                String s = (String)valor;
                if(s != "" && nombre.contains(s))
                    return true;
            }else{
                return false;
            }
            case CUENTA:
            if((valor instanceof Integer)){
                Integer i = (Integer) valor;
                if(i.intValue() <= this.cuenta)
                    return true;
            }
            else {
                return false;
            }
            case PROMEDIO:
            if(valor instanceof Double){
                Double d = (Double)valor;
                    if(d.doubleValue() <= this.promedio)
                        return true;
            } else{
                return false;
            }
            case EDAD:
            if(valor instanceof Integer){
             Integer i2 = (Integer)valor;
                if(i2.intValue() <= this.edad)
                return true;
            } else{
                return false;
            }

            default:
            return false;

        }
    }
    }

