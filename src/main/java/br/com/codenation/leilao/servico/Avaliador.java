package br.com.codenation.leilao.servico;

import br.com.codenation.leilao.exceptions.UsuarioInvalidoException;
import br.com.codenation.leilao.exceptions.ValorLanceInvalidoException;
import br.com.codenation.leilao.models.Lance;
import br.com.codenation.leilao.models.Leilao;

public class Avaliador {
    private double maiorDeTodos = Double.NEGATIVE_INFINITY;
    private double menorDeTodos = Double.POSITIVE_INFINITY;
    private double media = 0;

    public void avalia(Leilao leilao) {

        double total = 0;
        for(Lance lance : leilao.getLances()) {
            if(lance.getUsuario()==null) throw new UsuarioInvalidoException();
            if(lance.getValor() <= leilao.getValorInicial()) throw new ValorLanceInvalidoException();
            if(lance.getValor() > maiorDeTodos) maiorDeTodos = lance.getValor();
            if(lance.getValor() < menorDeTodos) menorDeTodos = lance.getValor();
            total += lance.getValor();
        }
        if(total == 0) {
            media = 0;
            return;
        }
        media = total / leilao.getLances().size();
    }

    public double getMaiorLance() { return maiorDeTodos; }
    public double getMenorLance() { return menorDeTodos; }
    public double getMedia() { return media; }
}
