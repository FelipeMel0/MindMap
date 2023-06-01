package com.mycompany.mindmap.Classes;

import java.sql.*;

public class Anuncio {
    
    private Blob imagem;

    public Blob getImagem() {
        return imagem;
    }

    public void setImagem(Blob imagem) {
        this.imagem = imagem;
    }

    public Anuncio(Blob imagem) {
        this.imagem = imagem;
    }
      
}
