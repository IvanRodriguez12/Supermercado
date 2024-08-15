import java.util.ArrayList;
import java.util.List;

class Humane {
    private String nombre;
    private String apellido;
    private int dni;

    public Humane(String nombre, String apellido, int dni) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
    }

    public String toString() {
        return "Nombre: " + this.nombre + "\nApellido: " + this.apellido + "\nDNI: " + this.dni;
    }
}

class Empleado extends Humane {
    private double sueldo;

    public Empleado(String nombre, String apellido, int dni, double sueldo) {
        super(nombre, apellido, dni);
        this.sueldo = sueldo;
    }

    public String toString() {
        return super.toString() + "\nSueldo: " + this.sueldo;
    }
}

class Caja {
    private Empleado empleado;
    private int nroCaja;

    public Caja(Empleado empleado, int nroCaja) {
        this.empleado = empleado;
        this.nroCaja = nroCaja;
    }

    public String toString() {
        return "Caja " + nroCaja;
    }
}

class Cliente extends Humane {
    private boolean mayorista;

    public Cliente(String nombre, String apellido, int dni, boolean mayorista) {
        super(nombre, apellido, dni);
        this.mayorista = mayorista;
    }

    public boolean esMayorista() {
        return mayorista;
    }

    public String toString() {
        return super.toString() + "\nMayorista: " + (this.mayorista ? "Sí" : "No");
    }
}

class Producto {
    private String nombre;
    private double precio;
    private int cantidad;

    public Producto(String nombre, double precio, int cantidad) {
        this.nombre = nombre;
        this.precio = precio;
        this.cantidad = cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public double getPrecio() {
        return precio;
    }
}

class Transaccion {
    private Cliente cliente;
    private List<Producto> productos;
    private double pagoTotal;
    private Caja caja;

    public Transaccion(Cliente cliente, List<Producto> productos, Caja caja) {
        this.cliente = cliente;
        this.productos = productos;
        this.pagoTotal = calcularTotal();
        this.caja = caja;
    }

    private double calcularTotal() {
        double total = 0;
        for (Producto producto : productos) {
            total += producto.getCantidad() * producto.getPrecio();
        }

        if (cliente.esMayorista()) {
            total *= 0.90;
        }

        return total;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Cliente:\n").append(cliente.toString()).append("\n");
        sb.append("Productos Comprados:\n");
        for (Producto producto : productos) {
            sb.append("- ").append(producto.getNombre())
                    .append(" x").append(producto.getCantidad())
                    .append(" - $").append(producto.getCantidad() * producto.getPrecio()).append("\n");
        }
        sb.append("Total Pagado: $").append(pagoTotal).append("\n");
        sb.append("Atendido en la ").append(caja.toString()).append("\n");
        return sb.toString();
    }
}

public class Supermercado {
    public static void main(String[] args) {
        Producto Chocolatada = new Producto("Chocolatada", 1000, 4);
        Producto Huevos = new Producto("Huevos", 600, 12);
        Producto Tomates = new Producto("Tomates", 400, 2);
        Producto Lechuga = new Producto("Lechuga", 300, 2);

        Empleado empleado1 = new Empleado("Esteban", "Narukami", 1234567, 200);
        Caja caja1 = new Caja(empleado1, 5);

        Cliente cliente1 = new Cliente("Mateo", "Soto", 3278912, false);

        List<Producto> productosComprados = new ArrayList<>();
        productosComprados.add(Chocolatada);
        productosComprados.add(Huevos);
        productosComprados.add(Tomates);
        productosComprados.add(Lechuga);


        Transaccion transaccion1 = new Transaccion(cliente1, productosComprados, caja1);
        System.out.println(transaccion1);
    }
}


