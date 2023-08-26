package br.unitins.topicos1.resource;

import java.util.List;
import br.unitins.topicos1.model.Pedido;
import br.unitins.topicos1.repository.PedidoRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.NotFoundException;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/pedidos")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PedidoResource {

    @Inject
    PedidoRepository repository;

    // novo pedido
    @POST
    @Transactional
    public Pedido insert(Pedido pedido) {
        Pedido novoPedido = new Pedido();
        novoPedido.setTipoProduto(pedido.getTipoProduto());
        novoPedido.setSabor(pedido.getSabor());
        novoPedido.setTamanho(pedido.getTamanho());

        repository.persist(novoPedido);

        return novoPedido;
    }

    // apagando dados pelo id
    @DELETE
    @Path("/{id}")
    @Transactional
    public void Delete(@PathParam("id") Long id) {
        Pedido saborExistente = repository.findById(id);

        if (saborExistente != null) {
            repository.delete(saborExistente);
        } else {
            throw new NotFoundException("Sabor " + id + " não encontrado!");
        }
    }

    // atualizar um pedido
    @PUT
    @Path("/{id}")
    @Transactional
    public Pedido Update(@PathParam("id") Long id, Pedido pedido) {
        Pedido pedidoExistente = repository.findById(id);

        if (pedidoExistente != null) {
            pedidoExistente.setTipoProduto(pedido.getTipoProduto());
            pedidoExistente.setSabor(pedido.getSabor());
            pedidoExistente.setTamanho(pedido.getTamanho());
            return pedidoExistente;
        } else {
            throw new NotFoundException("Pedido " + id + " não encontrado! ");
        }
    }

    // lista
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<Pedido> findAll() {
        return repository.listAll();
    }

    @GET
    @Path("/{id}")
    public Pedido findById(@PathParam("id") Long id) {
        return repository.findById(id);
    }

    @GET
    @Path("/search/{tipoProduto}")
    public List<Pedido> findByTipoProduto(@PathParam("produto") String tipoProduto) {
        return repository.findByTipoProduto(tipoProduto);
    }

}