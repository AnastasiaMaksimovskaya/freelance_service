package com.free.freelance_service.specifications;

import com.free.freelance_service.entity.Order;
import com.free.freelance_service.request.SearchRequest;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class OrderSpecifications {

    public static Specification<Order> search(SearchRequest searchRequest) {
        return (root, cq, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (CollectionUtils.isEmpty(predicates)) {
                cb.isNotNull(root.get("id"));
            }
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
    }
}
