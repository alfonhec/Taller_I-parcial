/*package py.edu.ucom.servicios;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import py.edu.ucom.entities.Monedas;
import py.edu.ucom.utils.DataSourceJSON;

@ApplicationScoped
public class CambiosService {

    @Inject
    public DataSourceJSON ds;

    public Double realizarCambio(String codigoOrigen, String codigoDestino, Integer monto){
            Double cambio= 0.0;
            Monedas m = this.ds.buscarMonedas(codigoOrigen);

           if( m.getCambios().containsKey(codigoDestino)){
                Double valorCambio = m.getCambios().get(codigoDestino);
                cambio = monto * valorCambio;
           }

            return cambio;

    }
    
}
*/