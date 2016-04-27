package br.edu.ufrpe.uag.projetao.model;
// Generated 26/04/2016 23:17:23 by Hibernate Tools 4.3.1

import br.edu.ufrpe.uag.projetao.interfaces.InterfaceEntity;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * AlocacaoImagemClasse generated by hbm2java
 */
@Entity
@Table(name = "alocacao_imagem_classe", schema = "public"
)
public class AlocacaoImagemClasse implements java.io.Serializable, InterfaceEntity {

    private int id;
    private BaseImagemClasse baseImagemClasse;
    private ImagemClasse imagemClasse;
    private Usuario usuario;
    private Set<EscolhaImagemClasse> escolhaImagemClasses = new HashSet<>(0);
    private Set<ClasssificacaoImagemClasse> classsificacaoImagemClasses = new HashSet<>(0);

    public AlocacaoImagemClasse() {
    }

    public AlocacaoImagemClasse(int id, BaseImagemClasse baseImagemClasse, ImagemClasse imagemClasse, Usuario usuario) {
        this.id = id;
        this.baseImagemClasse = baseImagemClasse;
        this.imagemClasse = imagemClasse;
        this.usuario = usuario;
    }

    public AlocacaoImagemClasse(int id, BaseImagemClasse baseImagemClasse, ImagemClasse imagemClasse, Usuario usuario, Set<EscolhaImagemClasse> escolhaImagemClasses, Set<ClasssificacaoImagemClasse> classsificacaoImagemClasses) {
        this.id = id;
        this.baseImagemClasse = baseImagemClasse;
        this.imagemClasse = imagemClasse;
        this.usuario = usuario;
        this.escolhaImagemClasses = escolhaImagemClasses;
        this.classsificacaoImagemClasses = classsificacaoImagemClasses;
    }

    @Id

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "base_imagem_classe", nullable = false)
    public BaseImagemClasse getBaseImagemClasse() {
        return this.baseImagemClasse;
    }

    public void setBaseImagemClasse(BaseImagemClasse baseImagemClasse) {
        this.baseImagemClasse = baseImagemClasse;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "imagem_classe", nullable = false)
    public ImagemClasse getImagemClasse() {
        return this.imagemClasse;
    }

    public void setImagemClasse(ImagemClasse imagemClasse) {
        this.imagemClasse = imagemClasse;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "supervisor", nullable = false)
    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "alocacaoImagemClasse")
    public Set<EscolhaImagemClasse> getEscolhaImagemClasses() {
        return this.escolhaImagemClasses;
    }

    public void setEscolhaImagemClasses(Set<EscolhaImagemClasse> escolhaImagemClasses) {
        this.escolhaImagemClasses = escolhaImagemClasses;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "alocacaoImagemClasse")
    public Set<ClasssificacaoImagemClasse> getClasssificacaoImagemClasses() {
        return this.classsificacaoImagemClasses;
    }

    public void setClasssificacaoImagemClasses(Set<ClasssificacaoImagemClasse> classsificacaoImagemClasses) {
        this.classsificacaoImagemClasses = classsificacaoImagemClasses;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.baseImagemClasse);
        hash = 59 * hash + Objects.hashCode(this.imagemClasse);
        hash = 59 * hash + Objects.hashCode(this.usuario);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AlocacaoImagemClasse other = (AlocacaoImagemClasse) obj;
        if (!Objects.equals(this.baseImagemClasse, other.baseImagemClasse)) {
            return false;
        }
        if (!Objects.equals(this.imagemClasse, other.imagemClasse)) {
            return false;
        }
        return Objects.equals(this.usuario, other.usuario);
    }

    @Override
    public String toString() {
        return "AlocacaoImagemClasse{" + "baseImagemClasse=" + baseImagemClasse + ", imagemClasse=" + imagemClasse + ", usuario=" + usuario + '}';
    }

}
