package py.edu.ucom.controllers;

import java.util.List;
import jakarta.inject.Inject;
import py.edu.ucom.entities.Cliente;
import py.edu.ucom.utils.DataSourceJSONProducto;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/tienda/cajas/clientes")
public class ClienteResource {

    @Inject
    private DataSourceJSONProducto dataSourceJSON;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Cliente> obtenerClientes() {
        return dataSourceJSON.obtenerClientes();
    }

    @GET
    @Path("{clienteDocumento}")
    @Produces(MediaType.APPLICATION_JSON)
    public Cliente obtenerCliente(@PathParam("clienteDocumento") String clienteDocumento) {
        return dataSourceJSON.buscarCliente(clienteDocumento);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crearCliente(Cliente cliente) {
        dataSourceJSON.crearCliente(cliente);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("{clienteDocumento}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizarCliente(@PathParam("clienteDocumento") String clienteDocumento, Cliente nuevoCliente) {
        if (dataSourceJSON.actualizarCliente(clienteDocumento, nuevoCliente)) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("{clienteDocumento}")
    public Response eliminarCliente(@PathParam("clienteDocumento") String clienteDocumento) {
        if (dataSourceJSON.eliminarCliente(clienteDocumento)) {
            return Response.status(Response.Status.NO_CONTENT).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
