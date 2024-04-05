
package com.proyecto.service;

import com.proyecto.domain.Consulta;
import java.util.List;


public interface ConsultaService {
    public List<Consulta> getConsultas(boolean activos);
    
    public Consulta  getConsulta(Consulta consulta);
    
    public Consulta getConsultaById(Long idConsulta);
    
    public void save(Consulta consulta);
    
    public void delete(Consulta consulta);
}
