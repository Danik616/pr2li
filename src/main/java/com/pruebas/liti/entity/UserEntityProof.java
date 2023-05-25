package com.pruebas.liti.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("usuario")

public class UserEntityProof {

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

    private String usuarionombre1;

    private String usuarionombre2;

    private String usuarionombre3;

    private String usuarionombre4;

    private String usuarioCorreo;

    private String usuarioTelefono;

    private String usuarioDireccion;

    private String usuarioFax;

    private long usuarioVisitas;

    private Date fechaIngreso;

    private Date fechaUltimaVisita;

    private String sesionId;

    private String sesionEstado;

    public UserEntityProof() {
    }

    public UserEntityProof(String usuarioCorreo, String usuarioClave) {
        this.usuarioCorreo = usuarioCorreo;
        this.usuarioClave = usuarioClave;
    }

    public UserEntityProof(String usuarioId, long localidadId, String tpDocumentoId, String usuarioIdentificacion, String usuarioClave,
            String usuarioEstado, String usuarionombre1, String usuarionombre2, String usuarionombre3,
            String usuarionombre4, String usuarioCorreo, long perfilId) {
        this.usuarioId = usuarioId;
        this.localidadId = localidadId;
        this.tpDocumentoId = tpDocumentoId;
        this.usuarioIdentificacion=usuarioIdentificacion;
        this.usuarioClave = usuarioClave;
        this.usuarioEstado = usuarioEstado;
        this.usuarionombre1 = usuarionombre1;
        this.usuarionombre2 = usuarionombre2;
        this.usuarionombre3 = usuarionombre3;
        this.usuarionombre4 = usuarionombre4;
        this.usuarioCorreo = usuarioCorreo;
        this.perfilId=perfilId;
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

    public String getUsuarionombre1() {
        return usuarionombre1;
    }

    public void setUsuarionombre1(String usuarionombre1) {
        this.usuarionombre1 = usuarionombre1;
    }

    public String getUsuarionombre2() {
        return usuarionombre2;
    }

    public void setUsuarionombre2(String usuarionombre2) {
        this.usuarionombre2 = usuarionombre2;
    }

    public String getUsuarionombre3() {
        return usuarionombre3;
    }

    public void setUsuarionombre3(String usuarionombre3) {
        this.usuarionombre3 = usuarionombre3;
    }

    public String getUsuarionombre4() {
        return usuarionombre4;
    }

    public void setUsuarionombre4(String usuarionombre4) {
        this.usuarionombre4 = usuarionombre4;
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
                + usuarioIdentificacion + ", usuarionombre1=" + usuarionombre1 + ", usuarionombre2=" + usuarionombre2
                + ", usuarionombre3=" + usuarionombre3 + ", usuarionombre4=" + usuarionombre4 + ", usuarioCorreo="
                + usuarioCorreo + ", usuarioTelefono=" + usuarioTelefono + ", usuarioDireccion=" + usuarioDireccion
                + ", usuarioFax=" + usuarioFax + ", usuarioVisitas=" + usuarioVisitas + ", fechaIngreso=" + fechaIngreso
                + ", fechaUltimaVisita=" + fechaUltimaVisita + ", sesionId=" + sesionId + ", sesionEstado="
                + sesionEstado + "]";
    }

}
