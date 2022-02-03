package com.alkemy.desafioDisney.Enum;

public enum Errors {

    GENERO_NOT_FOUND("Genero no encontrado."),
    DUPILCATE_GENERO( "Genero ya existente."),
    PELICULA_NOT_FOUND("Pelicula no encontrada"),
    DUPLICATE_PELICULA("Pelicula ya existente"),
    PERSONAJE_NOT_FOUND("Personaje no encontrado"),
    DUPLICATE_PERSONAJE("Personaje ya existente"),
    USER_NOT_FOUND("Usuario o contraseña no encontrado");

    private final String messege;

    Errors(String messege){
        this.messege = messege;
    }

    public String getMessege() {
        return messege;
    }
}
