package model;

public class Usuario {
    
    String username;
    String password;
    String email;
    Perfil perfil;
    Persona persona;
    
    public Usuario() {}
    
    public Usuario(String username, String password, String email, Perfil perfil) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.perfil = perfil;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Perfil getPerfil() {
        return perfil;
    }

    public void setPerfil(Perfil perfil) {
        this.perfil = perfil;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
}
