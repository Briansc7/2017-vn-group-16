package providers;

import java.util.List;

import dtos.PathFile;
import model.Metodologia;

public interface MetodologiaProvider {
	List<Metodologia> getInformationMetodologia(PathFile datosDeCarga);
}
