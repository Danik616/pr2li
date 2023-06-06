package com.pruebas.liti.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

@Table("usuarios")

public class UserEntityProof implements Persistable<String> {

    @Id
    private String usuarioId;

    private long localidadId;

    private long perfilId;

    private long clienteId;

    private String tpDocumentoId;

    private long funcionarioId;

    private String usuarioClave;

    private String usuarioEstado;

    private String usuarioIdentificacion;

    private String usuarioNombre1;

    private String usuarioNombre2;

    private String usuarioNombre3;

    private String usuarioNombre4;

    private String usuarioCorreo;

    private String usuarioTelefono;

    private String usuarioDireccion;

    private String usuarioFax;

    private long usuarioVisitas;

    private Date fechaIngreso;

    private Date fechaUltimaVisita;

    private String sesionId;

    private String sesionEstado;

    @Transient
    private boolean isNew = true;

    public UserEntityProof() {
    }

    public UserEntityProof(String usuarioCorreo, String usuarioClave) {
        this.usuarioCorreo = usuarioCorreo;
        this.usuarioClave = usuarioClave;
    }

    public UserEntityProof(String usuarioId, long localidadId, String tpDocumentoId, String usuarioIdentificacion,
            String usuarioClave,
            String usuarioEstado, String usuarioNombre1, String usuarioNombre2, String usuarioNombre3,
            String usuarioNombre4, String usuarioCorreo, long perfilId) {
                this.usuarioId = usuarioId;
                this.localidadId = localidadId;
                this.perfilId = perfilId;
                this.tpDocumentoId = tpDocumentoId;
                this.usuarioClave = usuarioClave;
                this.usuarioEstado = usuarioEstado;
                this.usuarioIdentificacion = usuarioIdentificacion;
                this.usuarioNombre1 = usuarioNombre1;
                this.usuarioNombre2 = usuarioNombre2;
                this.usuarioNombre3 = usuarioNombre3;
                this.usuarioNombre4 = usuarioNombre4;
                this.usuarioCorreo = usuarioCorreo;

    }

    


    public String getUsuarioCorreo() {
        return usuarioCorreo;
    }

    public void setUsuarioCorreo(String usuarioCorreo) {
        this.usuarioCorreo = usuarioCorreo;
    }

    public String getUsuarioClave() {
        return usuarioClave;
    }

    public void setUsuarioClave(String usuarioClave) {
        this.usuarioClave = usuarioClave;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public long getLocalidadId() {
        return localidadId;
    }

    public void setLocalidadId(long localidadId) {
        this.localidadId = localidadId;
    }

    public long getPerfilId() {
        return perfilId;
    }

    public void setPerfilId(long perfilId) {
        this.perfilId = perfilId;
    }

    public long getClienteId() {
        return clienteId;
    }

    public void setClienteId(long clienteId) {
        this.clienteId = clienteId;
    }

    public String getTpDocumentoId() {
        return tpDocumentoId;
    }

    public void setTpDocumentoId(String tpDocumentoId) {
        this.tpDocumentoId = tpDocumentoId;
    }

    public long getFuncionarioId() {
        return funcionarioId;
    }

    public void setFuncionarioId(long funcionarioId) {
        this.funcionarioId = funcionarioId;
    }

    public String getUsuarioEstado() {
        return usuarioEstado;
    }

    public void setUsuarioEstado(String usuarioEstado) {
        this.usuarioEstado = usuarioEstado;
    }

    public String getUsuarioIdentificacion() {
        return usuarioIdentificacion;
    }

    public void setUsuarioIdentificacion(String usuarioIdentificacion) {
        this.usuarioIdentificacion = usuarioIdentificacion;
    }

    public String getUsuarioNombre1() {
        return usuarioNombre1;
    }

    public void setUsuarioNombre1(String usuarioNombre1) {
        this.usuarioNombre1 = usuarioNombre1;
    }

    public String getUsuarioNombre2() {
        return usuarioNombre2;
    }

    public void setUsuarioNombre2(String usuarioNombre2) {
        this.usuarioNombre2 = usuarioNombre2;
    }

    public String getUsuarioNombre3() {
        return usuarioNombre3;
    }

    public void setUsuarioNombre3(String usuarioNombre3) {
        this.usuarioNombre3 = usuarioNombre3;
    }

    public String getUsuarioNombre4() {
        return usuarioNombre4;
    }

    public void setUsuarioNombre4(String usuarioNombre4) {
        this.usuarioNombre4 = usuarioNombre4;
    }

    public String getUsuarioTelefono() {
        return usuarioTelefono;
    }

    public void setUsuarioTelefono(String usuarioTelefono) {
        this.usuarioTelefono = usuarioTelefono;
    }

    public String getUsuarioDireccion() {
        return usuarioDireccion;
    }

    public void setUsuarioDireccion(String usuarioDireccion) {
        this.usuarioDireccion = usuarioDireccion;
    }

    public String getUsuarioFax() {
        return usuarioFax;
    }

    public void setUsuarioFax(String usuarioFax) {
        this.usuarioFax = usuarioFax;
    }

    public long getUsuarioVisitas() {
        return usuarioVisitas;
    }

    public void setUsuarioVisitas(long usuarioVisitas) {
        this.usuarioVisitas = usuarioVisitas;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaUltimaVisita() {
        return fechaUltimaVisita;
    }

    public void setFechaUltimaVisita(Date fechaUltimaVisita) {
        this.fechaUltimaVisita = fechaUltimaVisita;
    }

    public String getSesionId() {
        return sesionId;
    }

    public void setSesionId(String sesionId) {
        this.sesionId = sesionId;
    }

    public String getSesionEstado() {
        return sesionEstado;
    }

    public void setSesionEstado(String sesionEstado) {
        this.sesionEstado = sesionEstado;
    }

    @Override
    public String toString() {
        return "UserEntityProof [usuarioId=" + usuarioId + ", localidadId=" + localidadId + ", perfilId=" + perfilId
                + ", clienteId=" + clienteId + ", tpDocumentoId=" + tpDocumentoId + ", funcionarioId=" + funcionarioId
                + ", usuarioClave=" + usuarioClave + ", usuarioEstado=" + usuarioEstado + ", usuarioIdentificacion="
                + usuarioIdentificacion + ", usuarioNombre1=" + usuarioNombre1 + ", usuarioNombre2=" + usuarioNombre2
                + ", usuarioNombre3=" + usuarioNombre3 + ", usuarioNombre4=" + usuarioNombre4 + ", usuarioCorreo="
                + usuarioCorreo + ", usuarioTelefono=" + usuarioTelefono + ", usuarioDireccion=" + usuarioDireccion
                + ", usuarioFax=" + usuarioFax + ", usuarioVisitas=" + usuarioVisitas + ", fechaIngreso=" + fechaIngreso
                + ", fechaUltimaVisita=" + fechaUltimaVisita + ", sesionId=" + sesionId + ", sesionEstado="
                + sesionEstado + "]";
    }

    @Override
    public String getId() {
        return this.usuarioId;
    }

    @Override
    public boolean isNew() {
        return this.isNew;
    }

    public void setAsSaved() {
        this.isNew = false;
    }
}
