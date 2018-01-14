package com.kula.work.repository;

import com.kula.work.domain.User;
import com.kula.work.domain.stock.entity.StockModelEntity;
import com.kula.work.domain.stock.entity.StockModelEntity_;
import com.kula.work.domain.stock.entity.UserStockModelEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface UserStockModelEntityRepository extends JpaRepository<UserStockModelEntity,Long>{
       Optional<List<UserStockModelEntity>> findUserStockModelEntitiesByUser_Id(Long userId);

       Optional<UserStockModelEntity> findUserStockModelEntitiesByUserAndStockModelEntity(User user, StockModelEntity stockModelEntity);

    Optional<UserStockModelEntity> findUserStockModelEntitiesByUser_IdAndStockModelEntity_Code(Long userId , String code);
}
