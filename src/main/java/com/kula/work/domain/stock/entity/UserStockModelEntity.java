package com.kula.work.domain.stock.entity;

import com.kula.work.domain.AbstractAuditingEntity;
import com.kula.work.domain.User;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.Min;

@Entity
@Table(name = "user_stock")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class UserStockModelEntity extends AbstractAuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_stock_sequence_generator")
    @SequenceGenerator(name = "user_stock_sequence_generator" , sequenceName = "user_stock_id_sequence")
    @Column(name = "user_stock_id")
    private Long id;

    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="user_id",referencedColumnName="id",
    nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(
        name = "user_stock_code",
        referencedColumnName = "stock_code",
        nullable = false
    )
    private StockModelEntity stockModelEntity;

    @Column(name = "stock_count" , nullable = false )
    @Min(0)
    private int count;

    public  UserStockModelEntity(){}

    public UserStockModelEntity(User user , StockModelEntity stockModelEntity){
        this.user = user;
        this.stockModelEntity = stockModelEntity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public StockModelEntity getStockModelEntity() {
        return stockModelEntity;
    }

    public void setStockModelEntity(StockModelEntity stockModelEntity) {
        this.stockModelEntity = stockModelEntity;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
