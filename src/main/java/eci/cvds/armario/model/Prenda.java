package eci.cvds.armario.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "prendas")
public class Prenda {
    @Id
    @Column(name = "prenda_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID prendaId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "tipo")
    private TipoPrenda tipo;

    @Column(name = "categoria")
    private CategoriaPrenda categoria;

    @Column(name = "image_url_base_64", columnDefinition = "LONGTEXT")
    private String imageUrlBase64;
}
