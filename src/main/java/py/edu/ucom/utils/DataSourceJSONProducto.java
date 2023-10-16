package py.edu.ucom.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.enterprise.context.ApplicationScoped;
import py.edu.ucom.entities.Cliente;
import py.edu.ucom.entities.Producto;
import py.edu.ucom.entities.Pago;

@ApplicationScoped
public class DataSourceJSONProducto{
    public String SRC_PRODUCTO = "/home/alfonhec/Escritorio/Taller II/Examen parcial/Proyecto_Cajas/src/main/java/py/edu/ucom/utils/producto.json";
    public String SRC_CLIENTE = "/home/alfonhec/Escritorio/Taller II/Examen parcial/Proyecto_Cajas/src/main/java/py/edu/ucom/utils/cliente.json";
    public String SRC_PAGO = "/home/alfonhec/Escritorio/Taller II/Examen parcial/Proyecto_Cajas/src/main/java/py/edu/ucom/utils/pago.json";

     // Crear un nuevo producto
    public void crearProducto(Producto producto) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            List<Producto> lista = obtenerProductos();
            lista.add(producto);
            mapper.writeValue(new File(this.SRC_PRODUCTO), lista);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Leer todos los productos
    public List<Producto> obtenerProductos() {
        ObjectMapper mapper = new ObjectMapper();
        List<Producto> productos = new ArrayList<>();
        try {
            productos = mapper.readValue(
                new File(this.SRC_PRODUCTO),
                new TypeReference<List<Producto>>() {
                }
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

        return productos;
    }

    public Producto buscarProducto(String codigo) {
        List<Producto> productos = obtenerProductos();

        for (Producto producto : productos) {
            if (producto.getCodigo().equals(codigo)) {
                return producto;
            }
        }

        return null; // O lanza una excepción si lo consideras necesario
    }

    // Actualizar un producto existente
    public boolean actualizarProducto(String codigo, Producto nuevoProducto) {
        List<Producto> data = obtenerProductos();
        boolean actualizado = false;

        for (Producto item : data) {
            if (item.getCodigo().equals(codigo)) {
                // Actualiza los campos necesarios
                if (nuevoProducto.getNombre() != null) {
                    item.setNombre(nuevoProducto.getNombre());
                }
                if (nuevoProducto.getPrecio() != null) {
                    item.setPrecio(nuevoProducto.getPrecio());
                }
                actualizado = true; // Indica que la actualización se realizó

                break;
            }
        }

        if (actualizado) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                mapper.writeValue(new File(this.SRC_PRODUCTO), data); // Guarda los cambios en el archivo JSON
            } catch (Exception e) {
                e.printStackTrace();
                actualizado = false; // Hubo un error al guardar los cambios
            }
        }

        return actualizado;
    }

    // Eliminar un producto por su código
    public boolean eliminarProducto(String codigo) {
        List<Producto> data = obtenerProductos();
        boolean eliminado = false;

        for (Producto producto : data) {
            if (producto.getCodigo().equals(codigo)) {
                data.remove(producto);
                eliminado = true;
                break;
            }
        }

        if (eliminado) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                mapper.writeValue(new File(this.SRC_PRODUCTO), data);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return eliminado;
    }

    public void crearCliente(Cliente cliente) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            List<Cliente> lista = obtenerClientes();
            lista.add(cliente);
            mapper.writeValue(new File(this.SRC_CLIENTE), lista);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Cliente> obtenerClientes() {
        ObjectMapper mapper = new ObjectMapper();
        List<Cliente> clientes = new ArrayList<>();
        try {
            clientes = mapper.readValue(
                new File(this.SRC_CLIENTE),
                new TypeReference<List<Cliente>>() {
                }
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clientes;
    }

    public Cliente buscarCliente(String documento) {
        List<Cliente> data = obtenerClientes();

        for (Cliente cliente : data) {
            if (cliente.getDocumento().equals(documento)) {
                return cliente;
            }
        }

        return null; // Retorna null si el cliente no se encuentra
    }

    public boolean actualizarCliente(String documento, Cliente nuevoCliente) {
        List<Cliente> data = obtenerClientes();
        boolean actualizado = false;

        for (Cliente item : data) {
            if (item.getDocumento().equals(documento)) {
                if (nuevoCliente.getNombre() != null) {
                    item.setNombre(nuevoCliente.getNombre());
                    actualizado = true;
                }
                break;
            }
        }

        if (actualizado) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                mapper.writeValue(new File(this.SRC_CLIENTE), data); // Guarda los cambios en el archivo JSON
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return actualizado;
    }

    public boolean eliminarCliente(String documento) {
        List<Cliente> data = obtenerClientes();
        boolean eliminado = false;

        data.removeIf(cliente -> cliente.getDocumento().equals(documento));

        if (eliminado) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                mapper.writeValue(new File(this.SRC_CLIENTE), data); // Guarda los cambios en el archivo JSON
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return eliminado;
    }

    // Agrega los métodos para manejar pagos (registrar, obtener, buscar, actualizar y eliminar pagos)
    public void crearPago(Pago pago) {
        try {
            ObjectMapper mapper = new ObjectMapper();
    
            List<Pago> lista = obtenerPagos();
            lista.add(pago);
            mapper.writeValue(new File(this.SRC_PAGO), lista);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void registrarPago(Pago pago) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            List<Pago> lista = obtenerPagos();
            lista.add(pago);
            mapper.writeValue(new File(this.SRC_PAGO), lista);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Pago> obtenerPagos() {
        ObjectMapper mapper = new ObjectMapper();
        List<Pago> pagos = new ArrayList<>();
        try {
            pagos = mapper.readValue(
                new File(this.SRC_PAGO),
                new TypeReference<List<Pago>>() {
                }
            );
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pagos;
    }

    public Pago buscarPago(String id) {
        List<Pago> pagos = obtenerPagos();

        for (Pago pago : pagos) {
            if (pago.getId().equals(id)) {
                return pago;
            }
        }

        return null;
    }

    public boolean actualizarPago(String id, Pago nuevoPago) {
        List<Pago> data = obtenerPagos();
        boolean actualizado = false;

        for (Pago item : data) {
            if (item.getId().equals(id)) {
                // Actualiza los campos necesarios en la entidad Pago
                if (nuevoPago.getDetalle() != null) {
                    item.setDetalle(nuevoPago.getDetalle());
                }
                // Agrega más actualizaciones según los campos de tu entidad Pago
                actualizado = true;

                break;
            }
        }

        if (actualizado) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                mapper.writeValue(new File(this.SRC_PAGO), data); // Guarda los cambios en el archivo JSON
            } catch (Exception e) {
                e.printStackTrace();
                actualizado = false;
            }
        }

        return actualizado;
    }

    public boolean eliminarPago(String id) {
        List<Pago> data = obtenerPagos();
        boolean eliminado = false;

        data.removeIf(pago -> pago.getId().equals(id));

        if (eliminado) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                mapper.writeValue(new File(this.SRC_PAGO), data);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return eliminado;
    }
}

