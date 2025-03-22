package Tienda;

import javax.swing.JOptionPane;
import java.util.Stack;

class Metodos {
    private Stack<Prenda> inventario = new Stack<>();

    public void registrarPrenda() {
        String marca = JOptionPane.showInputDialog("Ingrese la marca de la prenda:");
        String referencia = JOptionPane.showInputDialog("Ingrese la referencia de la prenda:");
        double precio = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el precio de la prenda:"));
        int cantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad de prendas:"));

        for (Prenda p : inventario) {
            if (p.getReferencia().equalsIgnoreCase(referencia)) {
                p.setCantidad(p.getCantidad() + cantidad);
                JOptionPane.showMessageDialog(null, "Prenda existente. Se sumó la cantidad: " + cantidad);
                return;
            }
        }
        inventario.push(new Prenda(marca, referencia, precio, cantidad));
        JOptionPane.showMessageDialog(null, "Prenda registrada exitosamente.");
    }

    public void consultarPrenda() {
        String referencia = JOptionPane.showInputDialog("Ingrese la referencia de la prenda a consultar:");
        for (Prenda p : inventario) {
            if (p.getReferencia().equalsIgnoreCase(referencia)) {
                JOptionPane.showMessageDialog(null, "Marca: " + p.getMarca() + "\nReferencia: " + p.getReferencia() + "\nPrecio: " + p.getPrecio() + "\nCantidad: " + p.getCantidad());
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "La prenda no existe.");
    }

    public void modificarPrenda() {
        String referencia = JOptionPane.showInputDialog("Ingrese la referencia de la prenda a modificar:");
        for (Prenda p : inventario) {
            if (p.getReferencia().equalsIgnoreCase(referencia)) {
                double nuevoPrecio = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el nuevo precio:"));
                int nuevaCantidad = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la nueva cantidad:"));
                p.setPrecio(nuevoPrecio);
                p.setCantidad(nuevaCantidad);
                JOptionPane.showMessageDialog(null, "Prenda modificada exitosamente.");
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "La prenda no existe.");
    }

    public void venderPrenda() {
        String referencia = JOptionPane.showInputDialog("Ingrese la referencia de la prenda a vender:");
        for (Prenda p : inventario) {
            if (p.getReferencia().equalsIgnoreCase(referencia)) {
                int cantidadVender = Integer.parseInt(JOptionPane.showInputDialog("Ingrese la cantidad a vender:"));
                if (cantidadVender > p.getCantidad()) {
                    JOptionPane.showMessageDialog(null, "Stock insuficiente. Cantidad disponible: " + p.getCantidad());
                } else {
                    p.setCantidad(p.getCantidad() - cantidadVender);
                    JOptionPane.showMessageDialog(null, "Venta realizada. Nuevo stock: " + p.getCantidad());
                }
                return;
            }
        }
        JOptionPane.showMessageDialog(null, "La prenda no existe.");
    }

    public void consultarStock() {
        if (inventario.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No hay prendas en el inventario.");
            return;
        }
        StringBuilder mensaje = new StringBuilder("Stock de prendas:\n");
        for (Prenda p : inventario) {
            mensaje.append("Marca: ").append(p.getMarca())
                   .append(" - Referencia: ").append(p.getReferencia())
                   .append(" - Precio: ").append(p.getPrecio())
                   .append(" - Cantidad: ").append(p.getCantidad())
                   .append("\n");
        }
        JOptionPane.showMessageDialog(null, mensaje.toString());
    }

    public void mostrarMenu() {
        while (true) {
            int opcion = Integer.parseInt(JOptionPane.showInputDialog(
                "1. Registrar prenda\n2. Consultar prenda\n3. Modificar prenda\n4. Vender prenda\n5. Consultar stock\n6. Salir"));
            switch (opcion) {
                case 1: registrarPrenda(); break;
                case 2: consultarPrenda(); break;
                case 3: modificarPrenda(); break;
                case 4: venderPrenda(); break;
                case 5: consultarStock(); break;
                case 6: return;
                default: JOptionPane.showMessageDialog(null, "Opción inválida.");
            }
        }
    }
}