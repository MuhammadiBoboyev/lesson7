package uz.pdp.lesson7.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.lesson7.entity.template.AbstractEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Comment extends AbstractEntity {
    @Column(nullable = false, columnDefinition = "text")
    private String text;

    @ManyToOne(optional = false)
    private Post post;
}
