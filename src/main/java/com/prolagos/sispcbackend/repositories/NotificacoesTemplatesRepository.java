package com.prolagos.sispcbackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.prolagos.sispcbackend.domain.Appweb_Notificacoes_Templates;


@Repository
public interface NotificacoesTemplatesRepository extends JpaRepository<Appweb_Notificacoes_Templates, Integer>{
}
