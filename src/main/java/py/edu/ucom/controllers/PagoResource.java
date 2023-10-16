
package py.edu.ucom.controllers;

import java.util.List;
import jakarta.inject.Inject;
import py.edu.ucom.entities.Pago;
import py.edu.ucom.utils.DataSourceJSONProducto;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/tienda/caja/")
public class PagoResource {

    @Inject
    private DataSourceJSONProducto dataSourceJSON;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Pago> obtenerPagos() {
        return dataSourceJSON.obtenerPagos();
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Pago obtenerPago(@PathParam("id") String id) {
        return dataSourceJSON.buscarPago(id);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crearPago(Pago pago) {
        dataSourceJSON.crearPago(pago);
        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response actualizarPago(@PathParam("id") String id, Pago nuevoPago) {
        if (dataSourceJSON.actualizarPago(id, nuevoPago)) {
            return Response.status(Response.Status.OK).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("{id}")
    public Response eliminarPago(@PathParam("id") String id) {
        if (dataSourceJSON.eliminarPago(id)) {
            return Response.status(Response.Status.NO_CONTENT).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
    }
}
