package com.memorizei.model.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "t_card")
public class Card extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String pergunta;

    private String resposta;

    @ManyToOne
    @JoinColumn(name = "fk_baralho")
    private Baralho baralho;

}
