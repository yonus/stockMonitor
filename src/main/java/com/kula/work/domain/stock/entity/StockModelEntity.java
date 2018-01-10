package com.kula.work.domain.stock.entity;


import com.kula.work.config.Constants;
import com.kula.work.domain.AbstractAuditingEntity;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * This class provides Entity Model for Stock Model in database
 *
 */

@Entity
@Table(name = "stock")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class StockModelEntity extends AbstractAuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stock_sequence_generator")
    @SequenceGenerator(name = "stock_sequence_generator" , sequenceName = "stock_id_sequence")
    @Column(name = "stock_id")
    private Long id;

    @NotNull
    @Size(min = 3, max =5)
    @Column(length = 5, unique = true, nullable = false ,name ="stock_code")
    private String code;

    @NotNull
    @Size(min = 1, max =100)
    @Column(length = 100, name = "stock_name")
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
