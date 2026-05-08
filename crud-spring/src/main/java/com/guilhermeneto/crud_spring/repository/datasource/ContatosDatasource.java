package com.guilhermeneto.crud_spring.repository.datasource;

import java.util.List;

import com.guilhermeneto.crud_spring.models.Contatos;
import com.guilhermeneto.crud_spring.models.Parceiros;

public class ContatosDatasource {

    public static void getContatos(List<Parceiros> listaParceiros) {
        if (listaParceiros.isEmpty()) return;

        // --- PARCEIRO 1 ---
        Parceiros p1 = listaParceiros.get(0);
        
            Contatos c1 = new Contatos();
                c1.setNomecontato("Vendedor Principal");
                c1.setTelefone("3433363232");
                c1.setEmail("vendedor1@loja1.com.br");
                c1.setParceiros(p1);

                p1.getContatos().add(c1);

            Contatos c2 = new Contatos();
                c2.setNomecontato("Suporte Técnico");
                c2.setTelefone("3499999999");
                c2.setEmail("suporte@loja1.com.br");
                c2.setParceiros(p1);

                p1.getContatos().add(c2);

        // --- PARCEIRO 2 ---
        if (listaParceiros.size() > 1) {
        Parceiros p2 = listaParceiros.get(1);
            Contatos c3 = new Contatos();
                c3.setNomecontato("Suporte do Parceiro2");
                c3.setTelefone("3488888888");
                c3.setEmail("suporte@loja2.com");
                c3.setParceiros(p2);

                p2.getContatos().add(c3);
                
        }
    }
}