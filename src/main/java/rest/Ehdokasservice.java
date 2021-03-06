package rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import dao.EhdokasDao;
import persist.Ehdokkaat;

@Path("/ehdokasservice")
public class Ehdokasservice {
    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_JSON)
    public void addEhdokas(Ehdokkaat ehdokas) {
        EhdokasDao.addEhdokas(ehdokas);
    }
   
	@POST
	@Path("/add")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Ehdokkaat postEhdokas(Ehdokkaat ehdokas) {
		EhdokasDao.addEhdokas(ehdokas);
		return ehdokas;
	}
    

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Ehdokkaat> getEhdokkaat(){
        return EhdokasDao.getEhdokkaat();
    }
    

    @POST
    @Path("/delete/{id}")
    public boolean deleteEhdokas(@PathParam("id") int id) {
        return EhdokasDao.deleteEhdokas(id);
    }

    
}