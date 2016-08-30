package models;

import com.avaje.ebean.Model;
import com.sun.java.swing.plaf.motif.resources.motif_de;

import javax.persistence.*;
import java.util.List;

/**
 * Created by ls.hernandez10 on 30/08/2016.
 */
@Entity
@Table(name = "usuarioentity")
public class UsuarioEntity extends Model
{
    public static Finder<Long,UsuarioEntity> FINDER = new Finder<>(UsuarioEntity.class);

    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE,generator = "Usuario")
    private Long id;
    private String nombre;
    private List<CampoEntity> campos;
    private TiposUsuario tipo;

    public enum TiposUsuario
    {
        JEFE_DE_CAMPO, JEFE_DE_PRODUCCION;
    }

    public UsuarioEntity()
    {
        nombre = null;
        campos = null;
        tipo = null;
    }

    public UsuarioEntity(Long id)
    {
        this();
        this.id = id;
    }

    public UsuarioEntity(String pNombre, List<CampoEntity> pCampos, TiposUsuario pTipo)
    {
        nombre = pNombre;
        if(pTipo == TiposUsuario.JEFE_DE_CAMPO)
        {
            if(pCampos.size() == 1)
                campos = pCampos;
            tipo = pTipo;
        }
        else if(pTipo == TiposUsuario.JEFE_DE_PRODUCCION)
        {
            campos = pCampos;
            tipo = pTipo;
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<CampoEntity> getCampos() {
        return campos;
    }

    public void setCampos(List<CampoEntity> campos) {
        this.campos = campos;
    }

    public TiposUsuario getTipo() {
        return tipo;
    }

    public void setTipo(TiposUsuario tipo) {
        this.tipo = tipo;
    }
}
