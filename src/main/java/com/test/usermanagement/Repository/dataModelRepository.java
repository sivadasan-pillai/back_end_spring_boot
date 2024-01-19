package com.test.usermanagement.Repository;

import com.test.usermanagement.entity.dataModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

    public interface dataModelRepository extends JpaRepository<dataModel, Long> {
        List<dataModel> findByTableNameAndColumnNameAndUniqueColumn(String tableName, String columnName, String uniqueColumn);
    }


