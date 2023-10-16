package py.edu.ucom.controllers;

import java.util.List;

import jakarta.inject.Inject;
import py.edu.ucom.entities.Producto;
import py.edu.ucom.utils.DataSourceJSONProducto;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("productos")
public class ProductoResource {

    @Inject
    private DataSourceJSONProducto dataSourceJSON;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Producto> obtenerProductos() {
        return dataSourceJSON.obtenerProductos();
    }

    @GET
    @Path("{codigo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Producto obtenerProducto(@PathParam("codigo") String codigo) {
        return dataSourceJSON.buscarProducto(codigo);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crearProducto(Producto producto) {
        dataSourceJSON.crearProducto(producto);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("{codigo}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizarProducto(@PathParam("codigo") String codigo, Producto nuevoProducto) {
        if (dataSourceJSON.actualizarProducto(codigo, nuevoProducto)) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
    

    @DELETE
    @Path("{codigo}")
    public Response eliminarProducto(@PathParam("codigo") String codigo) {
        if (dataSourceJSON.eliminarProducto(codigo)) {
            return Response.status(Response.Status.NO_CONTENT).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
