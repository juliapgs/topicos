package br.unitins.topicos1.repository;

import java.util.List;
import br.unitins.topicos1.model.Pedido;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PedidoRepository implements PanacheRepository<Pedido> {
    public List<Pedido> findByTipoProduto(String tipoProduto) {
        return find("produto LIKE ?1 ", "%" + tipoProduto + "%").list();
    }
}
