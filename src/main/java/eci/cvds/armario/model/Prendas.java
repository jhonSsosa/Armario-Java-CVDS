package eci.cvds.armario.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PRENDAS")
public class User {
    @Id
    @Column(name = "PRENDA_ID")
    @GeneratedValue(generator="system-uuid")
    @GenericGenerator(name="system-uuid", strategy = "uuid")
    private String prenda_id;

    @NotNull
    @Column(name = "UserID")
    private String userid;

    @NotNull
    @Pattern(regexp = "^(Pantalon|Camisa|Zapatos|Acesorios|Media|Chaqueta)$")
    @Column(name = "Tipo")
    private String tipo;

    @NotNull
    @Pattern(regexp = "^(Formal|Semifomral|Casual|Deportivo)$")
    @Column(name = "Categoria")
    private Categorias categoria;
}

public enum TipoPrenda {
    PANTALON,
    CAMISA,
    ZAPATOS,
    ACCESORIO
}