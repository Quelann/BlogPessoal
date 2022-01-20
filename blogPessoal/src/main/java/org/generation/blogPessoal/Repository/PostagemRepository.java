package org.generation.blogPessoal.Repository;

import java.util.List;

import org.generation.blogPessoal.Model.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostagemRepository extends JpaRepository<Postagem, Long>{
	//JpaRepository = <o tipo de entidade sendo adicionada e o tipo de ID>
	//Repository = Comunica com banco de dados
	
	public List<Postagem> findAllByTituloContainingIgnoreCase (String titulo);
	//importar java util 
	//findAllByTituloContainingIgnoreCase == bucar o nome da entidade 
	
}
