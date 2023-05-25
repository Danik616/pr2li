package com.pruebas.liti.dto;

public class UserDto {
    private String email;
    private String password;
    private long localidadId;
    private String tpDocumentoId;
    //Al guardar usuario ponerlo en estado s
    private String usuarioIdentificacion;
    private String usuarioEstado;
    private String usuarioId;
    private String nombres;
    private String apellidos;
    private long role;
    private long perfilId;

    

    

    

    public UserDto(String email, String password, long localidadId, String tpDocumentoId, String usuarioIdentificacion,
            String usuarioEstado, String usuarioId, String nombres, String apellidos, long role, long perfilId) {
        this.email = email;
        this.password = password;
        this.localidadId = localidadId;
        this.tpDocumentoId = tpDocumentoId;
        this.usuarioIdentificacion = usuarioIdentificacion;
        this.usuarioEstado = usuarioEstado;
        this.usuarioId = usuarioId;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.role = role;
        this.perfilId = perfilId;
    }

    public UserDto() {
        
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getRole() {
        return role;
    }

    public void setRole(long role) {
        this.role = role;
    }

    public long getLocalidadId() {
        return localidadId;
    }

    public void setLocalidadId(long localidadId) {
        this.localidadId = localidadId;
    }

    public String getTpDocumentoId() {
        return tpDocumentoId;
    }

    public void setTpDocumentoId(String tpDocumentoId) {
        this.tpDocumentoId = tpDocumentoId;
    }

    public String getUsuarioEstado() {
        return usuarioEstado;
    }

    public void setUsuarioEstado(String usuarioEstado) {
        this.usuarioEstado = usuarioEstado;
    }

    public String getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(String usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getUsuarioIdentificacion() {
        return usuarioIdentificacion;
    }

    public void setUsuarioIdentificacion(String usuarioIdentificacion) {
        this.usuarioIdentificacion = usuarioIdentificacion;
    }

    public long getPerfilId() {
        return perfilId;
    }

    public void setPerfilId(long perfilId) {
        this.perfilId = perfilId;
    }

    
}
