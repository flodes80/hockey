package converters;

import javax.el.ELContext;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import managedbeans.ApplicationBean;
import modele.gardien.Gardien;

@FacesConverter(value = "GardienConverter")
public class GardienConverter implements Converter<Gardien> {

	@Override
	public Gardien getAsObject(FacesContext arg0, UIComponent arg1, String arg2) {
		// Récupération du applicationbean
	    ELContext elContext = arg0.getELContext();
	    ApplicationBean applicationBean = (ApplicationBean) elContext.getELResolver().getValue(elContext, null, "applicationBean");
	    
	    return applicationBean.getCollGardiens().stream()
											    .filter(g -> g.toString().equalsIgnoreCase(arg2))
											    .findFirst()
											    .orElse(null);
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Gardien arg2) {
		return arg2 != null ? arg2.toString() : null;
	}

}
